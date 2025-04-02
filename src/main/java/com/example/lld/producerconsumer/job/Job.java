package com.example.lld.producerconsumer.job;

public class Job implements Comparable<Job>{


    private final String jobId;

    private final Runnable payload;

    private final int priority;

    private JobStatus status;

    public Job(String jobId, Runnable payload, int priority, JobStatus status) {
        this.jobId = jobId;
        this.payload = payload;
        this.priority = priority;
        this.status = status;
    }

    public int getPriority() {
        return priority;
    }

    public JobStatus getStatus() {
        return status;
    }

    @Override
    public int compareTo(Job job) {
        return this.priority - job.getPriority();
    }

    public Runnable getPayload() {
        return payload;
    }

    public String getJobId() {
        return jobId;
    }

    @Override
    public String toString() {
        return "Job{" +
                "jobId='" + jobId + '\'' +
                ", priority=" + priority +
                ", status=" + status +
                '}';
    }

    public void setStatus(JobStatus status) {
        this.status = status;
    }
}
