package kr.megaptera.assignment.controller;

import kr.megaptera.assignment.dto.*;
import kr.megaptera.assignment.service.CommentService;
import kr.megaptera.assignment.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/comments")
public class CommentController {
    private final CommentService commentService;

    @Autowired
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<CommentResponse> list(@RequestParam("postId") String postId) {
        List<CommentResponse> commentResponseList = commentService.listCommentsByPostId(postId);

        return commentResponseList;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestParam("postId") String postId, @RequestBody CommentCreateRequest request) {
        commentService.create(request, postId);
    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@RequestBody CommentUpdateRequest request, @PathVariable String id, @RequestParam("postId") String postId) {
        commentService.update(request,id,postId);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable String id, @RequestParam String postId) {
        commentService.delete(id,postId);
    }
}
