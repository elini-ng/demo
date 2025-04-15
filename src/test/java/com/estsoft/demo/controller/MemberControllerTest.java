package com.estsoft.demo.controller;

import com.estsoft.demo.repository.Member;
import com.estsoft.demo.repository.MemberRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest     //Create an application context for testing
@AutoConfigureMockMvc   //Generate and auto-config MockMvc
class MemberControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private MemberRepository memberRepository;

    @BeforeEach
    public void setUp() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
        memberRepository.deleteAll();
    }

    @DisplayName("query member validation")
    @Test
    public void showMembers() throws Exception {
        //given:
        Member member = new Member(null, "name1");
        //Member member = new Member(1L, "name1");     //use when Member class is not using @GeneratedValue(strategy = GenerationType.IDENTITY)
        memberRepository.save(member);

        //when: Call GET /member
        ResultActions resultActions = mockMvc.perform(get("/members"));

        //then:
        resultActions.andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(member.getId()))
                .andExpect(jsonPath("$[0].name").value(member.getName()));

    }
}