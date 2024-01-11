package com.SoalTestJava.dto.CRUDResult;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CreateResultDTO extends ResultDTO {
    private Long created_by;
    private LocalDateTime created_at;

    public CreateResultDTO(String entity, Long pk, Long created_by, LocalDateTime created_at) {
        super(entity, pk);
        this.created_by = created_by;
        this.created_at = created_at;
    }
}
