package kr.megaptera.assignment.controllers;

import kr.megaptera.assignment.services.CommentService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/comments")
@CrossOrigin
public class CommentController {
    private final CommentService commentService;

}
