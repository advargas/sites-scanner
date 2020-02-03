package com.scanner.executor;

import com.scanner.SitesScanner;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Default implementation of SitesScanner, using ExecutorService and Callable tasks.
 */
public class DefaultSitesScanner implements SitesScanner {

    @Override
    public List<String> scan(List<String> sites, int workers, String pattern) throws InterruptedException, ExecutionException {

        List<List<String>> groups = extractGroups(sites, workers);

        System.out.println("Starting scanning ...");
        System.out.println("Workers: " + groups.size());

        // Initiates a thread pool with the number of groups (workers)
        ExecutorService executorService = Executors.newFixedThreadPool(groups.size());

        // Adds the tasks
        List<DefaultContentFinder> callableTasks = new ArrayList<>();

        for (int i = 0; i < groups.size(); i++) {
            List<String> group = groups.get(i);
            System.out.println("Worker " + (i+1) + ", sites to scan: " + group.size());
            callableTasks.add(new DefaultContentFinder(group, pattern));
        }

        // Invokes all workers and awaits for the response asynchronously
        List<Future<List<String>>> futures = executorService.invokeAll(callableTasks);

        List<String> results = new ArrayList<>();
        for (Future<List<String>> future : futures) {
            results.addAll(future.get());
        }

        // Shutdowns the ExecutorService
        executorService.shutdown();

        System.out.println("Scanning completed with " + results.size() + " findings of pattern " + pattern);
        return results;
    }
}
