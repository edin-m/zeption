package io.leres.example.posts;

import io.leres.exceptions.ResourceNotFound;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PostFinder {

    Page<Post> getAllPosts(Pageable pageable);

    Post getPostById(long postId) throws ResourceNotFound;

}
