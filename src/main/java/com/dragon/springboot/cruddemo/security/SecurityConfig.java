package com.dragon.springboot.cruddemo.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {


        @Bean
        public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

            http.authorizeHttpRequests(config -> config
                            .requestMatchers("/showLoginForm", "/login", "/resources/**").permitAll()
                            .anyRequest().authenticated())
                    .formLogin(form -> form
                            .loginPage("/showLoginForm")
                            .loginProcessingUrl("/authenticateTheUser")
                            .permitAll())
            .logout(logout -> logout.permitAll()
            );

            return http.build();
        }
    }


