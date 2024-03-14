package kr.megaptera.assignment.controllers;

import kr.megaptera.assignment.dtos.PostDto;
import kr.megaptera.assignment.service.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/posts")
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
    public List<PostDto> getPostList() {
        return postListService.getPostList();
    }
}
