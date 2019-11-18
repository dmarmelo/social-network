package pt.estgp.socialnetwork.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.NaturalId;
import pt.estgp.socialnetwork.domain.audit.DomainObject;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@Entity
@Table(uniqueConstraints = {
        @UniqueConstraint(columnNames = {
                "username"
        }),
        @UniqueConstraint(columnNames = {
                "email"
        })
})
public class User extends DomainObject {

    @NotBlank
    private String name;

    @NotBlank
    private String username;

    @NaturalId
    @NotBlank
    @Email
    private String email;

    @NotBlank
    @JsonIgnore
    private String password;

    public User(String name, String username, String email, String password) {
        this.name = name;
        this.username = username;
        this.email = email;
        this.password = password;
    }
}
