package com.example.taskhub.Assignment;

import com.example.taskhub.User.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AssignmentRepository extends JpaRepository<Assignment, String> {
    Optional<Assignment> findByProjectIdAndUserId(String projectId, String UserId);
    List<Assignment> findByProjectId(String projectId);
    List<Assignment> findByUserId(String userId);
}
