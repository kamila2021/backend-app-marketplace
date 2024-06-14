package com.noseque.config.filters;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.noseque.config.providers.JwtTokenProvider;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;

import static com.noseque.config.SecurityConfig.AUTH_WHITELIST;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@Log4j2
@Configuration
public class JwtTokenFilter extends OncePerRequestFilter {

    private final JwtTokenProvider jwtTokenProvider;

    public JwtTokenFilter(JwtTokenProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
    }


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        filterChain.doFilter(request, response);
        return;
//        if (Arrays.stream(AUTH_WHITELIST).anyMatch(path -> request.getServletPath().startsWith(path))) {
//            filterChain.doFilter(request, response);
//            return;
//        }
//
//        String authHeader = request.getHeader(AUTHORIZATION);
//
//        if (authHeader != null && authHeader.startsWith("Bearer ")) {
//            try {
//                String token = authHeader.substring("Bearer ".length());
//                if(!this.jwtTokenProvider.validateToken(token)){
//                    response.setStatus(HttpStatus.UNAUTHORIZED.value());
//                    response.setContentType(APPLICATION_JSON_VALUE);
//                    new ObjectMapper().writeValue(response.getOutputStream(), Collections.singletonMap("error_message", "El header de autorización no es válido o no existe."));
//                    return;
//                }
//                SecurityContextHolder.getContext().setAuthentication(this.jwtTokenProvider.getUsernameAndPasswordAuth(token));
//                filterChain.doFilter(request, response);
//            } catch (IllegalArgumentException e) {
//                log.error("Error procesando token JWT: {}", e.getMessage());
//                response.setStatus(HttpStatus.UNAUTHORIZED.value());
//                response.setContentType(APPLICATION_JSON_VALUE);
//                new ObjectMapper().writeValue(response.getOutputStream(), Collections.singletonMap("error_message", "Token JWT Invalido"));
//            }
//        } else {
//            response.setStatus(HttpStatus.UNAUTHORIZED.value());
//            response.setContentType(APPLICATION_JSON_VALUE);
//            new ObjectMapper().writeValue(response.getOutputStream(), Collections.singletonMap("error_message", "El header de autorización no es válido o no existe."));
//        }
    }
}
