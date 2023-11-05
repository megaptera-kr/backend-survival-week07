package kr.megaptera.assignment.models.posts;

import jakarta.transaction.Transactional;
import kr.megaptera.assignment.models.columns.Author;
import kr.megaptera.assignment.models.columns.Content;
import kr.megaptera.assignment.models.columns.Title;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class PostTest {

    @Test
    @DisplayName("addComment")
    void testAddComment() {
        Title mockPostTitle = new Title("title");
        Author mockPostAuthor = new Author("author");
        Content mockPostContent = new Content("content");

        String mockCommentAuthor = "author";
        String mockCommentContent = "content";

        Post newPost = new Post(mockPostTitle, mockPostAuthor, mockPostContent);

        newPost.addComment(mockCommentAuthor, mockCommentContent);

        assertThat(newPost.findAllComments()).hasSize(1);
    }

//    @Test
//    @DisplayName("findAllComment")
//    void testFindAllComment() {
//
//    }
//
//    @Test
//    @DisplayName("deleteComment")
//    void testDeleteComment() {
//        String mockId = UUID.randomUUID().toString();
//
//        Post newPost = new Post(mockPostTitle, mockPostAuthor, mockPostContent);
//
//        this.postRepository.save(newPost);
//
//        newPost.addComment(mockCommentAuthor, mockCommentContent);
//
//        assertThat(newPost.findAllComments()).hasSize(1);
//    }
}