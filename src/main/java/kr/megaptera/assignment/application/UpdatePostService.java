package kr.megaptera.assignment.application;

import jakarta.transaction.Transactional;
import kr.megaptera.assignment.dtos.PostUpdateDto;
import kr.megaptera.assignment.models.MultilineText;
import kr.megaptera.assignment.models.Post;
import kr.megaptera.assignment.models.PostId;
import kr.megaptera.assignment.repositories.PostRepository;
import org.springframework.stereotype.Service;

@Service
public class UpdatePostService {

//    // JDBC
//    private final JdbcPostDao postDao;
//
//    public UpdatePostService(JdbcPostDao postDao) {
//        this.postDao = postDao;
//    }
//
//    public void updatePost(String id, PostUpdateDto postUpdateDto) {
//        Post post = postDao.find(PostId.of(id));
//
//        post.update(
//                postUpdateDto.getTitle(),
//                MultilineText.of(postUpdateDto.getContent())
//        );
//
//        postDao.save(post);
//    }

    // JPA
    private final PostRepository postRepository;

    public UpdatePostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Transactional
    public void updatePost(String id, PostUpdateDto postUpdateDto) {
        Post post = postRepository.findById(PostId.of(id)).orElseThrow();

        post.update(
                postUpdateDto.getTitle(),
                MultilineText.of(postUpdateDto.getContent())
        );
    }
}
