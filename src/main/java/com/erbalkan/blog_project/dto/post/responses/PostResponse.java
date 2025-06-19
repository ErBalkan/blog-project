package com.erbalkan.blog_project.dto.post.responses;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data // Lombok'un @Data anotasyonu, @Getter, @Setter, @ToString, @EqualsAndHashCode ve @RequiredArgsConstructor anotasyonlarını içerir.
@AllArgsConstructor // Tüm alanları içeren bir yapıcı (constructor) oluşturur.
public class PostResponse {
    private Long id;
    private String title;
    private String content;
    private String author;
}

