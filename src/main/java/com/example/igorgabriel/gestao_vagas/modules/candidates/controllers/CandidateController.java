package com.example.igorgabriel.gestao_vagas.modules.candidates.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.igorgabriel.gestao_vagas.modules.candidates.CandidateEntity;
import com.example.igorgabriel.gestao_vagas.modules.candidates.useCases.CreateCandidateUseCases;

import jakarta.validation.Valid;

/**
 * CandidateController is a RestController class that exposes the API endpoints related to the CandidateEntity.
 */
@RestController
@RequestMapping("/candidates")
public class CandidateController {

    @Autowired
    private CreateCandidateUseCases createCandidateUseCases;

    @PostMapping("/")
    public ResponseEntity<Object> create(@Valid @RequestBody CandidateEntity candidateEntity) {
        try {
            var result = this.createCandidateUseCases.execute(candidateEntity);
            return ResponseEntity.ok().body(result);
        } catch (UserFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
