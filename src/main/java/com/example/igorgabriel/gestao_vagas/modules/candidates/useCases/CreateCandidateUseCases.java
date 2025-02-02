package com.example.igorgabriel.gestao_vagas.modules.candidates.useCases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.igorgabriel.gestao_vagas.modules.candidates.CandidateEntity;
import com.example.igorgabriel.gestao_vagas.modules.candidates.controllers.CandidateRepository;
import com.example.igorgabriel.gestao_vagas.modules.candidates.controllers.UserFoundException;

@Service
public class CreateCandidateUseCases {

    @Autowired
    private CandidateRepository candidateRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public CandidateEntity execute(CandidateEntity candidateEntity) {
        this.candidateRepository.findByUsernameOrEmail(candidateEntity.getUserName(), candidateEntity.getEmail())
                .ifPresent((user) -> {
                    throw new UserFoundException("User already exists" + user.getUserName());
                });

        var password = passwordEncoder.encode(candidateEntity.getPassword());
        candidateEntity.setPassword(password);

        return this.candidateRepository.save(candidateEntity);
    }
    
}
