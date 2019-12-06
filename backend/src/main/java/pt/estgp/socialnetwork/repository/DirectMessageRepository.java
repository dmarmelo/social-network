package pt.estgp.socialnetwork.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pt.estgp.socialnetwork.domain.DirectMessage;

public interface DirectMessageRepository extends JpaRepository<DirectMessage, Long> {
}
