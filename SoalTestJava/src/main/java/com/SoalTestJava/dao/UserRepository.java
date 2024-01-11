package com.SoalTestJava.dao;

import com.SoalTestJava.dto.User.UserIndexDTO;
import com.SoalTestJava.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query("""
            select us
            From User as us
            where us.username = :username
            """)
    public Optional<User> findByUsername(@Param("username") String username);

    @Query("""
            select new com.SoalTestJava.dto.User.UserIndexDTO(us.id, us.username, us.createdBy, us.createdAt)
            from User as us
            where us.id = :id
            """)
    public UserIndexDTO getAccountLoginById(@Param("id") Long id);

    @Query("""
            select new com.SoalTestJava.dto.User.UserIndexDTO(us.id, us.username, us.createdBy, us.createdAt)
            from User as us
            """)
    public List<UserIndexDTO> getList();
}