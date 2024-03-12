package com.ubb.raiffeisen.raiffeisenbackendproject.Auth;

import com.ubb.raiffeisen.raiffeisenbackendproject.User.ChangePasswordRequest;
import com.ubb.raiffeisen.raiffeisenbackendproject.User.User;
import com.ubb.raiffeisen.raiffeisenbackendproject.User.UserJpaRepository;
import com.ubb.raiffeisen.raiffeisenbackendproject.User.UserNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.naming.AuthenticationException;
import java.util.Optional;

@RestController
@RequestMapping(value = "api/user")
public class AuthUser {

    private final AuthenticationService service;
    private final UserJpaRepository userJpaRepository;

    private final PasswordEncoder passwordEncoder;

    public AuthUser(AuthenticationService service, UserJpaRepository userJpaRepository, PasswordEncoder passwordEncoder) {
        this.service = service;
        this.userJpaRepository = userJpaRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest request){
        return ResponseEntity.ok(service.register(request));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request){
        return ResponseEntity.ok(service.authenticate(request));
    }

    @PatchMapping("/recover-password/{user_id}")
    public ResponseEntity<?> changePassword(@RequestBody ChangePasswordRequest request, @PathVariable Long user_id){
        Optional<User> getUser = userJpaRepository.findById(user_id);
        if (getUser.isEmpty()) throw new UserNotFoundException("User with id: " + user_id + " doest not exist!");

        User user = getUser.get();

        if (!request.getNewPassword().equals(request.getConfirmationPassword())) {
            throw new IllegalStateException("Passwords are not the same");
        }
        user.setPassword(passwordEncoder.encode(request.getNewPassword()));
        userJpaRepository.save(user);

        return ResponseEntity.ok(user);
    }

}
