package kr.megaptera.assignment.service;

import jakarta.transaction.Transactional;
import kr.megaptera.assignment.dto.PostCreateRequest;
import kr.megaptera.assignment.dto.PostResponse;
import kr.megaptera.assignment.dto.PostUpdateRequest;
import kr.megaptera.assignment.exception.NotFoundException;
import kr.megaptera.assignment.model.Post;
import kr.megaptera.assignment.repositories.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PostService {
    private final PostRepository postRepository;

    @Autowired
    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public List<PostResponse> list() {
        return postRepository.findAll().stream().map(Post::toPostResponse).toList();
    }

    public PostResponse detail(String id) {
        return postRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Post with id " + id + " not found")).toPostResponse();
    }
    @Transactional
    public void create(PostCreateRequest request) {
        Post post = new Post();
        postRepository.save(post.fromCreateRequest(request));
    }

    @Transactional
    public void update(PostUpdateRequest request, String id) {
        Post post = postRepository.findById(id).orElseThrow(() -> new NotFoundException("Post with id " + id + " not found"));
        post.fromUpdateRequest(request);
    }
    @Transactional
    public void delete(String id) {
        postRepository.deleteById(id);
    }
}
