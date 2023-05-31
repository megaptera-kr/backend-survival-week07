package kr.megaptera.assignment.controllers;


import kr.megaptera.assignment.dtos.PostDto;
import kr.megaptera.assignment.services.PostService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/posts")
@CrossOrigin
public class PostController {

    private final PostService postService;


    @GetMapping
    public List<PostDto> postList(){
        return postService.allLists();
    }

    @GetMapping("/{id}")
    public PostDto getPostDetail(@PathVariable String id){
        PostDto postDetail = postService.getPostDetail(id);

        return postDetail;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void postPost(@RequestBody PostDto postDto){
        postService.createPost(postDto);
    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void modifyPost(@PathVariable String id, @RequestBody PostDto postDto){
        postDto.setId(id);
        postService.modifyPost(postDto);
    }
    @DeleteMapping("/{id}")
    public void deletePost(@PathVariable String id){
        postService.deletePost(id);
    }
}


