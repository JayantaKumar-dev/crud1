package com.crm1.controller;

import com.crm1.entity.Post;
import com.crm1.repository.CommentRepository;
import com.crm1.repository.PostRepository;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/posts")
public class PostController {
    private PostRepository postRepository;

    public PostController(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @PostMapping
    public String createPost(@RequestBody Post post) {
        postRepository.save(post);
        return "saved";
    }

    @DeleteMapping
    public void deletePost(){
        postRepository.deleteById(1L);
    }


}
