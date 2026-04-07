package com.blogapi.blog_api.controller;

import com.blogapi.blog_api.dto.PostRequestDto;
import com.blogapi.blog_api.dto.PostResponseDto;
import com.blogapi.blog_api.service.PostService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;

    @PostMapping
    public ResponseEntity<PostResponseDto> createPost(
        @Valid @RequestBody PostRequestDto dto,
        @RequestParam String email  ){
        return new ResponseEntity<>(postService.createPost(dto, email), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<PostResponseDto>> getAllPosts(){
        return ResponseEntity.ok(postService.getAllPosts());
    }
    @GetMapping("/{id}")
    public ResponseEntity<PostResponseDto> getPostById(@PathVariable Long id){
        return ResponseEntity.ok(postService.getPostById(id));
    }
    @PutMapping("/{id}")
    public ResponseEntity<PostResponseDto> updatePost(
            @PathVariable Long id,
            @Valid @RequestBody PostRequestDto dto,
            @RequestParam String email
    ){
        return ResponseEntity.ok(postService.updatePost(id, dto, email));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePost(
            @PathVariable Long id,
            @RequestParam String email
    ){
        postService.deletePost(id, email);
        return ResponseEntity.ok("Comment deleted successfully");
    }
}
