package com.SoalTestJava.restController;

import com.SoalTestJava.dto.ResponseTokenDTO;
import com.SoalTestJava.security.ApplicationUserDetails;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class BaseRestController {
    protected String getUsername() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getName();
    }

    protected Long getUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        var userId = ((ApplicationUserDetails) authentication.getPrincipal()).getUserId();
        return userId;
    }

    protected ResponseEntity<Object> loginFailed() {
        return ResponseEntity.status(401).body(new ResponseTokenDTO(false, "Unauthorized"));
    }
}
