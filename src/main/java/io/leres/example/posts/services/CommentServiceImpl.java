package io.leres.example.posts.services;

import io.leres.example.posts.Comment;
import io.leres.example.posts.Post;
import io.leres.example.posts.repo.CommentRepository;
import io.leres.exceptions.ResourceNotFound;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class CommentServiceImpl implements CommentService {

    private CommentRepository commentRepository;

    public CommentServiceImpl(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    @Override
    public Page<Comment> getCommentsForPost(long postId, Pageable pageable) {
        return commentRepository.findByPostId(postId, pageable);
    }

    @Override
    public Comment createComment(Comment comment) {
        return commentRepository.save(comment);
    }

    @Override
    public Comment updateComment(long commentId, Comment example) throws ResourceNotFound {
        Comment comment = getByCommentId(commentId);

        comment.setComment(example.getComment());

        return comment;
    }

    private Comment getByCommentId(long commentId) throws ResourceNotFound {
        return commentRepository.findById(commentId).orElseThrow(() -> new ResourceNotFound(commentId));
    }

    @Override
    public void deleteById(long commentId) throws ResourceNotFound {
        if (!commentRepository.existsById(commentId)) {
            throw new ResourceNotFound(commentId);
        }

        commentRepository.deleteById(commentId);
    }

    @Override
    @Transactional
    public void deletePostComments(Post post) {
        commentRepository.deleteInBulkByPostId(post.getId());
    }
}
