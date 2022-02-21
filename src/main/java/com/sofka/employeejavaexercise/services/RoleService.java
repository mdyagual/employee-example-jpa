package com.sofka.employeejavaexercise.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sofka.employeejavaexercise.models.Role;
import com.sofka.employeejavaexercise.repositories.RoleRepository;

@Service
public class RoleService {
    @Autowired
    RoleRepository rRepository;
}
