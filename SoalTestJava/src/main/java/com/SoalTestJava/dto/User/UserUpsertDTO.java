package com.SoalTestJava.dto.User;

import com.SoalTestJava.dto.Job.JobIndexDTO;
import com.SoalTestJava.dto.UserDetail.UserDetailIndexDTO;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserUpsertDTO {
    private Long id;

    @NotBlank(message = "Username Cannot Be Blank")
    private String username;

    @NotBlank(message = "Password Cannot Be Blank")
    private String password;

    private UserDetailIndexDTO detail;

    private List<JobIndexDTO> jobs;
}
