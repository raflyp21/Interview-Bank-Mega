package com.SoalTestJava.service.implementation;

import com.SoalTestJava.dao.JobRepository;
import com.SoalTestJava.dto.Job.JobIndexDTO;
import com.SoalTestJava.entity.Job;
import com.SoalTestJava.service.abstracts.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JobImplementation implements JobService {
    @Autowired
    private JobRepository jobRepository;

    @Override
    public List<JobIndexDTO> GetAccountJobById(Long id) {
        var accountJobs = jobRepository.getAccountJobsByUserId(id);

        return accountJobs;
    }

    @Override
    public Optional<Job> findByUserId(Long id) {
        var job = jobRepository.findByUserId(id);

        return job;
    }

    @Override
    public Job save(Job job) {
        return jobRepository.save(job);
    }


}
