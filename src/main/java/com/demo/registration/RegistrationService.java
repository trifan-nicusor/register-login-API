package com.demo.registration;

import com.demo.registration.token.ConfirmationToken;
import com.demo.registration.token.ConfirmationTokenService;
import com.demo.user.User;
import com.demo.user.UserDetailService;
import com.demo.user.UserRole;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class RegistrationService {

    private final EmailValidator validator;
    private final UserDetailService userDetailService;
    private final ConfirmationTokenService tokenService;

    public String register(RegistrationRequest request) {
        boolean isEmailValid = validator.test(request.email());

        if (!isEmailValid) {
            throw new IllegalStateException("Email not valid!");
        }

        return userDetailService.signUp(new User(
                request.email(),
                request.password(),
                request.firstName(),
                request.lastName(),
                UserRole.USER,
                false,
                false
                ));
    }

    @Transactional
    public String confirmToken(String token) {
        ConfirmationToken confirmationToken = tokenService
                .getToken(token)
                .orElseThrow(() -> new IllegalStateException("Token not found!"));

        if(confirmationToken.getConfirmedAt() != null) {
            throw new IllegalStateException("Email already confirmed!");
        }

        LocalDateTime expiredAt = confirmationToken.getExpiresAt();
        userDetailService.enableUser(confirmationToken.getUser().getEmail());

        return "confirmed";
    }
}