package io.leres.scenario;

import io.leres.IntegrationTests;
import io.leres.example.posts.Comment;
import io.leres.example.posts.Post;
import io.leres.example.posts.repo.CommentRepository;
import io.leres.example.posts.repo.PostRepository;
import io.leres.example.posts.repo.TagRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

@Category(IntegrationTests.class)
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class PostScenarioIntegrationTest {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private CommentRepository commentRepository;

//    @Autowired
//    private PostService postService;

    @Autowired
    private TagRepository tagRepository;

    @Autowired
    private EntityManager entityManager;
//
    @Test
//    @Transactional
    public void testPosts() {
//        Post post = new Post("post");
//        post = postRepository.save(post);
//
//        Comment comment = new Comment("comment");
//        comment.setPost(post);
//        comment = commentRepository.save(comment);
//
//        postService.printComments(post.getId());
//
//        ///
//
//        post = postRepository.findById(post.getId()).get();
//
//        Tag tag1 = new Tag("Spring Boot");
//        Tag tag2 = new Tag("Hibernate");
//
//        postService.addTags(post, tag1, tag2);
//
//        postService.printTags(post.getId());
//
    }
//
//    @Test
//    @Transactional
//    public void testManyToManyFromWebsite() {
//        postRepository.deleteAllInBatch();
//        tagRepository.deleteAllInBatch();
//
//        // =======================================
//
//        // Create a Post
//        Post post = new Post("Hibernate Many to Many Example with Spring Boot");
//
//        // Create two tags
//        Tag tag1 = new Tag("Spring Boot");
//        Tag tag2 = new Tag("Hibernate");
//
//
//        // Add tag references in the post
//        post.getTags().add(tag1);
//        post.getTags().add(tag2);
//
//        // Add post reference in the tags
//        tag1.getPosts().add(post);
//        tag2.getPosts().add(post);
//
//        postRepository.save(post);
//
//        postService.printTags(post.getId());
//
//    }
//
//    @Test
//    @Transactional
//    public void testGetByIdThenPrintComments() {
//        postRepository.deleteAllInBatch();
//        tagRepository.deleteAllInBatch();
//
//        Post post = new Post("wicked");
//        postRepository.save(post);
//
//        Comment comment = new Comment("comment");
//        comment.setPost(post);
//        commentRepository.save(comment);
//
//        post = postService.getPostById(post.getId());
//        postService.printComments(post);
//    }

    @Test
    @Transactional
    public void testEntityManager() {
        Post post = new Post("post");
        Comment comment = new Comment("comment");
        comment.setPost(post);
        post.getComments().add(comment);

        entityManager.persist(post);
        log.info(post.toString());

        entityManager.detach(post);
        post.setPost("wicked");
        log.info(post.toString());

        post = entityManager.merge(post);
        log.info(post.toString());
    }
}
