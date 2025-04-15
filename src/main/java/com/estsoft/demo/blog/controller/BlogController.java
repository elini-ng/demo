package com.estsoft.demo.blog.controller;

import com.estsoft.demo.blog.domain.Article;
import com.estsoft.demo.blog.dto.AddArticleRequest;
import com.estsoft.demo.blog.dto.ArticleResponse;
import com.estsoft.demo.blog.dto.UpdateArticleRequest;
import com.estsoft.demo.blog.service.BlogService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BlogController {
    private final BlogService blogService;

    public BlogController(BlogService blogService) {
        this.blogService = blogService;
    }

    //POST /api/articles
    @PostMapping("/api/articles")
    public ResponseEntity<ArticleResponse> saveArticle (@RequestBody AddArticleRequest request) {
        Article savedArticle = blogService.saveArticle(request);

        return ResponseEntity.status(HttpStatus.CREATED)
                .contentType(MediaType.APPLICATION_JSON)
                .body(savedArticle.toDto());
    }

    //GET /api/articles
    @GetMapping("api/articles")
    public ResponseEntity<List<ArticleResponse>> getAllArticles() {
        List<Article> articles = blogService.getAllArticles();

        List<ArticleResponse> responseBody = articles.stream().map(article ->
                new ArticleResponse(article.getId(), article.getTitle(), article.getContent()))
                .toList();

        return ResponseEntity.status(HttpStatus.OK)
                .body(responseBody);
    }

    //GET /api/articles/{id}
    @GetMapping("api/articles/{id}")
    public ResponseEntity<ArticleResponse> getArticle (@PathVariable("id") Long id) {
        Article article = blogService.getArticleById(id);
        return ResponseEntity.ok(article.toDto());
    }

    //DELETE /api/articles/{id}
    @DeleteMapping("api/articles/{id}")
    public ResponseEntity<Void> deleteArticle (@PathVariable("id") Long id) {
        blogService.deleteArticleById(id);
        return ResponseEntity.ok().build();
    }

    //PUT /api/articles/{id}
    @PutMapping("api/articles/{id}")
    public ResponseEntity<ArticleResponse> updateArticle (@PathVariable("id") Long id,
                                                          @RequestBody UpdateArticleRequest request) {
        Article updatedArticle = blogService.updateArticleById(id, request);

        ArticleResponse response = updatedArticle.toDto();
        return ResponseEntity.ok(response);
    }

    //IllegalArgumentException 500x > 4xx
    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleIllegalArgumentException(IllegalArgumentException ex) {
        return ex.getMessage();
    }

}
