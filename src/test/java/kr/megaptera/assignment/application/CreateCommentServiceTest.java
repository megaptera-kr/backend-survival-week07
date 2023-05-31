package kr.megaptera.assignment.application;

import kr.megaptera.assignment.dtos.request.RqCreateCommentDto;
import kr.megaptera.assignment.models.Comment;
import kr.megaptera.assignment.repositories.CommentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class CreateCommentServiceTest {

    private CommentRepository commentRepository;
    private CreateCommentService createCommentService;

    @BeforeEach
    void setUp() {
        commentRepository = mock(CommentRepository.class);

        createCommentService = new CreateCommentService(commentRepository);
    }

    @Test
    void createComment() {

        RqCreateCommentDto dto = new RqCreateCommentDto();
        dto.setAuthor("author");
        dto.setContent("content");

        String postId = "postId";


        createCommentService.createComment(dto, postId);

        verify(commentRepository).save(any(Comment.class));
    }

}