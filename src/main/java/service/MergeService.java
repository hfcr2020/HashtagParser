package service;

import com.jayway.jsonpath.JsonPath;
import org.json.simple.JSONObject;
import type.TiktokScraper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MergeService {

    public List<TiktokScraper> readTiktokFile(File tiktokFile, String country, String hashtagName) throws IOException {
        List<TiktokScraper> tiktokSampleList = new ArrayList<>();
        ArrayList<TiktokScraper> tikTokJson = JsonPath.parse(tiktokFile).read("$");
        for (Object sample: tikTokJson) {
            tiktokSampleList.add(TiktokScraper.builder()
                    .id(JsonPath.parse(sample).read("$.id").toString())
                    .createTime(JsonPath.parse(sample).read("$.createTime").toString())
                    .authorName(JsonPath.parse(sample).read("$.authorMeta.name").toString())
                    .authorFollowers(JsonPath.parse(sample).read("$.authorMeta.following").toString())
                    .authorFans(JsonPath.parse(sample).read("$.authorMeta.fans").toString())
                    .authorHeart(JsonPath.parse(sample).read("$.authorMeta.heart").toString())
                    .videoUrl(JsonPath.parse(sample).read("$.webVideoUrl").toString())
                    .videoDuration(JsonPath.parse(sample).read("$.videoMeta.duration").toString())
                    .diggCount(JsonPath.parse(sample).read("$.diggCount").toString())
                    .shareCount(JsonPath.parse(sample).read("$.shareCount").toString())
                    .playCount(JsonPath.parse(sample).read("$.playCount").toString())
                    .commentCount(JsonPath.parse(sample).read("$.commentCount").toString())
                    .hashtags(JsonPath.parse(sample).read("$.hashtags").toString())
                    .hashtagName(hashtagName)
                    .country(country)
                    .build());
        }

        return tiktokSampleList;
    }
}
