package service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

public class SearchService {

    public void generateTiktokFilesFromDictionary() {
        Properties wordsFile = new Properties();

        try (
            InputStream inputStream = getClass().getClassLoader().getResourceAsStream("dictionary.properties")) {
            wordsFile.load(inputStream);
            List<String> words = Arrays.asList(wordsFile.getProperty("words").split(","));
            for (String s: words
                 ) {
                generateTiktokFilesForHashtag(s, "10");
            }

        } catch (IOException e) {
            e.printStackTrace();
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
        runCommandInCmd("tiktok-scraper hashtag " + hashtagName + " -n " + numberOfEntries +  " -f " + hashtagName + " -t all");
    }
}