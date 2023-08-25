package com.example.blogapp.users;

import com.example.blogapp.users.dtos.CreateUserRequest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
public class UsersServiceTests {
    @Autowired
    UsersService usersService;

    @Test
    void serviceTest() {
        var user = usersService.createUser(new CreateUserRequest("name", "password", "email@gmail.com"));
        Assertions.assertNotNull(user);
        Assertions.assertEquals("name", user.getUsername());
    }
}
