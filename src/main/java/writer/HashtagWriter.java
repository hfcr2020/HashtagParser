package writer;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

public class HashtagWriter {

    private static final String[] HEADERS = { "HASHTAG", "FREQUENCY"};

    public void createCSVFile(Map map, String fileName) throws IOException {
        FileWriter out = new FileWriter(fileName);
        try (
            CSVPrinter printer = new CSVPrinter(out, CSVFormat.DEFAULT.withHeader(HEADERS)))
            {map.forEach((hashtag, freq) -> {
                try {
                    printer.printRecord(hashtag, freq);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        }
    }
}
