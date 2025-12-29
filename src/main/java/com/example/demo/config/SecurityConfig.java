// package com.example.demo.security;

// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.http.HttpMethod;
// import org.springframework.security.authentication.AuthenticationManager;
// import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
// import org.springframework.security.config.annotation.web.builders.HttpSecurity;
// import org.springframework.security.config.http.SessionCreationPolicy;
// import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
// import org.springframework.security.crypto.password.PasswordEncoder;
// import org.springframework.security.web.SecurityFilterChain;
// import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

// @Configuration
// public class SecurityConfig {

//     private final JwtAuthenticationFilter jwtAuthenticationFilter;

//     public SecurityConfig(JwtAuthenticationFilter jwtAuthenticationFilter) {
//         this.jwtAuthenticationFilter = jwtAuthenticationFilter;
//     }

//     @Bean
//     public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

//         http
//             .csrf(csrf -> csrf.disable())
//             .sessionManagement(session ->
//                 session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//             )
//             .authorizeHttpRequests(auth -> auth

//                 // 🔓 Auth APIs
//                 .requestMatchers("/users/register", "/users/login").permitAll()

//                 // 🔓 Swagger
//                 .requestMatchers(
//                         "/swagger-ui.html",
//                         "/swagger-ui/**",
//                         "/v3/api-docs/**"
//                 ).permitAll()

//                 // 🔓 PARCEL APIs (FIX)
//                 .requestMatchers(HttpMethod.GET, "/parcels/**").permitAll()
//                 .requestMatchers(HttpMethod.POST, "/parcels/**").permitAll()

//                 // 🔒 Everything else
//                 .anyRequest().authenticated()
//             )
//             .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

//         return http.build();
//     }

//     @Bean
//     public PasswordEncoder passwordEncoder() {
//         return new BCryptPasswordEncoder();
//     }

//     @Bean
//     public AuthenticationManager authenticationManager(
//             AuthenticationConfiguration config) throws Exception {
//         return config.getAuthenticationManager();
//     }
// }
// package com.example.demo.security;

// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.http.HttpMethod;
// import org.springframework.security.authentication.AuthenticationManager;
// import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
// import org.springframework.security.config.annotation.web.builders.HttpSecurity;
// import org.springframework.security.config.http.SessionCreationPolicy;
// import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
// import org.springframework.security.crypto.password.PasswordEncoder;
// import org.springframework.security.web.SecurityFilterChain;
// import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
// import org.springframework.web.cors.CorsConfiguration;
// import org.springframework.web.cors.CorsConfigurationSource;
// import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

// import java.util.List;

// @Configuration
// public class SecurityConfig {

//     private final JwtAuthenticationFilter jwtAuthenticationFilter;

//     public SecurityConfig(JwtAuthenticationFilter jwtAuthenticationFilter) {
//         this.jwtAuthenticationFilter = jwtAuthenticationFilter;
//     }

//     @Bean
//     public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

//         http
//             // 🔴 REQUIRED FOR SWAGGER & BROWSER CALLS
//             .cors(cors -> cors.configurationSource(corsConfigurationSource()))

//             .csrf(csrf -> csrf.disable())

//             .sessionManagement(session ->
//                 session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//             )

//             .authorizeHttpRequests(auth -> auth

//                 // 🔓 ALLOW PREFLIGHT REQUESTS
//                 .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()

//                 // 🔓 AUTH APIs
//                 .requestMatchers("/users/register", "/users/login").permitAll()

//                 // 🔓 SWAGGER
//                 .requestMatchers(
//                     "/swagger-ui.html",
//                     "/swagger-ui/**",
//                     "/v3/api-docs/**"
//                 ).permitAll()

//                 // 🔓 PARCEL APIs
//                 .requestMatchers("/parcels/**").permitAll()

//                 // 🔒 EVERYTHING ELSE
//                 .anyRequest().authenticated()
//             )

//             // 🔐 JWT FILTER
//             .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

//         return http.build();
//     }

//     // ✅ CORS CONFIGURATION (MANDATORY)
//     @Bean
//     public CorsConfigurationSource corsConfigurationSource() {

//         CorsConfiguration config = new CorsConfiguration();

//         config.setAllowedOrigins(List.of("*"));   // Swagger / frontend
//         config.setAllowedMethods(List.of(
//                 "GET", "POST", "PUT", "DELETE", "OPTIONS"
//         ));
//         config.setAllowedHeaders(List.of("*"));
//         config.setAllowCredentials(false);

//         UrlBasedCorsConfigurationSource source =
//                 new UrlBasedCorsConfigurationSource();
//         source.registerCorsConfiguration("/**", config);

//         return source;
//     }

//     @Bean
//     public PasswordEncoder passwordEncoder() {
//         return new BCryptPasswordEncoder();
//     }

//     @Bean
//     public AuthenticationManager authenticationManager(
//             AuthenticationConfiguration config) throws Exception {
//         return config.getAuthenticationManager();
//     }
// }
package com.example.demo.config;


import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import com.example.demo.security.JwtAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private JwtAuthenticationFilter jwtRequestFilter;
    
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/api/auth/**", "/swagger-ui/**", "/api-docs/**").permitAll()
                .anyRequest().authenticated()
            )
            .sessionManagement(session -> session
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            );

        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
}

