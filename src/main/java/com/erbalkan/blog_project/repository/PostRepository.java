package com.erbalkan.blog_project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.erbalkan.blog_project.model.Post;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    // Bu sınıf Post entity'si için CRUD işlemlerini sağlar.
    // JpaRepository, temel CRUD işlemlerini ve daha fazlasını sağlar.
}
