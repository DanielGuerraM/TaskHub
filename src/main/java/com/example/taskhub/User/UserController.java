package com.example.taskhub.User;

import com.example.taskhub.Exceptions.ExceptionsDetails;
import com.example.taskhub.Exceptions.UserExceptions.UserException;
import com.example.taskhub.User.DTO.CreateUserDTO;
import com.example.taskhub.User.DTO.UpdateUserDTO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/user")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) { this.userService = userService; }

    @GetMapping
    public ResponseEntity<Object> findAllUsers(){
        return this.userService.findAllUsers();
    }

    @GetMapping(path = "{idUser}")
    public ResponseEntity<Object> findUserById(@PathVariable("idUser") String id) {
        return userService.findUserById(id);
    }

    @PostMapping
    public ResponseEntity<Object> createUser(@RequestBody @Valid CreateUserDTO user, BindingResult result) {
        if(result.hasErrors()) {
            throw new UserException("Validation errors",
                    new ExceptionsDetails(false, "User creation validation errors have occurred", result.getAllErrors()));
        }

        return userService.createUser(user);
    }

    @PatchMapping(path = "{idUser}")
    public ResponseEntity<Object> updateUser(@PathVariable("idUser") String id, @RequestBody UpdateUserDTO user) {
        return this.userService.updateUser(id, user);
    }

    @DeleteMapping(path = "{idUser}")
    public ResponseEntity<Object> deleteUser(@PathVariable("idUser") String id) {
        return this.userService.deleteUser(id);
    }
}
