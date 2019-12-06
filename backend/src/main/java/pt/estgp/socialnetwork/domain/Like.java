package pt.estgp.socialnetwork.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pt.estgp.socialnetwork.domain.audit.OwnedDomainObject;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "likes") // Because 'like' is a reserved keyword in SQL
public class Like extends OwnedDomainObject {
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "post_id")
    private Post post;

    /*@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "author_id")
    private User autor;*/

    private boolean removed = false;
}
