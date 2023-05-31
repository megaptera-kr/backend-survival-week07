package kr.megaptera.assignment.application;

import kr.megaptera.assignment.dtos.PostDto;
import kr.megaptera.assignment.exceptions.NoPosts;
import kr.megaptera.assignment.exceptions.PostNotFound;
import kr.megaptera.assignment.models.Post;
import kr.megaptera.assignment.models.PostContent;
import kr.megaptera.assignment.models.PostId;
import kr.megaptera.assignment.models.PostTitle;
import kr.megaptera.assignment.repositories.PostRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class PostService {
    private final PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public List<PostDto> getList() {
        List<Post> posts = postRepository.findAll();
        if (posts == null) {
            throw new NoPosts();
        }
        return posts.stream().map(post -> new PostDto(post)).toList();
    }

    public PostDto getPost(String id) {
        Optional<Post> post = postRepository.findById(PostId.of(id));
        if (post == null) {
            throw new PostNotFound();
        }
        return new PostDto(post.get());
    }

    public void createPost(PostDto dto) {
        Post post = new Post(PostTitle.of(dto.getTitle()), dto.getAuthor(), PostContent.of(dto.getContent()));
        postRepository.save(post);
    }

    public void updatePost(String id, PostDto dto) {
        Optional<Post> post = postRepository.findById(PostId.of(id));
        post.get().update(dto);
        postRepository.save(post.get());
    }

    public void deletePost(String id) {
        postRepository.deleteById(PostId.of(id));
    }
}
