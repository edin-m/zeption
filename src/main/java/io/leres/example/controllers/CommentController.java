package io.leres.example.controllers;

import io.leres.example.posts.*;
import io.leres.exceptions.ResourceNotFound;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class CommentController {

    private CommentFinder commentFinder;
    private PostFinder postFinder;
    private CommentCuder commentCuder;

    public CommentController(CommentFinder commentFinder, PostFinder postFinder, CommentCuder commentCuder) {
        this.commentFinder = commentFinder;
        this.postFinder = postFinder;
        this.commentCuder = commentCuder;
    }

    @GetMapping("/posts/{postId}/comments")
    public Page<Comment> getAllCommentsByPostId(@PathVariable("postId") Long postId, Pageable pageable) {
        return commentFinder.getCommentsForPost(postId, pageable);
    }

    @PostMapping("/posts/{postId}/comments")
    public Comment createComment(@PathVariable("postId") Long postId, @RequestBody Comment comment) throws ResourceNotFound {
        Post post = postFinder.getPostById(postId);

        comment.setPost(post);

        return commentCuder.createComment(comment);
    }

    @PutMapping("/comments/{commentId}")
    public Comment updateComment(@PathVariable("postId") Long postId, @PathVariable("commentId") Long commentId,
                                 @RequestBody Comment comment) throws ResourceNotFound {
        return commentCuder.updateComment(commentId, comment);
    }

    @DeleteMapping("/comments/{commentId}")
    public ResponseEntity<?> deleteComment(@PathVariable("commentId") Long commentId) throws ResourceNotFound {
        commentCuder.deleteById(commentId);
        return ResponseEntity.ok().build();
    }
}
