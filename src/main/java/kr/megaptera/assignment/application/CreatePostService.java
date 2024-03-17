package kr.megaptera.assignment.application;

import kr.megaptera.assignment.dtos.PostCreateDto;
import kr.megaptera.assignment.models.MultilineText;
import kr.megaptera.assignment.models.Post;
import kr.megaptera.assignment.repositories.PostRepository;
import org.springframework.stereotype.Service;

@Service
public class CreatePostService {

//    // JDBC
//    private final JdbcPostDao postDao;
//
//    public CreatePostService(JdbcPostDao postDao) {
//        this.postDao = postDao;
//    }
//
//    public void createPost(PostCreateDto postCreateDto) {
//        Post post = new Post(
//                postCreateDto.getTitle(),
//                postCreateDto.getAuthor(),
//                MultilineText.of(postCreateDto.getContent())
//        );
//
//        postDao.save(post);
//    }

    // JPA
    private final PostRepository postRepository;

    public CreatePostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public void createPost(PostCreateDto postCreateDto) {
        Post post = new Post(
                postCreateDto.getTitle(),
                postCreateDto.getAuthor(),
                MultilineText.of(postCreateDto.getContent())
        );

        postRepository.save(post);
    }
}