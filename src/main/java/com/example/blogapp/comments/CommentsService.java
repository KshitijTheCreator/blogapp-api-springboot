package com.example.blogapp.comments;

public class CommentsService {
    private CommentsRepository commentsRepository;
    public CommentsService(CommentsRepository commentsRepository) {
        this.commentsRepository = commentsRepository;
    }
}
