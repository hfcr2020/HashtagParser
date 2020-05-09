import service.FrequencyService;
import service.SearchService;
import type.Hashtag;

import java.io.IOException;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {
        String inputFilename = "tiktok_colombia";

        FrequencyService frequencyService = new FrequencyService();
        SearchService searchService = new SearchService();

        searchService.generateTrendingFile(inputFilename, "1000");
        String jsonInputFilename = inputFilename + ".json";

        List<Hashtag> hashFreq = frequencyService.getHashtagListOrderedByFrequencyUsingInputFile(jsonInputFilename);

        List<Hashtag> mostPopularHashtags = hashFreq.subList(0, 1);
        int count = 0;
        for (Hashtag h: mostPopularHashtags
             ) {
            count++;
            System.out.println("Top ten: # " + count + " " + h.getName());
            searchService.generateTiktokFilesForHashtag(h.getName(), "1");
        }

        searchService.generateTiktokFilesFromDictionary();
    }
}
