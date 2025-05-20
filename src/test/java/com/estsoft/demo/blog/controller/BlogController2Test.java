package com.estsoft.demo.blog.controller;

import com.estsoft.demo.blog.domain.Article;
import com.estsoft.demo.blog.repository.BlogRepository;
import com.estsoft.demo.blog.service.BlogService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import java.util.List;

@ExtendWith(MockitoExtension.class)
class BlogController2Test {
    @InjectMocks
    private BlogController blogController;

    @Mock
    private BlogService blogService;

    private MockMvc mockMvc;

    @BeforeEach
    public void setUp() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(blogController).build();
    }

    // POST /api/articles
    @Test
    public void testSaveArticle() throws Exception {
        //given:
        String jsonContent  = """
                {
                    "title": "title1",
                    "content": "content1"
                }
                """;
        Mockito.when(blogService.saveArticle(any()))
                .thenReturn(new Article("title1", "content1"));


        //when:
        ResultActions resultActions = mockMvc.perform(post("/api/articles")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonContent));

        //then:
        resultActions.andExpect(status().isCreated())
                .andExpect(jsonPath("$.title").value("title1"))
                .andExpect(jsonPath("$.content").value("content1"));
    }

    // GET /api/articles Test viewing all blog posts
    @Test
    public void testGetAllArticles() throws Exception {
        //given:
        Article article = new Article("title1", "content1");
        List<Article> articleList = List.of(article);

        Mockito.when(blogService.getAllArticles())
                .thenReturn(articleList);

        //when:
        ResultActions resultActions = mockMvc.perform(get("/api/articles"));

        //then:
        resultActions.andExpect(status().isOk())
                .andExpect(jsonPath("$[0].title").value("title1"))
                .andExpect(jsonPath("$[0].content").value("content1"));
    }

    // GET /api/articles/{id} Test viewing a single blog post
    @Test
    public void testGetArticleById() throws Exception {
        //given:
        Article article = new Article("title1", "content1");

        Mockito.when(blogService.getArticleById(1L))
                .thenReturn(article);

        //when:
        ResultActions resultActions = mockMvc.perform(get("/api/articles/{id}", 1L));

        //then:
        resultActions.andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("title1"))
                .andExpect(jsonPath("$.content").value("content1"));
    }
}