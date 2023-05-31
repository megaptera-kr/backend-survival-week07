package kr.megaptera.assignment.applications.comments;

import kr.megaptera.assignment.dtos.comments.CommentCreateDto;
import kr.megaptera.assignment.repositories.CommentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

class CreateCommentServiceTest {
    private CommentRepository commentRepository;
    private CreateCommentService createCommentService;

    @BeforeEach
    void setUp() {
        commentRepository = mock(CommentRepository.class);
        createCommentService = new CreateCommentService(commentRepository);
    }

    @Test
    @DisplayName("create post")
    void createPost() {
        var commentCreateDto = new CommentCreateDto("author", "content");
        var postReadDto = createCommentService.execute("postId", commentCreateDto);

        assertThat(postReadDto.getAuthor()).isEqualTo(commentCreateDto.getAuthor());
        assertThat(postReadDto.getContent()).isEqualTo(commentCreateDto.getContent());
    }
}