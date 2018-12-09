package io.leres.example.posts.services;

import io.leres.example.posts.CommentCuder;
import io.leres.example.posts.Post;
import io.leres.example.posts.Tag;
import io.leres.example.posts.TagCuder;
import io.leres.example.posts.repo.PostRepository;
import io.leres.exceptions.ResourceNotFound;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@Slf4j
public class PostServiceImpl implements PostService {

    private PostRepository postRepository;
    private TagCuder tagCuder;
    private CommentCuder commentCuder;

    public PostServiceImpl(PostRepository postRepository, TagCuder tagCuder, CommentCuder commentCuder) {
        this.postRepository = postRepository;
        this.tagCuder = tagCuder;
        this.commentCuder = commentCuder;
    }

    @Override
    public Post createPost(Post post) {
        return postRepository.save(post);
    }

    @Override
    public Page<Post> getAllPosts(Pageable pageable) {
        return postRepository.findAll(pageable);
    }

    @Override
    public Post getPostById(long postId) throws ResourceNotFound {
        return postRepository.findById(postId).orElseThrow(() -> new ResourceNotFound(postId));
    }

    @Override
    public void removePost(long postId) throws ResourceNotFound {
        Post post = getPostById(postId);
        commentCuder.deletePostComments(post);
        postRepository.delete(post);
    }

    @Override
    public Post update(long postId, Post example) throws ResourceNotFound {
        Post post = getPostById(postId);

        post.setPost(example.getPost());

        return postRepository.save(post);
    }

    @Override
    public Post updateTags(Post post, Set<String> tagNames) {
        Set<Tag> tags = tagCuder.getOrCreateTags(tagNames);
        post.setTags(tags);
        return postRepository.save(post);
    }
}
