package com.SoalTestJava.service.implementation;

import com.SoalTestJava.dao.UserDetailRepository;
import com.SoalTestJava.dto.UserDetail.UserDetailIndexDTO;
import com.SoalTestJava.entity.UserDetail;
import com.SoalTestJava.service.abstracts.UserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailImplementation implements UserDetailService {
    @Autowired
    private UserDetailRepository userDetailRepository;

    @Override
    public UserDetailIndexDTO GetAccountDetailById(Long id) {
        var accountDetail = userDetailRepository.getAccountDetailByUserId(id);
        return accountDetail;
    }

    @Override
    public Optional<UserDetail> findByUserId(Long id) {
        var userDetail = userDetailRepository.findByUserId(id);
        return userDetail;
    }

    @Override
    public UserDetail save(UserDetail userDetail) {
        return userDetailRepository.save(userDetail);
    }
}
