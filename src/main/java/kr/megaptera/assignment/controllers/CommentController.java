package kr.megaptera.assignment.controllers;

import kr.megaptera.assignment.dtos.CommentDto;
import kr.megaptera.assignment.service.CommentDeleteService;
import kr.megaptera.assignment.service.CommentInsertService;
import kr.megaptera.assignment.service.CommentListService;
import kr.megaptera.assignment.service.CommentUpdateService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comments")
@CrossOrigin
public class CommentController {
    private final CommentListService commentListService;
    private final CommentInsertService commentInsertService;
    private final CommentUpdateService commentUpdateService;
    private final CommentDeleteService commentDeleteService;

    public CommentController(CommentListService commentListService
            , CommentInsertService commentInsertService
            , CommentUpdateService commentUpdateService
            , CommentDeleteService commentDeleteService) {
        this.commentListService = commentListService;
        this.commentInsertService = commentInsertService;
        this.commentUpdateService = commentUpdateService;
        this.commentDeleteService = commentDeleteService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<CommentDto> getCommentList(@RequestParam String postId) {
        CommentDto commentDto = new CommentDto(postId);
        return commentListService.getCommentList(commentDto);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void insertComment(@RequestParam String postId, @RequestBody CommentDto commentDto) {
        commentDto.setPostId(postId);
        commentInsertService.insertComment(commentDto);
    }

    @PatchMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateComment(@PathVariable("id") String id
            , @RequestParam("postId") String postId
            , @RequestBody CommentDto commentDto) {
        commentDto.setId(id);
        commentDto.setPostId(postId);
        commentUpdateService.updateComment(commentDto);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteComment(@PathVariable("id") String id
            , @RequestParam("postId") String postId) {
        CommentDto commentDto = new CommentDto(id, postId);
        commentDeleteService.deleteComment(commentDto);
    }
}
