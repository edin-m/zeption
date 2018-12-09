package io.leres.example.posts;

import io.leres.entities.Resource;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true, exclude = { "comments", "tags" })
@ToString(callSuper = true, exclude = { "comments", "tags" })
public class Post extends Resource {

    private String post;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "post", orphanRemoval = true)
    private Set<Comment> comments = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = { CascadeType.MERGE, CascadeType.PERSIST }
    )
    @JoinTable(name = "post_tags",
            joinColumns = @JoinColumn(name = "post_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id")
    )
    private Set<Tag> tags = new HashSet<>();

    public Post(String post) {
        this.post = post;
    }
}
