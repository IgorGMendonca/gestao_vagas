package com.example.igorgabriel.gestao_vagas.modules.candidates.controllers;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.igorgabriel.gestao_vagas.modules.candidates.CandidateEntity;
import com.example.igorgabriel.gestao_vagas.modules.candidates.useCases.CreateCandidateUseCases;
import com.example.igorgabriel.gestao_vagas.modules.candidates.useCases.ProfileCandidateUseCeses;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

/**
 * CandidateController is a RestController class that exposes the API endpoints related to the CandidateEntity.
 */
@RestController
@RequestMapping("/candidates")
public class CandidateController {

    @Autowired
    private CreateCandidateUseCases createCandidateUseCases;

    @Autowired
    private ProfileCandidateUseCeses profileCandidateUseCeses;

    @PostMapping("/")
    public ResponseEntity<Object> create(@Valid @RequestBody CandidateEntity candidateEntity) {
        try {
            var result = this.createCandidateUseCases.execute(candidateEntity);
            return ResponseEntity.ok().body(result);
        } catch (UserFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/")
    @PreAuthorize("hasRole('CANDIDATE')")
    public ResponseEntity<Object> get(HttpServletRequest request) {
        var candidateId = request.getAttribute("candidate_id");

        try {
            var result = this.profileCandidateUseCeses.execute(UUID.fromString(candidateId.toString()));
            return ResponseEntity.ok().body(result);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
