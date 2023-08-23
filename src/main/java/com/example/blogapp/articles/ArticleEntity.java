package com.example.blogapp.articles;

import com.example.blogapp.users.UserEntity;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

import java.util.Date;

@Entity(name = "articles")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED, force = true)
public class ArticleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @NonNull
    @Column(nullable = false)
    private String title;

    @NonNull
    @Column(nullable = false, unique = true)
    private String slug;

    @Nullable
    private String subtitle;

    @CreatedDate
    private Date createdAt;

    @NonNull
    @Column(nullable = false)
    private String body;

    @ManyToOne
    @JoinColumn(name = "authorId", nullable = false)
    private UserEntity author;



}
