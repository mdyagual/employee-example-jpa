package com.sofka.employeejavaexercise.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

import com.sofka.employeejavaexercise.exceptions.ProjectNoExisteException;
import com.sofka.employeejavaexercise.models.Project;
import com.sofka.employeejavaexercise.repositories.ProjectRepository;

@Service
public class ProjectService {
    @Autowired
    ProjectRepository pRepository;
    
    //Obtener todos los projects
    public ArrayList<Project> getProjects(){
        return (ArrayList<Project>) pRepository.findAll();
    }

    //Obtener empleado por id
    public Optional<Project> getProjectById(Long id){
        return pRepository.findById(id);
    }

    //Crear empleado
    public Project saveProject(Project p){
        return pRepository.saveAndFlush(p);
    }

    //Actualizar empleado
    public boolean updateProject(Project p){
        Optional<Project> op_e = getProjectById(p.getId());  
        try{                       
            if(op_e.isPresent()){                
                pRepository.saveAndFlush(p);
                return true;
            }else{
                throw new ProjectNoExisteException("Project with id: "+p.getId()+" does not exist");
            }
            
        }catch(ProjectNoExisteException emp){   
            System.out.println(emp);         
            return false;
        }
        
    }

    //Eliminar empleado
    public boolean deleteProject(Long id){    
        try{
            Optional<Project> op_e = getProjectById(id);             
            if(op_e.isPresent()){
                pRepository.deleteById(id);
                return true;

            }else{
                throw new ProjectNoExisteException("Project with id: "+id+" does not exist");
            }
            
        }catch(ProjectNoExisteException e){   
            System.out.println(e);         
            return false;
        }
    }

}
