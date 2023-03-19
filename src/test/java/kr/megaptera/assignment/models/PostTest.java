package kr.megaptera.assignment.models;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class PostTest {

    @Test
    void createTest() {
        // 모델을 생성할 수 있는지 확인
        Post post = new Post("제목321", "나", "적당한내용");

        assertThat(post.title.equals("제목321"));
        assertThat(post.author.equals("나"));
        assertThat(post.content.equals("적당한제목"));
    }

    @Test
    void updateTest() {
        // 모델을 생성하고 업데이트 하는 기능 확인
        Post post = new Post(new PostId("ID0701"), "제목제목", "본인", "그냥내용");

        post.update("바뀐제목", "바뀐내용");

        assertThat(post.title.equals("바뀐제목"));
        assertThat(post.content.equals("바뀐내용"));
    }


}