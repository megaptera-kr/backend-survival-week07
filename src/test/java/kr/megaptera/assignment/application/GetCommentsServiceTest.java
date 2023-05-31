package kr.megaptera.assignment.application;

import kr.megaptera.assignment.dtos.CommentDto;
import kr.megaptera.assignment.models.Author;
import kr.megaptera.assignment.models.Comment;
import kr.megaptera.assignment.models.Content;
import kr.megaptera.assignment.models.PostId;
import kr.megaptera.assignment.repositories.CommentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.assertj.core.api.Assertions.assertThat;


class GetCommentsServiceTest {

    private CommentRepository commentRepository;

    private GetCommentsService getCommentsService;

    @BeforeEach
    void setUp() {
        commentRepository = mock(CommentRepository.class);

        getCommentsService = new GetCommentsService(commentRepository);
    }

    @Test
    void GetComments() {
        PostId postId = new PostId("test");

        Author author = new Author("author");
        Content content = new Content("content");

        Comment comment = new Comment(postId, author, content);

        given(commentRepository.findAllByPostId(postId))
                .willReturn(java.util.List.of(comment));

        ResponseEntity<List<CommentDto>> test = getCommentsService.getCommentByPostId("test");

        assertThat(test.getBody().get(0).getAuthor()).isEqualTo(author.toString());
        assertThat(test.getBody().get(0).getContent()).isEqualTo(content.toString());
        assertThat(test.getBody().size()).isEqualTo(1);
    }
}