package com.example.igorgabriel.gestao_vagas.security;

import java.io.IOException;
import java.util.Collection;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.example.igorgabriel.gestao_vagas.providers.JWTProvider;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class SecurityFilter extends OncePerRequestFilter {
    
    @Autowired
    private JWTProvider jwtProvider;


    /* @Override is an annotation that informs the compiler that the element is meant to override an element declared in a superclass.
     * The doFilterInternal method is called by the filter chain to apply the filter to the request and response. */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

                String header = request.getHeader("Authorization");

                if(header != null) {
                   var subjectToken = this.jwtProvider.validateToken(header);

                   if(subjectToken.isEmpty()) {
                       response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                       return;
                   }
                   request.setAttribute("company_id", subjectToken);
                   UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(subjectToken, null, Collections.emptyList());

                   SecurityContextHolder.getContext().setAuthentication(authentication);
                }

                filterChain.doFilter(request, response);
            }
    
}
