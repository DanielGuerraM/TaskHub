package com.example.taskhub.Assignment;

import com.example.taskhub.Assignment.DTO.AssignmentDTO;
import com.example.taskhub.Util.ServiceResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/assignment")
public class AssignmentController {
    private final AssignmentService assignmentService;

    @Autowired
    public AssignmentController(AssignmentService assignmentService){
        this.assignmentService = assignmentService;
    }

    @GetMapping
    public ResponseEntity<Object> getAssignments(@RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "50") int size) {
        return assignmentService.getAssignment(page, size);
    }

    @GetMapping(path = "{projectId}")
    public ResponseEntity<Object> getUsersByProjectId(@PathVariable("projectId") String projectId) {
        return assignmentService.getUsersByProjectId(projectId);
    }

    @PostMapping
    public ResponseEntity<Object> assignProject(@RequestBody @Valid AssignmentDTO assignment, BindingResult result) {
        ServiceResponse response = new ServiceResponse();

        if(result.hasErrors()) {
            response.setSuccess(false);
            response.setMessage("Validation error");
            response.setData(result.getAllErrors());

            return ResponseEntity.badRequest().body(response);
        }

        return assignmentService.AssignProject(assignment);
    }

    @DeleteMapping(path = "{assignmentId}")
    public ResponseEntity<Object> removeAssignment(@PathVariable("assignmentId") String assignmentId) {
        return assignmentService.removeAssignment(assignmentId);
    }
}
