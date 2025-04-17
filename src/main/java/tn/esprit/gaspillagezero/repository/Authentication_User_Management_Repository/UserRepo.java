package tn.esprit.gaspillagezero.repository.Authentication_User_Management_Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.gaspillagezero.entites.Authentication_User_Management.User;

import java.util.Optional;

public interface UserRepo extends JpaRepository<User, Integer> {

    Optional<User> findByEmail(String email);
    Optional<User> findByVerificationToken(String token);

}
