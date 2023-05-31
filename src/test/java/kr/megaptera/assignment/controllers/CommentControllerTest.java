package kr.megaptera.assignment.controllers;

import jakarta.transaction.Transactional;
import kr.megaptera.assignment.AssignmentApplication;
import kr.megaptera.assignment.dtos.comments.CommentCreateDto;
import kr.megaptera.assignment.dtos.comments.CommentReadDto;
import kr.megaptera.assignment.dtos.comments.CommentUpdateDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(
        classes = AssignmentApplication.class,
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Transactional
class CommentControllerTest {
    @Value("${local.server.port}")
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    @DisplayName("GET /comments")
    void list() {
        var postId = "post_id";
        var postedSize = 3;

        for (int i = 0; i < postedSize; i++) {
            addComment(postId);
        }

        var posts = getCommentByPostId(postId);
        assertThat(posts).hasSize(postedSize);

        for (var post : posts) {
            removeComment(post.getId());
        }
    }

    @Test
    @DisplayName("POST /comments")
    void create() {
        var postId = "post_id";
        var commentReadDto = addComment(postId);

        assertThat(commentReadDto.getAuthor()).isEqualTo("author");
        assertThat(commentReadDto.getContent()).isEqualTo("content");

        removeComment(commentReadDto.getId());
    }

    @Test
    @DisplayName("PATCH /posts/{id}")
    void update() {
        var postId = "post_id";
        var commentReadDto = addComment(postId);

        var url = "http://localhost:" + port + "/comments/" + commentReadDto.getId();
        var commentUpdateDto = new CommentUpdateDto("updated content");
        // Patch == Put
        restTemplate.put(url, commentUpdateDto, CommentReadDto.class);

        removeComment(commentReadDto.getId());
    }

    @Test
    @DisplayName("DELETE /posts/{id}")
    void deletePost() {
        var postId = "post_id";
        var commentReadDto = addComment(postId);

        removeComment(commentReadDto.getId());
    }

    private List<CommentReadDto> getCommentByPostId(String postId) {
        var url = "http://localhost:" + port + "/comments?postId=" + postId;
        var dtos = restTemplate.getForObject(url, CommentReadDto[].class);
        return Arrays.stream(dtos).toList();
    }

    private CommentReadDto addComment(String postId) {
        var url = "http://localhost:" + port + "/comments?postId=" + postId;

        var commentCreateDto = new CommentCreateDto("author", "content");
        var commentReadDto = restTemplate.postForObject(url, commentCreateDto, CommentReadDto.class);

        return commentReadDto;
    }

    private void removeComment(String commentId) {
        var url = "http://localhost:" + port + "/comments/" + commentId;
        restTemplate.delete(url);
    }
}
