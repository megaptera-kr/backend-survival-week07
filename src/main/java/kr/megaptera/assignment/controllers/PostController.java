package kr.megaptera.assignment.controllers;

import kr.megaptera.assignment.dtos.PostCreateDto;
import kr.megaptera.assignment.dtos.PostDetailDto;
import kr.megaptera.assignment.dtos.PostUpdateDto;
import kr.megaptera.assignment.services.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/posts")
public class PostController {
    private final ListPostService listPostService;
    private final DetailPostService detailPostService;
    private final CreatePostService createPostService;

    private final UpdatePostService updatePostService;
    private final DeletePostService deletePostService;

    public PostController(ListPostService listPostService, DetailPostService detailPostService, CreatePostService createPostService, UpdatePostService updatePostService, DeletePostService deletePostService) {
        this.listPostService = listPostService;
        this.detailPostService = detailPostService;
        this.createPostService = createPostService;
        this.updatePostService = updatePostService;
        this.deletePostService = deletePostService;
    }

    @GetMapping
    public List<PostDetailDto> list() {
        return listPostService.list();
    }

    @GetMapping("/{id}")
    public PostDetailDto detail(@PathVariable String id) {
        return detailPostService.detail(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody PostCreateDto dto) {
        createPostService.create(dto);
    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable String id, @RequestBody PostUpdateDto dto) {
        updatePostService.update(id, dto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable String id) {
        deletePostService.delete(id);
    }
}
