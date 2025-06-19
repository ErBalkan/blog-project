package com.erbalkan.blog_project.dto.post.requests;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostCreateRequest {

    @NotBlank(message = "Başlık boş olamaz")
    // @Size(min = 5, max = 100, message = "Başlık 5-100 karakter arasında olmalıdır")
    // gibi şartlar da eklenebilir.
    private String title;

    @NotBlank(message = "İçerik boş olamaz")
    private String content;

    @NotBlank(message = "Yazar boş olamaz")
    private String author;
}

