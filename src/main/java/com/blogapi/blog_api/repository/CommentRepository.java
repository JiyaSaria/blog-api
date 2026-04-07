package com.blogapi.blog_api.repository;

import com.blogapi.blog_api.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.net.ConnectException;
import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
List<Comment> findByPostId(Long postId);
}
