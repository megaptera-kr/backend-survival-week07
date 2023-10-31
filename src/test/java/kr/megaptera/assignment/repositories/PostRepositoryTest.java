package kr.megaptera.assignment.repositories;

import jakarta.transaction.Transactional;
import kr.megaptera.assignment.models.posts.Post;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class PostRepositoryTest {
    @Autowired
    PostRepository postRepository;

    @Test
    @DisplayName("save")
    void testSave() {
        String mockTitle = "title";
        String mockAuthor = "author";
        String mockContent = "content";

        int initialPostSize = this.postRepository.findAll().size();

        Post newPost = new Post(mockTitle, mockAuthor, mockContent);

        assertThat(newPost.getId()).isNull();

        this.postRepository.save(newPost);

        assertThat(newPost.getId()).isNotNull();

        assertThat(this.postRepository.findAll()).hasSize(initialPostSize + 1);
    }

    @Test
    @DisplayName("findAll")
    void testFindAll() {
        List<Post> listPost = this.postRepository.findAll();

        assertThat(listPost).hasSize(0);
    }

    @Test
    @DisplayName("findById")
    void testFindById() {
        String mockTitle = "title";
        String mockAuthor = "author";
        String mockContent = "content";
        
        Post newPost = new Post(mockTitle, mockAuthor, mockContent);

        this.postRepository.save(newPost);

        Optional<Post> foundPost = this.postRepository.findById(newPost.getId());

        assertThat(foundPost).isNotNull();
    }

    @Test
    @DisplayName("delete")
    void testDelete() {
        String mockTitle = "title";
        String mockAuthor = "author";
        String mockContent = "content";

        int initialPostSize = this.postRepository.findAll().size();

        Post newPost = new Post(mockTitle, mockAuthor, mockContent);

        this.postRepository.save(newPost);

        assertThat(this.postRepository.findAll()).hasSize(initialPostSize + 1);

        this.postRepository.delete(newPost);

        assertThat(this.postRepository.findAll()).hasSize(initialPostSize);
    }

}