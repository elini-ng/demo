package com.estsoft.demo.blog.service;

import com.estsoft.demo.blog.domain.Article;
import com.estsoft.demo.blog.domain.Comment;
import com.estsoft.demo.blog.dto.AddArticleRequest;
import com.estsoft.demo.blog.dto.CommentRequest;
import com.estsoft.demo.blog.dto.UpdateArticleRequest;
import com.estsoft.demo.blog.repository.BlogRepository;
import com.estsoft.demo.blog.repository.CommentRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class BlogService {

    private final CommentRepository commentRepository;
    private BlogRepository blogRepository;

    public BlogService(BlogRepository blogRepository, CommentRepository commentRepository) {
        this.blogRepository = blogRepository;
        this.commentRepository = commentRepository;
    }

    public Article saveArticle(AddArticleRequest request) {
        return blogRepository.save(request.toEntity());
    }

    public List<Article> getAllArticles() {
        return blogRepository.findAll();
    }

    public Article getArticleById(Long id) {
        Optional<Article> article = blogRepository.findById(id);
        return article.orElseThrow(() -> new IllegalArgumentException("Id not found: " + id));
    }

    public void deleteArticleById(Long id) {
        blogRepository.deleteById(id);
    }

    @Transactional
    public Article updateArticleById(Long id, UpdateArticleRequest request) {
        Article article = blogRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Id not found: " + id));    //throw 500 error

        article.update(request.getTitle(), request.getContent());
        return article;
    }

    public Comment saveComment(Long articleId, CommentRequest request) {
        Article article = getArticleById(articleId);
        return commentRepository.save(new Comment(request.getBody(), article));
    }

    public Comment getComment(Long commentId) {
        return commentRepository.findById(commentId)
                .orElseThrow(() -> new IllegalArgumentException("Comment id " + commentId + " is not found"));
    }

    @Transactional
    public Comment updateComment(Long commentId, CommentRequest request) {
        Comment comment = getComment(commentId);
        return comment.updateBody(request.getBody());
    }

    public void deleteComment(Long commentId) {
        commentRepository.deleteById(commentId);
    }
}
