package com.example.taskhub.project;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProjectRepository extends JpaRepository<Project, String> {
    Optional<Project> findProjectByTitle(String title);
    Optional<Project> findProjectByIdAndDeletedAtIsNull(String idProject);
}
