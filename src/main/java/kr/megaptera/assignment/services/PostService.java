package kr.megaptera.assignment.services;

import kr.megaptera.assignment.dtos.PostDto;
import kr.megaptera.assignment.entities.Post;
import kr.megaptera.assignment.exceptions.PostNotFoundExceptions;
import kr.megaptera.assignment.repositories.PostRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Transactional
public class PostService {

    private final PostRepository postRepository;

    public void createPost(PostDto dto){
        Post post = new Post(dto.getTitle(), dto.getAuthor(), dto.getContent());
        postRepository.save(post);
    }
    public List<PostDto> allLists(){

        List<Post> all = postRepository.findAll();
        List<PostDto> collect = all.stream()
                .map(post -> new PostDto(post))
                .collect(Collectors.toList());

        return collect;
    }

    public PostDto getPostDetail(String id){
        System.out.println("getPostDetail id::"+Long.parseLong(id));

        Optional<Post> byId = postRepository.findById(Long.parseLong(id));
        if(byId.get() != null) {
            PostDto postDto = new PostDto(byId.get());
            return postDto;
        } else throw new PostNotFoundExceptions();
    }

    public void modifyPost(PostDto postDto){
        Optional<Post> byId = postRepository.findById(Long.parseLong(postDto.getId()));
        if(byId.get() != null) {
            Post post = byId.get();
            post.setPost(postDto.getTitle(), post.getAuthor(), postDto.getContent());
            postRepository.flush();
        }
        else throw new PostNotFoundExceptions();
    }

    public int deletePost(String id){
        int count = postRepository.deleteByIdCount(Long.parseLong(id));
        return count;
    }


}
