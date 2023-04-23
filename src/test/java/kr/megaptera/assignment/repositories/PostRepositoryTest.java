package kr.megaptera.assignment.repositories;

import jakarta.transaction.Transactional;
import kr.megaptera.assignment.models.posts.MultilineText;
import kr.megaptera.assignment.models.posts.Post;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class PostRepositoryTest {

    @Autowired
    private PostRepository postRepository;

    // TODO : (dh) JPA DB 관련된 내용이 한개만 작성했을때 하고, 다른 DB 사용하는 테스트가 발생하니 테스트가 불가능함!
    // .Net 에서 익히 사용하던 전략은 테스트 상에서는 DB Context (DAO) 를 테스트시에 in-memory 로 사용하였었는데.. 이건 좀?
    @Test
    public void crudTest() {
        var newPost = new Post("crud_test_id", "title", "author", new MultilineText("content"));
        var savedPost = postRepository.save(newPost);

        savedPost.setTitle("changed title");
        savedPost.setAuthor("changed author");
        savedPost.getContent().appendText("changed content");

        var updatedPost = postRepository.save(newPost);
        assertThat(updatedPost).isNotNull();
        assertThat(updatedPost.getContent()).isEqualTo(newPost.getContent());
        assertThat(updatedPost.getAuthor()).isEqualTo(newPost.getAuthor());
        assertThat(updatedPost.getContent().toString()).isEqualTo(newPost.getContent().toString());

        postRepository.deleteById(updatedPost.getId());

        var allPosts = postRepository.findAll();
        assertThat(allPosts).hasSize(0);
    }
}