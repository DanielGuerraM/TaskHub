package com.example.taskhub.project;

import com.example.taskhub.User.User;
import com.example.taskhub.User.UserRepository;
import com.example.taskhub.Util.ServiceResponse;
import com.example.taskhub.project.DTO.CreateProjectDTO;
import com.example.taskhub.project.DTO.UpdateProjectDTO;
import com.example.taskhub.project.enums.ProjectStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProjectService {

    private final ProjectRepository projectRepository;
    private final UserRepository userRepository;

    @Autowired
    public ProjectService(ProjectRepository projectRepository, UserRepository userRepository) {
        this.projectRepository = projectRepository;
        this.userRepository = userRepository;
    }

    public Page<Project> getProjects(int page, int size) {
        PageRequest pageRequest = PageRequest.of(page - 1 , size);

        return this.projectRepository.findAll(pageRequest);
    }

    public ResponseEntity<Object> createProject(CreateProjectDTO project) {
        Optional<Project> existingProject = projectRepository.findProjectByTitle(project.getTitle());
        ServiceResponse response = new ServiceResponse();

        if(existingProject.isPresent()) {
            response.setSuccess(false);
            response.setMessage("The project you are trying to create already exists");

            return ResponseEntity.badRequest().body(response);
        }

        Optional<User> existingUser = userRepository.findById(project.getCreated_by());

        if(existingUser.isEmpty()) {
            response.setSuccess(false);
            response.setMessage("The user with which you want to register the order does not exist.");

            return ResponseEntity.badRequest().body(response);
        }

        Project newProject = new Project();

        newProject.setTitle(project.getTitle());
        newProject.setDescription(project.getDescription());
        newProject.setStatus(ProjectStatus.setProjectStatus(ProjectStatus.projectStatusList.TO_START));
        newProject.setStart_date(project.getStart_date());
        newProject.setFinish_date(project.getFinish_date());
        newProject.setCreated_by(project.getCreated_by());
        newProject.setProgress(0.00F);


        response.setSuccess(true);
        response.setMessage("The project was created successfully");
        response.setData(this.projectRepository.save(newProject));

        return ResponseEntity.ok(response);
    }

    public ResponseEntity<Object> updateProject(String projectId, UpdateProjectDTO project) {
        ServiceResponse response = new ServiceResponse();
        Optional<Project> existingProject = projectRepository.findById(projectId);

        if(existingProject.isEmpty()) {
            response.setSuccess(false);
            response.setMessage("The project you are trying to update does not exist");

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }

        Optional<User> existingUser = userRepository.findById(project.getUpdated_by());

        if(existingUser.isEmpty()) {
            response.setSuccess(false);
            response.setMessage("The user with which you are trying to update the record does not exist");

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
        else {
//            existingProject.get().set

            response.setSuccess(true);
            response.setData(existingProject);

            return ResponseEntity.ok(response);
        }
    }
}
