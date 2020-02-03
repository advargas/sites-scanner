package com.scanner;

import com.scanner.executor.DefaultSitesScanner;
import com.scanner.util.FileUtils;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class SitesScannerApp {

    public static void main(String[] args) {
        System.out.println("Sites-Scanner starting ...");

        if (args == null || args.length < 3) {
            System.err.println("Error in parameters, you should set: sites file path, number or workers and pattern");
            System.exit(0);
        }

        SitesScanner scanner = new DefaultSitesScanner();
        List<String> results = null;
        try {
            results = scanner.scan(FileUtils.readSites(args[0]), Integer.parseInt(args[1]), args[2]);
            FileUtils.writeResults(results);

        } catch (InterruptedException e) {
            System.err.println("Error executing concurrent workers ...");
            e.printStackTrace();
        } catch (ExecutionException e) {
            System.err.println("Error getting responses from concurrent workers");
            e.printStackTrace();
        } catch (IOException e) {
            System.err.println("Error reading/writing sites.txt/output.txt files");
            e.printStackTrace();
        }
    }
}
