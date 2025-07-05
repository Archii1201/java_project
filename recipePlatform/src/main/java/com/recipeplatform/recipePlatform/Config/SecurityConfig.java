package com.recipeplatform.recipePlatform.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.Customizer;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;

import javax.sql.DataSource;

@Configuration
public class SecurityConfig {


    @Bean
    public UserDetailsManager userDetailsManager(DataSource dataSource) {
        JdbcUserDetailsManager manager = new JdbcUserDetailsManager(dataSource);

        // Fetch user details with enabled status
        manager.setUsersByUsernameQuery(
                "SELECT username, password, enabled FROM users WHERE username = ?"
        );

        // Ensure roles are fetched properly (assumes "role" column has 'USER'/'ADMIN' — no 'ROLE_' prefix)
        manager.setAuthoritiesByUsernameQuery(
	    	"SELECT username, role FROM users WHERE username = ?"
	    );


        return manager;
    }

    // ❗✅ Keeping your original filterChain() method intact!
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(configurer ->
                configurer
                        .requestMatchers(HttpMethod.POST, "/api/users/register").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/users/**").hasAnyAuthority("ROLE_USER","ROLE_ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/api/users/**").hasAuthority("ROLE_ADMIN")

                        .requestMatchers(HttpMethod.GET, "/api/recipes/**").hasAnyAuthority("ROLE_USER","ROLE_ADMIN") // <--- This was already here
                        .requestMatchers(HttpMethod.POST, "/api/recipes/**").hasAnyAuthority("ROLE_USER", "ROLE_ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/api/recipes/**").hasAnyAuthority("ROLE_USER", "ROLE_ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/api/recipes/**").hasAnyAuthority("ROLE_USER", "ROLE_ADMIN")

                        .requestMatchers(HttpMethod.POST, "/api/likes/**").hasAuthority("ROLE_USER")
                        .requestMatchers(HttpMethod.GET, "/api/likes/**").hasAnyAuthority("ROLE_USER","ROLE_ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/api/likes/**").hasAuthority("ROLE_USER")

                        .requestMatchers(HttpMethod.POST, "/api/comments/**").hasAuthority("ROLE_USER")
                        .requestMatchers(HttpMethod.GET, "/api/comments/**").hasAnyAuthority("ROLE_USER","ROLE_ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/api/comments/**").hasAuthority("ROLE_USER")
                        .anyRequest().authenticated()
        );
        http.httpBasic(Customizer.withDefaults());
        http.csrf(csrf -> csrf.disable());

        return http.build();
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}


//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.HttpMethod;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.security.config.Customizer;
//import org.springframework.security.provisioning.JdbcUserDetailsManager;
//import org.springframework.security.provisioning.UserDetailsManager;
//
//import javax.sql.DataSource;
//
//@Configuration
//public class SecurityConfig {
//
//
//	@Bean
//	public UserDetailsManager userDetailsManager(DataSource dataSource) {
//	    JdbcUserDetailsManager manager = new JdbcUserDetailsManager(dataSource);
//
//
//	    manager.setUsersByUsernameQuery(
//	        "SELECT username, password, enabled FROM users WHERE username = ?"
//	    );
//
//	    manager.setAuthoritiesByUsernameQuery(
//	    	"SELECT username, role FROM users WHERE username = ?"
//	    );
//
//	    return manager;
//	}
//
//
//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        http.authorizeHttpRequests(configurer ->
//            configurer
//                .requestMatchers(HttpMethod.POST, "/api/users/register").permitAll()
//                .requestMatchers(HttpMethod.GET, "/api/users/**").hasAnyAuthority("ROLE_USER","ROLE_ADMIN")
//                .requestMatchers(HttpMethod.DELETE, "/api/users/**").hasAuthority("ROLE_ADMIN")
//
//             // User upgrade to premium - accessible to ROLE_USER
//                .requestMatchers(HttpMethod.POST, "/api/users/upgrade/**").hasAuthority("ROLE_USER")
//
//                // Event endpoints
//                .requestMatchers(HttpMethod.POST, "/api/events/create/**").hasAuthority("ROLE_ADMIN")
//                .requestMatchers(HttpMethod.POST, "/api/events/register/**").hasAuthority("ROLE_USER")
//                .requestMatchers(HttpMethod.GET, "/api/events/**").hasAnyAuthority("ROLE_USER", "ROLE_ADMIN")
//
//
//                .requestMatchers(HttpMethod.GET, "/api/recipes/**").hasAnyAuthority("ROLE_USER","ROLE_ADMIN")
//                .requestMatchers(HttpMethod.POST, "/api/recipes/**").hasAnyAuthority("ROLE_USER", "ROLE_ADMIN")
//                .requestMatchers(HttpMethod.PUT, "/api/recipes/**").hasAnyAuthority("ROLE_USER", "ROLE_ADMIN")
//                .requestMatchers(HttpMethod.DELETE, "/api/recipes/**").hasAnyAuthority("ROLE_USER", "ROLE_ADMIN")
//
//                .requestMatchers(HttpMethod.POST, "/api/likes/**").hasAuthority("ROLE_USER")
//                .requestMatchers(HttpMethod.GET, "/api/likes/**").hasAnyAuthority("ROLE_USER","ROLE_ADMIN")
//                .requestMatchers(HttpMethod.DELETE, "/api/likes/**").hasAuthority("ROLE_USER")
//
//                .requestMatchers(HttpMethod.POST, "/api/comments/**").hasAuthority("ROLE_USER")
//                .requestMatchers(HttpMethod.GET, "/api/comments/**").hasAnyAuthority("ROLE_USER","ROLE_ADMIN")
//                .requestMatchers(HttpMethod.DELETE, "/api/comments/**").hasAuthority("ROLE_USER")
//                .anyRequest().authenticated()
//        );
//        http.httpBasic(Customizer.withDefaults());
//        http.csrf(csrf -> csrf.disable());
//
//        return http.build();
//    }
//
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//
//}

