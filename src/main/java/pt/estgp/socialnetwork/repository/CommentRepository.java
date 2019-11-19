package pt.estgp.socialnetwork.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pt.estgp.socialnetwork.domain.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
