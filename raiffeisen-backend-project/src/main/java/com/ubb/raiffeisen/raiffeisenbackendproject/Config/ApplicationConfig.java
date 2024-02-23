package com.ubb.raiffeisen.raiffeisenbackendproject.Config;

import com.ubb.raiffeisen.raiffeisenbackendproject.User.UserJpaRepository;
import com.ubb.raiffeisen.raiffeisenbackendproject.User.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@Configuration
@RequiredArgsConstructor
public class ApplicationConfig {

    private final UserJpaRepository userJpaRepository;

    @Bean
    public UserDetailsService userDetailsService(){
        return username -> userJpaRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }
}
