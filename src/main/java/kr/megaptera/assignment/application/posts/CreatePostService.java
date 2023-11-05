package kr.megaptera.assignment.application.posts;

import jakarta.transaction.Transactional;
import kr.megaptera.assignment.dtos.posts.CreatePostDto;
import kr.megaptera.assignment.dtos.posts.PostDto;
import kr.megaptera.assignment.models.columns.Author;
import kr.megaptera.assignment.models.columns.Content;
import kr.megaptera.assignment.models.columns.Title;
import kr.megaptera.assignment.models.posts.Post;
import kr.megaptera.assignment.repositories.PostRepository;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class CreatePostService {
    private final PostRepository postRepository;

    public CreatePostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public PostDto createPost(CreatePostDto createPostDto) {
        Post newPost = new Post(
                new Title(createPostDto.getTitle()),
                new Author(createPostDto.getAuthor()),
                new Content(createPostDto.getContent())
        );

        this.postRepository.save(newPost);

        return PostDto.of(newPost);
    }
}
