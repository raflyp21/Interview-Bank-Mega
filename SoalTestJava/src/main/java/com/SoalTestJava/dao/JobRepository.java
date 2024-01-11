package com.SoalTestJava.dao;

import com.SoalTestJava.dto.Job.JobIndexDTO;
import com.SoalTestJava.entity.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface JobRepository extends JpaRepository<Job, Long> {
    @Query("""
            select new com.SoalTestJava.dto.Job.JobIndexDTO(job.name, job.startAt, job.endAt)
            from Job as job
            where job.userId = :userId
            """)
    public List<JobIndexDTO> getAccountJobsByUserId(@Param("userId") Long id);

    @Query("""
            select Job
            from Job as job
            where job.userId = :userId
            """)
    public Optional<Job> findByUserId(@Param("userId") Long id);
}