package com.example.taskhub.Util;

import com.example.taskhub.Task.Enums.TaskStatus;
import com.example.taskhub.Task.Task;
import com.example.taskhub.Task.TaskRepository;
import com.example.taskhub.project.Project;
import com.example.taskhub.project.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Console;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class Helpers {

    private final TaskRepository taskRepository;
    private final ProjectRepository projectRepository;

    @Autowired
    public Helpers(TaskRepository taskRepository, ProjectRepository projectRepository) {
        this.taskRepository = taskRepository;
        this.projectRepository = projectRepository;
    }
    public boolean RecalculateProjectProgress(Project project, String projectId) {
        if(projectId == null || projectId.isEmpty()){
            return false;
        }

        List<Task> projectTasks = this.taskRepository.findTasksByProject_idAndDeletedAtIsNull(projectId);

        Float value = (float) 100 / projectTasks.size();
        AtomicInteger count = new AtomicInteger();

        projectTasks.forEach(task -> {
            if(task.getStatus().equals(TaskStatus.setTaskStatus(TaskStatus.TaskStatusList.COMPLETED))
            || task.getStatus().equals(TaskStatus.setTaskStatus(TaskStatus.TaskStatusList.CANCELLED))) {
                count.getAndIncrement();
            }
        });

        project.setProgress(value * count.intValue());

        this.projectRepository.save(project);

        return true;
    }
}
