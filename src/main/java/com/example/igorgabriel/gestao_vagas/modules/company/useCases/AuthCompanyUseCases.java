package com.example.igorgabriel.gestao_vagas.modules.company.useCases;

import javax.naming.AuthenticationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.igorgabriel.gestao_vagas.modules.company.dto.AuthCompanyDTO;
import com.example.igorgabriel.gestao_vagas.modules.company.repositories.CompanyRepository;

@Service
public class AuthCompanyUseCases {
    
    // The @Value annotation is used to inject values from properties files into fields
    @Value("${security.token}")
    private String secretKey;

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public String execute(AuthCompanyDTO authCompanyDTO) throws AuthenticationException {
        var company = this.companyRepository.findByUsername(authCompanyDTO.getUsername())
            .orElseThrow(() -> new RuntimeException("Company not found"));


        // Compare the password from the request with the password from the database
       var passwordMath = this.passwordEncoder.matches(authCompanyDTO.getPassword(), company.getPassword());

        if (!passwordMath) {
            throw new AuthenticationException();
        }

        // Create a token using Algorithm.HMAC256
        Algorithm algorithm = Algorithm.HMAC256(secretKey);
        var token = JWT.create().withIssuer("").withSubject(company.getId().toString()).sign(algorithm);

        return token;
    }
}
