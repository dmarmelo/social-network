package pt.estgp.socialnetwork.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pt.estgp.socialnetwork.domain.Post;
import pt.estgp.socialnetwork.domain.User;
import pt.estgp.socialnetwork.exception.BadRequestException;
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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
        // Email Regex
        Pattern p = Pattern.compile("(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])");
        Matcher m = p.matcher(email);
        boolean isValid = m.find();
        if (!isValid) {
            throw new BadRequestException("Not a valid email");
        }
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
