package kr.megaptera.assignment;

import kr.megaptera.assignment.applications.CreateCommentService;
import kr.megaptera.assignment.applications.CreatePostService;
import kr.megaptera.assignment.dtos.CreateCommentDto;
import kr.megaptera.assignment.dtos.PostCreateDto;
import kr.megaptera.assignment.models.Post;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class AppRunner implements CommandLineRunner {
    private final CreatePostService createPostService;
    private final CreateCommentService createCommentService;

    public AppRunner(CreatePostService createPostService, CreateCommentService createCommentService) {
        this.createPostService = createPostService;
        this.createCommentService = createCommentService;
    }

    @Override
    public void run(String... args) throws Exception {
        for(int i = 0 ; i < 3; i++) {
            PostCreateDto postCreateDto = new PostCreateDto("hi there"+i, "me"+i, "hahahahaha"+i);
            Post post = new Post(postCreateDto);

            createPostService.createPost(postCreateDto);

            for(int j = 0; j < 3; j++) {
                CreateCommentDto createCommentDto = new CreateCommentDto("comment"+j, "content"+j);
                createCommentService.createComment(post.id().toString(), createCommentDto);
            }
        }
    }
}
