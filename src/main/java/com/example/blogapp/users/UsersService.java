package com.example.blogapp.users;

import com.example.blogapp.users.dtos.CreateUserRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsersService {
    private final UsersRepository usersRepository;

    public UsersService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    public UserEntity createUser(CreateUserRequest request) {
        var newUser = UserEntity.builder()
                .username(request.getUsername())
//                .password(request.getPassword())
                .email(request.getEmail())
                .build();
        return usersRepository.save(newUser);
    }

    public UserEntity getUser(String username) {
        return usersRepository.findByUsername(username).orElseThrow(() -> new UserNotFoundException(username));
    }
    public UserEntity getUser(Long authorId) {
        return usersRepository.findById(authorId).orElseThrow(() -> new UserNotFoundException(authorId));
    }

    public UserEntity loginUser(String username, String password) {
        var user = usersRepository.findByUsername(username).orElseThrow(() -> new UserNotFoundException(username));
        //todo password validation
        return user;
    }

    public static class UserNotFoundException extends IllegalArgumentException {
        public UserNotFoundException(String username) {
            super("User " + username + " not found");
        }

        public UserNotFoundException(Long id) {
            super("author with id: " + id + " is not found");
        }
    }
}
