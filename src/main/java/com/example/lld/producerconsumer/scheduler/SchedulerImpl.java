package com.example.lld.producerconsumer.scheduler;

import com.example.lld.producerconsumer.counter.Counter;
import com.example.lld.producerconsumer.job.Job;
import com.example.lld.producerconsumer.job.JobStatus;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;

public class SchedulerImpl implements Scheduler{

    BlockingQueue<Job> queue;

    ConcurrentHashMap<String, Job> jobMap;

    Counter counter;

    public SchedulerImpl(BlockingQueue<Job> queue, ConcurrentHashMap<String, Job> jobMap, Counter counter) {
        this.queue = queue;
        this.jobMap = jobMap;
        this.counter = counter;
    }

    @Override
    public void submit(Job job) {
        try{
            System.out.println(Thread.currentThread().getName() + " submitting " + job.toString());
            jobMap.put(job.getJobId(), job);
            this.queue.put(job);
            this.counter.increment();
        }
        catch (Exception e){
            System.out.println("Error in submitting job");
        }

    }

    @Override
    public void cancel(String jobId) {

    }

    @Override
    public JobStatus getStatus(String jobId) {
        return jobMap.containsKey(jobId) ? jobMap.get(jobId).getStatus() : null;
    }
}
