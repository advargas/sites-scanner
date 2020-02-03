package com.scanner.util;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

/**
 * Utility to manage files.
 */
public class FileUtils {

    public static List<String> readSites(String filePath) throws IOException {
        System.out.println("Reading sites from " + filePath);
        return Files.readAllLines(Path.of(filePath));
    }

    public static void writeResults(List<String> results) throws IOException {
        System.out.println("Writing results to output.txt");
        Files.write(Path.of("output.txt"), results, Charset.defaultCharset());
    }
}
