package com.sparta.springreview.entity;

import jakarta.persistence.*;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class Timestamp {

    @CreatedDate
    @Column(updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    public LocalDateTime createdAt;

    @CreatedDate
    @Column
    @Temporal(TemporalType.TIMESTAMP)
    public LocalDateTime modifiedAt;
}
