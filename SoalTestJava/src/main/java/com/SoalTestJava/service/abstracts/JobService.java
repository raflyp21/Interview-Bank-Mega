package com.SoalTestJava.service.abstracts;

import com.SoalTestJava.dto.Job.JobIndexDTO;
import com.SoalTestJava.entity.Job;

import java.util.List;
import java.util.Optional;

public interface JobService {

    public List<JobIndexDTO> GetAccountJobById(Long id);

    public Optional<Job> findByUserId(Long id);

    public Job save(Job job);
}
