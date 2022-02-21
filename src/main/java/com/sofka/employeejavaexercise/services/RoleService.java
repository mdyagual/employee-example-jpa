package com.sofka.employeejavaexercise.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

import com.sofka.employeejavaexercise.exceptions.RoleNoExisteException;
import com.sofka.employeejavaexercise.models.Role;
import com.sofka.employeejavaexercise.repositories.RoleRepository;

@Service
public class RoleService {
    @Autowired
    RoleRepository rRepository;

    //Obtener todos los roles
    public ArrayList<Role> getRoles(){
        return (ArrayList<Role>) rRepository.findAll();
    }

    //Obtener rol por id
    public Optional<Role> getRoleById(Long id){
        return rRepository.findById(id);
    }

    //Crear rol
    public Role saveEmployee(Role r){
        return rRepository.saveAndFlush(r);
    }

    //Actualizar rol
    public boolean updateEmployee(Role r){
        Optional<Role> op_r = getRoleById(r.getId());  
        try{                       
            if(op_r.isPresent()){                
                rRepository.saveAndFlush(r);
                return true;
            }else{
                throw new RoleNoExisteException("Role with id: "+r.getId()+" does not exist");
            }
            
        }catch(RoleNoExisteException rol){   
            System.out.println(rol);         
            return false;
        }
        
    }

    //Eliminar rol
    public boolean deleteRole(Long id){    
        try{
            Optional<Role> op_r = getRoleById(id);             
            if(op_r.isPresent()){
                rRepository.deleteById(id);
                return true;

            }else{
                throw new RoleNoExisteException("Role with id: "+id+" does not exist");
            }
            
        }catch(RoleNoExisteException rol){   
            System.out.println(rol);         
            return false;
        }
    }

    
}
