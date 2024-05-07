package com.example.taskhub.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    Optional<User> findByEmail(String email);
    Optional<User> findByUsername(String user_name);
    List<User> findByDeletedAtIsNull();
    Optional<User> findByIdAndDeletedAtIsNull(String userId);
}
