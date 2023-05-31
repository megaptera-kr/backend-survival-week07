package kr.megaptera.assignment.application;

import kr.megaptera.assignment.dtos.PostDto;
import kr.megaptera.assignment.models.Post;
import kr.megaptera.assignment.models.PostContent;
import kr.megaptera.assignment.models.PostId;
import kr.megaptera.assignment.models.PostTitle;
import kr.megaptera.assignment.repositories.PostRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class PostServiceTest {


    PostRepository postRepository;
    PostService postService;

    @BeforeEach
    void setUp() {
        postRepository = mock(PostRepository.class);
        postService = new PostService(postRepository);
    }


    @Test
    @DisplayName("포스트서비스 list")
    void getList() {
        PostId postId = new PostId("100");
        PostTitle postTitle = new PostTitle("제목");
        PostContent postContent = new PostContent("내용");

        given(postRepository.findAll()).willReturn(List.of(
                new Post(postId, postTitle, "작성자", postContent),
                new Post(postId, postTitle, "작성자2", postContent)
        ));

        List<PostDto> dtos = postService.getList();

        assertThat(dtos).hasSize(2);
    }

    @Test
    @DisplayName("포스트서비스 detil")
    void detail() {
        PostId postId = PostId.of("100");

        given(postRepository.findById(postId)).willReturn(Optional.of(new Post(
                new PostId(postId.toString()), new PostTitle("zzz"), "작성자", new PostContent("하하")
        )));

        Optional<Post> dtoop = postRepository.findById(postId);
        PostDto dto = new PostDto(dtoop.get());


        assertThat(dto).isNotNull();
        assertThat(dto.getTitle()).isEqualTo("zzz");
    }

    @Test
    @DisplayName("포스트서비스 create")
    void create() {
        PostDto postDto = new PostDto("", "gkgk", "writer", "하하하");
        Post post = new Post(PostTitle.of(postDto.getTitle()), postDto.getAuthor(), PostContent.of(postDto.getContent()));
        postRepository.save(post);

        verify(postRepository).save(any(Post.class));
    }

    @Test
    @DisplayName("포스트서비스 update")
    void update() {
        PostId id = new PostId("10");
        Post post = new Post(id, new PostTitle("수정 전 제목"), "작성자1", new PostContent("수정 전 내용"));
        PostDto postDto = new PostDto("수정 후", "수정 후 내용");
        given(postRepository.findById(id)).willReturn(Optional.of(post));


        postService.updatePost(id.toString(), postDto);

        assertThat(post.title().toString()).isEqualTo("수정 후");
        assertThat(post).isNotNull();
        assertThat(post.content().toString()).isEqualTo("수정 후 내용");
    }

    @Test
    @DisplayName("포스트서비스 delete")
    void delete() {
        PostId postId = new PostId("1004");
        Post post = new Post(postId, new PostTitle("삭제"), "작성자1", new PostContent("삭제테스트"));

        given(postRepository.findById(postId)).willReturn(Optional.of(post));

        postService.deletePost(postId.toString());

        verify(postRepository).deleteById(postId);
    }
}