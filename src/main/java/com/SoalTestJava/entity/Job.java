package com.SoalTestJava.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "Jobs")
public class Job {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private Long id;

    @Column(name = "User_Id")
    private Long userId;

    @Column(name = "Name")
    private String name;

    @Column(name = "Start_At")
    private LocalDateTime startAt;

    @Column(name = "End_At")
    private LocalDateTime endAt;

    @Column(name = "Created_By")
    private Long createdBy;

    @Column(name = "Created_At")
    private LocalDateTime createdAt;

    @Column(name = "Updated_By")
    private Long updatedBy;

    @Column(name = "Updated_At")
    private LocalDateTime updatedAt;

    @Column(name = "Deleted_By")
    private Long deletedBy;

    @Column(name = "Deleted_At")
    private LocalDateTime deletedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "User_Id", insertable = false, updatable = false)
    private User user;

    public Job(Long id, Long userId, String name, LocalDateTime startAt, LocalDateTime endAt, Long createdBy, LocalDateTime createdAt) {
        this.id = id;
        this.userId = userId;
        this.name = name;
        this.startAt = startAt;
        this.endAt = endAt;
        this.createdBy = createdBy;
        this.createdAt = createdAt;
    }
}