package com.estsoft.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PostContent {
    private int userId;
    private int id;
    private String title;
    private String body;

    @Override
    public String toString() {
        return id + ", " + title + "\n";
    }
}
