package kr.megaptera.assignment.controllers;

import kr.megaptera.assignment.dtos.CommentDto;
import kr.megaptera.assignment.dtos.PostDto;
import kr.megaptera.assignment.services.CommentService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/comments")
@CrossOrigin
public class CommentController {
    private final CommentService commentService;
    @GetMapping("/{postId}")
    public List<CommentDto> commentList(@RequestParam String postId){
        return commentService.allLists(postId);
    }
    @PostMapping("/{postId}")
    @ResponseStatus(HttpStatus.CREATED)
    public void createComment(@RequestParam String postId,
                            @RequestBody CommentDto commentDto){
        commentDto.setId(postId);
        commentService.createComment(commentDto);
    }


}
