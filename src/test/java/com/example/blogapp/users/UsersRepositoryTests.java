package com.example.blogapp.users;

import com.example.blogapp.articles.ArticleEntity;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

@DataJpaTest
@ActiveProfiles("test")
public class UsersRepositoryTests {
    @Autowired
    private UsersRepository usersRepository;

    @Test
    @Order(1) // used to define in which order the tests will be executed
    void db_create_test() {
        var user = UserEntity.builder().username("name").email("ksj@gmail.com").build();

        usersRepository.save(user);
    }
}
