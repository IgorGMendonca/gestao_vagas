package com.example.igorgabriel.gestao_vagas.modules.company.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.igorgabriel.gestao_vagas.modules.company.dto.AuthCompanyDTO;
import com.example.igorgabriel.gestao_vagas.modules.company.useCases.AuthCompanyUseCases;

import jakarta.validation.Valid;

import javax.naming.AuthenticationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/auth")
public class AuthCompanyController {
    
    @Autowired
    private AuthCompanyUseCases authCompanyUseCases;    

    @PostMapping("/company")
    public ResponseEntity<Object> create(@Valid @RequestBody AuthCompanyDTO authCompanyDTO) throws AuthenticationException {
        try {
            var token = this.authCompanyUseCases.execute(authCompanyDTO);
            return ResponseEntity.ok().body(token);
        } catch (AuthenticationException e) {
            return ResponseEntity.badRequest().body("Invalid credentials");
        }
    }
    
}
