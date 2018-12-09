package io.leres.example.posts;

import io.leres.exceptions.ResourceNotFound;

public interface CommentCuder {

    Comment createComment(Comment comment);

    Comment updateComment(long commentId, Comment example) throws ResourceNotFound;

    void deleteById(long commentId) throws ResourceNotFound;

    void deletePostComments(Post post);
}
