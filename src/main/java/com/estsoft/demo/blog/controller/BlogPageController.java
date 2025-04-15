package com.estsoft.demo.blog.controller;

import com.estsoft.demo.blog.domain.Article;
import com.estsoft.demo.blog.dto.ArticleViewResponse;
import com.estsoft.demo.blog.service.BlogService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class BlogPageController {

    private final BlogService blogService;

    public BlogPageController(BlogService blogService) {
        this.blogService = blogService;
    }

    @GetMapping("/articles")
    public String getArticles(Model model) {
        List<ArticleViewResponse> articleList = blogService.getAllArticles().stream()
                .map(ArticleViewResponse::new)
                .toList();
        model.addAttribute("articles", articleList);

        return "articleList";
    }

    @GetMapping("/articles/{id}")
    public String getArticle(@PathVariable("id") Long id, Model model) {
        Article article = blogService.getArticleById(id);
        model.addAttribute("article", new ArticleViewResponse(article));

        return "article";
    }

    @GetMapping("/new-article")
    public String newArticle(@RequestParam(value = "id",required = false) Long id, Model model) {
        if (id != null) {
            Article article = blogService.getArticleById(id);
            model.addAttribute("article", new ArticleViewResponse(article));
        } else {
            model.addAttribute("article", new ArticleViewResponse());
        }

        return "newArticle";
    }
}
