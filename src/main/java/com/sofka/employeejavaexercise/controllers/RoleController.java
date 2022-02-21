package com.sofka.employeejavaexercise.controllers;

import java.util.ArrayList;
import java.util.Optional;

import com.sofka.employeejavaexercise.models.Role;
import com.sofka.employeejavaexercise.services.RoleService;

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
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/role")
public class RoleController {
    @Autowired
    RoleService rService;
    //------------------------------------------GET--------------------------------------------
    @GetMapping("/all")
    //http://127.0.0.1:8081/role/all
    public ArrayList<Role> obtenerProyectos(){
        return this.rService.getRoles();
    }

    @GetMapping(path = "/{id}")
    //http://127.0.0.1:8081/role/id
    public Optional<Role> obtenerProyectoPorId(@PathVariable("id") Long id){
        return this.rService.getRoleById(id);
    }

    //------------------------------------------POST--------------------------------------------
    @PostMapping("/registrar")
    //http://127.0.0.1:8081/role/registrar 
    public Role guardarProyecto(@RequestBody Role p){      
        return this.rService.saveRole(p);            
    }

    //------------------------------------------PUT--------------------------------------------
    @PutMapping(path = "/actualizar")
    //http://127.0.0.1:8081/role/actualizar  
    public String actualizarProyecto(@RequestBody Role p){
        boolean ok = this.rService.updateRole(p);
        if(ok){
            return "Usuario actualizado";
        }else{
           
            return "No se pudo actualizar";
        }        

    }

    //----------------------------------------DELETE--------------------------------------------
    @DeleteMapping(path= "/eliminar/{id}")
    //http://127.0.0.1:8081/role/eliminar/id
    public String eliminarPorId(@PathVariable("id") Long id){        
        boolean ok = this.rService.deleteRole(id);
        if(ok){
            return "Usuario eliminado";
        }else{
           
            return "No se pudo eliminar";
        }
    }

    @DeleteMapping("/eliminarTodo")
    //http://127.0.0.1:8081/Role/eliminarTodo
	public ResponseEntity<HttpStatus> eliminarTodosLosRoles() {
		return this.rService.deleteAllRoles();

	}
}
