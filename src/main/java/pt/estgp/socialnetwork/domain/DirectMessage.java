package pt.estgp.socialnetwork.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pt.estgp.socialnetwork.domain.audit.OwnedDomainObject;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class DirectMessage extends OwnedDomainObject {
    @NotBlank
    @Column(length = 70000)
    private String content;

    /*@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "sender_id")
    @JsonManagedReference
    private User sender;*/

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "recipient_id")
    @JsonManagedReference
    private User recipient;
}
