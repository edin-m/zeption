package io.leres.example.controllers.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
public class PostTagModel {
    private long postId;
    private Set<String> tags;
}
