package pt.estgp.socialnetwork.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pt.estgp.socialnetwork.domain.Comment;
import pt.estgp.socialnetwork.domain.Like;
import pt.estgp.socialnetwork.domain.Post;
import pt.estgp.socialnetwork.payload.StringPayload;
import pt.estgp.socialnetwork.repository.UserRepository;
import pt.estgp.socialnetwork.security.CurrentUser;
import pt.estgp.socialnetwork.security.UserPrincipal;
import pt.estgp.socialnetwork.service.PostService;

import javax.validation.Valid;
import java.util.Set;

@RestController
@RequestMapping(path = "/api/post")
@RequiredArgsConstructor
public class PostController {
    private final UserRepository userRepository;
    private final PostService postService;

    @GetMapping
    public ResponseEntity<Set<Post>> all(@CurrentUser UserPrincipal userPrincipal) {
        var user = userRepository.findById(userPrincipal.getId()).get();
        return new ResponseEntity<>(user.getPosts(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Post> createPost(@Valid @RequestBody StringPayload postPayload, @CurrentUser UserPrincipal userPrincipal) {
        var post = postService.createPost(postPayload.getContent(), userPrincipal);
        return new ResponseEntity<>(post, HttpStatus.CREATED);
    }

    @PostMapping("/{postId}/comment")
    public ResponseEntity<Comment> createComment(@PathVariable Long postId, @Valid @RequestBody StringPayload commentPayload, @CurrentUser UserPrincipal userPrincipal) {
        var comment = postService.createComment(postId, commentPayload.getContent(), userPrincipal);
        return new ResponseEntity<>(comment, HttpStatus.CREATED);
    }

    @PostMapping("/{postId}/like")
    public ResponseEntity<Like> addLike(@PathVariable Long postId, @CurrentUser UserPrincipal userPrincipal) {
        var like = postService.addLike(postId, userPrincipal);
        return new ResponseEntity<>(like, HttpStatus.CREATED);
    }

    @PostMapping("/{postId}/unlike")
    public ResponseEntity<Like> remLike(@PathVariable Long postId, @CurrentUser UserPrincipal userPrincipal) {
        var like = postService.remLike(postId, userPrincipal);
        return new ResponseEntity<>(like, HttpStatus.CREATED);
    }
}
