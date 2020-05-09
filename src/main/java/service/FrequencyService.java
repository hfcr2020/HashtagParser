package service;

import parser.JSONHashtag;
import type.Hashtag;
import writer.HashtagWriter;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static parser.JSONHashtag.getCountryFromFile;

public class FrequencyService {

    JSONHashtag parser = new JSONHashtag();
    HashtagWriter write = new HashtagWriter();

    public void generateFrequencyCsvFileForUniqueCountry(String inputFilename, String outputFilename) throws IOException {
        List<Hashtag> hashFreq = getHashtagListOrderedByFrequencyUsingInputFile(inputFilename);
        write.createCSVFile(hashFreq, outputFilename);
    }

    public List<Hashtag> getHashtagListOrderedByFrequencyUsingInputFile(String inputFilename) throws IOException {
        File file = new File("./" + inputFilename);
        String country = getCountryFromFile(file.getName());
        ArrayList<String> hash = parser.getListOfHashtags(file);
        List<Hashtag> hashFreq = parser.getHashtagListOrderedByFrequency(hash, country);
        return hashFreq;

    }

    public void generateFrequencyCsvFileForSeveralFiles(String outputFileName) throws IOException {
        List<Hashtag> consolidatedList = new ArrayList<>();
        File fileDir = new File("./filesByCountry");

        for (File file: fileDir.listFiles()) {
            String country = getCountryFromFile(file.getName());
            ArrayList<String> hash = parser.getListOfHashtags(file);
            List<Hashtag> hashFreq = parser.getHashtagListOrderedByFrequency(hash, country);
            consolidatedList= Stream.concat(consolidatedList.stream(), hashFreq.stream())
                    .collect(Collectors.toList());
        }

        consolidatedList.sort(Comparator.comparing(Hashtag::getFrequency).reversed());

        write.createCSVFile(consolidatedList, outputFileName);
    }
}
