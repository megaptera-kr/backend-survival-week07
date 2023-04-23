package kr.megaptera.assignment.applications.posts;

import com.github.f4b6a3.tsid.TsidCreator;
import jakarta.transaction.Transactional;
import kr.megaptera.assignment.dtos.posts.PostCreateDto;
import kr.megaptera.assignment.dtos.posts.PostReadDto;
import kr.megaptera.assignment.models.posts.MultilineText;
import kr.megaptera.assignment.models.posts.Post;
import kr.megaptera.assignment.models.posts.PostId;
import kr.megaptera.assignment.repositories.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Transactional
@Service
public class CreatePostService {
    private final PostRepository postRepository;

    @Autowired
    public CreatePostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public PostReadDto execute(PostCreateDto dto) {
        var id = TsidCreator.getTsid().toString();
        var model = new Post(id, dto.getTitle(), dto.getAuthor(), new MultilineText(dto.getContent()));
        postRepository.save(model);

        return new PostReadDto(model);
    }
}
