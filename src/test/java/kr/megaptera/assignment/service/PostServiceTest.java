package kr.megaptera.assignment.service;

import kr.megaptera.assignment.dto.PostCreateRequest;
import kr.megaptera.assignment.dto.PostResponse;
import kr.megaptera.assignment.dto.PostUpdateRequest;
import kr.megaptera.assignment.exception.NotFoundException;
import kr.megaptera.assignment.model.Post;
import kr.megaptera.assignment.repositories.PostRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class PostServiceTest {
    private PostRepository postRepository;
    private PostService postService;

    @BeforeEach
    void setUp() {
        postRepository = mock(PostRepository.class);
        postService = new PostService(postRepository);
    }

    @Test
    @DisplayName("게시물 생성")
    void create() {
        //given
        PostCreateRequest newPost = new PostCreateRequest("제목", "작성자", "내용");
        //when
        postService.create(newPost);
        //then
        verify(postRepository).save(any(Post.class));
    }

    @Test
    @DisplayName("게시물 목록조회")
    void list() {
        //given
        //when
        List<PostResponse> postList = postService.list();
        //then
        verify(postRepository).findAll();
    }

    @Test
    @DisplayName("게시물 조회")
    void detail() {
        //given
        given(postRepository.findById("TSID1"))
                .willReturn(Optional.of(new Post(
                        "TSID1",
                        "첫 번째 글 제목",
                        "저자1",
                        "첫 번째 글 내용")
                ));

        PostResponse postResponse = postService.detail("TSID1");
        //when
        //then
        assertThat(postResponse.title()).isEqualTo("첫 번째 글 제목");
    }
    @Test
    @DisplayName("게시물 수정")
    void update() {
        // given
        Post existingPost = new Post("TSID1", "첫 번째 글 제목", "저자1", "첫 번째 글 내용");
        given(postRepository.findById("TSID1")).willReturn(Optional.of(existingPost));

        PostUpdateRequest postUpdateRequest = new PostUpdateRequest("동재업데이트", "으악");

        // when
        postService.update(postUpdateRequest, "TSID1");

        // 이 부분은 Mockito를 사용해 업데이트된 객체를 반환하도록 설정합니다.
        given(postRepository.findById("TSID1")).willReturn(Optional.of(new Post("TSID1", "동재업데이트", "저자1", "으악")));

        // then
        PostResponse updatePostResponse = postService.detail("TSID1");
        assertThat(updatePostResponse.title()).isEqualTo("동재업데이트");
    }

    @Test
    @DisplayName("게시물 삭제")
    void delete() {
        //given
        Post existingPost = new Post("TSID1", "첫 번째 글 제목", "저자1", "첫 번째 글 내용");
        given(postRepository.findById("TSID1")).willReturn(Optional.of(existingPost));
        //when
        postService.delete(existingPost.getId());

        //then
        verify(postRepository).deleteById(existingPost.getId());
    }


}
