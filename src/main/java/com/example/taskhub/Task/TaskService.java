package com.example.taskhub.Task;

import com.example.taskhub.Exceptions.ExceptionsDetails;
import com.example.taskhub.Exceptions.ProjectExceptions.ProjectNotFoundException;
import com.example.taskhub.Exceptions.TaskExceptions.TaskException;
import com.example.taskhub.Exceptions.TaskExceptions.TaskNotFoundException;
import com.example.taskhub.Exceptions.UserExceptions.UserNotFoundException;
import com.example.taskhub.Task.DTO.CreateTaskDTO;
import com.example.taskhub.Task.DTO.ResponseTaskDTO;
import com.example.taskhub.Task.DTO.UpdateTaskDTO;
import com.example.taskhub.Task.Enums.TaskStatus;
import com.example.taskhub.User.User;
import com.example.taskhub.User.UserRepository;
import com.example.taskhub.User.UserResponse;
import com.example.taskhub.Util.Helpers;
import com.example.taskhub.project.Project;
import com.example.taskhub.project.ProjectRepository;
import org.apache.coyote.Response;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TaskService {

    private final TaskRepository taskRepository;
    private final ProjectRepository projectRepository;
    private final UserRepository userRepository;

    @Autowired
    public TaskService(TaskRepository taskRepository, ProjectRepository projectRepository, UserRepository userRepository) {
        this.taskRepository = taskRepository;
        this.projectRepository = projectRepository;
        this.userRepository = userRepository;
    }

    public ResponseEntity<Object> findAllTasksByProjectId(String projectId) {
        UserResponse response = new UserResponse();

        Project existigProject = this.projectRepository.findById(projectId).orElseThrow(
                () -> new ProjectNotFoundException("Project does not exists",
                        new ExceptionsDetails(false, "The project you are trying to find does not exists", null))
        );

        List<Task> tasks = this.taskRepository.findTasksByProject_idAndDeletedAtIsNull(projectId);

        if((long) tasks.size() == 0) {
            response.setSuccess(true);
            response.setMessage("The project has no assigned tasks");
            response.setData(null);

            return ResponseEntity.ok(response);
        }

        ResponseTaskDTO responseTask = new ResponseTaskDTO(existigProject);

        responseTask.setTasks(tasks);

        response.setSuccess(true);
        response.setMessage("Tasks successfully encountered");
        response.setData(responseTask);

        return ResponseEntity.ok(response);
    }

    public ResponseEntity<Object> findTaskById(String idProject, String idTask){
        if(idProject == null || idProject.isEmpty()) {
            throw new TaskException("The id is null or empty",
                    new ExceptionsDetails(false, "An error occurred while querying the task", null));
        }

        if(idTask == null || idTask.isEmpty()) {
            throw new TaskException("The id is null or empty",
                    new ExceptionsDetails(false, "An error occurred while querying the task", null));
        }

        Task existingTask = this.taskRepository.findTaskByIdAndDeletedAtIsNull(idTask).orElseThrow(
                () -> new TaskNotFoundException("Task does not exists",
                        new ExceptionsDetails(false, "The task you are trying to find does not exists", null))
        );

        Project existingProject = this.projectRepository.findById(idProject).orElseThrow(
                () -> new ProjectNotFoundException("Project does not exists",
                        new ExceptionsDetails(false, "The project you are trying to find does not exists", null))
        );

        Task projectTask = this.taskRepository.findTaskByProject_idAndId(idProject, idTask);

        TaskResponse response = new TaskResponse();

        response.setSuccess(true);
        response.setMessage("The project task has been successfully met");
        response.setData(projectTask);


        return ResponseEntity.ok(response);
    }

    public ResponseEntity<Object> countProjectTask(String idProject) {
        if(idProject == null || idProject.isEmpty()) {
            throw new TaskException("The id is null or empty",
                    new ExceptionsDetails(false, "An error occurred while querying the task", null));
        }

        Project existigProject = this.projectRepository.findById(idProject).orElseThrow(
                () -> new ProjectNotFoundException("Project does not exists",
                        new ExceptionsDetails(false, "The project you are trying to find does not exists", null))
        );

        TaskResponse response = new TaskResponse();

        response.setSuccess(true);
        response.setMessage(null);
        response.setData(this.taskRepository.countTaskByProject_idAndDeletedAtIsNull(idProject));

        return ResponseEntity.ok(response);
    }

    public ResponseEntity<Object> createTask(CreateTaskDTO task) {
        if(task == null) {
            throw new TaskException("The request body is null",
                    new ExceptionsDetails(false, "The information submitted is not valid", null));
        }

        Project existingProject = this.projectRepository.findById(task.getProject_id()).orElseThrow(
                () -> new ProjectNotFoundException("The sent id does not exist in the database",
                        new ExceptionsDetails(false, "The project to which you wish to add a new task does not exist", null))
        );

        User existingUser = this.userRepository.findById(task.getUser_id()).orElseThrow(
                () -> new UserNotFoundException("The sent id does not exist in the database",
                        new ExceptionsDetails(false, "The user with which you want to create the task does not exist", null))
        );

      Task newTask = new Task(task, existingUser, existingProject);

      TaskResponse response = new TaskResponse();

      response.setSuccess(true);
      response.setMessage("Task created successfully");
      response.setData(this.taskRepository.save(newTask));

      Helpers helpers = new Helpers(this.taskRepository, this.projectRepository);

      helpers.RecalculateProjectProgress(existingProject, task.getProject_id());

      return ResponseEntity.ok(response);
    }

    public ResponseEntity<Object> updateTask(String idTask, UpdateTaskDTO task){

        if(idTask == null || idTask.isEmpty()) {
            throw new TaskException("The id is null or empty",
                    new ExceptionsDetails(false, "An error has occurred with the parameters sent by the URL", null));
        }

        if(task == null) {
            throw new TaskException("The request body",
                    new ExceptionsDetails(false, "An error has occurred with the parameters sent by the URL", null));
        }

        Task existingTask = this.taskRepository.findTaskByIdAndDeletedAtIsNull(idTask).orElseThrow(
                () -> new TaskNotFoundException("The taks does not exist",
                        new ExceptionsDetails(false, "The task you are trying to update does not exist", null))
        );

        BeanUtils.copyProperties(task, existingTask, "id");

        TaskResponse response = new TaskResponse();

        response.setSuccess(true);
        response.setMessage("Task updated successfully");
        response.setData(this.taskRepository.save(existingTask));

        Helpers helpers = new Helpers(this.taskRepository, this.projectRepository);

        helpers.RecalculateProjectProgress(existingTask.getProject(), existingTask.getProject().getId());

        return ResponseEntity.ok(response);
    }

    public ResponseEntity<Object> deleteTask(String idTask){
        if(idTask == null || idTask.isEmpty()) {
            throw  new TaskException("The task id is null or empty",
                    new ExceptionsDetails(false, "an error has occurred, the URL parameters are invalid", null));
        }

        Task existingTask = this.taskRepository.findTaskByIdAndDeletedAtIsNull(idTask).orElseThrow(
                () -> new TaskNotFoundException("The task does not exist",
                        new ExceptionsDetails(false, "The task you are trying to delete does not exists", null))
        );

        existingTask.setDeletedAt(LocalDate.now());

        this.taskRepository.save(existingTask);

        Helpers helpers = new Helpers(this.taskRepository, this.projectRepository);

        helpers.RecalculateProjectProgress(existingTask.getProject(), existingTask.getProject().getId());

        return ResponseEntity.noContent().build();
    }
}
