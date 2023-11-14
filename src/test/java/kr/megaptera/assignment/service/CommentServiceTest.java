package kr.megaptera.assignment.service;

import kr.megaptera.assignment.dto.*;
import kr.megaptera.assignment.exception.NotFoundException;
import kr.megaptera.assignment.model.Comment;
import kr.megaptera.assignment.model.Post;
import kr.megaptera.assignment.repositories.CommentRepository;
import kr.megaptera.assignment.repositories.PostRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@SpringBootTest
public class CommentServiceTest {
    private CommentService commentService;
    private CommentRepository commentRepository;
    private PostRepository postRepository;

    @BeforeEach
    void setUp() {
        postRepository = mock(PostRepository.class);
        commentRepository = mock(CommentRepository.class);
        commentService = new CommentService(commentRepository, postRepository);
    }

    @Test
    @DisplayName("댓글 생성")
    void create() {
        //given
        CommentCreateRequest newComment = new CommentCreateRequest("작성자", "내용");
        // when
        Post mockPost = new Post("TSID1", "글 제목", "글 작성자", "내용");
        when(postRepository.findById("TSID1")).thenReturn(Optional.of(mockPost));
        commentService.create(newComment,"TSID1");
        //then
        verify(commentRepository).save(any(Comment.class));
    }

    @Test
    @DisplayName("댓글 목록조회")
    void list() {
        //given
        //when
        List<CommentResponse> postList = commentService.listCommentsByPostId("TSID1");
        //then
        verify(commentRepository).findByPostId("TSID1");
    }

    @Test
    @DisplayName("댓글 수정")
    void update() {
        //given
        Post mockPost = new Post("TSID1", "글 제목", "글 작성자", "내용");
        Comment existingComment = new Comment("COMMENT1", "동재", "첫 번째 댓글 내용", mockPost);
        given(commentRepository.findById("COMMENT1")).willReturn(Optional.of(existingComment));
        CommentUpdateRequest commentUpdateRequest = new CommentUpdateRequest("동재업데이트");
        //when
        commentService.update(commentUpdateRequest, "COMMENT1");
        //then
        Optional<Comment> comment = commentRepository.findById("COMMENT1");
        comment.ifPresent(Updatedcomment -> {
            assertThat(Updatedcomment.getContent()).isEqualTo("동재업데이트");
        });
    }

    @Test
    @DisplayName("댓글 삭제")
    void delete() {
        //given
        Post existingPost = new Post("TSID1", "글 제목", "글작성자", "내용");
        Comment existingComment = new Comment("COMMENT1", "동재", "첫 번째 댓글 내용", existingPost);
        given(commentRepository.findById("COMMENT1")).willReturn(Optional.of(existingComment));
        //when
        commentService.delete(existingComment.getId(),existingPost.getId());
        //then
        verify(commentRepository).delete(any(Comment.class));
    }

}
