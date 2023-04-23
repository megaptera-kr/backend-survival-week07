package kr.megaptera.assignment.repositories;

import kr.megaptera.assignment.domains.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.*;

import java.util.*;

@Repository
public interface PostRepository extends JpaRepository<Post, PostId> {

    List<Post> findAll();


}
