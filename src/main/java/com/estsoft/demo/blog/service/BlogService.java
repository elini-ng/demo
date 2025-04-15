package com.estsoft.demo.blog.service;

import com.estsoft.demo.blog.domain.Article;
import com.estsoft.demo.blog.dto.AddArticleRequest;
import com.estsoft.demo.blog.dto.UpdateArticleRequest;
import com.estsoft.demo.blog.repository.BlogRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class BlogService {

    private BlogRepository blogRepository;

    public BlogService(BlogRepository blogRepository) {
        this.blogRepository = blogRepository;
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
}
