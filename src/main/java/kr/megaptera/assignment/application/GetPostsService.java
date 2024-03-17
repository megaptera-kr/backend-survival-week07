package kr.megaptera.assignment.application;

import kr.megaptera.assignment.dtos.PostDto;
import kr.megaptera.assignment.models.Post;
import kr.megaptera.assignment.repositories.PostRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetPostsService {

//    // JDBC
//    private final JdbcPostDao postDao;
//
//    public GetPostsService(JdbcPostDao postDao) {
//        this.postDao = postDao;
//    }
//
//    public List<PostDto> getPostDtos() {
//        List<Post> posts = postDao.findAll();
//
//        return posts.stream().map(PostDto::new).toList();
//    }

    // JPA
    private final PostRepository postRepository;

    public GetPostsService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public List<PostDto> getPostDtos() {
        List<Post> posts = (List<Post>) postRepository.findAll();

        return posts.stream().map(PostDto::new).toList();
    }
}