package kr.megaptera.assignment.service;

import kr.megaptera.assignment.daos.PostDao;
import kr.megaptera.assignment.dtos.PostDto;
import org.springframework.stereotype.Service;

@Service
public class PostDeleteService {
    private final PostDao postDao;

    public PostDeleteService(PostDao postDao) {
        this.postDao = postDao;
    }

    public void deletePost(PostDto postDto) {
        postDao.updatePost(postDto);
    }
}
