package com.example.igorgabriel.gestao_vagas.modules.candidates.useCases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.igorgabriel.gestao_vagas.modules.candidates.CandidateEntity;
import com.example.igorgabriel.gestao_vagas.modules.candidates.controllers.CandidateRepository;
import com.example.igorgabriel.gestao_vagas.modules.candidates.controllers.UserFoundException;

@Service
public class CreateCandidateUseCases {

    @Autowired
    private CandidateRepository candidateRepository;

    public CandidateEntity execute(CandidateEntity candidateEntity) {
        this.candidateRepository.findByUsernameOrEmail(candidateEntity.getUserName(), candidateEntity.getEmail())
                .ifPresent((user) -> {
                    throw new UserFoundException("User already exists");
                });

        return this.candidateRepository.save(candidateEntity);
    }
    
}
