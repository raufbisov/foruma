package com.raufbisov.post;

import java.text.ParseException;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/posts")
public class PostController {
    private final PostService postService;

    @PostMapping
    public ResponseEntity<String> createPost(@RequestBody PostCreateRequest request, @RequestHeader("Authorization") String header) throws ParseException {
        postService.createPost(request, header);
        return ResponseEntity.ok("Post created");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePost(@PathVariable("id") String id, @RequestHeader("Authorization") String header) {
        postService.deletePost(id, header);
        return ResponseEntity.ok("Post deleted");
    }

    @GetMapping("/{id}")
    public Object getPost(@PathVariable("id") String id, @RequestHeader("Authorization") String header) {
        return postService.getPostById(id, header);
    }

    @GetMapping
    public List<Post> getAllPosts(@RequestHeader("Authorization") String header) {
        return postService.getAllPosts(header);
    }

    @GetMapping("/{id}/like")
    public ResponseEntity<String> likePost(
        @PathVariable("id") String id,
        @RequestHeader("Authorization") String header
    ) {
        return postService.likePost(id, header);
    }
}
