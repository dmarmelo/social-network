package pt.estgp.socialnetwork.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pt.estgp.socialnetwork.domain.Post;
import pt.estgp.socialnetwork.payload.StringPayload;
import pt.estgp.socialnetwork.repository.PostRepository;
import pt.estgp.socialnetwork.repository.UserRepository;
import pt.estgp.socialnetwork.security.CurrentUser;
import pt.estgp.socialnetwork.security.UserPrincipal;

import javax.validation.Valid;
import java.util.Set;

@RestController
@RequestMapping(path = "/api/post")
@RequiredArgsConstructor
public class PostController {
    private final UserRepository userRepository;
    private final PostRepository postRepository;

    @GetMapping
    public ResponseEntity<Set<Post>> all(@CurrentUser UserPrincipal userPrincipal) {
        var user = userRepository.findById(userPrincipal.getId()).get();
        return new ResponseEntity<>(user.getPosts(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Post> createPost(@Valid @RequestBody StringPayload postPayload, @CurrentUser UserPrincipal userPrincipal) {
        var user = userRepository.findById(userPrincipal.getId()).get();
        var post = new Post();
        post.setAuthor(user);
        post.setContent(postPayload.getContent());
        var postPersistent = postRepository.save(post);
        return new ResponseEntity<>(postPersistent, HttpStatus.CREATED);
    }
}
