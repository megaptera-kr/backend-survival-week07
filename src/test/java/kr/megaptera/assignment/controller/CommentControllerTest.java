package kr.megaptera.assignment.controller;


import kr.megaptera.assignment.dto.PostCreateRequest;
import kr.megaptera.assignment.dto.PostResponse;
import kr.megaptera.assignment.dto.PostUpdateRequest;
import kr.megaptera.assignment.model.Post;
import kr.megaptera.assignment.repositories.PostRepository;
import kr.megaptera.assignment.service.PostService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@WebMvcTest(CommentController.class)
class CommentControllerTest {
//    @Autowired
//    private PostService postService;
//
//    private PostRepository postRepository;
//
//    @BeforeEach
//    void setUp() {
//        postRepository = mock(PostRepository.class);
//    }
//
//    @Test
//    @DisplayName("게시물 생성")
//    void create() {
//        //given
//        PostCreateRequest newPost = new PostCreateRequest("제목", "작성자", "내용");
//        //when
//        postService.create(newPost);
//        //then
//        verify(postRepository).save(any(Post.class));
//    }
//
//    @Test
//    @DisplayName("게시물 목록조회")
//    void list() {
//        //given
//        //when
//        List<PostResponse> postList = postService.list();
//        //then
//        verify(postRepository).findAll();
//    }
//
//    @Test
//    @DisplayName("게시물 조회")
//    void detail() {
//        //given
//        PostResponse postResponse = postService.detail("TSID1");
//        //when
//        //then
//        assertThat(postResponse.title()).isEqualTo("첫 번째 글 제목");
//    }
//
//    @Test
//    @DisplayName("게시물 수정")
//    void update() {
//        //given
//        PostUpdateRequest postUpdateRequest = new PostUpdateRequest("동재업데이트", "으악");
//        //when
//        postService.update(postUpdateRequest, "TSID1");
//        PostResponse updatePostResponse = postService.detail("TSID1");
//        //then
//        assertThat(updatePostResponse.title()).isEqualTo("동재업데이트");
//    }
//
//    @Test
//    @DisplayName("게시물 삭제")
//    void delete() {
//        //given
//        String postId = "TSID3";
//
//        //when
//        Optional<Post> post = postRepository.findById(postId);
//        post.ifPresent(foundPost -> postService.delete(foundPost.getId()));
//
//        //then
//        post.ifPresent(foundPost -> verify(postRepository).delete(foundPost));
//    }
}
