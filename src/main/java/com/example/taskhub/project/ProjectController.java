package com.example.taskhub.project;

import com.example.taskhub.Util.ServiceResponse;
import com.example.taskhub.project.DTO.CreateProjectDTO;
import com.example.taskhub.project.DTO.UpdateProjectDTO;
import jakarta.validation.Valid;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping(path = "api/v1/project")
public class ProjectController {

    private final ProjectService projectService;

    @Autowired
    public ProjectController(ProjectService projectService) { this.projectService = projectService; }

    @GetMapping
    public Page<Project> getProjects(@RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "50") int size) {
        return projectService.getProjects(page, size);
    }

    @PostMapping
    public ResponseEntity<Object> createProject(@RequestBody @Valid CreateProjectDTO project, BindingResult result) {
        ServiceResponse response = new ServiceResponse();

        if(result.hasErrors()) {
            response.setSuccess(false);
            response.setMessage("Validate error");
            response.setData(result.getAllErrors());
            return ResponseEntity.badRequest().body(response);
        }

        return projectService.createProject(project);
    }

    @PutMapping(path = "{idProject}")
    public ResponseEntity<Object> updateProject(@PathVariable("idProject") String idProject, @RequestBody UpdateProjectDTO project) {
        return projectService.updateProject(idProject, project);
    }
}
