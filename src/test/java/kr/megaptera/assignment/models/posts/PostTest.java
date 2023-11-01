package kr.megaptera.assignment.models.posts;

import jakarta.transaction.Transactional;
import kr.megaptera.assignment.repositories.PostRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class PostTest {
    @Autowired
    PostRepository postRepository;

    @Test
    @DisplayName("addComment")
    void testAddComment() {
        String mockPostTitle = "title";
        String mockPostAuthor = "author";
        String mockPostContent = "content";

        String mockCommentAuthor = "author";
        String mockCommentContent = "content";

        Post newPost = new Post(mockPostTitle, mockPostAuthor, mockPostContent);

        this.postRepository.save(newPost);

        newPost.addComment(mockCommentAuthor, mockCommentContent);

        assertThat(newPost.findAllComments()).hasSize(1);
    }
}