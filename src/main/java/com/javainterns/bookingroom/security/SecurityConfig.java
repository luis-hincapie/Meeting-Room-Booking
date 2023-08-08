package com.javainterns.bookingroom.security;

import com.javainterns.bookingroom.security.filters.JwtAuthenticationFilter;
import com.javainterns.bookingroom.security.filters.JwtAuthorizationFilter;
import com.javainterns.bookingroom.security.jwt.JwtUtils;
import com.javainterns.bookingroom.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
/*@EnableGlobalMethodSecurity(prePostEnabled = true)*/
@EnableWebMvc
@EnableWebSecurity
public class SecurityConfig {

  @Autowired
  JwtUtils jwtUtils;

  @Autowired
  UserDetailsServiceImpl userDetailsService;

  @Autowired
  JwtAuthorizationFilter authorizationFilter;

  @Bean
  SecurityFilterChain securityFilterChain(
    HttpSecurity httpSecurity,
    AuthenticationManager authenticationManager
  ) throws Exception {
    JwtAuthenticationFilter jwtAuthenticationFilter = new JwtAuthenticationFilter(
      jwtUtils
    );
    jwtAuthenticationFilter.setAuthenticationManager(authenticationManager);
    jwtAuthenticationFilter.setFilterProcessesUrl("/login");

    return httpSecurity
      .csrf(AbstractHttpConfigurer::disable)
      .authorizeHttpRequests(auth -> {
        auth.requestMatchers("/", "/h2-console/", "/h2-console/**").permitAll();
        auth
          .requestMatchers(
            "/",
            "/error",
            "/csrf",
            "/swagger-ui.html",
            "/swagger-ui/**",
            "/v3/api-docs",
            "/v3/api-docs/**"
          )
          .permitAll();
        auth.requestMatchers("/rooms/", "/rooms/**").hasAnyRole("ADMIN");
        auth
          .requestMatchers(HttpMethod.GET, "/rooms/**", "/rooms/")
          .hasAnyRole("USER");
        auth.requestMatchers("/users/", "/users/**").hasAnyRole("ADMIN");
        auth
          .requestMatchers("/bookings/", "/bookings/**")
          .hasAnyRole("ADMIN", "USER");
        auth.anyRequest().authenticated();
      })
      .sessionManagement(session -> {
        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
      })
      .addFilter(jwtAuthenticationFilter)
      .addFilterBefore(
        authorizationFilter,
        UsernamePasswordAuthenticationFilter.class
      )
      .csrf(csrf -> {
        csrf.ignoringRequestMatchers("/h2-console/**");
      })
      .build();
  }

  @Bean
  PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  AuthenticationManager authenticationManager(
    HttpSecurity httpSecurity,
    PasswordEncoder passwordEncoder
  ) throws Exception {
    return httpSecurity
      .getSharedObject(AuthenticationManagerBuilder.class)
      .userDetailsService(userDetailsService)
      .passwordEncoder(passwordEncoder)
      .and()
      .build();
  }
}
