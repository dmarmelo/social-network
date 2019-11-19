package pt.estgp.socialnetwork.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import pt.estgp.socialnetwork.domain.audit.DomainObject;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
@Table(name = "likes") // Because 'like' is a reserved keyword in SQL
public class Like extends DomainObject {
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "post_id")
    private Post post;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "author_id")
    private User autor;

    private boolean removed = false;
}