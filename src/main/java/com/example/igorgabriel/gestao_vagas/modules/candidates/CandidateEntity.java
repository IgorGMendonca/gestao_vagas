package com.example.igorgabriel.gestao_vagas.modules.candidates;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.validator.constraints.Length;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data

// @Entity is used to specify that the class is an entity and is mapped to a database table.
@Entity(name = "candidate")
public class CandidateEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    // @Column is used to specify the mapped column for a persistent property or field, but if the name is omitted, the property or field name is used.
    @Column(name = "name")
    private String name;

    @Pattern(regexp = "^[a-zA-Z0-9]*$", message = "Username should have only letters and numbers")
    private String userName;

    @Email(message = "Email should be valid")
    private String email;

    @Length(min = 6, max = 10, message = "Password should have at least 6 characters")
    private String password;

    private String description;
    private String curriculum;

    @CreationTimestamp
    private LocalDateTime createdAt;


}
