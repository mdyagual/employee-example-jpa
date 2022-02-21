package com.sofka.employeejavaexercise.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sofka.employeejavaexercise.models.Project;
import com.sofka.employeejavaexercise.repositories.ProjectRepository;

@Service
public class ProjectService {
    @Autowired
    ProjectRepository pRepository;
    
}
