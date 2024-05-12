package com.example.taskhub.User;

import com.example.taskhub.Exceptions.ExceptionsDetails;
import com.example.taskhub.Exceptions.UserExceptions.EmailExistingException;
import com.example.taskhub.Exceptions.UserExceptions.UserException;
import com.example.taskhub.Exceptions.UserExceptions.UserNameExistingException;
import com.example.taskhub.Exceptions.UserExceptions.UserNotFoundException;
import com.example.taskhub.User.DTO.CreateUserDTO;
import com.example.taskhub.User.DTO.ResponseUserDTO;
import com.example.taskhub.User.DTO.UpdateUserDTO;
import com.example.taskhub.Util.ServiceResponse;
import com.example.taskhub.project.Project;
import com.example.taskhub.project.ProjectRepository;
import org.apache.coyote.Response;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final ProjectRepository projectRepository;

    @Autowired
    public UserService(UserRepository userRepository, ProjectRepository projectRepository) {
        this.userRepository = userRepository;
        this.projectRepository = projectRepository;
    }

    public ResponseEntity<Object> findAllUsers() {
        ServiceResponse response = new ServiceResponse();

        List<User> users =  this.userRepository.findByDeletedAtIsNull();

        if(users.stream().count() == 0) {
            response.setSuccess(true);
            response.setMessage("No users created");
            response.setData(null);

            return ResponseEntity.ok(response);
        }

        response.setSuccess(true);
        response.setMessage("Successfully found users");
        response.setData(users);

        return ResponseEntity.ok(response);
    }

    public ResponseEntity<Object> findUserById(String id){
        if(id == null || id.isEmpty()){
            throw new UserException("The id is null or empty",
                    new ExceptionsDetails(false, "This oncurred while trying to query the user", null));
        }

        Optional<User> existingUser = Optional.ofNullable(this.userRepository.findByIdAndDeletedAtIsNull(id).orElseThrow(
                () -> new UserNotFoundException("The user does not exists",
                        new ExceptionsDetails(false, "The user you are trying to find does not exist", null)))
        );

        ServiceResponse response = new ServiceResponse();

        response.setSuccess(true);
        response.setMessage("The user was successfully found");
        response.setData(existingUser);

        return ResponseEntity.ok(response);
    }

    public ResponseEntity<Object> createUser(CreateUserDTO user) {
        if(user == null) {
            throw new UserException("The body of the request is null and void",
                    new ExceptionsDetails(false, "An error has occurred with user creation, invalid data", null));
        }

        Optional<User> existingUserName = this.userRepository.findByUsername(user.getUsername());

        if(existingUserName.isPresent()) {
            throw new UserNameExistingException("The user name already exists",
                    new ExceptionsDetails(false, "The user name is already assigned", null));
        }

        Optional<User> existingEmail = this.userRepository.findByEmail(user.getEmail());

        if(existingEmail.isPresent()) {
            throw new EmailExistingException("The email already exists",
                    new ExceptionsDetails(false, "There is already a user with the e-mail address " + user.getEmail(), null));
        }

        ServiceResponse response = new ServiceResponse();
        User newUser = new User();

        BeanUtils.copyProperties(user, newUser);

        response.setSuccess(true);
        response.setMessage("User successfully created");
        response.setData(this.userRepository.save(newUser));

        return ResponseEntity.ok(response);
    }

    public ResponseEntity<Object> updateUser(String userId, UpdateUserDTO user) {
        if(userId == null || userId.isEmpty()) {
            throw new UserException("The id is null or empty",
                    new ExceptionsDetails(false, "This occurred when trying to update the user", null));
        }

        if(user == null) {
            throw new UserException("The request body is null",
                    new ExceptionsDetails(false, "This occurred when trying to update the user", null));
        }

        User existingUser = this.userRepository.findById(userId).orElseThrow(
                () -> new UserNotFoundException("User does not exists",
                        new ExceptionsDetails(false, "The user you are trying to update does not exist", null))
        );

        Optional<User> existingUsername = this.userRepository.findByUsername(user.getUsername());

        if(existingUsername.isPresent()){
            throw new UserException("The user name already exists",
                    new ExceptionsDetails(false, "The user name to which you are trying to update is already assigned", null));
        }

        BeanUtils.copyProperties(user, existingUser, "id");

        ServiceResponse response = new ServiceResponse();

        response.setSuccess(true);
        response.setMessage("User updated successfully");
        response.setData(this.userRepository.save(existingUser));

        return ResponseEntity.ok(response);
    }

    public ResponseEntity<Object> deleteUser(String userId) {
        if(userId == null || userId.isEmpty()) {
            throw new UserException("The id is null or empty",
                    new ExceptionsDetails(false, "This occurred when trying to delete the user", null));
        }

        User existingUser = this.userRepository.findById(userId).orElseThrow(
                () -> new UserNotFoundException("User does not exists",
                        new ExceptionsDetails(false, "The user you are trying to delete does not exist", null))
        );

        existingUser.setDeletedAt(LocalDate.now());

        this.userRepository.save(existingUser);

        return ResponseEntity.noContent().build();
    }
}
