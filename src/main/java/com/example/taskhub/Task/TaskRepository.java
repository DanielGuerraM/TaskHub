package com.example.taskhub.Task;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TaskRepository extends JpaRepository<Task, String> {
    List<Task> findTaskByProject_id(String projectId);

    Task findTaskByProject_idAndId(String idProject, String idTask);

    List<Task> findTasksByProject_idAndDeletedAtIsNull(String idProject);

    Optional<Task> findTaskByIdAndDeletedAtIsNull(String isTask);

    int countTaskByProject_idAndDeletedAtIsNull(String idProject);
}
