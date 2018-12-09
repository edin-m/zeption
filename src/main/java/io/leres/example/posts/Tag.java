package io.leres.example.posts;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.leres.entities.Resource;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true, exclude = "posts")
public class Tag extends Resource {

    private String name;

    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE }, mappedBy = "tags")
    private Set<Post> posts = new HashSet<>();

    public Tag(String tag) {
        this.name = tag;
    }

}
