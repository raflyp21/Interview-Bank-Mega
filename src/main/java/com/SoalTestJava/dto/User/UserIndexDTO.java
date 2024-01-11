package com.SoalTestJava.dto.User;

import com.SoalTestJava.dto.Job.JobIndexDTO;
import com.SoalTestJava.dto.UserDetail.UserDetailIndexDTO;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserIndexDTO {
    private Long id;
    private String username;
    private UserDetailIndexDTO detail;
    private List<JobIndexDTO> jobs;
    private Long createdBy;
    private LocalDateTime createdAt;
    private Long updatedBy;
    private LocalDateTime updatedAt;
    private Long deletedBy;
    private LocalDateTime deletedAt;

    public UserIndexDTO(Long id, String username, Long createdBy, LocalDateTime createdAt, Long updatedBy, LocalDateTime updatedAt, Long deletedBy, LocalDateTime deletedAt) {
        this.id = id;
        this.username = username;
        this.createdBy = createdBy;
        this.createdAt = createdAt;
        this.updatedBy = updatedBy;
        this.updatedAt = updatedAt;
        this.deletedBy = deletedBy;
        this.deletedAt = deletedAt;
    }

    public UserIndexDTO(Long id, String username, Long createdBy, LocalDateTime createdAt) {
        this.id = id;
        this.username = username;
        this.createdBy = createdBy;
        this.createdAt = createdAt;
    }
}