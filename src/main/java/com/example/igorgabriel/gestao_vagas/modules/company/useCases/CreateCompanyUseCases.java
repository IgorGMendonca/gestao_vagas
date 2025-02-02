package com.example.igorgabriel.gestao_vagas.modules.company.useCases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.igorgabriel.gestao_vagas.modules.candidates.controllers.UserFoundException;
import com.example.igorgabriel.gestao_vagas.modules.company.entities.CompanyEntity;
import com.example.igorgabriel.gestao_vagas.modules.company.repositories.CompanyRepository;

import jakarta.transaction.Transactional;

@Service
public class CreateCompanyUseCases {

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Transactional
    public CompanyEntity execute(CompanyEntity companyEntity) {
        this.companyRepository.findByUsernameOrEmail(companyEntity.getUserName(), companyEntity.getEmail())
            .ifPresent(company -> {
                throw new UserFoundException("Company already exists" + company.getUserName());
            });

            var password = passwordEncoder.encode(companyEntity.getPassword());
            companyEntity.setPassword(password);

        return this.companyRepository.save(companyEntity);
    }
    
}
