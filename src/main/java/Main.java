import parser.JSONHashtag;
import writer.HashtagWriter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class Main {

    public static void main(String[] args) throws IOException {
        String inputFilename = args[0];
        String outputFilename = args[1];

        JSONHashtag parser = new JSONHashtag();
        HashtagWriter write = new HashtagWriter();

        ArrayList<String> hash = parser.getListOfHashtags(inputFilename);
        HashMap<String, Integer> hashFreq = parser.getFrecuencyMap(hash);
        write.createCSVFile(hashFreq, outputFilename);
    }
}
