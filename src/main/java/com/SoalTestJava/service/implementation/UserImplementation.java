package com.SoalTestJava.service.implementation;

import com.SoalTestJava.dao.JobRepository;
import com.SoalTestJava.dao.UserRepository;
import com.SoalTestJava.dto.CRUDResult.CreateResultDTO;
import com.SoalTestJava.dto.CRUDResult.DeleteResultDTO;
import com.SoalTestJava.dto.CRUDResult.UpdateResultDTO;
import com.SoalTestJava.dto.JsonDataResultDTO;
import com.SoalTestJava.dto.JsonResultDTO;
import com.SoalTestJava.dto.User.UserIndexDTO;
import com.SoalTestJava.dto.User.UserUpsertDTO;
import com.SoalTestJava.entity.Job;
import com.SoalTestJava.entity.User;
import com.SoalTestJava.entity.UserDetail;
import com.SoalTestJava.security.ApplicationUserDetails;
import com.SoalTestJava.service.abstracts.JobService;
import com.SoalTestJava.service.abstracts.UserDetailService;
import com.SoalTestJava.service.abstracts.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class UserImplementation implements UserService, UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserDetailService userDetailService;

    @Autowired
    private JobService jobService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> userLogin = userRepository.findByUsername(username);

        if (userLogin.isEmpty()) {
            throw new UsernameNotFoundException("Not Found!");
        }

        return new ApplicationUserDetails(userLogin.get());
    }

    @Override
    public JsonDataResultDTO getUserById(Long id) {
        var account = userRepository.getAccountLoginById(id);

        if (account == null) {
            return new JsonDataResultDTO(false, null);
        }

        account.setDetail(userDetailService.GetAccountDetailById(id));
        account.setJobs(jobService.GetAccountJobById(id));

        return new JsonDataResultDTO(true, account);
    }

    @Override
    public JsonDataResultDTO getList() {
        var userList = userRepository.getList();

        for (UserIndexDTO dto : userList) {
            dto.setDetail(userDetailService.GetAccountDetailById(dto.getId()));
            dto.setJobs(jobService.GetAccountJobById(dto.getId()));
        }

        return new JsonDataResultDTO(true, userList);
    }

    @Override
    public JsonResultDTO upsert(UserUpsertDTO dto, Long idLogin) {
        try {
            var entity = userRepository.findById(dto.getId());
            User result = null;
            if (entity.isPresent()) {
                entity.get().setUsername(dto.getUsername());
                entity.get().setPassword(encryptPassword(dto.getPassword()));
                entity.get().setUpdatedBy(idLogin);
                entity.get().setUpdatedAt(LocalDateTime.now());
                result = userRepository.save(entity.get());

                var entityDetail = userDetailService.findByUserId(result.getId());
                if (entityDetail.isPresent()) {
                    entityDetail.get().setFirstName(dto.getDetail().getFirstName());
                    entityDetail.get().setLastName(dto.getDetail().getLastName());
                    entityDetail.get().setUpdatedBy(idLogin);
                    entityDetail.get().setUpdatedAt(LocalDateTime.now());
                    UserDetail resultDetail = userDetailService.save(entityDetail.get());
                }

                for (var job : dto.getJobs()) {
                    var entityJob = jobService.findByUserId(result.getId());
                    if (entityJob.isPresent()) {

                        entityJob.get().setName(job.getName());
                        entityJob.get().setStartAt(job.getStart_at());
                        entityJob.get().setEndAt(job.getUntil_at());
                        entityJob.get().setUpdatedBy(idLogin);
                        entityJob.get().setUpdatedAt(LocalDateTime.now());
                        Job resultJob = jobService.save(entityJob.get());
                    }
                }

                return new JsonResultDTO(true, "Succesfully Update Data", new UpdateResultDTO("User", result.getId(), idLogin, LocalDateTime.now()));
            }

            var newEntity = new User(
                    dto.getId(),
                    dto.getUsername(),
                    encryptPassword(dto.getPassword()),
                    idLogin,
                    LocalDateTime.now()
            );
            result = userRepository.save(newEntity);

            var newDetail = new UserDetail(
                    0L,
                    result.getId(),
                    dto.getDetail().getFirstName(),
                    dto.getDetail().getLastName(),
                    idLogin,
                    LocalDateTime.now()
            );
            var resultNewDetail = userDetailService.save(newDetail);

            for (var job : dto.getJobs()) {
                var newJob = new Job(
                        0L,
                        result.getId(),
                        job.getName(),
                        job.getStart_at(),
                        job.getUntil_at(),
                        idLogin,
                        LocalDateTime.now()
                );
                var resultNewJob = jobService.save(newJob);
            }
            return new JsonResultDTO(true, "Succesfully Created Data", new CreateResultDTO("User", result.getId(), idLogin, LocalDateTime.now()));
        } catch (Exception ex) {
            return new JsonResultDTO(false, "Something Wrong = " + ex.getMessage(), null);
        }
    }

    @Override
    public JsonResultDTO delete(Long id, Long idLogin) {
        try {
            var entity = userRepository.findById(id);
            var entityDetail = userDetailService.findByUserId(id);
            var entityJob = jobService.findByUserId(id);
            if (entity.isPresent() && entityDetail.isPresent() && entityJob.isPresent()) {
                entity.get().setDeletedBy(idLogin);
                entity.get().setDeletedAt(LocalDateTime.now());
                var result = userRepository.save(entity.get());

                entityDetail.get().setDeletedBy(idLogin);
                entityDetail.get().setDeletedAt(LocalDateTime.now());
                var resultDetail = userDetailService.save(entityDetail.get());

                entityJob.get().setDeletedBy(idLogin);
                entityJob.get().setDeletedAt(LocalDateTime.now());
                var resultJob = jobService.save(entityJob.get());

                return new JsonResultDTO(true, "Succesfully Delete Data", new DeleteResultDTO("User", result.getId(), idLogin, LocalDateTime.now()));
            }
            return new JsonResultDTO(false, "Data Not Found", null);
        } catch (Exception ex) {
            return new JsonResultDTO(false, "Something Wrong = " + ex.getMessage(), null);
        }
    }

    private String encryptPassword(String password) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.encode(password);
    }

}
