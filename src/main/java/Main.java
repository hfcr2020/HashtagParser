import org.apache.tools.ant.DirectoryScanner;
import service.FrequencyService;
import service.MergeService;
import service.SearchService;
import type.Hashtag;
import type.TiktokScraper;
import writer.HashtagWriter;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static parser.JSONHashtag.getCountryFromFile;

public class Main {

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Insert country: ");
        String country = scanner.next();
        String inputFilename = "tiktok_" + country; //Trending filename

        FrequencyService frequencyService = new FrequencyService();
        SearchService searchService = new SearchService();
        MergeService mergeService = new MergeService();

        System.out.println("Insert number of samples to get for trending topics: ");
        searchService.generateTrendingFile(inputFilename, scanner.next()); //Number of samples to get

        List<Hashtag> hashFreq = frequencyService.getHashtagListOrderedByFrequencyUsingInputFile(inputFilename + ".json");

        System.out.println("Insert number of most popular topics to search: ");
        List<Hashtag> mostPopularHashtags = hashFreq.subList(0, Integer.parseInt(scanner.next())); //Number of top hashtags to get

        System.out.println("For each of these most popular topics, how many samples do you want to get? ");
        String numberOfSamplesForEachTopics = scanner.next();
        int count = 0;
        for (Hashtag h: mostPopularHashtags
             ) {
            count++;
            System.out.println("Top ten: # " + count + " " + h.getName());
            searchService.generateTiktokFilesForHashtag(h.getName(), numberOfSamplesForEachTopics); //Number of samples
        }

        System.out.println("Now I will run tiktok-scraper for each of the words in the dictionary.txt file ");
        searchService.generateTiktokFilesFromDictionary(numberOfSamplesForEachTopics);

        System.out.println("Merging all json files... ");
        File jsonFileDir = new File("./");

        List<TiktokScraper> tiktokScraperList = new ArrayList<>();

        for (File file: jsonFileDir.listFiles()) {
            if (file.getName().contains(".json") && !file.getName().equals(inputFilename)) {
                String hashtagName = file.getName().substring(0, file.getName().indexOf(".json"));
                List<TiktokScraper> tempList = mergeService.readTiktokFile(file, country, hashtagName);
                tiktokScraperList.addAll(tempList);
            }
        }

        HashtagWriter writer = new HashtagWriter();
        writer.createTiktokCSVFile(tiktokScraperList, "output.csv");


    }
}
