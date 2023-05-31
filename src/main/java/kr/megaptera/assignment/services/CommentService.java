package kr.megaptera.assignment.services;

import kr.megaptera.assignment.dtos.CommentDto;
import kr.megaptera.assignment.dtos.PostDto;
import kr.megaptera.assignment.entities.Comment;
import kr.megaptera.assignment.entities.Post;
import kr.megaptera.assignment.exceptions.PostNotFoundExceptions;
import kr.megaptera.assignment.repositories.CommentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Transactional
public class CommentService {
    private final CommentRepository commentRepository;
    public void createComment(CommentDto dto){
        Comment comment = new Comment(dto.getAuthor(), dto.getContent());
        commentRepository.save(comment);
    }
    public List<CommentDto> allLists(String postId){

        List<Comment> allById = commentRepository.findAllByPostComment(Long.parseLong(postId));
        List<CommentDto> collect = allById.stream()
                .map(post -> new CommentDto(post))
                .collect(Collectors.toList());

        return collect;
    }



    public void modifyPost(PostDto postDto){
        Optional<Comment> byId = commentRepository.findById(Long.parseLong(postDto.getId()));
        if(byId.get() != null) {
            Comment dto = byId.get();
            dto.setComment(dto.getAuthor(), postDto.getContent());
            commentRepository.flush();
        }
        else throw new PostNotFoundExceptions();
    }

    public int deletePost(String id){
        int count = commentRepository.deleteByIdCount(Long.parseLong(id));
        return count;
    }


}
