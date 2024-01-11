package com.SoalTestJava.service.abstracts;

import com.SoalTestJava.dto.JsonDataResultDTO;
import com.SoalTestJava.dto.JsonResultDTO;
import com.SoalTestJava.dto.User.UserUpsertDTO;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserService {
    public UserDetails loadUserByUsername(String username);

    public JsonDataResultDTO getUserById(Long id);

    public JsonDataResultDTO getList();

    public JsonResultDTO upsert(UserUpsertDTO dto, Long idLogin);

    public JsonResultDTO delete(Long id, Long idLogin);
}
