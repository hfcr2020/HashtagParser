package parser;

import com.jayway.jsonpath.JsonPath;
import org.json.simple.JSONObject;
import type.Hashtag;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class JSONHashtag {

    public ArrayList<String> getListOfHashtags(File tikTokFile) throws IOException {
        ArrayList<String> hashtagList = new ArrayList<>();

        ArrayList<JSONObject> tikTokJson = JsonPath.parse(tikTokFile).read("$");

        for (Object record: tikTokJson) {
            ArrayList<JSONObject> hashtags = JsonPath.parse(record).read("$.hashtags");
            for (Object s : hashtags) {
                hashtagList.add(JsonPath.parse(s).read("$.name").toString());
            }
        }
        return hashtagList;
    }

    static public String getCountryFromFile(String filename) {
        int initialPos = filename.indexOf("_");
        int finalPos = filename.indexOf(".json");
        String country = filename.substring(initialPos + 1, finalPos);
        return country;
    }

    public List<Hashtag> getHashtagListOrderedByFrequency(List<String> originalList, String country) {
        List<String> hashtagWithoutDuplicates = originalList
                .stream()
                .distinct()
                .collect(Collectors.toList());

        List<Hashtag> hashtagList = new ArrayList<>();
        for (String s: hashtagWithoutDuplicates) {
            hashtagList.add(new Hashtag(s, Collections.frequency(originalList, s), country));
        }

        Collections.sort(hashtagList, Comparator.comparing(Hashtag::getFrequency).reversed());

        return hashtagList;
    }
}
