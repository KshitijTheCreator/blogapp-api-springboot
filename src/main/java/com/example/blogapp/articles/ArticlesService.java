package com.example.blogapp.articles;

import com.example.blogapp.articles.dtos.CreateArticleRequest;
import com.example.blogapp.articles.dtos.UpdateArticleRequest;
import com.example.blogapp.users.UsersRepository;
import com.example.blogapp.users.UsersService;
public class ArticlesService {
    private ArticlesRepository articlesRepository;
    private UsersRepository usersRepository;

    public ArticlesService(ArticlesRepository articlesRepository, UsersRepository usersRepository) {
        this.articlesRepository = articlesRepository;
        this.usersRepository = usersRepository;
    }
    //create article service
    //get all the articles
    //get articles via slug

    public ArticleEntity createArticle(CreateArticleRequest request, Long authorId) {
        var author = usersRepository.findById(authorId).orElseThrow(() -> new UsersService.UserNotFoundException(authorId));

        return articlesRepository.save(ArticleEntity.builder()
                        .title(request.getTitle())
                        .author(author)
                        .slug(request.getTitle().toLowerCase().replaceAll("\\s+", "-"))
                        .body(request.getBody())
                .build()
        );
    }
    public ArticleEntity updateArticle(UpdateArticleRequest request, Long authorId) {
        var article = articlesRepository.findById(authorId).orElseThrow(()-> new ArticleNotFoundException(authorId));
        if(request.getTitle()!= null) {
            article.setTitle(request.getTitle());
            article.setSlug(request.getTitle().toLowerCase().replaceAll("\\s+", "-"));
        }
        if(request.getSubtitle()!= null) {
            article.setBody(request.getSubtitle());
        }
        if(request.getBody()!= null) {
            article.setBody(request.getBody());
        }
        return articlesRepository.save(article);
    }

    public Iterable<ArticleEntity> getAllArticles() {
        return articlesRepository.findAll();
    }

    public ArticleEntity getArticleBySlug(String slug) {
        var article = articlesRepository.getArticleBySlug(slug);
        if(article == null) {
            throw new ArticleNotFoundException(slug);
        }
        return article;
    }
    static class ArticleNotFoundException extends IllegalArgumentException{
        public ArticleNotFoundException(String slug) {
            super("article with "+slug+" could not be found");
        }
        public ArticleNotFoundException(Long id) {
            super("article with "+id+" could not be found");
        }
    }

}
