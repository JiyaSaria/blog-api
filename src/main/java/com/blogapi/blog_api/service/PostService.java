package com.blogapi.blog_api.service;

import com.blogapi.blog_api.dto.PostRequestDto;
import com.blogapi.blog_api.dto.PostResponseDto;
import com.blogapi.blog_api.entity.Post;
import com.blogapi.blog_api.entity.User;
import com.blogapi.blog_api.repository.PostRepository;
import com.blogapi.blog_api.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    public PostResponseDto createPost(PostRequestDto dto, String email){
        User author = userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("User Not Found"));

        Post post = Post.builder().title(dto.getTitle()).content(dto.getContent()).author(author).build();
        Post saved = postRepository.save(post);
        return mapToDto(saved);
    }
    public List<PostResponseDto> getAllPosts(){
        return postRepository.findAll().stream().map(this::mapToDto).collect(Collectors.toList());
    }
    public PostResponseDto getPostById(Long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Post not found with id: " + id));
        return mapToDto(post);
    }
    public PostResponseDto updatePost(Long id, PostRequestDto dto, String email) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Post not found with id: " + id));

        if (!post.getAuthor().getEmail().equals(email)) {
            throw new RuntimeException("You can only edit your own posts");
        }

        post.setTitle(dto.getTitle());
        post.setContent(dto.getContent());
        return mapToDto(postRepository.save(post));
    }
    public void deletePost(Long id, String email) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Post not found with id: " + id));

        if (!post.getAuthor().getEmail().equals(email)) {
            throw new RuntimeException("You can only delete your own posts");
        }

        postRepository.delete(post);
    }
    private PostResponseDto mapToDto(Post post) {
        return PostResponseDto.builder()
                .id(post.getId())
                .title(post.getTitle())
                .content(post.getContent())
                .authorUsername(post.getAuthor().getUsername())
                .createdAt(post.getCreatedAt())
                .updatedAt(post.getUpdatedAt())
                .build();
    }
}
