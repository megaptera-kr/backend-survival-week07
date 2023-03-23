package kr.megaptera.assignment.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import java.util.List;
import java.util.Optional;
import kr.megaptera.assignment.dtos.CommentCreateDto;
import kr.megaptera.assignment.dtos.CommentDto;
import kr.megaptera.assignment.dtos.CommentUpdateDto;
import kr.megaptera.assignment.models.Comment;
import kr.megaptera.assignment.models.CommentId;
import kr.megaptera.assignment.models.Post;
import kr.megaptera.assignment.models.PostId;
import kr.megaptera.assignment.repositories.CommentRepository;
import kr.megaptera.assignment.repositories.PostRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CommentServiceTest {

    private CommentRepository commentRepository;

    private PostRepository postRepository;

    private CommentService commentService;

    @BeforeEach
    void setUp() {
        // 레포를 Mocking 하여 할당
        postRepository = mock(PostRepository.class);
        commentRepository = mock(CommentRepository.class);

        commentService = new CommentService(postRepository, commentRepository);
    }

    @Test
    @DisplayName("댓글 목록 조회 테스트")
    void getCommentList() {
        // Given : repo에서 조회될 댓글 목록 지정

        PostId postId = new PostId("ID0701");
        Post post = new Post(postId, "굉장제목", "작성자", "그냥내용");

        given(postRepository.findById(postId)).willReturn(Optional.of(post));
        given(commentRepository.findAllByPost(post))
            .willReturn(List.of(new Comment("댓글작성자", "내용", post)));

        // When : service 를 통해 댓글 목록 조회
        List<CommentDto> commentList = commentService.getCommentList(postId.toString());

        // Then :
        assertThat(commentList).hasSize(1);
    }

    @Test
    @DisplayName("댓글 생성 테스트")
    void saveComment() {
        // Given : 생성할 댓글을 위한 밑 작업
        PostId id = new PostId("ID0701");
        Post post = new Post(id, "굉장제목", "작성인", "평범내용");

        given(postRepository.findById(id)).willReturn(Optional.of(post));

        // When : service 호출
        CommentCreateDto commentCreateDto = new CommentCreateDto("댓글 작성자", "좋은 댓글");
        commentService.saveComment(id.toString(), commentCreateDto);

        // Then : repo 호출 확인
        verify(commentRepository).save(any(Comment.class));
    }

    @Test
    @DisplayName("댓글 수정 테스트")
    void updateComment() {
        // Given : 수정할 댓글 설정
        PostId id = new PostId("ID0701");
        Post post = new Post(id, "굉장제목", "작성인", "평범내용");

        CommentId commentId = new CommentId("CID0701");
        Comment comment = new Comment(commentId, "댓글 작성자", "댓글내용쓰", post);

        given(commentRepository.findById(commentId)).willReturn(Optional.of(comment));

        // When : 서비스 호출
        CommentUpdateDto commentUpdateDto = new CommentUpdateDto("새로운 내용");
        commentService.updateComment(commentId.toString(), commentUpdateDto);

        // Then : 업데이트 되었는지 확인
        assertThat(comment.content().equals("새로운 내용"));
    }

    @Test
    @DisplayName("댓글 삭제 조회")
    void deleteComment() {
        // Given : 삭제할 댓글 설정
        PostId id = new PostId("ID0701");
        Post post = new Post(id, "굉장제목", "작성인", "평범내용");

        CommentId commentId = new CommentId("CID0701");
        Comment comment = new Comment(commentId, "댓글 작성자", "댓글내용쓰", post);

        given(commentRepository.findById(commentId)).willReturn(Optional.of(comment));

        // When :
        commentService.deleteComment(commentId.toString());

        // Then :
        verify(commentRepository).delete(any(Comment.class));
    }


}
