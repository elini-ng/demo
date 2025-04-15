package com.estsoft.demo.repository;

import jakarta.persistence.*;
import lombok.Getter;
import java.util.*;

@Getter
@Entity
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="team_id", updatable = false, unique = true)
    private Long id;

    @Column
    private String name;

    @OneToMany(mappedBy = "team")
    private List<Member> members = new ArrayList<>();
}
