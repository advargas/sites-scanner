package com.scanner;

import com.scanner.util.WebUtils;

import java.util.ArrayList;
import java.util.List;

public interface ContentFinder {

    /**
     * Gets the matches of a pattern in a list of websites.
     * @param sites list of websites
     * @param pattern pattern
     * @return lines with matches
     */
    default List<String> getMatches(List<String> sites, String pattern) {

        List<String> result = new ArrayList<>();

        for (String site : sites) {
            System.out.println("Scanning site: " + site);

            String content = WebUtils.downloadContent(site);

            for (String line : content.split(WebUtils.LINE_SEPARATOR)) {
                if (line.toLowerCase().contains(pattern.toLowerCase())) {
                    result.add("Site "+ site + ": " + line.trim());
                }
            }
        }
        return result;
    }
}
