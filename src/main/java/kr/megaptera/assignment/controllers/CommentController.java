package kr.megaptera.assignment.controllers;

import kr.megaptera.assignment.dtos.CommentCreateDto;
import kr.megaptera.assignment.dtos.CommentDetailDto;
import kr.megaptera.assignment.dtos.CommentUpdateDto;
import kr.megaptera.assignment.services.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comments")
public class CommentController {
    private final ListCommentService listCommentService;
    private final DetailCommentService detailCommentService;
    private final CreateCommentService createCommentService;
    private final UpdateCommentService updateCommentService;
    private final DeleteCommentService deleteCommentService;

    public CommentController(ListCommentService listCommentService, DetailCommentService detailCommentService, CreateCommentService createCommentService, UpdateCommentService updateCommentService, DeleteCommentService deleteCommentService) {
        this.listCommentService = listCommentService;
        this.detailCommentService = detailCommentService;
        this.createCommentService = createCommentService;
        this.updateCommentService = updateCommentService;
        this.deleteCommentService = deleteCommentService;
    }

    @GetMapping
    public List<CommentDetailDto> list(@RequestParam String postId) {
        return listCommentService.list(postId);
    }

    @GetMapping("/{id}")
    public CommentDetailDto detail(@PathVariable String id, @RequestParam String postId) {
        return detailCommentService.detail(id, postId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestParam String postId, @RequestBody CommentCreateDto dto) {
        createCommentService.create(postId, dto);
    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable String id, @RequestParam String postId, @RequestBody CommentUpdateDto dto) {
        updateCommentService.update(id, postId, dto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable String id, @RequestParam String postId) {
        deleteCommentService.delete(id, postId);
    }
}
