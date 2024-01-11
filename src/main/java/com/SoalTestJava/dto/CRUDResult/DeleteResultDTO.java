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
public class DeleteResultDTO extends ResultDTO {
    private Long deleted_by;
    private LocalDateTime deleted_at;

    public DeleteResultDTO(String entity, Long pk, Long deleted_by, LocalDateTime deleted_at) {
        super(entity, pk);
        this.deleted_by = deleted_by;
        this.deleted_at = deleted_at;
    }
}
