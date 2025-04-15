package com.estsoft.demo.blog.dto;

import com.estsoft.demo.blog.domain.Article;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AddArticleRequest {
    private String title;
    private String content;

    //AddArticleRequest transform into Article (Entity)
    public Article toEntity() {
        return Article.builder()
                .title(title)
                .content(content)
                .build();

        //Same as below code
        //return new Article(title, content);
    }
}
