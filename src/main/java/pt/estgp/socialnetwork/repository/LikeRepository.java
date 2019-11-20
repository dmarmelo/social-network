package pt.estgp.socialnetwork.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pt.estgp.socialnetwork.domain.Like;

import java.util.Optional;

public interface LikeRepository extends JpaRepository<Like, Long> {
    Optional<Like> findByPostIdAndAutorIdAndRemovedFalse(Long postId, Long autorId);
}
