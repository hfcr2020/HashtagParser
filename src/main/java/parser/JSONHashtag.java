package parser;

import com.jayway.jsonpath.JsonPath;
import org.json.simple.JSONObject;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class JSONHashtag {

    private static final String WORKING_DIR = Paths.get("")
            .toAbsolutePath()
            .toString();

    public ArrayList<String> getListOfHashtags(String filename) throws IOException {
        ArrayList<String> hashtagList = new ArrayList<>();

        File tikTokFile = new File( WORKING_DIR + "/" + filename);

        ArrayList<JSONObject> tikTokJson = JsonPath.parse(tikTokFile).read("$");

        for (Object record: tikTokJson) {
            ArrayList<JSONObject> hashtags = JsonPath.parse(record).read("$.hashtags");
            for (Object s : hashtags) {
                hashtagList.add(JsonPath.parse(s).read("$.name").toString());
            }
        }
        return hashtagList;
    }

    public HashMap<String, Integer> getFrecuencyMap(List<String> originalList) {
        List<String> hashtagWithoutDuplicates = originalList
                .stream()
                .distinct()
                .collect(Collectors.toList());

        HashMap<String, Integer> hashTagMap = new HashMap<>();
        for (String s: hashtagWithoutDuplicates) {
            hashTagMap.put(s, Collections.frequency(originalList, s));
        }

        return hashTagMap;
    }
}
