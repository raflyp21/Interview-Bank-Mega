package com.SoalTestJava.dao;

import com.SoalTestJava.dto.UserDetail.UserDetailIndexDTO;
import com.SoalTestJava.entity.UserDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserDetailRepository extends JpaRepository<UserDetail, Long> {

    @Query("""
            select new com.SoalTestJava.dto.UserDetail.UserDetailIndexDTO(usd.firstName, usd.lastName)
            from UserDetail as usd
            where usd.userId = :userId
            """)
    public UserDetailIndexDTO getAccountDetailByUserId(@Param("userId") Long id);

    @Query("""
            select usd
            from UserDetail as usd
            where usd.userId = :userId
            """)
    public Optional<UserDetail> findByUserId(@Param("userId") Long id);
}