package com.blogapi.blog_api.service;

import com.blogapi.blog_api.dto.CommentRequestDto;
import com.blogapi.blog_api.dto.CommentResponseDto;
import com.blogapi.blog_api.entity.Comment;
import com.blogapi.blog_api.entity.Post;
import com.blogapi.blog_api.entity.User;
import com.blogapi.blog_api.repository.CommentRepository;
import com.blogapi.blog_api.repository.PostRepository;
import com.blogapi.blog_api.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final CommentRepository commentRepository;

    public CommentResponseDto addComment(Long postId, CommentRequestDto dto, String email){
        Post post = postRepository.findById(postId).orElseThrow(()->
                new RuntimeException("Post not found with id" + postId));
        User author = userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("User not found"));

        Comment comment = Comment.builder().content(dto.getContent()).post(post).author(author).build();
        return mapToDto(commentRepository.save(comment));
    }
    public List<CommentResponseDto> getCommentsByPost(Long postId){
        return commentRepository.findByPostId(postId).stream().map(this::mapToDto).collect(Collectors.toList());
    }
    public void deleteComment(Long commentId, String email){
        Comment comment = commentRepository.findById(commentId).orElseThrow(()-> new RuntimeException("Comment not found with this id"));
        if(!comment.getAuthor().getEmail().equals(email)){
            throw new RuntimeException("You can only delete your own comment");
        }
        commentRepository.delete(comment);
    }
    private CommentResponseDto mapToDto(Comment comment){
        return CommentResponseDto.builder().id(comment.getId()).content(comment.getContent()).authorUsername(comment.getAuthor().getUsername()).createdAt(comment.getCreatedAt()).build();
    }
}
