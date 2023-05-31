package kr.megaptera.assignment.repositories;

import kr.megaptera.assignment.entities.Post;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
@Rollback(false)
class PostRepositoryTest {

    @Autowired
    private PostRepository postRepository;



    @Test
    void 객체생성_인서트_삭제(){

        Post post = 객체생성_및_인서트();

        Optional<Post> byId = postRepository.findById(post.getId());
        assertThat(byId.get()).isNotNull();

        //delete
        int result = postRepository.deleteByIdCount(post.getId());
        assertThat(result).isEqualTo(1);
    }

    @Test
    void 객체생성_인서트_수정(){

        Post post = 객체생성_및_인서트();

        Optional<Post> byId = postRepository.findById(post.getId());
        assertThat(byId.get()).isNotNull();

        //modify
        Post getPost = byId.get();
        System.out.println("수정전:"+getPost.toString());
        getPost.setPost("수정", "park", "수정수정");
        postRepository.flush();

        //get
        Optional<Post> getPostAgain = postRepository.findById(post.getId());
        System.out.println("수정후:"+getPostAgain.toString());
        assertThat(getPostAgain.get().getTitle()).isEqualTo("수정");
    }

    Post 객체생성_및_인서트(){

        Post post = new Post( "hello2", "kim2", "안녕하세요2");

        //insert
        postRepository.save(post);
        System.out.println("id: "+post.getId());
        postRepository.flush();
        return post;
    }
}