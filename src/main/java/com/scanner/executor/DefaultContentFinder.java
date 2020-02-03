package com.scanner.executor;

import com.scanner.ContentFinder;

import java.util.List;
import java.util.concurrent.Callable;

/**
 * Default implementation of ContentFinder, using ExecutorService and Callable tasks.
 */
public class DefaultContentFinder implements Callable<List<String>>, ContentFinder {

    private List<String> sites;
    private String pattern;

    public DefaultContentFinder(List<String> sites, String pattern) {
        this.sites = sites;
        this.pattern = pattern;
    }

    @Override
    public List<String> call() throws Exception {
        // It finds the patterns in the list of sites
        return getMatches(this.sites, this.pattern);
    }
}
