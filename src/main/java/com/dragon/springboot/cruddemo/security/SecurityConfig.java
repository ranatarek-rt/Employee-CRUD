package com.dragon.springboot.cruddemo.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
public class SecurityConfig {


        //Access users from database for authentications
        @Bean
        public UserDetailsManager userDetailsManager(DataSource dataSource){

            JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager(dataSource);
            //Note! the user id will be passes from the login form
            //define query to fetch userName
            jdbcUserDetailsManager.setUsersByUsernameQuery("select user_id,pw, active from members where user_id=?");
            //define query to fetch role by userName
            jdbcUserDetailsManager.setAuthoritiesByUsernameQuery("select user_id,role from roles where user_id=?");
            return jdbcUserDetailsManager;
        }
        @Bean
        public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

            http.authorizeHttpRequests(config -> config
                            .requestMatchers("/employees/list").hasAnyRole("EMPLOYEE","MANAGER","ADMIN")
                            .requestMatchers("/employees/deleteEmployee").hasRole("ADMIN")
                            .requestMatchers("/employees/updateEmployee").hasRole("ADMIN")
                            .requestMatchers("/employees/updateForm").hasRole("ADMIN")
                            .requestMatchers("/employees/addNewEmployee").hasAnyRole("ADMIN","MANAGER")
                            .requestMatchers("/employees/addNewEmployeeForm").hasAnyRole("ADMIN","MANAGER")
                            .requestMatchers("/showLoginForm", "/login", "/resources/**").permitAll()
                            .anyRequest().authenticated())
                    .formLogin(form -> form
                            .loginPage("/showLoginForm")
                            .loginProcessingUrl("/authenticateTheUser")
                            .permitAll())
            .logout(logout -> logout.permitAll()

            ).exceptionHandling(config->config
                            .accessDeniedPage("/employees/accessDenied"));

            return http.build();
        }
    }


