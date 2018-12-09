package io.leres.example.posts.repo;

import io.leres.example.posts.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    Page<Comment> findByPostId(long postId, Pageable pageable);

    @Modifying
    @Query("delete Comment c where c.post.id = ?1")
    void deleteInBulkByPostId(long postId);
}
