package com.example.igorgabriel.gestao_vagas.modules.company.useCases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.igorgabriel.gestao_vagas.modules.candidates.controllers.UserFoundException;
import com.example.igorgabriel.gestao_vagas.modules.company.entities.CompanyEntity;
import com.example.igorgabriel.gestao_vagas.modules.company.repositories.CompanyRepository;

@Service
public class CreateCompanyUseCases {

    @Autowired
    private CompanyRepository companyRepository;

    public CompanyEntity execute(CompanyEntity companyEntity) {
        this.companyRepository.findByUsernameOrEmail(companyEntity.getUserName(), companyEntity.getEmail())
            .ifPresent(company -> {
                throw new UserFoundException("Company already exists" + company.getUserName());
            });

        return this.companyRepository.save(companyEntity);
    }
    
}
