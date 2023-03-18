package kr.megaptera.assignment.controllers;

import java.util.List;
import kr.megaptera.assignment.dtos.CommentCreateDto;
import kr.megaptera.assignment.dtos.CommentDto;
import kr.megaptera.assignment.dtos.CommentUpdateDto;
import kr.megaptera.assignment.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/comments")
@CrossOrigin
public class CommentController {

    @Autowired
    private CommentService commentService;

    // 댓글 전체 조회
    @GetMapping
    public List<CommentDto> getCommentList(@RequestParam String postId) {
        return commentService.getCommentList(postId);
    }

    // 댓글 작성
    @PostMapping
    public void saveComment(@RequestParam String postId,
        @RequestBody CommentCreateDto commentCreateDto) {
        commentService.saveComment(postId, commentCreateDto);
    }

    // 댓글 수정
    @PatchMapping("/{commentId}")
    public CommentDto updateComment(@PathVariable String commentId, @RequestParam String postId,
        @RequestBody CommentUpdateDto commentUpdateDto) {
        return commentService.updateComment(commentId, commentUpdateDto);
    }

    // 댓글 삭제
    @DeleteMapping("/{commentId}")
    public CommentDto deleteComment(@PathVariable String commentId, @RequestParam String postId) {
        return commentService.deleteComment(commentId);
    }
}
