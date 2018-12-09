package io.leres.example.controllers;

import io.leres.example.posts.Post;
import io.leres.example.posts.PostCuder;
import io.leres.example.posts.PostFinder;
import io.leres.exceptions.ResourceNotFound;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
public class PostController {

    private PostFinder postFinder;
    private PostCuder postCuder;

    public PostController(PostFinder postFinder, PostCuder postCuder) {
        this.postFinder = postFinder;
        this.postCuder = postCuder;
    }

    @GetMapping("/posts")
    public Page<Post> getAllPosts(Pageable pageable) {
        return postFinder.getAllPosts(pageable);
    }

    @PostMapping("/posts")
    public Post createPost(@RequestBody Post post) {
        return postCuder.createPost(post);
    }

    @PutMapping("/posts/{postId}")
    public Post updatePost(@PathVariable Long postId, @RequestBody Post post) throws ResourceNotFound {
        return postCuder.update(postId, post);
    }

    @PutMapping("/posts/{postId}/tags")
    public Post updatePostTags(@PathVariable Long postId, @RequestBody Set<String> tagNames) throws ResourceNotFound {
        Post post = postFinder.getPostById(postId);
        return postCuder.updateTags(post, tagNames);
    }

    @DeleteMapping("/posts/{postId}")
    public ResponseEntity<?> deletePost(@PathVariable Long postId) throws ResourceNotFound {
        postCuder.removePost(postId);
        return ResponseEntity.ok().build();
    }

}
