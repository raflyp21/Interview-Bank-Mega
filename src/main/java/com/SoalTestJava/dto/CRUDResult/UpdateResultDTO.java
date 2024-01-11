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
public class UpdateResultDTO extends ResultDTO {
    private Long updated_by;
    private LocalDateTime updated_at;

    public UpdateResultDTO(String entity, Long pk, Long updated_by, LocalDateTime updated_at) {
        super(entity, pk);
        this.updated_by = updated_by;
        this.updated_at = updated_at;
    }
}
