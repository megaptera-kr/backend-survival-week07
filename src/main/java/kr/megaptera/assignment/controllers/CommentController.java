package kr.megaptera.assignment.controllers;

import kr.megaptera.assignment.applications.CreateCommentService;
import kr.megaptera.assignment.applications.DeleteCommentService;
import kr.megaptera.assignment.applications.GetCommentsService;
import kr.megaptera.assignment.applications.UpdateCommentService;
import kr.megaptera.assignment.dtos.CommentDto;
import kr.megaptera.assignment.dtos.CommentUpdateDto;
import kr.megaptera.assignment.dtos.CreateCommentDto;
import kr.megaptera.assignment.exceptions.CommentNotFound;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comments")
@CrossOrigin
public class CommentController {
    private GetCommentsService getCommentsService;
    private CreateCommentService createCommentService;
    private UpdateCommentService updateCommentService;
    private DeleteCommentService deleteCommentService;

    public CommentController(GetCommentsService getCommentsService,
                             CreateCommentService createCommentService,
                             UpdateCommentService updateCommentService,
                             DeleteCommentService deleteCommentService) {
        this.getCommentsService = getCommentsService;
        this.createCommentService = createCommentService;
        this.updateCommentService = updateCommentService;
        this.deleteCommentService = deleteCommentService;
    }

    @GetMapping("/{postId}")
    public List<CommentDto> getCommentList(@PathVariable String postId){
        return getCommentsService.getComments(postId);
    }

    @PostMapping("/{postId}")
    @ResponseStatus(HttpStatus.CREATED)
    public void createComment(@PathVariable String postId, @RequestBody CreateCommentDto createCommentDto){
        createCommentService.createComment(postId, createCommentDto);
    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateComment(@PathVariable String id, @RequestParam String postId, @RequestBody CommentUpdateDto commentUpdateDto){
        updateCommentService.updateComment(id, postId, commentUpdateDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCommet(@PathVariable String id, @RequestParam String postId){
        deleteCommentService.deleteComment(id, postId);
    }

    @ExceptionHandler(CommentNotFound.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String postNotFound(){
        return "댓글을 찾을 수 없습니다.";
    }
}
