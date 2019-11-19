package pt.estgp.socialnetwork.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pt.estgp.socialnetwork.domain.Follow;

public interface FollowRepository extends JpaRepository<Follow, Long> {
}
