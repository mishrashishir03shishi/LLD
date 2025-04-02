package com.example.lld.producerconsumer;

import com.example.lld.producerconsumer.counter.Counter;
import com.example.lld.producerconsumer.job.Job;
import com.example.lld.producerconsumer.job.JobStatus;
import com.example.lld.producerconsumer.scheduler.Scheduler;
import com.example.lld.producerconsumer.scheduler.SchedulerImpl;
import com.example.lld.producerconsumer.worker.Worker;
import com.example.lld.producerconsumer.worker.WorkerImpl;

import java.util.concurrent.*;

public class JobScheduler {

    public static void main(String[] args){

        BlockingQueue<Job> queue = new PriorityBlockingQueue<>(5);
        ConcurrentHashMap<String, Job> map = new ConcurrentHashMap<>();

        Counter submissionCounter = new Counter();

        Scheduler scheduler = new SchedulerImpl(queue, map, submissionCounter);

        ExecutorService schedulerExecutorService = Executors.newFixedThreadPool(5);
        for(int i=1; i<=50; i++){
            final int jobNumber = i;
            Job job = new Job("Job " + i, () -> {
                System.out.println("Executing job number " + jobNumber);
                try{
                    Thread.sleep(500);
                }
                catch (InterruptedException e){
                    System.out.println("Thread interrupted");
                }
            }, i%10, JobStatus.SUBMITTED);
            schedulerExecutorService.execute(() -> {
                System.out.println("Submitting job " + job.getJobId());
                scheduler.submit(job);
            });
        }

        schedulerExecutorService.shutdown();

        try{
            Thread.sleep(200);
        }
        catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }

        ExecutorService workerExecutorService = Executors.newFixedThreadPool(5);

        Counter workerCounter = new Counter();

        for (int i = 0; i < 5; i++) {
            Worker worker = new WorkerImpl(queue, map, workerCounter);
            workerExecutorService.submit(() -> {
                while (true) {
                    worker.executeJob();
                }
            });
        }

        workerExecutorService.shutdown();

        Executors.newSingleThreadScheduledExecutor().scheduleAtFixedRate(() -> {
            System.out.println("Jobs in queue: " + queue.size());
            System.out.println("Executed: " + workerCounter.getCounter());
        }, 0, 1, TimeUnit.SECONDS);

        while (!schedulerExecutorService.isTerminated() && !workerExecutorService.isTerminated()){

        }

        System.out.println("Total Jobs Scheduled : " + submissionCounter.getCounter());
        System.out.println("Total Jobs Executed : " + workerCounter.getCounter());

    }
}
