package pt.estgp.socialnetwork.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pt.estgp.socialnetwork.domain.audit.DomainObject;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Post extends DomainObject {
    @NotBlank
    @Column(length = 70000)
    private String content;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "autor_id")
    @JsonManagedReference
    private User author;

    @OneToMany(
            mappedBy = "post",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            orphanRemoval = true
    )
    @OrderBy("createdAt desc")
    @JsonManagedReference
    private Set<Comment> comments = new HashSet<>();

    @OneToMany(
            mappedBy = "post",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            orphanRemoval = true
    )
    @OrderBy("createdAt desc")
    @JsonIgnore
    private Set<Like> likes = new HashSet<>();

    @Transient
    public Long getLikesNumber() {
        return likes.stream()
                .filter(l -> !l.isRemoved())
                .count();
    }
}
