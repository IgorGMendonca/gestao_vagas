package com.example.igorgabriel.gestao_vagas.modules.candidates.controllers;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.igorgabriel.gestao_vagas.modules.candidates.CandidateEntity;

/**
 * CandidateRepository is a JpaRepository interface that extends JpaRepository, which is a JPA specific extension of Repository.
 */	

public interface CandidateRepository extends JpaRepository<CandidateEntity, UUID> {
    Optional<CandidateEntity> findByUsernameOrEmail(String Username, String email);
}
