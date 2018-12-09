package io.leres.example.posts;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CommentFinder {

    Page<Comment> getCommentsForPost(long postId, Pageable pageable);

}
