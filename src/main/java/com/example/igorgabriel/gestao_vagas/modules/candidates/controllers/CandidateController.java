package com.example.igorgabriel.gestao_vagas.modules.candidates.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.igorgabriel.gestao_vagas.modules.candidates.CandidateEntity;

import jakarta.validation.Valid;

/**
 * CandidateController is a RestController class that exposes the API endpoints related to the CandidateEntity.
 */
@RestController
@RequestMapping("/candidates")
public class CandidateController {

    @Autowired
    private CandidateRepository candidateRepository;

    @PostMapping("/")
    public CandidateEntity create(@Valid @RequestBody CandidateEntity candidateEntity) {
        return this.candidateRepository.save(candidateEntity);
    }
}
