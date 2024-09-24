package com.mypg.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;


import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    Environment env;
    SecurityConfig(Environment environment) {
        this.env = environment;
    }

    @Bean
    public SecurityFilterChain config(HttpSecurity http) throws Exception{
        http.authorizeHttpRequests(authorizationManagerRequestMatcherRegistry -> authorizationManagerRequestMatcherRegistry
            .requestMatchers("/guest/**").hasRole("GUEST")
            .requestMatchers("/admin/**").hasRole("ADMIN")
            .requestMatchers("/owner/**").hasRole("OWNER")
            .requestMatchers("/register/**","/login/**").permitAll()
            .anyRequest().authenticated()).formLogin(withDefaults())
                .logout(withDefaults());
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public InMemoryUserDetailsManager userDetailsManager() {

        String owner_username=env.getProperty("owner.username");
        String owner_password=env.getProperty("owner.password");
        String admin_username=env.getProperty("admin.username");
        String admin_password=env.getProperty("admin.password");

        assert owner_username != null;
        assert owner_password != null;

        assert admin_username != null;
        assert admin_password != null;

        UserDetails admin = User.builder().username(admin_username)
                .password(passwordEncoder().encode(admin_password)).roles("ADMIN").build();
        UserDetails owner = User.builder().username(owner_username)
                .password(passwordEncoder().encode(owner_password)).roles("OWNER").build();
        UserDetails guest = User.builder().username("guest")
                .password(passwordEncoder().encode("guest@123")).roles("GUEST").build();
        return new InMemoryUserDetailsManager(admin,guest,owner);
    }
}
