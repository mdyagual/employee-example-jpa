package com.sofka.employeejavaexercise.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sofka.employeejavaexercise.models.Employee;
import com.sofka.employeejavaexercise.repositories.EmployeeRepository;

@Service
public class EmployeeService {
    @Autowired
    EmployeeRepository eRepository;
}
