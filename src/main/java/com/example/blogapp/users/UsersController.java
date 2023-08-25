package com.example.blogapp.users;

import com.example.blogapp.users.dtos.CreateUserRequest;
import com.example.blogapp.users.dtos.UserResponse;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/users")
public class UsersController {
    private final UsersService usersService;
    private final ModelMapper modelMapper;

    public UsersController(UsersService usersService, ModelMapper modelMapper) {
        this.usersService = usersService;
        this.modelMapper = modelMapper;
    }

    //user register as well as long for the registered users
    @PostMapping("")
    ResponseEntity<UserResponse> signupUser(@RequestBody CreateUserRequest request) {
        UserEntity savedUser = usersService.createUser(request);
        URI savedUserUri = URI.create("/users" + savedUser.getId());
        return ResponseEntity.created(savedUserUri).body(modelMapper.map(savedUser, UserResponse.class));
    }
    @PostMapping("/login")
    ResponseEntity<UserResponse> loginUser(@RequestBody CreateUserRequest request) {
        UserEntity user = usersService.loginUser(request.getEmail(), request.getPassword());
        return ResponseEntity.ok(modelMapper.map(user, UserResponse.class));
    }

    @ExceptionHandler(UsersService.UserNotFoundException.class)
    ResponseEntity<String> handleUserNotFoundException(UsersService.UserNotFoundException exception) {
        return ResponseEntity.notFound().build();
    }

}
