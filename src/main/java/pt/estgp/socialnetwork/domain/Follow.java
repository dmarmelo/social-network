package pt.estgp.socialnetwork.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pt.estgp.socialnetwork.domain.audit.DomainObject;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Follow extends DomainObject {
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "follower_id")
    private User follower;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "following_id")
    private User following;

    private boolean removed = false;
}
