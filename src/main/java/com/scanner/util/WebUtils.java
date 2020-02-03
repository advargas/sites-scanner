package com.scanner.util;

import org.jsoup.Jsoup;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Utility to download and extract content from a web page.
 */
public class WebUtils {

    public static final String LINE_SEPARATOR = "------";

    public static String downloadContent(String url) {

        try {
            URL urlResource = new URL(url);
            InputStream input = urlResource.openStream();  // throws an IOException
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));

            String line = "";
            StringBuilder rawContent = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                rawContent.append(line + LINE_SEPARATOR);
            }
            return Jsoup.parse(rawContent.toString()).text();

        } catch (MalformedURLException e) {
            System.err.println("ERROR: Malformed URL " + url);
        } catch (IOException e) {
            System.err.println("ERROR: URL not available " + url);
        }
        return "";
    }
}
