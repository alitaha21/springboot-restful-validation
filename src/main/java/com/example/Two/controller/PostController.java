package com.example.Two.controller;

import com.example.Two.model.Post;
import com.example.Two.service.PostService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    private final PostService postService;
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/")
    public ResponseEntity<List<Post>> findAll() {
        return new ResponseEntity<>(postService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Post> findById(@PathVariable Long id) throws Exception {
        return new ResponseEntity<>(postService.findById(id), HttpStatus.OK);
    }

    @PostMapping("/new-post")
    public ResponseEntity<String> save(@RequestBody @Valid Post post) {
        return new ResponseEntity<>(postService.save(post), HttpStatus.CREATED);
    }

    @PutMapping("/update-post/{id}")
    public ResponseEntity<String> update(@RequestBody Post post) {
        return new ResponseEntity<>(postService.update(post), HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        return new ResponseEntity<>(postService.delete(id), HttpStatus.ACCEPTED);
    }
}