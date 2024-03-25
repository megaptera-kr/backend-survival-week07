package kr.megaptera.assignment.service;

import kr.megaptera.assignment.daos.PostDao;
import kr.megaptera.assignment.dtos.PostDto;
import org.springframework.stereotype.Service;

@Service
public class PostInsertService {
    private final PostDao postDao;

    public PostInsertService(PostDao postDao) {
        this.postDao = postDao;
    }

    public void insertPost(PostDto postDto) {
        postDao.insertPost(postDto);
    }
}
