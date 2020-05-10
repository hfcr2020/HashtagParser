package service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

public class SearchService {

    public void generateTiktokFilesFromDictionary(String numberOfSamples) throws IOException {
        List<String> words = new ArrayList<>();
        BufferedReader br = new BufferedReader(new FileReader("dictionary.txt"));
        String line;
        while ((line = br.readLine()) != null) {
            words = Arrays.asList(line.split(","));
        }

        for (String s: words
        ) {
            generateTiktokFilesForHashtag(s, numberOfSamples);
        }
    }

    public void generateTrendingFile(String outputFilename, String numberOfEntries) {
        runCommandInCmd("tiktok-scraper trend -t all " + " -n " + numberOfEntries +  " -f " + outputFilename);
    }

    public void runCommandInCmd(String command) {
        ProcessBuilder processBuilder = new ProcessBuilder();
        processBuilder.command("cmd.exe", "/c", command);

        try {

            Process process = processBuilder.start();

            BufferedReader reader =
                    new BufferedReader(new InputStreamReader(process.getInputStream()));

            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }

            int exitCode = process.waitFor();
            System.out.println("\nExited with error code : " + exitCode);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void generateTiktokFilesForHashtag(String hashtagName, String numberOfEntries) {
        String command = "tiktok-scraper hashtag " + hashtagName + " -n " + numberOfEntries +  " -f " + hashtagName + " -t all";
        runCommandInCmd(command);
    }
}