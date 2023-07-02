package com.thanathip.training.backend.config;

import com.thanathip.training.backend.config.token.TokenFilterConfigurer;
import com.thanathip.training.backend.service.TokenService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;

import java.util.List;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    private final TokenService tokenService;
    String[] PUBLIC = new String[]{"/user/register", "/user/login"};

    public SecurityConfig(TokenService tokenService) {
        this.tokenService = tokenService;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {

        http.cors(config -> {
            CorsConfiguration corsConfiguration = new CorsConfiguration();
            corsConfiguration.setAllowedOrigins(List.of("*"));
            corsConfiguration.setAllowedMethods(List.of("*"));
            corsConfiguration.setAllowedHeaders(List.of("*"));
            config.configurationSource(request -> corsConfiguration);
        });
        http.sessionManagement(config -> {
            config.sessionFixation().none();
        });
        http.csrf(AbstractHttpConfigurer::disable);
        http.sessionManagement(config -> {
            config.sessionFixation().none();
            config.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        });
        http.authorizeHttpRequests(config -> {
            System.out.println("config = " + config);
            config.requestMatchers("/user/register", "/user/login").anonymous()
                    .anyRequest().authenticated();
        }).apply(new TokenFilterConfigurer(tokenService));

        return http.build();
    }


}
