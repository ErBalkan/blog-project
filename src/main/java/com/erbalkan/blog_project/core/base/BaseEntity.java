package com.erbalkan.blog_project.core.base;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;

@MappedSuperclass
@Getter
@Setter
public abstract class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = this.updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
}

/*
 Açıklama:
Tüm entity’lere id, createdAt, updatedAt otomatik gelir.

@MappedSuperclass: Bu sınıf direkt tabloya dönüşmez, miras veren bir altyapıdır.

@PrePersist, @PreUpdate: Hibernate event hook’tur, insert/update öncesi çalışır.

Tekrar kullanılabilirlik sağlar → DRY prensibi.
 */