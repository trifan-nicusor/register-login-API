package com.demo.user;

import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserDetailService implements UserDetailsService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final static String USER_NOT_FOUND = "User with email %s not found!";

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email).orElseThrow(
                        () -> new UsernameNotFoundException(String.format(USER_NOT_FOUND, email)));
    }

    public String signUp(User user) {
       boolean userExists = userRepository
               .findByEmail(user.getEmail())
               .isPresent();

       if(userExists) {
           throw new IllegalStateException("Email already taken!");
       }

       String encodedPassword = passwordEncoder.encode(user.getPassword());
       user.setPassword(encodedPassword);

       userRepository.save(user);

       return "it works";
    }
}