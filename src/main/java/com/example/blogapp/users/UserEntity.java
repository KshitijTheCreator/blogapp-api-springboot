package com.example.blogapp.users;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;


@Entity(name = "users")
@Getter
@Builder
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED, force = true)
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @NonNull
    @Column(nullable = false)
    private String username;
    @NonNull
    @Column(nullable = false)
    private String email;
    @Nullable
    private String bio;
    @Nullable
    private String image;

}
