package backendrestaurant.com.example.backendrestaurant.config;

import backendrestaurant.com.example.backendrestaurant.security.JwtRequestFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http, JwtRequestFilter jwtRequestFilter) throws Exception {
        http
                .httpBasic(hp -> hp.disable())

                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(
                                "/v3/api-docs/**",
                                "/swagger-ui/**",
                                "/swagger-ui.html"
                        ).permitAll()
                        .requestMatchers("/login").permitAll()
                        .requestMatchers("/reservations/").authenticated()
                        .requestMatchers("/drinks/").authenticated()
                        .requestMatchers("/users").hasRole("BAAS")
                        .requestMatchers("/users/{id}").hasRole("BAAS")
                        .requestMatchers("/bon/").authenticated()
                        .requestMatchers("/allergieen/").authenticated()
                        .requestMatchers("/newsletter/").authenticated()
                        .requestMatchers("/bestelling/").authenticated()
                        .requestMatchers("/menuItems/all/{id}").authenticated()
                        .requestMatchers("/menuItems/all/create").hasRole("CHEFKOK")
                        .requestMatchers("/menuItems/all/{id}/block").hasRole("CHEFKOK")
                        .requestMatchers("/menuItems/all/{id}/unblock").hasRole("CHEFKOK")
                        .requestMatchers("/menuItems/**").hasAnyRole("SERVEERSTERS", "RESTAURANTMANAGER")
                        .requestMatchers("/drinks/dranken/{id}").hasRole("BAAS")
                        .requestMatchers("/drinks/**").hasRole("BAAS")
                        .requestMatchers("/drinks/").hasAnyRole("SERVEERSTERS", "RESTAURANTMANAGER")
                        .requestMatchers("/newsletter/upload").hasRole("BAAS")
                        .requestMatchers("/newsletter/download/latest").hasAnyRole("BAAS", "RESTAURANTMANAGER", "SERVEERSTERS", "CHEFKOK")
                        .requestMatchers("/bon/**").hasAnyRole("BAAS", "RESTAURANTMANAGER", "SERVEERSTERS")
                        .requestMatchers("/allergieen/**").hasAnyRole("BAAS", "RESTAURANTMANAGER", "SERVEERSTERS")
                        .requestMatchers("/bestelling/**").hasAnyRole("BAAS", "RESTAURANTMANAGER", "SERVEERSTERS")
                        .requestMatchers("/reservations/**").hasAnyRole("BAAS", "RESTAURANTMANAGER")
                        .anyRequest().denyAll()


                )
                .addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class)
                .csrf(csrf -> csrf.disable())
                .cors(cors -> {})
                .sessionManagement( session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
        ;
        return  http.build();
    }

    @Bean
    public AuthenticationManager authManager(HttpSecurity http, PasswordEncoder encoder, UserDetailsService apiUserDetailsService) throws Exception {
        var builder = http.getSharedObject(AuthenticationManagerBuilder.class);
        builder.userDetailsService(apiUserDetailsService).passwordEncoder(encoder);
        return builder.build();
    }
}
