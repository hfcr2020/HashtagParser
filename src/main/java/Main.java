import service.FrequencyService;
import service.MergeService;
import service.SearchService;
import type.Hashtag;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);

        FrequencyService frequencyService = new FrequencyService();
        SearchService searchService = new SearchService();
        MergeService mergeService = new MergeService();

        System.out.println("Insert country: ");
        String country = scanner.next();

        String numberOfSamplesForEachTopics = "";

        String inputFilename = "tiktok_" + country; //Trending filename
        System.out.println("What do you want to do? ");
        System.out.println("1. Merge CSV files in the existing folder ");
        System.out.println("2. Get tiktok files for hashtags in dictionary.txt file ");
        System.out.println("3. Generate frequency file for trending topics ");
        System.out.println("4. Run all steps and generate a final file ");
        System.out.println("Enter number: ");
        String option = scanner.next();
        switch (Integer.parseInt(option)) {
            case(1):
                System.out.println("Merging all json files... ");
                mergeService.mergeAllJsonFilesInDir(inputFilename, country);
                break;

            case(2):
                System.out.println("How many sample would you like to get? Insert number: ");
                numberOfSamplesForEachTopics = scanner.next();
                System.out.println("Now I will run tiktok-scraper for each of the words in the dictionary.txt file ");
                searchService.generateTiktokFilesFromDictionary(numberOfSamplesForEachTopics);
                break;

            case (3):
                System.out.println("How many sample would you like to get? Insert number: ");
                numberOfSamplesForEachTopics = scanner.next();
                System.out.println("Insert number of hashtags to search for : ");
                generateFrequencyFileAndSearchForTopHashtags(scanner.next(), inputFilename, numberOfSamplesForEachTopics);
                break;
            case (4):
                System.out.println("How many sample would you like to get? Insert number: ");
                numberOfSamplesForEachTopics = scanner.next();
                System.out.println("Insert number of hashtags to search for : ");
                generateFrequencyFileAndSearchForTopHashtags(scanner.next(), inputFilename, numberOfSamplesForEachTopics);
                searchService.generateTiktokFilesFromDictionary(numberOfSamplesForEachTopics);
                mergeService.mergeAllJsonFilesInDir(inputFilename, country);
                break;
        }
    }

    private static void generateFrequencyFileAndSearchForTopHashtags(String numberOfHashtags, String inputFilename,
                                                                     String numberOfSamplesForEachTopics) throws IOException {
        FrequencyService frequencyService = new FrequencyService();
        SearchService searchService = new SearchService();
        System.out.println("Insert number of hashtags to search for : ");
        List<Hashtag> mostPopularHashtags = frequencyService.generateListOfHashtags(inputFilename, numberOfSamplesForEachTopics, numberOfHashtags);
        int count = 0;
        for (Hashtag h: mostPopularHashtags
        ) {
            count++;
            System.out.println("Top ten: # " + count + " " + h.getName());
            searchService.generateTiktokFilesForHashtag(h.getName(), numberOfSamplesForEachTopics); //Number of samples
        }
    }
}
