package kr.megaptera.assignment.controllers;

import kr.megaptera.assignment.application.comments.CreateCommentService;
import kr.megaptera.assignment.application.comments.DeleteCommentService;
import kr.megaptera.assignment.application.comments.GetCommentsService;
import kr.megaptera.assignment.application.comments.UpdateCommentService;
import kr.megaptera.assignment.dtos.comments.CommentDto;
import kr.megaptera.assignment.dtos.comments.CreateCommentDto;
import kr.megaptera.assignment.dtos.comments.UpdateCommentDto;
import kr.megaptera.assignment.exceptions.CommentNotFound;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comments")
public class CommentController {
    private final CreateCommentService createCommentService;
    private final GetCommentsService getCommentsService;
    private final UpdateCommentService updateCommentService;
    private final DeleteCommentService deleteCommentService;

    public CommentController(
            CreateCommentService createCommentService,
            GetCommentsService getCommentsService,
            UpdateCommentService updateCommentService,
            DeleteCommentService deleteCommentService
    ) {
        this.createCommentService = createCommentService;
        this.getCommentsService = getCommentsService;
        this.updateCommentService = updateCommentService;
        this.deleteCommentService = deleteCommentService;
    }

    // 댓글 작성
    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public void postComment(@RequestParam String postId, @RequestBody CreateCommentDto createCommentDto) {
        this.createCommentService.createComment(postId, createCommentDto);
    }

    // 댓글 조회
    @GetMapping("")
    public List<CommentDto> getComments(@RequestParam String postId) {
        return this.getCommentsService.getComments(postId);
    }

    // 댓글 수정
    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void patchComment(
            @PathVariable String id,
            @RequestParam String postId,
            @RequestBody UpdateCommentDto updateCommentDto
    ) {
        this.updateCommentService.updateComment(id, updateCommentDto);
    }

    // 댓글 삭제
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteComment(@PathVariable String id, @RequestParam String postId) {
        this.deleteCommentService.deleteComment(postId, id);
    }

    @ExceptionHandler(CommentNotFound.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String notFoundComment() {
        return "댓글을 찾을 수 없습니다.";
    }
}
