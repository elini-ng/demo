package com.estsoft.demo.blog.controller;

import com.estsoft.demo.blog.domain.Article;
import com.estsoft.demo.blog.domain.Comment;
import com.estsoft.demo.blog.dto.*;
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

        List<ArticleResponse> responseBody = articles.stream()
                .map(ArticleResponse::new)
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

    @PostMapping("/api/articles/{articleId}/comments")
    public ResponseEntity<CommentResponse> saveComment (@PathVariable("articleId") Long articleId,
                                                        @RequestBody CommentRequest request) {
        Comment comment = blogService.saveComment(articleId, request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new CommentResponse(comment));
    }

    @GetMapping("/api/comments/{commentId}")
    public ResponseEntity<CommentResponse> getComment(@PathVariable("commentId") Long commentId) {
        Comment comment = blogService.getComment(commentId);
        return ResponseEntity.ok(new CommentResponse(comment));
    }

    @PutMapping("/api/comments/{commentId}")
    public ResponseEntity<CommentResponse> updateComment (@PathVariable("commentId") Long commentId,
                                                          @RequestBody CommentRequest request) {
        Comment comment = blogService.updateComment(commentId, request);
        return ResponseEntity.ok(new CommentResponse(comment));
    }

    @DeleteMapping("/api/comments/{commentId}")
    public ResponseEntity<Void> deleteComment (@PathVariable("commentId") Long commentId) {
        blogService.deleteComment(commentId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/api/articles/{articleId}/comments")
    public ResponseEntity<ArticleCommentResponse> getArticleWithComment (@PathVariable("articleId") Long articleId) {
        Article article = blogService.getArticleById(articleId);
        return ResponseEntity.ok(new ArticleCommentResponse(article));
    }

}
