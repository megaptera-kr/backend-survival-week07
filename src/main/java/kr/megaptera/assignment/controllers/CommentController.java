package kr.megaptera.assignment.controllers;

import kr.megaptera.assignment.applications.comment.*;
import kr.megaptera.assignment.dtos.comment.*;
import kr.megaptera.assignment.exceptions.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@CrossOrigin
@RequestMapping("/comments")
public class CommentController {
    private final GetCommentDtosService getCommentDtosService;
    private final CreateCommentService createCommentService;
    private final UpdateCommentService updateCommentService;
    private final DeleteCommentService deleteCommentService;

    public CommentController(GetCommentDtosService getCommentDtosService,
                             CreateCommentService createCommentService,
                             UpdateCommentService updateCommentService,
                             DeleteCommentService deleteCommentService) {
        this.getCommentDtosService = getCommentDtosService;
        this.createCommentService = createCommentService;
        this.updateCommentService = updateCommentService;
        this.deleteCommentService = deleteCommentService;
    }

    @GetMapping
    public List<CommentDTO> list(
            @RequestParam String postId
    ) {
        List<CommentDTO> commentDtos = getCommentDtosService.getCommentDtos(postId);
        return commentDtos;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CommentDTO create(
            @RequestParam String postId,
            @RequestBody CommentCreateDTO commentCreateDTO
    ) {
        CommentDTO commentDTO = createCommentService.create(postId, commentCreateDTO);

        return commentDTO;
    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(
            @PathVariable String commentId,
            @RequestParam String postId,
            @RequestBody CommentUpdateDTO commentUpdateDTO
    ) {
        updateCommentService.update(commentId, postId, commentUpdateDTO);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(
            @PathVariable String commentId,
            @RequestParam String postId
    ) {
        deleteCommentService.delete(commentId, postId);
    }

    @ExceptionHandler(CommentNotFound.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String commentNotFound() {
        return "Comment Not Found..";
    }
}
