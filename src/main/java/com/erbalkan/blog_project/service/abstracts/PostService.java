package com.erbalkan.blog_project.service.abstracts;

import java.util.List;

import com.erbalkan.blog_project.core.result.DataResult;
import com.erbalkan.blog_project.core.result.Result;
import com.erbalkan.blog_project.dto.post.requests.PostCreateRequest;
import com.erbalkan.blog_project.dto.post.requests.UpdatePostRequest;
import com.erbalkan.blog_project.dto.post.responses.PostResponse;

public interface PostService {
    Result createPost(PostCreateRequest request);
    DataResult<List<PostResponse>> getAllPosts();
    DataResult<String> updatePost(Long postId, UpdatePostRequest request);
    DataResult<String> deletePost(Long id);
}
