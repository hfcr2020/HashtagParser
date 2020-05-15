package service;

import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.Option;
import type.TiktokScraper;
import writer.HashtagWriter;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MergeService {

    public List<TiktokScraper> readTiktokFile(File tiktokFile, String country, String hashtagName) throws IOException {
        List<TiktokScraper> tiktokSampleList = new ArrayList<>();
        Configuration configuration = Configuration.builder().options(Option.DEFAULT_PATH_LEAF_TO_NULL).build();
        System.out.println("File " + tiktokFile.getName() + " will be read");
        ArrayList<TiktokScraper> tikTokJson = JsonPath.parse(tiktokFile).read("$");
        for (Object sample: tikTokJson) {
            DocumentContext doc = JsonPath.using(configuration).parse(sample);
            tiktokSampleList.add(TiktokScraper.builder()
                    .id(doc.read("$.id").toString())
                    .text(doc.read("$.text").toString())
                    .createTime(doc.read("$.createTime").toString())
                    .authorId(doc.read("$.authorMeta.id").toString())
                    .authorName(doc.read("$.authorMeta.name").toString())
                    .authorNickName(doc.read("$.authorMeta.nickName").toString())
                    .authorFollowers(doc.read("$.authorMeta.following").toString())
                    .authorFans(doc.read("$.authorMeta.fans").toString())
                    .authorHeart(doc.read("$.authorMeta.heart").toString())
                    .authorVideo(doc.read("$.authorMeta.video").toString())
                    .authorDigg(doc.read("$.authorMeta.digg").toString())
                    .authorVerified(doc.read("$.authorMeta.verified").toString())
                    .authorPrivate(doc.read("$.authorMeta.private").toString())
                    .authorSignature(doc.read("$.authorMeta.signature").toString())
                    .authorAvatar(doc.read("$.authorMeta.avatar").toString())
                    .musicId(doc.read("$.musicMeta.musicId").toString())
                    .musicName(doc.read("$.musicMeta.musicName").toString())
                    .musicAuthor(doc.read("$.musicMeta.musicAuthor").toString())
                    .musicOriginal(doc.read("$.musicMeta.musicOriginal").toString())
                    .playUrl(doc.read("$.musicMeta.playUrl") != null ?
                            doc.read("$.musicMeta.playUrl").toString() : "")
                    .musicId(doc.read("$.musicMeta.musicId").toString())
                    .coversDefault(doc.read("$.covers.default").toString())
                    .coversOrigin(doc.read("$.covers.origin").toString())
                    .coversDynamic(doc.read("$.covers.dynamic").toString())
                    .imageUrl(doc.read("$.imageUrl").toString())
                    .videoUrl(doc.read("$.videoUrl").toString())
                    .videoUrlNoWaterMark(doc.read("$.videoUrlNoWaterMark").toString())
                    .videoWidth(doc.read("$.videoMeta.width").toString())
                    .videoHeight(doc.read("$.videoMeta.height").toString())
                    .videoRatio(doc.read("$.videoMeta.ratio").toString())
                    .downloaded(doc.read("$.downloaded").toString())
                    .mentions(doc.read("$.mentions").toString())
                    .webVideoUrl(doc.read("$.webVideoUrl").toString())
                    .videoDuration(doc.read("$.videoMeta.duration").toString())
                    .diggCount(doc.read("$.diggCount").toString())
                    .shareCount(doc.read("$.shareCount").toString())
                    .playCount(doc.read("$.playCount").toString())
                    .commentCount(doc.read("$.commentCount").toString())
                    .hashtags(doc.read("$.hashtags").toString())
                    .hashtagName(hashtagName)
                    .country(country)
                    .build());
        }

        System.out.println("File " + tiktokFile.getName() + " was properly read");

        return tiktokSampleList;
    }

    public void mergeAllJsonFilesInDir(String inputFilename, String country) throws IOException {
        MergeService mergeService = new MergeService();
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
