package com.SoalTestJava.restController;

import com.SoalTestJava.dto.ErrorValidationDTO;
import com.SoalTestJava.dto.JsonResultDTO;
import com.SoalTestJava.dto.User.UserUpsertDTO;
import com.SoalTestJava.service.abstracts.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedList;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserRestController extends BaseRestController {
    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<Object> getListUser() {
        try {
            var idLogin = getUserId(); //<-- untuk pengecekan apakah sudah login atau belum
            return ResponseEntity.status(200).body(userService.getList());
        } catch (Exception ex) {
            return loginFailed();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getUserById(@PathVariable Long id) {
        try {
            var idLogin = getUserId();
            var userLogin = userService.getUserById(id);
            return ResponseEntity.status(userLogin.isStatus() ? 200 : 404).body(userLogin);
        } catch (Exception ex) {
            return loginFailed();
        }
    }

    @PostMapping
    public ResponseEntity<Object> insert(@Valid @RequestBody UserUpsertDTO dto,
                                         BindingResult bindingResult) {
        try {
            var idLogin = getUserId();
            if (bindingResult.hasErrors()) {

                var listError = bindingResult.getAllErrors().stream().toList();
                List<ErrorValidationDTO> listErrorDTO = new LinkedList<>();
                for (ObjectError err : listError) {
                    if (err instanceof FieldError) {
                        FieldError fieldError = (FieldError) err;
                        String msg = fieldError.getDefaultMessage();
                        String propertyName = fieldError.getField();

                        listErrorDTO.add(new ErrorValidationDTO(propertyName, msg));
                    } else {
                        listErrorDTO.add(new ErrorValidationDTO(err.getDefaultMessage().split("_")[0].split(" ")[0], err.getDefaultMessage().split("_")[1]));

                    }
                }
                return ResponseEntity.status(400).body(new JsonResultDTO(false, listErrorDTO));
            }
            var data = userService.upsert(dto, idLogin);
            return ResponseEntity.status(201).body(data);
        } catch (Exception ex) {
            return loginFailed();
        }
    }

    @PutMapping
    public ResponseEntity<Object> upsert(@Valid @RequestBody UserUpsertDTO dto,
                                         BindingResult bindingResult) {
        try {
            var idLogin = getUserId();
            if (bindingResult.hasErrors()) {

                var listError = bindingResult.getAllErrors().stream().toList();
                List<ErrorValidationDTO> listErrorDTO = new LinkedList<>();
                for (ObjectError err : listError) {
                    if (err instanceof FieldError) {
                        FieldError fieldError = (FieldError) err;
                        String msg = fieldError.getDefaultMessage();
                        String propertyName = fieldError.getField();

                        listErrorDTO.add(new ErrorValidationDTO(propertyName, msg));
                    } else {
                        listErrorDTO.add(new ErrorValidationDTO(err.getDefaultMessage().split("_")[0].split(" ")[0], err.getDefaultMessage().split("_")[1]));

                    }
                }
                return ResponseEntity.status(400).body(new JsonResultDTO(false, listErrorDTO));
            }
            var data = userService.upsert(dto, idLogin);
            return ResponseEntity.status(201).body(data);
        } catch (Exception ex) {
            return loginFailed();
        }
    }

    @DeleteMapping
    public ResponseEntity<Object> delete(@RequestParam Long id) {
        try {
            var idLogin = getUserId();
            var data = userService.delete(id, idLogin);
            return ResponseEntity.status(201).body(data);
        } catch (Exception ex) {
            return loginFailed();
        }
    }
}
