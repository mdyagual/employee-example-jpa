package com.sofka.employeejavaexercise.repositories;

import com.sofka.employeejavaexercise.models.Role;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role,Long>{
    Role findByName(String name);

}
