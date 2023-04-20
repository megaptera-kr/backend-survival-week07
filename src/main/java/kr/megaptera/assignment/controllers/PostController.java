package kr.megaptera.assignment.controllers;

import kr.megaptera.assignment.applications.*;
import kr.megaptera.assignment.dtos.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/posts")
@CrossOrigin
public class PostController {
    private final CreatePostService createPostService;
    private final GetPostsService getPostsService;
    private final GetPostService getPostService;
    private final UpdatePostService updatePostService;
    private final DeletePostService deletePostService;

    public PostController(CreatePostService createPostService, GetPostsService getPostsService, GetPostService getPostService, UpdatePostService updatePostService, DeletePostService deletePostService) {
        this.createPostService = createPostService;
        this.getPostsService = getPostsService;
        this.getPostService = getPostService;
        this.updatePostService = updatePostService;
        this.deletePostService = deletePostService;
    }

    @GetMapping()
    public List<PostDto> list() {
        List<PostDto> postDtos = getPostsService.getList();

        return postDtos;
    }

    @GetMapping("/{id}")
    public PostDto detail(@PathVariable String id) {
        PostDto postDto = getPostService.getPostDto(id);

        return postDto;
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public void createPost(@RequestBody PostCreateDto postCreateDto) {
        createPostService.createPost(postCreateDto);
    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updatePost(@PathVariable String id, @RequestBody PostUpdateDto postUpdateDto) {
        updatePostService.updatePost(id, postUpdateDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePost(@PathVariable String id) {
        deletePostService.deletePost(id);
    }
}

