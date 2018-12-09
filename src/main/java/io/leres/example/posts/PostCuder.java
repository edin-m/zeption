package io.leres.example.posts;

import io.leres.exceptions.ResourceNotFound;

import java.util.Set;

public interface PostCuder {

    Post createPost(Post post);

    void removePost(long postId) throws ResourceNotFound;

    Post update(long postId, Post example) throws ResourceNotFound;

    Post updateTags(Post post, Set<String> tagNames);
}
