package kr.megaptera.assignment.application;


import kr.megaptera.assignment.dtos.CommentCreateDto;
import kr.megaptera.assignment.models.Comment;
import kr.megaptera.assignment.models.MultilineText;
import kr.megaptera.assignment.models.Post;
import kr.megaptera.assignment.repositories.CommentRepository;
import kr.megaptera.assignment.repositories.PostRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class CreateCommentServiceTest {
    private CommentRepository commentRepository;

    private PostRepository postRepository;

    private CreateCommentService createCommentService;

    @BeforeEach
    void setUp() {
        commentRepository = mock(CommentRepository.class);
        postRepository = mock(PostRepository.class);
        createCommentService = new CreateCommentService(commentRepository, postRepository);
    }

    @Test
    @DisplayName("댓글 생성")
    void create() {
        String postId = "1";

        Post post = new Post(Long.parseLong(postId), "제목", "작성자", MultilineText.of("내용"));

        given(postRepository.findById(post.id())).willReturn(Optional.of(post));

        CommentCreateDto newComment = new CommentCreateDto("작성자", "댓글 내용");

        createCommentService.createComment(postId, newComment);

        verify(commentRepository).save(any(Comment.class));
    }
}
