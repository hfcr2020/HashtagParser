package writer;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import type.Hashtag;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class HashtagWriter {

    private static final String[] HEADERS = { "HASHTAG", "FREQUENCY", "COUNTRY"};

    public void createCSVFile(List<Hashtag> hashtagList, String fileName) throws IOException {
        FileWriter out = new FileWriter(fileName);
        try (
            CSVPrinter printer = new CSVPrinter(out, CSVFormat.DEFAULT.withHeader(HEADERS)))
            {hashtagList.forEach( hashtag -> {
                try {
                    printer.printRecord(hashtag.getName(), hashtag.getFrequency(), hashtag.getCountry());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        }
    }
}
