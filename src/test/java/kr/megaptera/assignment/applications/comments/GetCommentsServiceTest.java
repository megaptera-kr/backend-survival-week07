package kr.megaptera.assignment.applications.comments;

import kr.megaptera.assignment.models.comments.Comment;
import kr.megaptera.assignment.repositories.CommentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class GetCommentsServiceTest {

    private CommentRepository commentRepository;
    private GetCommentsService getCommentsService;

    @BeforeEach
    void setUp() {
        commentRepository = mock(CommentRepository.class);
        getCommentsService = new GetCommentsService(commentRepository);
    }

    @Test
    @DisplayName("get comments by postId")
    void list() {
        var postId = "post_id";
        var comments = List.of(
            new Comment("comment_id_01", postId, "author", "content"),
            new Comment("comment_id_02", postId, "author", "content")
        );

        given(commentRepository.findByPostId(postId)).willReturn(comments);

        var commentReadDtos = getCommentsService.execute(postId);
        assertThat(commentReadDtos).hasSize(comments.size());
    }
}