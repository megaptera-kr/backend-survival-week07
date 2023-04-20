package kr.megaptera.assignment.controllers;

import kr.megaptera.assignment.applications.*;
import kr.megaptera.assignment.dtos.*;
import kr.megaptera.assignment.exceptions.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/comments")
@CrossOrigin
public class CommentController {
    private final CreateCommentService createCommentService;
    private final GetCommentsService getCommentsService;
    private final UpdateCommentService updateCommentService;
    private final DeleteCommentService deleteCommentService;

    public CommentController(CreateCommentService createCommentService, GetCommentsService getCommentsService, UpdateCommentService updateCommentService, DeleteCommentService deleteCommentService) {
        this.createCommentService = createCommentService;
        this.getCommentsService = getCommentsService;
        this.updateCommentService = updateCommentService;
        this.deleteCommentService = deleteCommentService;
    }

    @GetMapping
    public List<CommentDto> list(@RequestParam String postId) {
        List<CommentDto> commentList = getCommentsService.getCommentDtos(postId);
        return commentList;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createComment(@RequestParam String postId,
                              @RequestBody CommentCreateDto commentCreateDto) {
        createCommentService.createCommentDto(postId, commentCreateDto);
    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateComment(@PathVariable String id,
                              @RequestParam String postId, @RequestBody CommentUpdateDto commentUpdateDto) {
        updateCommentService.updateCommentDto(id, postId, commentUpdateDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteComment(@PathVariable String id, @RequestParam String postId) {
        deleteCommentService.delete(id, postId);
    }

    @ExceptionHandler(CommentNotFound.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String commentNotFound() {
        return "댓글을 찾을 수 없습니다.";
    }
}
