package kr.megaptera.assignment.controller;

import kr.megaptera.assignment.dto.PostCreateRequest;
import kr.megaptera.assignment.dto.PostResponse;
import kr.megaptera.assignment.dto.PostUpdateRequest;
import kr.megaptera.assignment.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/posts")
public class PostController {

    private final PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<PostResponse> list() {
        List<PostResponse> postResponseList = postService.list();

        return postResponseList;
    }
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public PostResponse detail(@PathVariable String id) {
        PostResponse postResponse = postService.detail(id);
        return postResponse;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody PostCreateRequest request) {
        postService.create(request);
    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@RequestBody PostUpdateRequest request, @PathVariable String id) {
        postService.update(request,id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable String id) {
        postService.delete(id);
    }
}
