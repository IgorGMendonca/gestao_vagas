package com.example.igorgabriel.gestao_vagas.modules.candidates.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AuthCandidateResponseDTO {

    private String access_token;
    private Long expires_in;
    
}
