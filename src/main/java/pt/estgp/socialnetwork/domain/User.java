package pt.estgp.socialnetwork.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.NaturalId;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;
import pt.estgp.socialnetwork.domain.audit.DomainObject;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Indexed
@Table(uniqueConstraints = {
        @UniqueConstraint(columnNames = {
                "username"
        }),
        @UniqueConstraint(columnNames = {
                "email"
        })
})
public class User extends DomainObject {

    @Field
    @NotBlank
    private String name;

    @Field
    @NotBlank
    private String username;

    @Field
    @NaturalId
    @NotBlank
    @Email
    private String email;

    @NotBlank
    @JsonIgnore
    private String password;

    @NotBlank
    private Instant birthDate;

    @OneToMany(
            mappedBy = "author",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            orphanRemoval = true
    )
    @OrderBy("createdAt desc")
    @JsonBackReference
    private Set<Post> posts = new HashSet<>();

    public User(String name, String username, String email, String password) {
        this.name = name;
        this.username = username;
        this.email = email;
        this.password = password;
    }
}
