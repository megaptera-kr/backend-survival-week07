package kr.megaptera.assignment.application;

import jakarta.transaction.Transactional;
import kr.megaptera.assignment.domains.dto.CommentCreateDto;
import kr.megaptera.assignment.domains.model.Comment;
import kr.megaptera.assignment.domains.model.MultilineText;
import kr.megaptera.assignment.domains.model.Post;
import kr.megaptera.assignment.domains.model.PostAuthor;
import kr.megaptera.assignment.domains.model.PostId;
import kr.megaptera.assignment.domains.model.PostTitle;
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

@Transactional
class CreateCommentServiceTest {

    private PostRepository postRepository;
    private CommentRepository commentRepository;

    private CreateCommentService createCommentService;

    @BeforeEach
    void setUp() {
        postRepository = mock(PostRepository.class);
        commentRepository = mock(CommentRepository.class);
        createCommentService = new CreateCommentService(postRepository, commentRepository);
    }

    @Test
    @DisplayName("게시물에 댓글 생성")
    void createComment() {
        given(postRepository.findById(PostId.of("1")))
                .willReturn(
                        Optional.of(new Post(
                                PostId.of("1"),
                                PostTitle.of("내가 첫 글???"),
                                PostAuthor.of("김종희"),
                                MultilineText.of("신난닷!!\n너무 좋아용~~!!"))));
        CommentCreateDto commentCreateDto = new CommentCreateDto("운영자", "감사합니다. 앞으로 자주 이용해 주세요.");
        createCommentService.createComment("1", commentCreateDto);

        verify(commentRepository).save(any(Comment.class));
    }

}
