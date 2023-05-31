package kr.megaptera.assignment.application;

import kr.megaptera.assignment.dtos.PostDto;
import kr.megaptera.assignment.dtos.request.RqCreatePostDto;
import kr.megaptera.assignment.models.Author;
import kr.megaptera.assignment.models.Content;
import kr.megaptera.assignment.models.Post;
import kr.megaptera.assignment.models.Title;
import kr.megaptera.assignment.repositories.PostRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class CreatePostService {

    private final PostRepository postRepository;

    public CreatePostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public ResponseEntity<PostDto> createPost(RqCreatePostDto dto) {
        Post post = new Post(Title.of(dto.getTitle()),
                Author.of(dto.getAuthor()),
                Content.of(dto.getContent()));
        Post savePost = postRepository.save(post);
        PostDto postDto = new PostDto(post);
        return new ResponseEntity<>(postDto, HttpStatus.CREATED);
    }
}
