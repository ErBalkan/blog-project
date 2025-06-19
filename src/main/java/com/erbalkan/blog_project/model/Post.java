package com.erbalkan.blog_project.model;

import com.erbalkan.blog_project.core.base.BaseEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity // Bu sınıf bir tabloya karşılık gelir.
@Getter // Lombok ile getter metodları otomatik oluşturulur.
@Setter // Lombok ile setter metodları otomatik oluşturulur.
public class Post extends BaseEntity{

    private String title;

    @Column(columnDefinition = "TEXT")
    // TEXT tipi, uzun metinler için kullanılır.
    // Bu, veritabanında metin alanının TEXT olarak tanımlanmasını sağlar
    // ve metin uzunluğunun sınırlı olmadığını belirtir.
    private String content;

    private String author;
}

/*
 Açıklama:
@Entity: Bu sınıf tabloya dönüşür.

@Column(columnDefinition = "TEXT"): İçerik uzun olacağı için varsayılan VARCHAR(255)'ten çıkıyoruz.

BaseEntity'den miras alarak id, createdAt, updatedAt’i otomatik kazandık.
 */
