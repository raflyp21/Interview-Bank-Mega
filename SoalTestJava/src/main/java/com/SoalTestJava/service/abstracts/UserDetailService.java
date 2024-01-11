package com.SoalTestJava.service.abstracts;

import com.SoalTestJava.dto.UserDetail.UserDetailIndexDTO;
import com.SoalTestJava.entity.UserDetail;

import java.util.Optional;

public interface UserDetailService {
    public UserDetailIndexDTO GetAccountDetailById(Long id);

    public Optional<UserDetail> findByUserId(Long id);

    public UserDetail save(UserDetail userDetail);
}
