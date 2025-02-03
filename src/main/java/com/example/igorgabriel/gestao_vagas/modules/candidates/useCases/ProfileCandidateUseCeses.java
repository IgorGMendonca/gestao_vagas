package com.example.igorgabriel.gestao_vagas.modules.candidates.useCases;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.igorgabriel.gestao_vagas.modules.candidates.controllers.CandidateRepository;
import com.example.igorgabriel.gestao_vagas.modules.candidates.dto.ProfileCandidateResponseDTO;

@Service
public class ProfileCandidateUseCeses {
    
    @Autowired
    private CandidateRepository candidateRepository;

    public ProfileCandidateResponseDTO execute(UUID candidateId) {
        var candidate = this.candidateRepository.findById(candidateId)
            .orElseThrow(() -> new UsernameNotFoundException("Candidate not found"));

        var candidateDTO = ProfileCandidateResponseDTO.builder()
                .description(candidate.getDescription())
                .email(candidate.getEmail())
                .name(candidate.getName())
                .username(candidate.getUserName())
                .id(candidate.getId())
                .build();

        return candidateDTO;
    }
}
