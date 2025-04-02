package com.example.lld.producerconsumer.worker;

import com.example.lld.producerconsumer.job.JobStatus;

public interface Worker {


    void executeJob();

    void updateJobStatus(String jobId, JobStatus status);
}
