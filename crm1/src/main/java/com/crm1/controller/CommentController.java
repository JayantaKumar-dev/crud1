package com.crm1.controller;

import com.crm1.entity.Comment;
import com.crm1.entity.Post;
import com.crm1.repository.CommentRepository;
import com.crm1.repository.PostRepository;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/comments")
public class CommentController {
    private PostRepository postRepository;
    private CommentRepository commentRep;

    public CommentController(PostRepository postRepository, CommentRepository commentRep) {
        this.postRepository = postRepository;
        this.commentRep = commentRep;
    }

    @PostMapping
    public String createComment(@RequestBody Comment comment, @RequestParam long postId){
        Post post = postRepository.findById(postId).get();
        comment.setPost(post);
        commentRep.save(comment);
        return "Comment created successfully!";

    }

}
