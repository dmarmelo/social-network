package pt.estgp.socialnetwork.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pt.estgp.socialnetwork.domain.Post;
import pt.estgp.socialnetwork.domain.User;
import pt.estgp.socialnetwork.exception.ResourceNotFoundException;
import pt.estgp.socialnetwork.payload.UserIdentityAvailability;
import pt.estgp.socialnetwork.payload.UserProfile;
import pt.estgp.socialnetwork.payload.UserSummary;
import pt.estgp.socialnetwork.repository.PostRepository;
import pt.estgp.socialnetwork.repository.UserRepository;
import pt.estgp.socialnetwork.search.UserSearch;
import pt.estgp.socialnetwork.security.CurrentUser;
import pt.estgp.socialnetwork.security.UserPrincipal;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {
    private final UserRepository userRepository;
    private final PostRepository postRepository;
    private final UserSearch userSearch;

    @GetMapping("/me")
    public UserSummary getCurrentUser(@CurrentUser UserPrincipal currentUser) {
        return new UserSummary(currentUser.getId(), currentUser.getUsername(), currentUser.getName());
    }

    @GetMapping("/checkUsernameAvailability/{username}")
    public UserIdentityAvailability checkUsernameAvailability(@PathVariable String username) {
        boolean isAvailable = !userRepository.existsByUsername(username);
        return new UserIdentityAvailability(isAvailable);
    }

    @GetMapping("/checkEmailAvailability/{email}")
    public UserIdentityAvailability checkEmailAvailability(@PathVariable String email) {
        boolean isAvailable = !userRepository.existsByEmail(email);
        return new UserIdentityAvailability(isAvailable);
    }

    @GetMapping("/{username}")
    public UserProfile getUserProfile(@PathVariable(value = "username") String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("User", "username", username));

        long postCount = postRepository.countByCreatedById(user.getId());

        return new UserProfile(user.getId(), user.getUsername(), user.getName(), user.getCreatedAt(), postCount);
    }

    @GetMapping("/users/{username}/posts")
    public List<Post> getPollsCreatedBy(@PathVariable String username, @CurrentUser UserPrincipal currentUser) {
        return Collections.emptyList();
    }

    @GetMapping("/search/{text}")
    public List<User> search(@PathVariable String text) {
        return userSearch.search(text);
    }
}
