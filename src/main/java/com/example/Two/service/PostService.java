package com.example.Two.service;

import com.example.Two.model.Post;
import com.example.Two.repository.PostRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PostService {

    private final PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public List<Post> findAll() {
        return (List<Post>) postRepository.findAll();
    }

    public Post findById(Long id) throws Exception {
        if (postRepository.findById(id).isPresent())
            return postRepository.findById(id).get();
        else
            throw new Exception();
    }

    public String save(Post post) {
        post.setCreatedAt(LocalDateTime.now());
        postRepository.save(post);
        return "Created...";
    }

    public String update(Post post) {
        post.setUpdatedAt(LocalDateTime.now());
        postRepository.save(post);
        return "Updated...";
    }

    public String delete(Long id) {
        postRepository.deleteById(id);
        return "Deleted...";
    }
}
