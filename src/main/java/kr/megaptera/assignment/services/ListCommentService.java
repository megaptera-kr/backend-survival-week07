package kr.megaptera.assignment.services;

import kr.megaptera.assignment.dtos.CommentDetailDto;
import kr.megaptera.assignment.entities.PostEntity;
import kr.megaptera.assignment.mappers.CommentMapper;
import kr.megaptera.assignment.repositories.PostRepository;
import kr.megaptera.assignment.vos.PostId;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class ListCommentService {
    private final PostRepository postRepository;

    public ListCommentService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public List<CommentDetailDto> list(String id) {
        return postRepository
                .findById(PostId.of(UUID.fromString(id)))
                .map(PostEntity::getComments)
                .orElseGet(ArrayList::new)
                .stream()
                .map(CommentMapper::commentDetailDto)
                .toList();
    }
}
