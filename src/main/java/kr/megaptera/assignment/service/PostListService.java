package kr.megaptera.assignment.service;

import kr.megaptera.assignment.daos.PostDao;
import kr.megaptera.assignment.dtos.PostDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostListService {
    private final PostDao postDao;

    public PostListService(PostDao postDao) {
        this.postDao = postDao;
    }

    public List<PostDto> getPostList() {
        return postDao.getPostList();
    }
}
