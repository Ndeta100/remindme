package com.remindme.security;

import com.remindme.service.UserAccountService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.crypto.SecretKey;
@AllArgsConstructor
@Configuration
@EnableWebSecurity( debug = true )
public class ApplicationSecurityConfig extends WebSecurityConfiguration {
    private final PasswordEncoder passwordEncoder;
    private final UserAccountService userAccountService;
    private final SecretKey secretKey;

}
