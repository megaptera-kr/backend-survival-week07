package kr.megaptera.assignment.service;

import kr.megaptera.assignment.daos.PostDao;
import kr.megaptera.assignment.dtos.PostDto;
import org.springframework.stereotype.Service;

@Service
public class PostService {
    private final PostDao postDao;

    public PostService(PostDao postDao) {
        this.postDao = postDao;
    }

    public PostDto getPost(PostDto postDto) {
        return postDao.getPost(postDto);
    }
}
