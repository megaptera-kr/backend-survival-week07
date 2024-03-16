package kr.megaptera.assignment.daos;

import kr.megaptera.assignment.dtos.PostDto;
import kr.megaptera.assignment.models.Author;
import kr.megaptera.assignment.models.Content;
import kr.megaptera.assignment.models.Post;
import kr.megaptera.assignment.models.Title;
import kr.megaptera.assignment.repositories.PostRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Component
public class PostDao {
    private final PostRepository postRepository;

    public PostDao(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public List<PostDto> getPostList() {
        Iterator<Post> iterator = postRepository.findAll().iterator();
        List<PostDto> postDtoList = new ArrayList<>();
        while (iterator.hasNext()) {
            Post post = iterator.next();
            postDtoList.add(new PostDto(post));
        }
        return postDtoList;
    }

    public PostDto getPost(PostDto postDto) {
        PostDto getPost = new PostDto(postRepository.findById(postDto.getId()).get());
        return getPost;
    }

    @Transactional
    public void insertPost(PostDto postDto) {
        Post insertPost = new Post(new Author(postDto.getAuthor())
                , new Title(postDto.getTitle())
                , new Content(postDto.getContent()));
        postRepository.save(insertPost);
    }

    @Transactional
    public void updatePost(PostDto postDto) {
        Post updatePost = new Post(postDto.getId()
                , new Title(postDto.getTitle())
                , new Content(postDto.getContent()));
        postRepository.save(updatePost);
    }

    @Transactional
    public void deletePost(PostDto postDto) {
        Post deletePost = new Post(postDto.getId());
        postRepository.delete(deletePost);
    }
}
