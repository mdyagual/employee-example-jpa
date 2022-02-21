package com.sofka.employeejavaexercise.controllers;

import java.util.ArrayList;
import java.util.Optional;

import com.sofka.employeejavaexercise.models.Employee;
import com.sofka.employeejavaexercise.services.EmployeeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
    @Autowired
    EmployeeService eService;

    //------------------------------------------GET--------------------------------------------
    @GetMapping("/all")
    //http://127.0.0.1:8081/employee/all
    public ArrayList<Employee> obtenerEmpleados(){
        return this.eService.getEmployees();
    }

    @GetMapping(path = "/{id}")
    //http://127.0.0.1:8081/employee/id
    public Optional<Employee> obtenerEmpleadosPorId(@PathVariable("id") Long id){
        return this.eService.getEmployeeById(id);
    }

    //------------------------------------------POST--------------------------------------------
    @PostMapping("/registrar")
    //http://127.0.0.1:8081/employee/registrar 
    public Employee guardarEmpleado(@RequestBody Employee e){      
        return this.eService.saveEmployee(e);            
    }

    //------------------------------------------PUT--------------------------------------------
    @PutMapping(path = "/actualizar")
    //http://127.0.0.1:8081/employee/actualizar  
    public String actualizarEmpleado(@RequestBody Employee e){
        boolean ok = this.eService.updateEmployee(e);
        if(ok){
            return "Usuario actualizado";
        }else{
           
            return "No se pudo actualizar";
        }        

    }

    //----------------------------------------DELETE--------------------------------------------
    @DeleteMapping(path= "/eliminar/{id}")
    //http://127.0.0.1:8081/employee/eliminar/id
    public String eliminarPorId(@PathVariable("id") Long id){        
        boolean ok = this.eService.deleteEmployee(id);
        if(ok){
            return "Usuario eliminado";
        }else{
           
            return "No se pudo eliminar";
        }
    }

    @DeleteMapping("/eliminarTodo")
    //http://127.0.0.1:8081/employee/eliminarTodo
	public ResponseEntity<HttpStatus> eliminarTodosLosEmpleados() {
		return this.eService.deleteAllEmployees();

	}

}
