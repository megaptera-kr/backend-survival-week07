package kr.megaptera.assignment.application;

import java.util.List;
import kr.megaptera.assignment.dtos.PostResponse;
import kr.megaptera.assignment.dtos.UpdatePostRequest;
import kr.megaptera.assignment.exceptions.PostNotFound;
import kr.megaptera.assignment.models.Post;
import kr.megaptera.assignment.repositories.PostRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PostService {

    private final PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }


    @Transactional
    public List<PostResponse> getPostList() {
        List<Post> posts = postRepository.findAll();

        return posts.stream().map(PostResponse::new).toList();
    }

    @Transactional
    public PostResponse getPost(String id) {
        Post post = postRepository.findById(Long.parseLong(id))
                .orElseThrow(PostNotFound::new);

        return new PostResponse(post);
    }

    @Transactional
    public PostResponse createPost(Post post) {
        Post saved = postRepository.save(post);

        return new PostResponse(saved);
    }


    @Transactional
    public void updatePost(String id, UpdatePostRequest request) {
        Post post = postRepository.findById(Long.parseLong(id)).orElseThrow(PostNotFound::new);
        post.update(request.getTitle(), request.getContent());
    }

    @Transactional
    public void deletePost(String id) {
        postRepository.deleteById(Long.parseLong(id));
    }
}
