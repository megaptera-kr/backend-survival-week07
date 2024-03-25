package kr.megaptera.assignment.controllers;

import kr.megaptera.assignment.dtos.PostDto;
import kr.megaptera.assignment.service.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/posts")
@CrossOrigin
public class PostController {

    private final PostListService postListService;
    private final PostService postService;
    private final PostInsertService postInsertService;
    private final PostUpdateService postUpdateService;
    private final PostDeleteService postDeleteService;

    public PostController(PostListService postListService
            , PostService postService
            , PostInsertService postInsertService
            , PostUpdateService postUpdateService
            , PostDeleteService postDeleteService) {
        this.postListService = postListService;
        this.postService = postService;
        this.postInsertService = postInsertService;
        this.postUpdateService = postUpdateService;
        this.postDeleteService = postDeleteService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<PostDto> getPostList() {
        return postListService.getPostList();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public PostDto getPost(@PathVariable("id") String postId) {
        return postService.getPost(new PostDto(postId));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void insertPost(@RequestBody PostDto postDto) {
        postInsertService.insertPost(postDto);
    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updatePost(@PathVariable("id") String id, @RequestBody PostDto postDto) {
        postDto.setId(id);
        postUpdateService.updatePost(postDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePost(@PathVariable("id") String id) {
        postDeleteService.deletePost(new PostDto(id));
    }
}
