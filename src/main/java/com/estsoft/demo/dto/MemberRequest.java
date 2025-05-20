package com.estsoft.demo.dto;

import com.estsoft.demo.repository.Member;
import com.estsoft.demo.repository.Team;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 *{
 *     "name": "name1"
 *     "team": {
 *         "id":1,
 *         "name":"FC바르셀로나"
 *     }
 *}
 **/


@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MemberRequest {
    private String name;
    private TeamRequest team;

    //DTO -> Entity
    public Member toEntity() {
        return new Member(name, team.toEntity());
    }

    @Getter
    @NoArgsConstructor
    static class TeamRequest {
        private Long id;
        private String name;

        //DTO -> Entity
        public Team toEntity() {
            return new Team(id, name);
        }
    }
}


