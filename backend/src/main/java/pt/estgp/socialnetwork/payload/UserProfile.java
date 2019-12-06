package pt.estgp.socialnetwork.payload;

import lombok.Data;

import java.time.Instant;

@Data
public class UserProfile {
    private Long id;
    private String username;
    private String name;
    private Instant joinedAt;
    private Long postCount;

    public UserProfile(Long id, String username, String name, Instant joinedAt, Long postCount) {
        this.id = id;
        this.username = username;
        this.name = name;
        this.joinedAt = joinedAt;
        this.postCount = postCount;
    }

}
