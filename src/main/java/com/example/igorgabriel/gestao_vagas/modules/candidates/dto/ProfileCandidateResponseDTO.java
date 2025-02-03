package com.example.igorgabriel.gestao_vagas.modules.candidates.dto;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProfileCandidateResponseDTO {
    
    private String description;
    private String username;
    private String email;
    private String name;
    private UUID id;

}
