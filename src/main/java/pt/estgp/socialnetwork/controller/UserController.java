package pt.estgp.socialnetwork.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pt.estgp.socialnetwork.domain.User;
import pt.estgp.socialnetwork.repository.UserRepository;
import pt.estgp.socialnetwork.search.UserSearch;

import java.util.List;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {
    private final UserRepository userRepository;
    private final UserSearch userSearch;

    @GetMapping("/search/{text}")
    public List<User> search(@PathVariable String text) {
        return userSearch.search(text);
    }
}
