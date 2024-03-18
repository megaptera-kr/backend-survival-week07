package kr.megaptera.assignment.controllers;

import kr.megaptera.assignment.entities.PostEntity;
import kr.megaptera.assignment.repositories.PostRepository;
import kr.megaptera.assignment.vos.Author;
import kr.megaptera.assignment.vos.CommentId;
import kr.megaptera.assignment.vos.PostContent;
import kr.megaptera.assignment.vos.PostId;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles("test")
@SpringBootTest
@AutoConfigureMockMvc
class CommentControllerTest {
    @Autowired
    private MockMvc mockMvc;


    @MockBean
    private PostRepository postRepository;

    @Test
    void list() throws Exception {
        List<PostId> postIdList = IntStream.range(0, 2)
                .mapToObj(i -> PostId.generate())
                .toList();

        postIdList
                .stream()
                .map(id -> PostEntity.of(
                        id,
                        "Title",
                        Author.of("Author"),
                        PostContent.of("Content"))
                )
                .flatMap(entity -> IntStream.range(0, 10).mapToObj(j -> {
                    entity.addComment(Author.of("Author"), "Content");
                    return entity;
                }))
                .forEach(entity -> given(postRepository.findById(entity.getId())).willReturn(Optional.of(entity)));

        for (PostId postId : postIdList) {
            mockMvc.perform(get("/comments?postId=%s".formatted(postId.toString())))
                    .andExpect(status().isOk())
                    .andDo(print());

            verify(postRepository).findById(argThat(id -> id.equals(postId)));
        }
    }

    @Test
    void detail() throws Exception {
        PostId postId = PostId.generate();
        PostEntity postEntity = PostEntity.of(
                postId, "Title", Author.of("Author"), PostContent.of("Content"));
        postEntity.addComment(Author.of("Author"), "Content");
        CommentId commentId = postEntity.getComments().get(0).getCommentId();
        given(postRepository.findById(postId)).willReturn(Optional.of(postEntity));

        mockMvc.perform(get("/comments/%s?postId=%s".formatted(commentId.toString(), postId.toString())))
                .andExpect(status().isOk())
                .andDo(print());

        verify(postRepository).findById(argThat(id -> id.equals(postId)));
    }

    @Test
    void create() throws Exception {
        PostId postId = PostId.generate();
        PostEntity postEntity = PostEntity.of(
                postId, "Title", Author.of("Author"), PostContent.of("Content"));
        given(postRepository.findById(postId)).willReturn(Optional.of(postEntity));

        String json = """
                {
                    "author": "Author",
                    "content": "Content"
                }
                """;
        mockMvc.perform(post("/comments?postId=%s".formatted(postId.toString()))
                        .content(json)
                        .contentType("application/json"))
                .andExpect(status().isCreated())
                .andDo(print());

        verify(postRepository).findById(argThat(id -> id.equals(postId)));
        assertThat(postEntity.getComments()).hasSize(1);
    }

    @Test
    void update() throws Exception {
        PostId postId = PostId.generate();
        PostEntity postEntity = PostEntity.of(
                postId, "Title", Author.of("Author"), PostContent.of("Content"));
        postEntity.addComment(Author.of("Author"), "Content");
        CommentId commentId = postEntity.getComments().get(0).getCommentId();
        given(postRepository.findById(postId)).willReturn(Optional.of(postEntity));


        String json = """
                {
                    "content": "Content"
                }
                """;
        mockMvc.perform(patch("/comments/%s?postId=%s".formatted(commentId.toString(), postId.toString()))
                        .content(json)
                        .contentType("application/json"))
                .andExpect(status().isNoContent())
                .andDo(print());

        verify(postRepository).findById(argThat(id -> id.equals(postId)));
        assertThat(postEntity.getComment(commentId)).isNotNull();
    }

    @Test
    void deletePost() throws Exception {
        PostId postId = PostId.generate();
        PostEntity postEntity = PostEntity.of(
                postId, "Title", Author.of("Author"), PostContent.of("Content"));
        postEntity.addComment(Author.of("Author"), "Content");
        CommentId commentId = postEntity.getComments().get(0).getCommentId();
        given(postRepository.findById(postId)).willReturn(Optional.of(postEntity));

        mockMvc.perform(delete("/comments/%s?postId=%s".formatted(commentId.toString(), postId.toString())))
                .andExpect(status().isNoContent())
                .andDo(print());

        verify(postRepository).findById(argThat(id -> id.equals(postId)));
        assertThat(postEntity.getComments()).hasSize(0);
    }
}