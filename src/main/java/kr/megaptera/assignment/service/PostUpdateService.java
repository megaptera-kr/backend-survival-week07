package kr.megaptera.assignment.service;

import kr.megaptera.assignment.daos.PostDao;
import kr.megaptera.assignment.dtos.PostDto;
import org.springframework.stereotype.Service;

@Service
public class PostUpdateService {
    private final PostDao postDao;

    public PostUpdateService(PostDao postDao) {
        this.postDao = postDao;
    }

    public void updatePost(PostDto postDto) {
        postDao.updatePost(postDto);
    }
}
