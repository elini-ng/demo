package com.estsoft.demo.service;

import com.estsoft.demo.dto.PostContent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Slf4j
@Service
public class ExternalService {
    public void call() {
        String url = "https://jsonplaceholder.typicode.com/posts";
        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<List<PostContent>> response = restTemplate.exchange(url, HttpMethod.GET, null, new ParameterizedTypeReference<>(){});

        log.info("code: {}", response.getStatusCode());
        List<PostContent> postContents = response.getBody();
        log.info("post content: {}", postContents);
    }
}
