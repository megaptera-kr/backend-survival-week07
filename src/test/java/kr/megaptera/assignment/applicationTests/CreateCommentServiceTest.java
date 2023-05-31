package kr.megaptera.assignment.applicationTests;

import kr.megaptera.assignment.applications.*;
import kr.megaptera.assignment.domains.*;
import kr.megaptera.assignment.dtos.*;
import kr.megaptera.assignment.repositories.*;
import org.junit.jupiter.api.*;

import static org.mockito.Mockito.*;

public class CreateCommentServiceTest {
    private CreateCommentService createCommentService;

    private CommentRepository commentRepository;

    @BeforeEach
    void setUp() {
        commentRepository = mock(CommentRepository.class);
        createCommentService = new CreateCommentService(commentRepository);
    }

    @Test
    @DisplayName("Create comment!!")
    void creation() {
        String postId = "0001";
//        Comment comment = new Comment(new CommentId("001"), "author", "content", new PostId("0001"));
        CommentCreateDto commentCreateDto = new CommentCreateDto("author", "content");
        createCommentService.createCommentDto(postId, commentCreateDto);
        verify(commentRepository).save(any(Comment.class));

    }
}
