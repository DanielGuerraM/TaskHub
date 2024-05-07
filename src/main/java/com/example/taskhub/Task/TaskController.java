package com.example.taskhub.Task;

import com.example.taskhub.Task.DTO.CreateTaskDTO;
import com.example.taskhub.Task.DTO.UpdateTaskDTO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "api/v1/task")
public class TaskController {
    private final TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping(path = "{idProject}")
    public ResponseEntity<Object> findAllTasksByProjectId(@PathVariable("idProject") String idProject) {
        return this.taskService.findAllTasksByProjectId(idProject);
    }

    @GetMapping(path = "{idProject}/tasks/{idTask}")
    public ResponseEntity<Object> findTaskById(@PathVariable("idProject") String idProject, @PathVariable("idTask") String idTask){
        return this.taskService.findTaskById(idProject, idTask);
    }

    @GetMapping(path = "count/{idProject}")
    public ResponseEntity<Object> countProjectTasks(@PathVariable("idProject") String idProject){
        return this.taskService.countProjectTask(idProject);
    }

    @PostMapping
    public ResponseEntity<Object> createTask(@RequestBody @Valid CreateTaskDTO task, BindingResult result) {
        TaskResponse response = new TaskResponse();

        if(result.hasErrors()) {
            response.setSuccess(false);
            response.setMessage("Validations errors");
            response.setData(result.getAllErrors());

            return ResponseEntity.badRequest().body(response);
        }

        return this.taskService.createTask(task);
    }

    @PatchMapping(path = "{idTask}")
    public ResponseEntity<Object> updateTask(@PathVariable("idTask") String idTask, @RequestBody UpdateTaskDTO task) {
        return this.taskService.updateTask(idTask, task);
    }

    @DeleteMapping(path = "{idTask}")
    public ResponseEntity<Object> deleteTask(@PathVariable("idTask") String idTask) {
        return this.taskService.deleteTask(idTask);
    }
}
