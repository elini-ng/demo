package com.estsoft.demo.controller;

import com.estsoft.demo.repository.Member;
import com.estsoft.demo.repository.MemberRepository;
import com.estsoft.demo.repository.Team;
import com.estsoft.demo.repository.TeamRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Disabled
@SpringBootTest     //Create an application context for testing
@AutoConfigureMockMvc   //Generate and auto-config MockMvc
class MemberControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private TeamRepository teamRepository;

    @BeforeEach
    public void setUp() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
        memberRepository.deleteAll();
    }

    @DisplayName("query member validation")
    @Test
    public void showMembers() throws Exception {
        //given:
        Team team = teamRepository.findById(1L).orElseGet(() -> new Team());
        Member member = new Member("name1", team);
        //Member member = new Member(1L, "name1");     //use when Member class is not using @GeneratedValue(strategy = GenerationType.IDENTITY)
        memberRepository.save(member);

        //when: Call GET /member
        ResultActions resultActions = mockMvc.perform(get("/members"));

        //then: after API is successfully run
        resultActions.andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(member.getId()))
                .andExpect(jsonPath("$[0].name").value(member.getName()));

    }

    //GET /members/{id} - get single member data test
    @Test
    public void showOneMember() throws Exception {
        //given: create a new member
        Team team = teamRepository.findById(1L).orElseGet(Team::new);
        Member member = new Member("name2", team);
        Member savedMember =memberRepository.save(member);

        //when
        ResultActions resultActions = mockMvc.perform(get("/members/{id}", savedMember.getId()));

        //then
        resultActions.andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(savedMember.getId()))
                .andExpect(jsonPath("$.name").value("name2"));
    }

    //POST /members
    @Test
    public void saveMember() throws Exception {
        //given
        String content = """
                {
                  "name": "Ron",
                  "team": {
                    "id": 1,
                    "name": "FC바르셀로나"
                  }
                }
                """;

        //when
        ResultActions resultActions = mockMvc.perform(post("/members")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content));

        //then
        resultActions.andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Ron"))
                .andExpect(jsonPath("$.teamDTO.teamId").value(1))
                .andExpect(jsonPath("$.teamDTO.name").value("FC바르셀로나"));

    }
}