package kr.megaptera.assignment.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import kr.megaptera.assignment.dtos.posts.PostCreateDto;
import kr.megaptera.assignment.dtos.posts.PostReadDto;
import kr.megaptera.assignment.dtos.posts.PostUpdateDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Transactional
class PostControllerTest {
    @Value("${local.server.port}")
    private int port;
    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    @DisplayName("GET /posts")
    void list() throws JsonProcessingException {
        var demoPost = addPost();

        var url = "http://localhost:" + port + "/posts";
        var body = restTemplate.getForObject(url, String.class);

        assertThat(body).isNotEmpty();

        ObjectMapper objectMapper = new ObjectMapper();
        PostReadDto[] posts = objectMapper.readValue(body, PostReadDto[].class);

        assertThat(posts).isNotEmpty();
        removePost(demoPost.getId());
    }

    @Test
    @DisplayName("GET /posts/{id} - with correct ID")
    void detailWithCorrectId() throws JsonProcessingException {
        var demoPost = addPost();

        var url = "http://localhost:" + port + "/posts/" + demoPost.getId();
        var responseEntity = restTemplate.getForEntity(url, String.class);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);

        ObjectMapper objectMapper = new ObjectMapper();
        PostReadDto post = objectMapper.readValue(responseEntity.getBody(), PostReadDto.class);

        assertThat(post.getId()).isEqualTo(demoPost.getId());

        removePost(demoPost.getId());
    }

    @Test
    @DisplayName("GET /posts/{id} - with not incorrect ID")
    void detailWithNotCorrectId() {
        var url = "http://localhost:" + port + "/posts/not_exist_post_id";
        var responseEntity = restTemplate.getForEntity(url, String.class);
        var statusCode = responseEntity.getStatusCode();

        assertThat(statusCode).isEqualTo(HttpStatus.NOT_FOUND);
    }

    @Test
    @DisplayName("POST /posts")
    void create() {
        var url = "http://localhost:" + port + "/posts";
        var postCreateDto = new PostCreateDto("title", "author", "content");
        var postReadDto = restTemplate.postForObject(url, postCreateDto, PostReadDto.class);

        assertThat(postReadDto.getTitle()).isEqualTo(postCreateDto.getTitle());
        assertThat(postReadDto.getAuthor()).isEqualTo(postCreateDto.getAuthor());
        assertThat(postReadDto.getContent()).isEqualTo(postCreateDto.getContent());

        removePost(postReadDto.getId());
    }

    @Test
    @DisplayName("PATCH /posts/{id}")
    void update() throws JsonProcessingException {
        var demoPost = addPost();
        var url = "http://localhost:" + port + "/posts/" + demoPost.getId();
        var postUpdateDto = new PostUpdateDto("updated title", "updated content");
        // Patch == Put
        restTemplate.put(url, postUpdateDto, PostReadDto.class);
        removePost(demoPost.getId());
    }

    @Test
    @DisplayName("DELETE /posts/{id}")
    void deletePost() {
        var demoPost = addPost();

        var url = "http://localhost:" + port + "/posts/" + demoPost.getId();
        restTemplate.delete(url);
    }

    private PostReadDto addPost() {
        var url = "http://localhost:" + port + "/posts";
        var postCreateDto = new PostCreateDto("title", "author", "content");
        var entity = restTemplate.postForEntity(url, postCreateDto, PostReadDto.class);

        assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.CREATED);

        return entity.getBody();
    }

    private void removePost(String id) {
        var url = "http://localhost:" + port + "/posts/" + id;
        restTemplate.delete(url);
    }
}