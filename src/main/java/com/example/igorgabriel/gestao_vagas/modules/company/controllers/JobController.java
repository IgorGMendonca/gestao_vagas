package com.example.igorgabriel.gestao_vagas.modules.company.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.igorgabriel.gestao_vagas.modules.company.entities.JobEntity;
import com.example.igorgabriel.gestao_vagas.modules.company.useCases.CreateJobUseCases;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/jobs")
public class JobController {
    
    private CreateJobUseCases createJobUseCases;

    public JobController(CreateJobUseCases createJobUseCases) {
        this.createJobUseCases = createJobUseCases;
    }

    @PostMapping("/")
    public JobEntity create(@Valid @RequestBody JobEntity jobEntity) {
        return this.createJobUseCases.execute(jobEntity);
    }
    
}
