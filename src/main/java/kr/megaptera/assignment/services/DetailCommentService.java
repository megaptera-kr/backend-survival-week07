package kr.megaptera.assignment.services;

import kr.megaptera.assignment.dtos.CommentDetailDto;
import kr.megaptera.assignment.mappers.CommentMapper;
import kr.megaptera.assignment.repositories.PostRepository;
import kr.megaptera.assignment.vos.CommentId;
import kr.megaptera.assignment.vos.PostId;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class DetailCommentService {
    private final PostRepository postRepository;

    public DetailCommentService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public CommentDetailDto detail(String id, String postId) {
        return postRepository
                .findById(PostId.of(UUID.fromString(postId)))
                .flatMap(entity -> entity
                        .getComment(CommentId.of(UUID.fromString(id)))
                        .map(CommentMapper::commentDetailDto))
                .orElse(null);
    }
}
