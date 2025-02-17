package com.example.igorgabriel.gestao_vagas.modules.company.entities;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.validator.constraints.Length;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Entity(name = "company")
@Data
public class CompanyEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotBlank
    @Pattern(regexp = "^[a-zA-Z0-9]*$", message = "Username should have only letters and numbers")
    private String userName;

    @Email(message = "Email should be valid")
    private String email;

    @Length(min = 6, max = 10, message = "Password should have at least 6 characters")
    private String password;
    private String website;
    private String name;
    private String description;
    
    @CreationTimestamp
    private LocalDateTime createdAt;

}
