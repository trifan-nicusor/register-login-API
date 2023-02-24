package com.demo.registration;

import com.demo.user.User;
import com.demo.user.UserDetailService;
import com.demo.user.UserRole;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RegistrationService {

    private final EmailValidator validator;
    private final UserDetailService userDetailService;

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
                true
                ));
    }
}