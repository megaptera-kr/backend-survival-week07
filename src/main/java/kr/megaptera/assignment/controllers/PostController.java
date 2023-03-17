package kr.megaptera.assignment.controllers;

import kr.megaptera.assignment.applications.post.*;
import kr.megaptera.assignment.dtos.post.*;
import kr.megaptera.assignment.exceptions.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@CrossOrigin
@RequestMapping("/posts")
public class PostController {
    private final GetPostDtosService getPostDtosService;
    private final GetPostDtoService getPostDtoService;
    private final CreatePostDtoService createPostDtoService;
    private final UpdatePostDtoService updatePostDtoService;
    private final DeletePostDtoService deletePostDtoService;

    public PostController(GetPostDtosService getPostDtosService,
                          GetPostDtoService getPostDtoService,
                          CreatePostDtoService createPostDtoService,
                          UpdatePostDtoService updatePostDtoService,
                          DeletePostDtoService deletePostDtoService) {
        this.getPostDtosService = getPostDtosService;
        this.getPostDtoService = getPostDtoService;
        this.createPostDtoService = createPostDtoService;
        this.updatePostDtoService = updatePostDtoService;
        this.deletePostDtoService = deletePostDtoService;
    }

    @GetMapping
    public List<PostDTO> list() {
        List<PostDTO> postDtos = getPostDtosService.getPostDtos();
        return postDtos;
    }

    @GetMapping("/{id}")
    public PostDTO detail(
            @PathVariable String id
    ) {
        PostDTO postDto = getPostDtoService.getPostDto(id);
        return postDto;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PostDTO create(
            @RequestBody PostCreateDTO postCreateDTO
    ) {
        PostDTO postDTO = createPostDtoService.create(postCreateDTO);
        return postDTO;
    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(
            @PathVariable String id,
            @RequestBody PostUpdateDTO postUpdateDTO
    ) {
        updatePostDtoService.update(id, postUpdateDTO);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(
            @PathVariable String id
    ) {
        deletePostDtoService.delete(id);
    }

    @ExceptionHandler(PostNotFound.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String postNotFound() {
        return "Post Not Exist..";
    }
}
