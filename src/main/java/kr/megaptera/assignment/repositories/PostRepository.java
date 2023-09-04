package kr.megaptera.assignment.repositories;

import kr.megaptera.assignment.entities.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    // TODO: JPA 이용해서 과제를 완성해 주세요.
    @Modifying
    @Query(value = "delete from Post p where p.id = ?1")
    int deleteByIdCount(Long id);

}
