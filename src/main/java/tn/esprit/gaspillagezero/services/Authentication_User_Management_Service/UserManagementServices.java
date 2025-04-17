package tn.esprit.gaspillagezero.services.Authentication_User_Management_Service;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import tn.esprit.gaspillagezero.dto.ReqRes;
import tn.esprit.gaspillagezero.entites.Authentication_User_Management.User;
import tn.esprit.gaspillagezero.repository.Authentication_User_Management_Repository.UserRepo;
import tn.esprit.gaspillagezero.services.Authentication_User_Management_Service.Mailing.EmailService;

import java.util.*;


@Service
public class UserManagementServices {

    @Autowired
    private UserRepo userRepo;
    @Autowired
    private JWTUtils jwtUtils;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private EmailService emailService;



    public ReqRes register(ReqRes registrationRequest){
        ReqRes resp = new ReqRes();

        try {
            User existingUser = userRepo.findByEmail(registrationRequest.getEmail()).orElse(null);
            if (existingUser != null) {
                resp.setStatusCode(400);
                resp.setMessage("User with this email already exists.");
                return resp;
            }
            User newUser = new User();
            newUser.setEmail(registrationRequest.getEmail());
            newUser.setCity(registrationRequest.getCity());
            newUser.setRole(registrationRequest.getRole());
            newUser.setName(registrationRequest.getName());
            newUser.setPassword(passwordEncoder.encode(registrationRequest.getPassword()));

            // Generate verification token
            String token = UUID.randomUUID().toString();
            newUser.setVerificationToken(token);
            newUser.setEnabled(false); // make sure they can't login yet

            User savedUser = userRepo.save(newUser);

            // Send verification email
            String verificationLink = "http://127.0.0.1:8089/auth/verify?token=" + token;
            emailService.sendEmail(
                    savedUser.getEmail(),
                    savedUser.getName(),
                    //EmailTemp.ACTIVATE_ACCOUNT,
                    verificationLink,
                    //token,
                    "Verify your email"
            );

            resp.setUser(savedUser);
            resp.setMessage("User saved. Verification email sent.");
            resp.setStatusCode(200);

        } catch (Exception e) {
            resp.setStatusCode(500);
            resp.setError(e.getMessage());
        }

        return resp;
    }



    public ReqRes login(ReqRes loginRequest){
        ReqRes response = new ReqRes();

        try {
            var user = userRepo.findByEmail(loginRequest.getEmail()).orElseThrow();
            if (!user.isEnabled()) {
                response.setStatusCode(403);
                response.setMessage("Account not verified. Please check your email.");
                return response;
            }
            authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getEmail(),
                            loginRequest.getPassword()));

            var jwt = jwtUtils.generateToken(user);
            var refreshToken = jwtUtils.generateRefreshToken(new HashMap<>(), user);
            response.setStatusCode(200);
            response.setToken(jwt);
            response.setRole(user.getRole());
            response.setRefreshToken(refreshToken);
            response.setExpirationTime("24Hrs");
            response.setMessage("Successfully Logged In");
            response.setUser(user);

        }catch (Exception e){
            response.setStatusCode(500);
            response.setMessage(e.getMessage());
        }
        return response;
    }


    public Map<String, Object> loginWithGoogle(String idToken) {
        try {
            GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(
                    new NetHttpTransport(), new JacksonFactory())
                    .setAudience(Collections.singletonList("240758916721-e108eq2rhn9og8judkgjbk7168caafiv.apps.googleusercontent.com"))
                    .build();

            GoogleIdToken googleIdToken = verifier.verify(idToken);
            if (googleIdToken != null) {
                GoogleIdToken.Payload payload = googleIdToken.getPayload();

                String email = payload.getEmail();
                String name = (String) payload.get("name");

                User user = userRepo.findByEmail(email).orElseGet(() -> {
                    User newUser = new User();
                    newUser.setEmail(email);
                    newUser.setName(name);
                    newUser.setCity("ariana");
                    newUser.setEnabled(true);
                    newUser.setRole("USER");
                    newUser.setPassword(passwordEncoder.encode("0000"));
                    return userRepo.save(newUser);
                });

                String jwtToken = jwtUtils.generateToken(user);

                return Map.of(
                        "token", jwtToken,
                        "user", user,
                        "role", user.getRole(),
                        "statusCode", 200
                );
            } else {
                return Map.of("error", "Invalid ID token");
            }

        } catch (Exception e) {
            return Map.of("error", "Login failed", "message", e.getMessage());
        }
    }


    public ReqRes refreshToken(ReqRes refreshTokenReqiest){
        ReqRes response = new ReqRes();
        try{
            String ourEmail = jwtUtils.extractUsername(refreshTokenReqiest.getToken());
            User users = userRepo.findByEmail(ourEmail).orElseThrow();
            if (jwtUtils.isTokenValid(refreshTokenReqiest.getToken(), users)) {
                var jwt = jwtUtils.generateToken(users);
                response.setStatusCode(200);
                response.setToken(jwt);
                response.setRefreshToken(refreshTokenReqiest.getToken());
                response.setExpirationTime("24Hr");
                response.setMessage("Successfully Refreshed Token");
            }
            response.setStatusCode(200);
            return response;

        }catch (Exception e){
            response.setStatusCode(500);
            response.setMessage(e.getMessage());
            return response;
        }
    }


    public ReqRes getAllUsers() {
        ReqRes reqRes = new ReqRes();

        try {
            List<User> result = userRepo.findAll();
            if (!result.isEmpty()) {
                reqRes.setUsersList(result);
                reqRes.setStatusCode(200);
                reqRes.setMessage("Successful");
            } else {
                reqRes.setStatusCode(404);
                reqRes.setMessage("No users found");
            }
            return reqRes;
        } catch (Exception e) {
            reqRes.setStatusCode(500);
            reqRes.setMessage("Error occurred: " + e.getMessage());
            return reqRes;
        }
    }


    public ReqRes getUserById(Integer id) {
        ReqRes reqRes = new ReqRes();
        try {
            User usersById = userRepo.findById(id).orElseThrow(() -> new RuntimeException("User Not found"));
            reqRes.setUser(usersById);
            reqRes.setStatusCode(200);
            reqRes.setMessage("Users with id '" + id + "' found successfully");
        } catch (Exception e) {
            reqRes.setStatusCode(500);
            reqRes.setMessage("Error occurred: " + e.getMessage());
        }
        return reqRes;
    }


    public ReqRes deleteUser(Integer userId) {
        ReqRes reqRes = new ReqRes();
        try {
            Optional<User> userOptional = userRepo.findById(userId);
            if (userOptional.isPresent()) {
                userRepo.deleteById(userId);
                reqRes.setStatusCode(200);
                reqRes.setMessage("User deleted successfully");
            } else {
                reqRes.setStatusCode(404);
                reqRes.setMessage("User not found for deletion");
            }
        } catch (Exception e) {
            reqRes.setStatusCode(500);
            reqRes.setMessage("Error occurred while deleting user: " + e.getMessage());
        }
        return reqRes;
    }

    public ReqRes updateUser(Integer userId, User updatedUser) {
        ReqRes reqRes = new ReqRes();
        try {
            Optional<User> userOptional = userRepo.findById(userId);
            if (userOptional.isPresent()) {
                User existingUser = userOptional.get();
                existingUser.setEmail(updatedUser.getEmail());
                existingUser.setName(updatedUser.getName());
                existingUser.setCity(updatedUser.getCity());
                existingUser.setRole(updatedUser.getRole());
                existingUser.setEnabled(updatedUser.isEnabled());
                // Check if password is present in the request
                if (updatedUser.getPassword() != null && !updatedUser.getPassword().isEmpty()) {
                    // Encode the password and update it
                    existingUser.setPassword(passwordEncoder.encode(updatedUser.getPassword()));
                }

                User savedUser = userRepo.save(existingUser);
                reqRes.setUser(savedUser);
                reqRes.setStatusCode(200);
                reqRes.setMessage("User updated successfully");
            } else {
                reqRes.setStatusCode(404);
                reqRes.setMessage("User not found for update");
            }
        } catch (Exception e) {
            reqRes.setStatusCode(500);
            reqRes.setMessage("Error occurred while updating user: " + e.getMessage());
        }
        return reqRes;
    }

    public ReqRes createUser(ReqRes request) {
        ReqRes resp = new ReqRes();

        try {
            User existingUser = userRepo.findByEmail(request.getEmail()).orElse(null);
            if (existingUser != null) {
                resp.setStatusCode(400);
                resp.setMessage("User with this email already exists.");
                return resp;
            }

            User newUser = new User();
            newUser.setEmail(request.getEmail());
            newUser.setName(request.getName());
            newUser.setCity(request.getCity());
            newUser.setRole(request.getRole());
            newUser.setEnabled(true); // immediately active
            newUser.setPassword(passwordEncoder.encode(request.getPassword()));

            User savedUser = userRepo.save(newUser);
            resp.setUser(savedUser);
            resp.setMessage("User created successfully.");
            resp.setStatusCode(201);

        } catch (Exception e) {
            resp.setStatusCode(500);
            resp.setMessage("Error occurred while creating user: " + e.getMessage());
        }

        return resp;
    }


    public ReqRes getMyInfo(String email){
        ReqRes reqRes = new ReqRes();
        try {
            Optional<User> userOptional = userRepo.findByEmail(email);
            if (userOptional.isPresent()) {
                reqRes.setUser(userOptional.get());
                reqRes.setStatusCode(200);
                reqRes.setMessage("successful");
            } else {
                reqRes.setStatusCode(404);
                reqRes.setMessage("User not found for update");
            }

        }catch (Exception e){
            reqRes.setStatusCode(500);
            reqRes.setMessage("Error occurred while getting user info: " + e.getMessage());
        }
        return reqRes;

    }
}
