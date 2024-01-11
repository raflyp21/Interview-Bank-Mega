package com.SoalTestJava.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Schema(description = "Object JSON yang digunakan untuk me-respond JWT.")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ResponseTokenDTO {
    @Schema(description = "Status yang menandakan berhasil login atau tidak.")
    private boolean status;

    @Schema(description = "Message Response dari server-side.")
    private String message;

    @Schema(description = "Token JWT untuk requester.")
    private String access_token;

    @Schema(description = "Token JWT untuk refresh requester.")
    private String refresh_token;

    public ResponseTokenDTO(boolean status, String message) {
        this.status = status;
        this.message = message;
    }
}
