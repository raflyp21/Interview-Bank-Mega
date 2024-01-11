package com.SoalTestJava.restController;

import com.SoalTestJava.dto.RequestTokenDTO;
import com.SoalTestJava.dto.ResponseTokenDTO;
import com.SoalTestJava.security.JwtManager;
import com.SoalTestJava.service.abstracts.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class AuthenticateRestController extends BaseRestController {
    @Autowired
    private UserService userService;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtManager jwtManager;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody RequestTokenDTO dto) {
        try {
            UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(dto.getUsername(), dto.getPassword());
            authenticationManager.authenticate(token);
        } catch (Exception ex) {
            return loginFailed();
        }

        ResponseTokenDTO response = null;

        UserDetails userDetails = userDetailsService.loadUserByUsername(dto.getUsername());
        String token = jwtManager.generateToken(dto.getSubject(), dto.getUsername(), dto.getSecretKey(), dto.getAudience());
        response = new ResponseTokenDTO(true, "Succesfully Login", token, token);
        return ResponseEntity.status(200).body(response);

    }

    @GetMapping("/account")
    public ResponseEntity<Object> getAccountLogin() {
        try {
            var userLogin = userService.getUserById(getUserId());
            return ResponseEntity.status(200).body(userLogin);
        } catch (Exception ex) {
            return loginFailed();
        }
    }

}
