package com.scanner;

import com.scanner.util.Partition;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public interface SitesScanner {

    /**
     * Splits the list of sites according to the number of workers, the remaining is put in
     * an extra group.
     * @param sites list of sites
     * @param workers number of workers
     * @return groups
     */
    default List<List<String>> extractGroups(List<String> sites, int workers) {

        if (sites != null && !sites.isEmpty()) {
            if (workers == 0 || workers > sites.size()) {
                workers = 1;
            }
            return Partition.ofSize(sites, sites.size() / workers);
        }
        return new ArrayList<>(new ArrayList<>());
    }

    /**
     * Scans a list of sites to find a specific pattern, with workers in parallel.
     * @param sites list of sites
     * @param workers number of workers
     * @param pattern pattern to find
     * @return list of matches
     * @throws InterruptedException
     * @throws ExecutionException
     */
    List<String> scan(List<String> sites, int workers, String pattern) throws InterruptedException, ExecutionException;
}
