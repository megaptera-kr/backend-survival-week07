package kr.megaptera.assignment.controllers;

import kr.megaptera.assignment.application.CreatePostService;
import kr.megaptera.assignment.application.DeletePostService;
import kr.megaptera.assignment.application.GetPostService;
import kr.megaptera.assignment.application.UpdatePostService;
import kr.megaptera.assignment.dtos.PostCreateDto;
import kr.megaptera.assignment.dtos.PostDto;
import kr.megaptera.assignment.dtos.PostUpdateDto;
import kr.megaptera.assignment.exceptions.PostNotFound;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/posts")
@CrossOrigin
public class PostController {
    private final GetPostService getPostService;
    private final CreatePostService createPostService;
    private final UpdatePostService updatePostService;
    private final DeletePostService deletePostService;

    public PostController(GetPostService getPostService,
                          CreatePostService createPostService,
                          UpdatePostService updatePostService,
                          DeletePostService deletePostService) {
        this.getPostService = getPostService;
        this.createPostService = createPostService;
        this.updatePostService = updatePostService;
        this.deletePostService = deletePostService;
    }

    @GetMapping
    private List<PostDto> list() {
        return getPostService.getPostDtos();
    }

    @GetMapping("/{id}")
    private PostDto detail(
        @PathVariable String id
    ) {
        return getPostService.getPostDto(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    private void create(
        @RequestBody PostCreateDto postCreateDto
    ) {
        createPostService.create(postCreateDto);
    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    private void update(
        @PathVariable String id,
        @RequestBody PostUpdateDto postUpdateDto
    ) {
        updatePostService.update(id, postUpdateDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    private void delete(@PathVariable String id) {
        deletePostService.delete(id);
    }

    @ExceptionHandler(PostNotFound.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    private PostNotFound postNotFound() {
        return new PostNotFound();
    }
}
