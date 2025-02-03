package com.example.igorgabriel.gestao_vagas.modules.company.controllers;

import java.util.UUID;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.igorgabriel.gestao_vagas.modules.company.dto.CreateJobDTO;
import com.example.igorgabriel.gestao_vagas.modules.company.entities.JobEntity;
import com.example.igorgabriel.gestao_vagas.modules.company.useCases.CreateJobUseCases;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/comapany/jobs")
public class JobController {

    private CreateJobUseCases createJobUseCases;

    public JobController(CreateJobUseCases createJobUseCases) {
        this.createJobUseCases = createJobUseCases;
    }

    @PostMapping("/")
    @PreAuthorize("hasRole('COMPANY')")
    public JobEntity create(@Valid @RequestBody CreateJobDTO createJobDTO, HttpServletRequest request) {
        var companyId = request.getAttribute("company_id");

        var jobEntity = JobEntity.builder().benefits(createJobDTO.getBenefits())
                .description(createJobDTO.getDescription())
                .level(createJobDTO.getLevel())
                .companyId(UUID.fromString(companyId.toString()))
                .build();

        return this.createJobUseCases.execute(jobEntity);
    }

}
