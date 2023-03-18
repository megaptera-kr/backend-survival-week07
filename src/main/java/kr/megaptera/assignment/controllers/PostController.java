package kr.megaptera.assignment.controllers;

import java.util.List;
import kr.megaptera.assignment.dtos.PostCreateDto;
import kr.megaptera.assignment.dtos.PostDto;
import kr.megaptera.assignment.dtos.PostUpdateDto;
import kr.megaptera.assignment.services.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/posts")
@CrossOrigin("*")
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    // 게시글 전체 조회 API
    @GetMapping
    public List<PostDto> getPostList() {
        return postService.getPostList();
    }

    // 게시글 등록 API
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void savePost(@RequestBody PostCreateDto postCreateDto) {
        postService.savePost(postCreateDto);
    }

    // 게시글 상세 조회 API
    @GetMapping("/{postId}")
    public PostDto getPostDetail(@PathVariable String postId) throws Exception {
        return postService.getPostDetail(postId);
    }

    // 게시글 수정 API
    @PatchMapping("/{postId}")
    public PostDto updatePost(@PathVariable String postId,
        @RequestBody PostUpdateDto postUpdateDto) throws Exception {
        return postService.updatePost(postId, postUpdateDto);
    }

    // 게시글 삭제 API
    @DeleteMapping("/{postId}")
    public PostDto deletePost(@PathVariable String postId) throws Exception {
        return postService.deletePost(postId);
    }
}
