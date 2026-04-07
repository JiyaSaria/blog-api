package com.blogapi.blog_api.controller;

import com.blogapi.blog_api.dto.CommentRequestDto;
import com.blogapi.blog_api.dto.CommentResponseDto;
import com.blogapi.blog_api.service.CommentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comments")
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;

    @PostMapping("/post/{postId}")
    public ResponseEntity<CommentResponseDto> addComment(
            @PathVariable Long postId,
            @Valid @RequestBody CommentRequestDto dto,
            @RequestParam String email
            ){
        return new ResponseEntity<>(commentService.addComment(postId, dto, email) , HttpStatus.CREATED);

    }
    @GetMapping("/post/{postId}")
    public ResponseEntity<List<CommentResponseDto>> getComments(@PathVariable Long postId){
        return ResponseEntity.ok(commentService.getCommentsByPost(postId));
    }
    @DeleteMapping("/{commentId}")
    public ResponseEntity<String> deleteComment(@PathVariable Long commentId, @RequestParam String email){
        commentService.deleteComment(commentId, email);
        return ResponseEntity.ok("Comment deleted successfully");
    }
}
