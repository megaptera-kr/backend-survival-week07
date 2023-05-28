package kr.megaptera.assignment.controllers;

import kr.megaptera.assignment.application.CreateCommentService;
import kr.megaptera.assignment.application.DeleteCommentService;
import kr.megaptera.assignment.application.GetCommentsService;
import kr.megaptera.assignment.application.UpdateCommentService;
import kr.megaptera.assignment.dtos.CommentCreateDto;
import kr.megaptera.assignment.dtos.CommentDto;
import kr.megaptera.assignment.dtos.CommentUpdateDto;
import kr.megaptera.assignment.exceptions.CommentNotFound;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comments")
@CrossOrigin
public class CommentController {
    private final GetCommentsService getCommentsService;
    private final CreateCommentService createCommentService;
    private final UpdateCommentService updateCommentService;
    private final DeleteCommentService deleteCommentService;

    public CommentController(GetCommentsService getCommentsService,
                             CreateCommentService createCommentService,
                             UpdateCommentService updateCommentService,
                             DeleteCommentService deleteCommentService) {
        this.getCommentsService = getCommentsService;
        this.createCommentService = createCommentService;
        this.updateCommentService = updateCommentService;
        this.deleteCommentService = deleteCommentService;
    }

    @GetMapping
    public List<CommentDto> list(@RequestParam String postId) {
        List<CommentDto> commentDtos =
                getCommentsService.getCommentDtos(postId);

        return commentDtos;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestParam String postId,
                       @RequestBody CommentCreateDto commentCreateDto) {
        createCommentService.createComment(postId, commentCreateDto);
    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(
            @PathVariable String id,
            @RequestParam String postId,
            @RequestBody CommentUpdateDto commentUpdateDto
    ) {
        updateCommentService.updateComment(id, postId, commentUpdateDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(
            @PathVariable String id,
            @RequestParam String postId
    ) {
        deleteCommentService.deleteComment(id, postId);
    }

    @ExceptionHandler(CommentNotFound.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String postNotFound() {
        return "댓글을 찾을 수 없습니다.";
    }
}
