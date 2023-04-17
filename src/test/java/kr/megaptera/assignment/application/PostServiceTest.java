package kr.megaptera.assignment.application;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

import java.util.List;
import kr.megaptera.assignment.dtos.CreatePostRequest;
import kr.megaptera.assignment.dtos.PostResponse;
import kr.megaptera.assignment.dtos.UpdatePostRequest;
import kr.megaptera.assignment.exceptions.PostNotFound;
import kr.megaptera.assignment.models.Author;
import kr.megaptera.assignment.models.Content;
import kr.megaptera.assignment.models.Post;
import kr.megaptera.assignment.models.Title;
import kr.megaptera.assignment.repositories.PostRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
public class PostServiceTest {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private PostService postService;

    @DisplayName("게시물 저장 테스트")
    @Test
    void createPost() {
        // given
        CreatePostRequest request = new CreatePostRequest("제목", "Harry", "내용");

        // when
        PostResponse result = postService.createPost(request.toEntity());

        // then
        assertThat(result.getTitle()).isEqualTo(request.getTitle());
        assertThat(result.getAuthor()).isEqualTo(request.getAuthor());
        assertThat(result.getContent()).isEqualTo(request.getContent());
    }

    @DisplayName("게시물 단건 조회 테스트")
    @Test
    void getPostSuccess() {
        // given
        Post expected = new Post(Title.of("제목"), Author.of("Harry"), Content.of("내용"));
        Post saved = postRepository.save(expected);

        // when
        PostResponse result = postService.getPost(saved.getId().toString());

        // then
        assertThat(result.getId()).isEqualTo(expected.getId().toString());
        assertThat(result.getTitle()).isEqualTo(expected.getTitle().toString());
        assertThat(result.getAuthor()).isEqualTo(expected.getAuthor().toString());
        assertThat(result.getContent()).isEqualTo(expected.getContent().toString());
    }

    @DisplayName("게시물 단건 조회 실패시 익셉션이 발생한다.")
    @Test
    void getPostFail() {
        assertThatCode(() -> postService.getPost("1"))
                .isInstanceOf(PostNotFound.class);
    }

    @DisplayName("게시물 다수 조회 테스트")
    @Test
    void getPostList() {
        // given
        Post post1 = new Post(Title.of("제목1"), Author.of("Harry"), Content.of("내용1"));
        Post post2 = new Post(Title.of("제목2"), Author.of("Harry"), Content.of("내용2"));
        postRepository.save(post1);
        postRepository.save(post2);

        // when
        List<PostResponse> postResponses = postService.getPostList();

        // then
        assertThat(postResponses.size()).isEqualTo(2);
    }

    @DisplayName("게시물 업데이트 테스트")
    @Test
    void updatePost() {
        // given
        Post oldPost = new Post(Title.of("제목"), Author.of("Harry"), Content.of("내용"));
        Post saved = postRepository.save(oldPost);

        UpdatePostRequest request = new UpdatePostRequest("제목바뀜", "내용바뀜");

        // when
        postService.updatePost(saved.getId().toString(), request);
        Post result = postRepository.findById(saved.getId()).orElseThrow(PostNotFound::new);

        // then
        assertThat(result.getId()).isEqualTo(oldPost.getId());
        assertThat(result.getTitle().toString()).isEqualTo(request.getTitle());
        assertThat(result.getAuthor()).isEqualTo(oldPost.getAuthor());
        assertThat(result.getContent().toString()).isEqualTo(request.getContent());

    }

    @DisplayName("게시물 삭제 테스트")
    @Test
    void deletePost() {
        // given
        Post post = new Post(Title.of("제목"), Author.of("Harry"), Content.of("내용"));
        postRepository.save(post);

        // when
        postService.deletePost(post.getId().toString());

        // then
        List<Post> posts = postRepository.findAll();
        assertThat(posts.size()).isEqualTo(0);
    }

}
