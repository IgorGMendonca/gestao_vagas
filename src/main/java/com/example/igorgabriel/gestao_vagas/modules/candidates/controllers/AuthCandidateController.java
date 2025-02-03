package com.example.igorgabriel.gestao_vagas.modules.candidates.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.igorgabriel.gestao_vagas.modules.candidates.dto.AuthCandidateRequestDTO;
import com.example.igorgabriel.gestao_vagas.modules.candidates.useCases.AuthCandidateUseCases;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/candidates")
public class AuthCandidateController {

    @Autowired
    private AuthCandidateUseCases authCandidateUseCases;

    @PostMapping("/auth")
    public ResponseEntity<Object> authenticate(@Valid @RequestBody AuthCandidateRequestDTO authCandidateRequestDTO) {
        try {
            var token = this.authCandidateUseCases.execute(authCandidateRequestDTO);
            return ResponseEntity.ok().body(token);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }
    }

}
