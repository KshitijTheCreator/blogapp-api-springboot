package com.example.blogapp.articles;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticlesRepository extends JpaRepository<ArticleEntity, Long> {
}
