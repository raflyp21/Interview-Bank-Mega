package com.SoalTestJava.dto.UserDetail;

import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserDetailIndexDTO {
    private String firstName;
    private String lastName;
}