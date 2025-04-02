package com.example.lld.producerconsumer.worker;

import com.example.lld.producerconsumer.counter.Counter;
import com.example.lld.producerconsumer.job.Job;
import com.example.lld.producerconsumer.job.JobStatus;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;

public class WorkerImpl implements Worker{

    BlockingQueue<Job> jobQueue;
    ConcurrentHashMap<String, Job> jobsMap;
    Counter counter;

    public WorkerImpl(BlockingQueue<Job> jobQueue, ConcurrentHashMap<String, Job> jobsMap, Counter counter) {
        this.jobQueue = jobQueue;
        this.jobsMap = jobsMap;
        this.counter = counter;
    }

    @Override
    public void executeJob() {
        Job job = null;
        try{
            job = jobQueue.take();
            System.out.println(Thread.currentThread().getName() + " executing job " + job.getJobId());
            updateJobStatus(job.getJobId(), JobStatus.RUNNING);
            job.getPayload().run();
            updateJobStatus(job.getJobId(), JobStatus.COMPLETED);
            System.out.println(Thread.currentThread().getName() + " finished executing job " + job.getJobId());
            counter.increment();
        }

        catch (Exception e){
            if (job != null) {
                updateJobStatus(job.getJobId(), JobStatus.FAILED);
            }
        }
    }

    @Override
    public void updateJobStatus(String jobId, JobStatus status) {
        jobsMap.computeIfPresent(jobId, (id, job) -> {
            job.setStatus(status);
            return job;
        });
    }
}
