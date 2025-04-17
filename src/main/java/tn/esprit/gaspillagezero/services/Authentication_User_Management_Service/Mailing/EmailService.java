package tn.esprit.gaspillagezero.services.Authentication_User_Management_Service.Mailing;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import static java.nio.charset.StandardCharsets.UTF_8;
import static org.springframework.mail.javamail.MimeMessageHelper.MULTIPART_MODE_MIXED;

@Service

public class EmailService {
    private final JavaMailSender mailSender;
    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }
    @Async
    public void sendEmail(
            String to,
            String username,
            //EmailTemp emailTemplate,
            String confirmationUrl,
            //String activationCode,
            String subject ) throws MessagingException {

        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(
                mimeMessage,
                MULTIPART_MODE_MIXED,
                UTF_8.name()
        );

        // Set the content of the email directly
        String emailContent = "<html><body>"
                + "<p>Hi " + username + ",</p>"
                + "<p>Thank you for registering with us. Please click the link below to confirm your account:</p>"
                + "<p><a href='" + confirmationUrl + "'>Activate Account</a></p>"
                //+ "<p>Your activation code is: " + activationCode + "</p>"
                + "</body></html>";

        // Set email properties
        helper.setFrom("omarmannai28@gmail.com");
        helper.setTo(to);
        helper.setSubject(subject);

        // Set the email content as HTML
        helper.setText(emailContent, true);  // 'true' indicates the content is HTML

        // Send the email
        mailSender.send(mimeMessage);
    }

    @Async
    public void sendEmail2(
            String to,
            String username,
            //EmailTemp emailTemplate,
            String confirmationUrl,
            //String activationCode,
            String subject ) throws MessagingException {

        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(
                mimeMessage,
                MULTIPART_MODE_MIXED,
                UTF_8.name()
        );

        // Set the content of the email directly
        String emailContent = "<html><body>"
                + "<p>Hi " + username + ",</p>"
                + "<p>Please click the link below to reset your password:</p>"
                + "<p><a href='" + confirmationUrl + "'>reset your password</a></p>"
                //+ "<p>Your activation code is: " + activationCode + "</p>"
                + "</body></html>";

        // Set email properties
        helper.setFrom("omarmannai28@gmail.com");
        helper.setTo(to);
        helper.setSubject(subject);

        // Set the email content as HTML
        helper.setText(emailContent, true);  // 'true' indicates the content is HTML

        // Send the email
        mailSender.send(mimeMessage);
    }
}

