package kr.megaptera.assignment.application;

import kr.megaptera.assignment.dtos.PostDto;
import kr.megaptera.assignment.models.Post;
import kr.megaptera.assignment.models.PostId;
import kr.megaptera.assignment.repositories.PostRepository;
import org.springframework.stereotype.Service;

@Service
public class GetPostService {

//    // JDBC
//    private final JdbcPostDao postDao;
//
//    public GetPostService(JdbcPostDao postDao) {
//        this.postDao = postDao;
//    }
//
//    public PostDto getPostDto(String id) {
//        Post post = postDao.find(PostId.of(id));
//
//        return new PostDto(post);
//    }

    // JPA
    private final PostRepository postRepository;

    public GetPostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public PostDto getPostDto(String id) {
        Post post = postRepository.findById(PostId.of(id)).orElseThrow();

        return new PostDto(post);
    }
}