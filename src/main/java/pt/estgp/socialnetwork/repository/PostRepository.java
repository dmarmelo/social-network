package pt.estgp.socialnetwork.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pt.estgp.socialnetwork.domain.Post;

public interface PostRepository extends JpaRepository<Post, Long> {
    long countByCreatedById(Long id);
}
