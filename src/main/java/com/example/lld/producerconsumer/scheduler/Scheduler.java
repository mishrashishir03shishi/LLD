package com.example.lld.producerconsumer.scheduler;

import com.example.lld.producerconsumer.job.Job;
import com.example.lld.producerconsumer.job.JobStatus;

public interface Scheduler {

    void submit(Job job);

    void cancel(String jobId);

    JobStatus getStatus(String jobId);
}
