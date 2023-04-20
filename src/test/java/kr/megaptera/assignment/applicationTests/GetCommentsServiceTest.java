package kr.megaptera.assignment.applicationTests;

import kr.megaptera.assignment.applications.*;
import kr.megaptera.assignment.domains.*;
import kr.megaptera.assignment.repositories.*;
import org.junit.jupiter.api.*;

import java.util.*;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.BDDMockito.*;

public class GetCommentsServiceTest {
    private GetCommentsService getCommentsService;
    private CommentRepository commentRepository;

    @BeforeEach
    void setUp() {
        commentRepository = mock(CommentRepository.class);
        getCommentsService = new GetCommentsService(commentRepository);
    }

    @Test
    @DisplayName("get Comments!")
    void list() {
        String postId = "001";
        given(commentRepository.findAllById(postId))
                .willReturn(List.of(
                        new Comment(new CommentId("0001"), "작성자", "제목", new PostId(postId))
                ));

        assertThat(getCommentsService.getCommentDtos(postId)).hasSize(1);
    }
}
