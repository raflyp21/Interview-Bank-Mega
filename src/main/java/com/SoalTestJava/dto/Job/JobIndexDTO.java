package com.SoalTestJava.dto.Job;

import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class JobIndexDTO {
    private String name;
    private LocalDateTime start_at;
    private LocalDateTime until_at;
}