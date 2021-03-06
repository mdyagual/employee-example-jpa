package com.sofka.employeejavaexercise.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

import com.sofka.employeejavaexercise.exceptions.EmployeeNoExisteException;
import com.sofka.employeejavaexercise.models.Employee;
import com.sofka.employeejavaexercise.repositories.EmployeeRepository;

@Service
public class EmployeeService {
    @Autowired
    EmployeeRepository eRepository;

    //Obtener todos los empleados
    public ArrayList<Employee> getEmployees(){
        return (ArrayList<Employee>) eRepository.findAll();
    }

    //Obtener empleado por id
    public Optional<Employee> getEmployeeById(Long id){
        return eRepository.findById(id);
    }

    //Crear empleado
    public Employee saveEmployee(Employee e){
        return eRepository.saveAndFlush(e);
    }

    //Actualizar empleado
    public boolean updateEmployee(Employee e){
        Optional<Employee> op_e = getEmployeeById(e.getId());  
        try{                       
            if(op_e.isPresent()){                
                eRepository.saveAndFlush(e);
                return true;
            }else{
                throw new EmployeeNoExisteException("Employee with id: "+e.getId()+" does not exist");
            }
            
        }catch(EmployeeNoExisteException emp){   
            System.out.println(emp);         
            return false;
        }
        
    }

    //Eliminar empleado
    public boolean deleteEmployee(Long id){    
        try{
            Optional<Employee> op_e = getEmployeeById(id);             
            if(op_e.isPresent()){
                eRepository.deleteById(id);
                return true;

            }else{
                throw new EmployeeNoExisteException("Employee with id: "+id+" does not exist");
            }
            
        }catch(EmployeeNoExisteException e){   
            System.out.println(e);         
            return false;
        }
    }

    //Eliminar todos los empleados
    public ResponseEntity<HttpStatus> deleteAllEmployees() {
		try {
			eRepository.deleteAll();
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
		}

	}

    

}
