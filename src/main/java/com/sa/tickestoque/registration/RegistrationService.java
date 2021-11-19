package com.sa.tickestoque.registration;
//
//import com.sa.tickestoque.email.EmailSender;
import com.sa.tickestoque.user.AppUser;
import com.sa.tickestoque.user.UserRole;
import com.sa.tickestoque.user.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.sa.tickestoque.registration.token.ConfirmationToken;
import com.sa.tickestoque.registration.token.ConfirmationTokenService;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class RegistrationService {

    private final UserService userService;
    private final EmailValidator emailValidator;
    private final ConfirmationTokenService confirmationTokenService;
//    private final EmailSender emailSender;

    public  String register(RegistrationRequest request) {
       boolean isValidEmail = emailValidator.test(request.getEmail());
       if(!isValidEmail){
           throw new IllegalStateException("email not valid");
       }
        return userService.signUpUser(
                new AppUser(
                        request.getFirstName(),
                        request.getLastName(),
                        request.getEmail(),
                        request.getPassword(),
                        UserRole.USER
                )
        );
    }
    @Transactional
    public String confirmToken(String token) {
        ConfirmationToken confirmationToken = confirmationTokenService
                .getToken(token)
                .orElseThrow(() ->
                        new IllegalStateException("token not found"));

        if (confirmationToken.getConfirmedAt() != null) {
            throw new IllegalStateException("email already confirmed");
        }

        LocalDateTime expiredAt = confirmationToken.getExpiresAt();

        if (expiredAt.isBefore(LocalDateTime.now())) {
            throw new IllegalStateException("token expired");
        }

        confirmationTokenService.setConfirmedAt(token);
        userService.enableAppUser(
                confirmationToken.getAppUser().getEmail());
        return "confirmed";
    }

}
