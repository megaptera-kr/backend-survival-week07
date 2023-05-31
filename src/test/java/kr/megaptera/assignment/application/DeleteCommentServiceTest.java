package kr.megaptera.assignment.application;

import kr.megaptera.assignment.models.Author;
import kr.megaptera.assignment.models.Comment;
import kr.megaptera.assignment.models.CommentId;
import kr.megaptera.assignment.models.Content;
import kr.megaptera.assignment.models.PostId;
import kr.megaptera.assignment.repositories.CommentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class DeleteCommentServiceTest {

    private CommentRepository commentRepository;

    private DeleteCommentService deleteCommentService;

    @BeforeEach
    void setUp() {
        commentRepository = mock(CommentRepository.class);

        deleteCommentService = new DeleteCommentService(commentRepository);
    }

    @Test
    void DeletePost() {
        CommentId commentId = new CommentId("test");
        PostId postId = new PostId("test");

        Author author = new Author("author");
        Content content = new Content("content");

        Comment comment = new Comment(commentId, postId, author, content);

        given(commentRepository.findById(commentId))
                .willReturn(java.util.Optional.of(comment));

        deleteCommentService.deleteComment(postId.toString(), commentId.toString());

        verify(commentRepository).delete(any(Comment.class));
    }

}