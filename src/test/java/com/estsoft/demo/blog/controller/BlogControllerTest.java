package com.estsoft.demo.blog.controller;

import com.estsoft.demo.blog.domain.Article;
import com.estsoft.demo.blog.dto.AddArticleRequest;
import com.estsoft.demo.blog.dto.UpdateArticleRequest;
import com.estsoft.demo.blog.repository.BlogRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Disabled
@SpringBootTest
@AutoConfigureMockMvc
class BlogControllerTest {
    @Autowired
    private WebApplicationContext webApplicationContext;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private BlogRepository blogRepository;
    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        blogRepository.deleteAll();
    }

    @Test
    public void saveArticle() throws Exception {
        //given:
        AddArticleRequest request = new AddArticleRequest("blog title","blog content");
        String requestBody = objectMapper.writeValueAsString(request);

        //when: POST /api/articles
        ResultActions resultActions = mockMvc.perform(post("/api/articles")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody)
        );

        //then:
        resultActions.andExpect(status().isCreated())
                .andExpect(jsonPath("$.title").value(request.getTitle()))
                .andExpect(jsonPath("$.content").value(request.getContent()));

    }

    @Test
    public void getAllArticles() throws Exception {
        //given: save an article
        Article savedArticle = Article.builder()
                .title("title to be saved")
                .content("content to be saved")
                .build();
        blogRepository.save(savedArticle);

        //when: GET /api/articles
        ResultActions resultActions = mockMvc.perform(get("/api/articles"));

        //then: compare given value and result value
        resultActions.andExpect(status().isOk())
                .andExpect(jsonPath("$[0].title").value(savedArticle.getTitle()))
                .andExpect(jsonPath("$[0].content").value(savedArticle.getContent()))
        ;
    }

    @Test
    public void getArticleById() throws Exception {
        //given: save an article, id
        Article savedArticle = Article.builder()
                .title("title to be saved")
                .content("content to be saved")
                .build();
        blogRepository.save(savedArticle);
        Long id = savedArticle.getId();

        //when: GET /api/article/{id}
        ResultActions resultActions = mockMvc.perform(get("/api/articles/{id}", id));

        //then: validation - compare given value and result value, check status code
        resultActions.andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(savedArticle.getId()))
                .andExpect(jsonPath("$.title").value(savedArticle.getTitle()))
                .andExpect(jsonPath("$.content").value(savedArticle.getContent()))
        ;
    }

    @Test
    public void deleteArticleById() throws Exception {
        //given: save an article, id
        Article savedArticle = blogRepository.save(new Article("new title", "new content"));
        Long id = savedArticle.getId();

        //when: DELETE /api/article/{id}
        ResultActions resultActions = mockMvc.perform(delete("/api/articles/{id}", id));

        //then: status is ok, article list is empty
        resultActions.andExpect(status().isOk());

        List<Article> articleList = blogRepository.findAll();
        //two ways to check
        Assertions.assertThat(articleList).isEmpty();                   //1. list isEmpty
        Assertions.assertThat(articleList.size()).isEqualTo(0); //2. list size is 0
    }

    @Test
    public void updateArticleById() throws Exception {
        //given: save article, id, new data
        Article savedArticle = blogRepository.save(new Article("blog title", "blog content"));
        Long id = savedArticle.getId();

        UpdateArticleRequest request = new UpdateArticleRequest("new title", "new content");
        String requestBody = objectMapper.writeValueAsString(request);  //Serialization

        //when: PUT /api/article/{id}
        ResultActions resultActions = mockMvc.perform(put("/api/articles/{id}", id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody));

        //then: status is ok, compare new data with current data
        resultActions.andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value(request.getTitle()))
                .andExpect(jsonPath("$.content").value(request.getContent()));

        Article modifiedArticle = blogRepository.findById(id).orElseThrow();
        Assertions.assertThat(modifiedArticle.getTitle()).isEqualTo(request.getTitle());
        Assertions.assertThat(modifiedArticle.getContent()).isEqualTo(request.getContent());
    }

    @Test
    public void updateArticleException() throws Exception {
        //given: id, new data
        Long id = 1L;

        UpdateArticleRequest request = new UpdateArticleRequest("new title", "new content");
        String requestBody = objectMapper.writeValueAsString(request);

        //when: PUT /api/article/{id}
        ResultActions resultActions = mockMvc.perform(put("/api/articles/{id}", id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody));

        //then: status is 404, compare new data with current data
        resultActions.andExpect(status().isNotFound());
    }
}