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
@Table(name = "User_Detail")
public class UserDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private Long id;

    @Column(name = "User_Id")
    private Long userId;

    @Column(name = "First_Name")
    private String firstName;

    @Column(name = "Last_Name")
    private String lastName;

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

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Id", referencedColumnName = "User_Id", insertable = false, updatable = false)
    private User user;

    public UserDetail(Long id, Long userId, String firstName, String lastName, Long createdBy, LocalDateTime createdAt) {
        this.id = id;
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.createdBy = createdBy;
        this.createdAt = createdAt;
    }
}