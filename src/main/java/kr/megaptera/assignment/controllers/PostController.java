package kr.megaptera.assignment.controllers;

import kr.megaptera.assignment.application.posts.*;
import kr.megaptera.assignment.dtos.posts.CreatePostDto;
import kr.megaptera.assignment.dtos.posts.PostDto;
import kr.megaptera.assignment.dtos.posts.UpdatePostDto;
import kr.megaptera.assignment.exceptions.PostNotFound;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/posts")
public class PostController {
    private final CreatePostService createPostService;
    private final GetPostsService getPostsService;

    private final GetPostService getPostService;
    private final UpdatePostService updatePostService;
    private final DeletePostService deletePostService;

    public PostController(
            CreatePostService createPostService,
            GetPostsService getPostsService,
            GetPostService getPostService,
            UpdatePostService updatePostService,
            DeletePostService deletePostService
    ) {
        this.createPostService = createPostService;
        this.getPostsService = getPostsService;
        this.getPostService = getPostService;
        this.updatePostService = updatePostService;
        this.deletePostService = deletePostService;
    }

    // 게시글 작성
    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public void postPost(@RequestBody CreatePostDto createPostDto) {
        this.createPostService.createPost(createPostDto);
    }

    // 게시글 조회
    @GetMapping("")
    public List<PostDto> getPosts() {
        return this.getPostsService.getPosts();
    }

    // 게시글 상세 조회
    @GetMapping("/{id}")
    public PostDto getPost(@PathVariable String id) {
        return this.getPostService.getPost(id);
    }

    // 게시글 수정
    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void patchPost(@PathVariable String id, @RequestBody UpdatePostDto updatePostDto) {
        this.updatePostService.updatePost(id, updatePostDto);
    }

    // 게시글 삭제
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePost(@PathVariable String id) {
        this.deletePostService.deletePost(id);
    }

    @ExceptionHandler(PostNotFound.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String postNotFound() {
        return "게시물을 찾을 수 없습니다.";
    }
}
