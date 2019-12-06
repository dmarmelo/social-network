package pt.estgp.socialnetwork.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pt.estgp.socialnetwork.domain.Comment;
import pt.estgp.socialnetwork.domain.Like;
import pt.estgp.socialnetwork.domain.Post;
import pt.estgp.socialnetwork.exception.BadRequestException;
import pt.estgp.socialnetwork.exception.ResourceNotFoundException;
import pt.estgp.socialnetwork.repository.CommentRepository;
import pt.estgp.socialnetwork.repository.LikeRepository;
import pt.estgp.socialnetwork.repository.PostRepository;
import pt.estgp.socialnetwork.repository.UserRepository;
import pt.estgp.socialnetwork.security.UserPrincipal;

@Service
@RequiredArgsConstructor
@Slf4j
public class PostService {
    private final UserRepository userRepository;
    private final PostRepository postRepository;
    private final CommentRepository commentRepository;
    private final LikeRepository likeRepository;

    @Transactional
    public Post createPost(String content, UserPrincipal userPrincipal) {
        //var user = userRepository.findById(userPrincipal.getId()).get();
        var post = new Post();
        //post.setAuthor(user);
        post.setContent(content);
        return postRepository.save(post);
    }

    @Transactional
    public Comment createComment(Long postId, String content, UserPrincipal userPrincipal) {
        //var user = userRepository.findById(userPrincipal.getId()).get();
        var post = postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "id", postId));
        var comment = new Comment();
        comment.setPost(post);
        //comment.setAuthor(user);
        comment.setContent(content);
        return commentRepository.save(comment);
    }

    @Transactional
    public Like addLike(Long postId, UserPrincipal userPrincipal) {
        var user = userRepository.findById(userPrincipal.getId()).get();
        var post = postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "id", postId));
        var hasLike = post.getLikes().stream()
                .filter(l -> !l.isRemoved())
                .anyMatch(l -> l.getCreatedBy().equals(user));
        if (hasLike) throw new BadRequestException("Post was already liked before!");
        var like = new Like();
        //like.setAutor(user);
        like.setPost(post);
        return likeRepository.save(like);
    }

    @Transactional
    public Like remLike(Long postId, UserPrincipal userPrincipal) {
        var post = postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "id", postId));
        var like = likeRepository.findByPostIdAndCreatedByIdAndRemovedFalse(post.getId(), userPrincipal.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Like", "postId:userId", postId + ":" + userPrincipal.getId()));
        like.setRemoved(true);
        return likeRepository.save(like);
    }
}
