package kr.megaptera.assignment.applications;

import kr.megaptera.assignment.dtos.PostCreateDto;
import kr.megaptera.assignment.dtos.PostDto;
import kr.megaptera.assignment.dtos.PostUpdateDto;
import kr.megaptera.assignment.models.Post;
import kr.megaptera.assignment.models.PostId;
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

public class PostServiceTest {
    private PostRepository postRepository;
    private CreatePostService createPostService;
    private UpdatePostService updatePostService;
    private DeletePostService deletePostService;
    private GetPostsService getPostsService;
    private GetPostService getPostService;

    @BeforeEach
    void setUp(){
        postRepository = mock(PostRepository.class);
        getPostService = new GetPostService(postRepository);
        getPostsService = new GetPostsService(postRepository);
        createPostService = new CreatePostService(postRepository);
        updatePostService = new UpdatePostService(postRepository);
        deletePostService = new DeletePostService(postRepository);
    }

    @Test
    @DisplayName("게시글 목록 조회 테스트")
    void findAll(){
        // given
        given(postRepository.findAll()).willReturn(
                List.of(
                        new Post("hi", "me", "hahaha"),
                        new Post("hi2", "me2", "hahaha2"),
                        new Post("hi3", "me3", "hahaha3"),
                        new Post("hi4", "me4", "hahaha4")
                )
        );

        // when
        List<PostDto> all = getPostsService.getPosts();

        // then
        assertThat(all).hasSize(4);
        assertThat(all.get(0).getAuthor()).isEqualTo("me");
        assertThat(all.get(3).getContent()).isEqualTo("hahaha4");
    }

    @Test
    @DisplayName("게시글 상세 조회 테스트")
    void findById(){
        //given
        String id = "1";
        Post post = new Post("hi", "me", "hahaha");
        given(postRepository.findById(PostId.of(id))).willReturn(
                Optional.of(post)
        );

        //when
        PostDto byId = getPostService.findById(id);

        //then
        assertThat(byId.getAuthor()).isEqualTo("me");
        assertThat(byId.getContent()).isEqualTo("hahaha");
        assertThat(byId.getTitle()).isEqualTo("hi");
    }

    @Test
    @DisplayName("게시글 생성 테스트")
    void create(){
        PostCreateDto postDto = new PostCreateDto("001", "hey", "this");
        createPostService.createPost(postDto);
        verify(postRepository).save(any(Post.class));
    }

    @Test
    @DisplayName("게시글 수정 테스트")
    void update_test(){
        //given
        String id = "1";
        PostUpdateDto postUpdateDto = new PostUpdateDto("hahah","kkkk");
        Post post = new Post("hahaha","meme","hhhhh");
        given(postRepository.findById(PostId.of(id))).willReturn(Optional.of(post));

        //when
        updatePostService.updatePost(id, postUpdateDto);

        //then
        assertThat(post.title()).isEqualTo("hahah");
        assertThat(post.content()).isEqualTo("kkkk");
    }

    @Test
    @DisplayName("게시글 삭제 테스트")
    void delete_post_test(){
        // given
        Long id = 1L;
        List<Post> list = List.of(
            new Post("hi", "me", "hahaha"),
            new Post("hi2", "me2", "hahaha2"),
            new Post("hi3", "me3", "hahaha3"),
            new Post("hi4", "me4", "hahaha4")
        );

        // when

        // then
//        verify(postRepository).deleteById(any(Long.class));

    }
}
