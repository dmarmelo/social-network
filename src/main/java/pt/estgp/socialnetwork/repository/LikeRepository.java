package pt.estgp.socialnetwork.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pt.estgp.socialnetwork.domain.Like;

public interface LikeRepository extends JpaRepository<Like, Long> {
}
