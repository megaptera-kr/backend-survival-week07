package kr.megaptera.assignment.applications.comment;

import jakarta.transaction.*;
import kr.megaptera.assignment.dtos.comment.*;
import kr.megaptera.assignment.exceptions.*;
import kr.megaptera.assignment.models.*;
import kr.megaptera.assignment.repositories.*;
import org.springframework.stereotype.*;

import java.util.*;

@Service
@Transactional
public class GetCommentDtosService {

    private final PostRepository postRepository;

    public GetCommentDtosService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public List<CommentDTO> getCommentDtos(String postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(PostNotFound::new);
        List<Comment> comments = post.list();
        return comments.stream().map(CommentDTO::new).toList();
    }
}
