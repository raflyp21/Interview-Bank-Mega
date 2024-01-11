package com.SoalTestJava.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class JsonResultDTO {
    private boolean status;
    private String message;
    private Object reference_data;

    public JsonResultDTO(boolean status, Object reference_data) {
        this.status = status;
        this.reference_data = reference_data;
    }
}
