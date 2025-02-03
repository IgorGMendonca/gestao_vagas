package com.example.igorgabriel.gestao_vagas.modules.candidates.useCases;

import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;

import javax.naming.AuthenticationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.igorgabriel.gestao_vagas.modules.candidates.controllers.CandidateRepository;
import com.example.igorgabriel.gestao_vagas.modules.candidates.dto.AuthCandidateRequestDTO;
import com.example.igorgabriel.gestao_vagas.modules.candidates.dto.AuthCandidateResponseDTO;

@Service
public class AuthCandidateUseCases {

    // The @Value annotation is used to inject values from properties files into fields
    @Value("${security.token.candidate}")
    private String secretKey;

    @Autowired
    private CandidateRepository candidateRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;
    
    public AuthCandidateResponseDTO execute(AuthCandidateRequestDTO authCandidateRequestDTO) throws AuthenticationException {
        var candidate = this.candidateRepository.findByUsername(authCandidateRequestDTO.username())
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        var passwordMatches = this.passwordEncoder.matches(authCandidateRequestDTO.password(), candidate.getPassword());
        
        if(!passwordMatches) {
            throw new AuthenticationException();
        }

        Algorithm algorithm = Algorithm.HMAC256(secretKey);
        var token = JWT.create().withSubject(candidate.getId().toString())
            .withSubject(candidate.getId().toString())
            .withClaim("roles", Arrays.asList("candidate"))
            .withExpiresAt(Instant.now().plus(Duration.ofHours(2)))
            .sign(algorithm);

        var authCandidateResponse = AuthCandidateResponseDTO.builder().access_token(token).build();
        return authCandidateResponse;
    }
}
