package kr.megaptera.assignment.applicationTests;

import kr.megaptera.assignment.applications.*;
import kr.megaptera.assignment.domains.*;
import kr.megaptera.assignment.repositories.*;
import org.junit.jupiter.api.*;

import java.util.*;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.*;

public class DeleteCommentServiceTest {
    private DeleteCommentService deleteCommentService;
    private CommentRepository commentRepository;

    @BeforeEach
    void setUp() {
        commentRepository = mock(CommentRepository.class);
        deleteCommentService = new DeleteCommentService(commentRepository);
    }

    @Test
    @DisplayName("deleeeete comment!")
    void delete() {
        CommentId commentId = new CommentId("001");
        PostId postId = new PostId("0001");

        Comment comment = new Comment(commentId, "author", "content", postId);

        given(commentRepository.findByIdAndPostId(commentId, postId))
                .willReturn(Optional.of(comment));

        deleteCommentService.delete(commentId.toString(), postId.toString());

        verify(commentRepository).delete(any(Comment.class));
    }
}
