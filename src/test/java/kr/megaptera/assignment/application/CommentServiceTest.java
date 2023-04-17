package kr.megaptera.assignment.application;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

import java.util.List;
import kr.megaptera.assignment.dtos.CommentResponse;
import kr.megaptera.assignment.dtos.CreateCommentRequest;
import kr.megaptera.assignment.dtos.UpdateCommentRequest;
import kr.megaptera.assignment.exceptions.CommentNotFound;
import kr.megaptera.assignment.models.Author;
import kr.megaptera.assignment.models.Comment;
import kr.megaptera.assignment.models.Content;
import kr.megaptera.assignment.models.Post;
import kr.megaptera.assignment.models.Title;
import kr.megaptera.assignment.repositories.CommentRepository;
import kr.megaptera.assignment.repositories.PostRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
public class CommentServiceTest {

    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private CommentService commentService;

    @DisplayName("댓글 생성 테스트")
    @Test
    void createComment() {
        // given
        Post post = new Post(Title.of("제목"), Author.of("Harry"), Content.of("내용"));
        CreateCommentRequest request = new CreateCommentRequest("potter", "좋아요");
        Post savedPost = postRepository.save(post);

        // when
        CommentResponse result = commentService.createComment(savedPost.getId().toString(),
                request);

        // then
        assertThat(result.getPostId()).isEqualTo(savedPost.getId().toString());
        assertThat(result.getAuthor()).isEqualTo(result.getAuthor());
        assertThat(result.getContent()).isEqualTo(result.getContent());
    }

    @DisplayName("하나의 게시물에 대한 댓글 다수 가져오기 테스트")
    @Test
    void getComments() {
        // given
        Post post = new Post(Title.of("제목"), Author.of("Harry"), Content.of("내용"));
        Comment comment1 = new Comment(post, Author.of("Potter"), Content.of("좋아요"));
        Comment comment2 = new Comment(post, Author.of("Ron"), Content.of("저도 좋아요"));

        postRepository.save(post);
        commentRepository.save(comment1);
        commentRepository.save(comment2);

        // when
        List<CommentResponse> comments = commentService.getComments(post.getId().toString());

        // then
        assertThat(comments.size()).isEqualTo(2);
    }

    @DisplayName("댓글 수정하기 테스트")
    @Test
    void updateComment() {
        // given
        Post post = new Post(Title.of("제목"), Author.of("Harry"), Content.of("내용"));
        Comment comment = new Comment(post, Author.of("Potter"), Content.of("좋아요"));
        UpdateCommentRequest request = new UpdateCommentRequest("댓글내용바뀜");

        Post savedPost = postRepository.save(post);
        Comment savedComment = commentRepository.save(comment);

        // when
        commentService.updateComment(savedComment.getId().toString(), savedPost.getId().toString(),
                request);
        Comment result = commentRepository.findByIdAndPostId(savedComment.getId(),
                        savedPost.getId())
                .orElseThrow(CommentNotFound::new);

        // then
        assertThat(result.getId()).isEqualTo(savedComment.getId());
        assertThat(result.getPost().getId()).isEqualTo(savedPost.getId());
        assertThat(result.getAuthor()).isEqualTo(comment.getAuthor());
        assertThat(result.getContent().toString()).isEqualTo(request.getContent());
    }

    @DisplayName("댓글이 없으면 익셉션이 발생한다.")
    @Test
    void updateCommentFail() {
        // given
        Post post = new Post(Title.of("제목"), Author.of("Harry"), Content.of("내용"));
        UpdateCommentRequest request = new UpdateCommentRequest("댓글내용바뀜");

        Post savedPost = postRepository.save(post);

        // when, then
        assertThatCode(()->commentService.updateComment("1", savedPost.getId().toString(), request))
                .isInstanceOf(CommentNotFound.class);
    }

    @DisplayName("댓글 삭제하기 테스트")
    @Test
    void deleteComment() {
        // given
        Post post = new Post(Title.of("제목"), Author.of("Harry"), Content.of("내용"));
        Comment comment = new Comment(post, Author.of("Potter"), Content.of("좋아요"));

        Post savedPost = postRepository.save(post);
        Comment savedComment = commentRepository.save(comment);

        // when
        commentService.deleteComment(savedComment.getId().toString(), savedPost.getId().toString());

        // then
        List<Comment> comments = commentRepository.findAll();
        assertThat(comments.size()).isEqualTo(0);
    }
}
