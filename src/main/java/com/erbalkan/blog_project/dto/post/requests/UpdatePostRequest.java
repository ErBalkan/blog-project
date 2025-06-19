package com.erbalkan.blog_project.dto.post.requests;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data 
public class UpdatePostRequest {

    @NotBlank(message = "Başlık boş olamaz")
    private String title;

    @NotBlank(message = "İçerik boş olamaz")
    private String content;
}

/*
Açıklamalar:

@NotBlank: Hem boş hem null kontrolü yapar. Validation mekanizmasıyla birlikte çalışır.

@Data: Lombok anotasyonu. Otomatik olarak getter-setter, constructor, toString üretir.
 */