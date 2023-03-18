package kr.megaptera.assignment.services;

import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import kr.megaptera.assignment.dtos.PostCreateDto;
import kr.megaptera.assignment.dtos.PostDto;
import kr.megaptera.assignment.dtos.PostUpdateDto;
import kr.megaptera.assignment.models.Post;
import kr.megaptera.assignment.models.PostId;
import kr.megaptera.assignment.repositories.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    // 게시글 전체 조회 메서드
    @Transactional
    public List<PostDto> getPostList() {
        return postRepository.findAll().stream().map(Post::toDto).toList();
    }

    // 새로운 게시글 저장 메서드
    @Transactional
    public void savePost(PostCreateDto postCreateDto) {
        Post newPost = new Post(postCreateDto.getTitle(), postCreateDto.getAuthor(),
            postCreateDto.getContent());

        postRepository.saveAndFlush(newPost);
    }

    // 게시글 상세 조회 메서드
    @Transactional
    public PostDto getPostDetail(String id) throws Exception {
        Optional<Post> optionalPost = postRepository.findById(PostId.of(id));

        if (optionalPost.isPresent()) {
            return optionalPost.get().toDto();
        } else {
            throw new Exception();
        }
    }

    public PostDto updatePost(String postId, PostUpdateDto postUpdateDto) throws Exception {
        Optional<Post> optionalPost = postRepository.findById(PostId.of(postId));

        if (optionalPost.isPresent()) {
            Post post = optionalPost.get();
            post.update(postUpdateDto.getTitle(), postUpdateDto.getContent());
            postRepository.saveAndFlush(post);
            return post.toDto();
        } else {
            throw new Exception();
        }
    }

    public PostDto deletePost(String postId) throws Exception {
        Optional<Post> optionalPost = postRepository.findById(PostId.of(postId));

        if (optionalPost.isPresent()) {
            Post post = optionalPost.get();
            postRepository.delete(post);
            return post.toDto();
        } else {
            throw new Exception();
        }
    }
}
