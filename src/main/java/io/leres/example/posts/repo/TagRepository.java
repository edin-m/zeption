package io.leres.example.posts.repo;

import io.leres.example.posts.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TagRepository extends JpaRepository<Tag, Long> {

    Tag findByName(String name);

}
