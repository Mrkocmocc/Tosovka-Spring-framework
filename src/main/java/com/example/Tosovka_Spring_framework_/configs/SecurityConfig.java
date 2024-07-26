package com.example.Tosovka_Spring_framework_.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.example.Tosovka_Spring_framework_.service.UserDetailsServiceImpl;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableMethodSecurity(prePostEnabled = true)
@RequiredArgsConstructor
public class SecurityConfig {

    @Autowired
    private final UserDetailsServiceImpl userDetailsService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http.authorizeHttpRequests((authorize) -> authorize
        .requestMatchers("/","/login/**","/register/**","/static/**","/image/**","/search/**","/events/**", "/event/**", "/profile/**").permitAll()
        .anyRequest().authenticated())
        .formLogin(login -> login.loginPage("/login").defaultSuccessUrl("/"))
        .logout((logout) -> logout.logoutUrl("/logout").logoutSuccessUrl("/"))
        .userDetailsService(userDetailsService)
        .build();
    }
}
