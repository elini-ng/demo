package com.estsoft.demo.blog.dto;

import com.estsoft.demo.blog.domain.Article;
import com.estsoft.demo.blog.domain.Comment;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@NoArgsConstructor
public class ArticleCommentResponse {
    private Long articleId;
    private String title;
    private String content;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdAt;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updatedAt;
    private List<CommentWithoutArticleResponse> comments;

    public ArticleCommentResponse(Article article) {
        this.articleId = article.getId();
        this.title = article.getTitle();
        this.content = article.getContent();
        this.createdAt = article.getCreatedAt();
        this.updatedAt = article.getUpdatedAt();

        List<Comment> commentList = article.getCommentList();
        this.comments = commentList.stream()
                .map(CommentWithoutArticleResponse::new)
                .toList();
    }
}
