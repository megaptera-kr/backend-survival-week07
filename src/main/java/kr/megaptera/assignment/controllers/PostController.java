package kr.megaptera.assignment.controllers;

import kr.megaptera.assignment.applications.*;
import kr.megaptera.assignment.dtos.PostCreateDto;
import kr.megaptera.assignment.dtos.PostDto;
import kr.megaptera.assignment.dtos.PostUpdateDto;
import kr.megaptera.assignment.exceptions.PostNotFound;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/posts")
@CrossOrigin
public class PostController {
    private final GetPostService getPostService;
    private final GetPostsService getPostsService;
    private final CreatePostService createPostService;
    private final UpdatePostService updatePostService;
    private final DeletePostService deletePostService;

    public PostController(CreatePostService createPostService,
                          GetPostService getPostService,
                          GetPostsService getPostsService,
                          UpdatePostService updatePostService,
                          DeletePostService deletePostService) {
        this.createPostService = createPostService;
        this.getPostService = getPostService;
        this.getPostsService = getPostsService;
        this.updatePostService = updatePostService;
        this.deletePostService = deletePostService;
    }

    @GetMapping
    public List<PostDto> getPosts(){
        return getPostsService.getPosts();
    }

    @GetMapping("/{id}")
    public PostDto getPost(@PathVariable String id){
        return getPostService.findById(id);
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PostDto createPost(@RequestBody PostCreateDto postDto){
        return createPostService.createPost(postDto);
    }

    @PatchMapping("/{id}")
    public void updatePost(@PathVariable String id, @RequestBody PostUpdateDto postUpdateDto){
        updatePostService.updatePost(id, postUpdateDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePost(@PathVariable String id){
        deletePostService.deletePost(id);
    }

    @ExceptionHandler(PostNotFound.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String postNotFound(){
        return "게시물을 찾을 수 없습니다.";
    }
}
