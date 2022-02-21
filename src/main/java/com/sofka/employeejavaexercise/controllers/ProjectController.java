package com.sofka.employeejavaexercise.controllers;

import java.util.ArrayList;
import java.util.Optional;

import com.sofka.employeejavaexercise.models.Project;
import com.sofka.employeejavaexercise.services.ProjectService;

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
@RequestMapping("/project")
public class ProjectController {
    @Autowired
    ProjectService pService;

    //------------------------------------------GET--------------------------------------------
    @GetMapping("/all")
    //http://127.0.0.1:8081/project/all
    public ArrayList<Project> obtenerProyectos(){
        return this.pService.getProjects();
    }

    @GetMapping(path = "/{id}")
    //http://127.0.0.1:8081/project/id
    public Optional<Project> obtenerProyectoPorId(@PathVariable("id") Long id){
        return this.pService.getProjectById(id);
    }

    //------------------------------------------POST--------------------------------------------
    @PostMapping("/registrar")
    //http://127.0.0.1:8081/project/registrar 
    public Project guardarProyecto(@RequestBody Project p){      
        return this.pService.saveProject(p);            
    }

    //------------------------------------------PUT--------------------------------------------
    @PutMapping(path = "/actualizar")
    //http://127.0.0.1:8081/Project/actualizar  
    public String actualizarProyecto(@RequestBody Project p){
        boolean ok = this.pService.updateProject(p);
        if(ok){
            return "Usuario actualizado";
        }else{
           
            return "No se pudo actualizar";
        }        

    }

    //----------------------------------------DELETE--------------------------------------------
    @DeleteMapping(path= "/eliminar/{id}")
    //http://127.0.0.1:8081/Project/eliminar/id
    public String eliminarPorId(@PathVariable("id") Long id){        
        boolean ok = this.pService.deleteProject(id);
        if(ok){
            return "Usuario eliminado";
        }else{
           
            return "No se pudo eliminar";
        }
    }

    @DeleteMapping("/eliminarTodo")
    //http://127.0.0.1:8081/Project/eliminarTodo
	public ResponseEntity<HttpStatus> eliminarTodosLosProyectos() {
		return this.pService.deleteAllProjects();

	}

}

