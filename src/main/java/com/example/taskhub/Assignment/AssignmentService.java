package com.example.taskhub.Assignment;

import com.example.taskhub.Assignment.DTO.AssignmentDTO;
import com.example.taskhub.Assignment.DTO.ResponseAssignmentDTO;
import com.example.taskhub.Assignment.DTO.ResponseUserDTO;
import com.example.taskhub.Assignment.DTO.ResponseUsersDTO;
import com.example.taskhub.User.User;
import com.example.taskhub.User.UserRepository;
import com.example.taskhub.Util.ServiceResponse;
import com.example.taskhub.project.Project;
import com.example.taskhub.project.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AssignmentService {
    private final AssignmentRepository assignmentRespository;
    private final UserRepository userRepository;
    private final ProjectRepository projectRespository;

    @Autowired
    public AssignmentService(AssignmentRepository assignmentRepository, UserRepository userRepository, ProjectRepository projectRespository) {
        this.assignmentRespository = assignmentRepository;
        this.userRepository = userRepository;
        this.projectRespository = projectRespository;
    }

    public ResponseEntity<Object> AssignProject(AssignmentDTO assignment) {
        ServiceResponse response = new ServiceResponse();

        Optional<User> existingUser = this.userRepository.findById(assignment.getUser_id());

        if(existingUser.isEmpty()) {
            response.setSuccess(false);
            response.setMessage("User does not exists");

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }

        Optional<Project> existingProject = this.projectRespository.findById(assignment.getProject_id());

        if(existingProject.isEmpty()) {
            response.setSuccess(false);
            response.setMessage("Project does not exists");

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }

        Optional<Assignment> existingAssignment = this.assignmentRespository.findByProjectIdAndUserId(assignment.getProject_id(), assignment.getUser_id());

        if(existingAssignment.isPresent()) {
            response.setSuccess(false);
            response.setMessage("The user is already assigned to the project");

            return ResponseEntity.badRequest().body(response);
        }

        Assignment newAssignment = new Assignment();

        newAssignment.setUser(existingUser.get());
        newAssignment.setProject(existingProject.get());
        newAssignment.setRole_id(assignment.getRole_id());
        newAssignment.setDate_of_assignment(LocalDate.now());

        response.setSuccess(true);
        response.setMessage("The project was assigned correctly");
        response.setData(this.assignmentRespository.save(newAssignment));

        return ResponseEntity.ok(response);
    }

    public ResponseEntity<Object> getAssignment(int page, int size) {
        ServiceResponse response = new ServiceResponse();

        response.setSuccess(true);
        response.setData(this.assignmentRespository.findAll());

        return ResponseEntity.ok(response);
    }

    public ResponseEntity<Object> getUsersByProjectId(String projectId) {
        ServiceResponse response = new ServiceResponse();

        Optional<Project> existingProject = this.projectRespository.findById(projectId);

        if(existingProject.isEmpty()) {
            response.setSuccess(false);
            response.setMessage("Project does not exists");

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }

        List<Assignment> result = this.assignmentRespository.findByProjectId(projectId);

        ResponseAssignmentDTO ServiceResponse = new ResponseAssignmentDTO();

        List<ResponseUsersDTO> responseUsersDTOList = result.stream().map(assignment -> {
            ResponseUsersDTO responseUsersDTO = new ResponseUsersDTO();

            responseUsersDTO.setNames(assignment.user.getNames());
            responseUsersDTO.setLast_names(assignment.user.getLast_names());
            responseUsersDTO.setUsername(assignment.user.getUsername());
            responseUsersDTO.setId(assignment.user.getId());
            responseUsersDTO.setEmail(assignment.user.getEmail());

            return responseUsersDTO;
        }).collect(Collectors.toList());

        ServiceResponse.setUsers(responseUsersDTOList);
        ServiceResponse.setProject_id(existingProject.get().getId());
        ServiceResponse.setProject_title(existingProject.get().getTitle());
        ServiceResponse.setStatus(existingProject.get().getStatus());

        response.setSuccess(true);
        response.setData(ServiceResponse);

        return ResponseEntity.ok(response);
    }

    public ResponseEntity<Object> removeAssignment(String assignmentId) {
        Optional<Assignment> existingAssignment = this.assignmentRespository.findById(assignmentId);
        ServiceResponse response = new ServiceResponse();

        if(existingAssignment.isEmpty()) {
            response.setSuccess(true);
            response.setMessage("The assignment does not exists");

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }

        this.assignmentRespository.deleteById(assignmentId);

        return ResponseEntity.noContent().build();
    }
}
