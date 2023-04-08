package com.bookstoreversion2.config;

import com.bookstoreversion2.services.UserServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig{

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private UserServiceImp userServiceImp;

    @Value("${spring.security.debug:false}")
    private boolean securityDebug;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers("/registration").permitAll()
                        .requestMatchers("/catalog/*").permitAll()
                        .requestMatchers("/home", "/", "").permitAll()
                        .requestMatchers("/admin/*").hasRole("ADMIN")
                        .requestMatchers("/manager/*").hasRole("MANAGER")
                        .requestMatchers("/user/*").hasRole("USER")
                        .requestMatchers("/account").fullyAuthenticated()
                        .anyRequest().authenticated()
                )
                .formLogin((form) -> form
                        .loginPage("/login")
                        .defaultSuccessUrl("/home")
                        .permitAll()
                )
                .logout((logout) -> logout.logoutSuccessUrl("/home"));

        return http.build();
    }
    @Autowired
    protected void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userServiceImp).passwordEncoder(bCryptPasswordEncoder);
    }
}