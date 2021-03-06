package writer;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import type.Hashtag;
import type.TiktokScraper;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class HashtagWriter {

    private static final String[] HEADERS = { "HASHTAG", "FREQUENCY", "COUNTRY"};
    private static final String[] HEADERS_TIKTOK = { "ID", "CREATE_TIME", "TEXT", "AUTHOR_NAME",
                        "AUTHOR_FOLLOWER", "AUTHOR_FANS", "AUTHOR_HEART", "VIDEO_URL",
                        "VIDEO_DURATION", "DIGG_COUNT", "SHARE_COUNT", "PLAY_COUNT", "COMMENT_COUNT",
                        "HASHTAGS", "COUNTRY", "HASHTAG_NAME" };

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

    public void createTiktokCSVFile(List<TiktokScraper> tiktokList, String fileName) throws IOException {
        FileWriter out = new FileWriter(fileName);
        try (
                CSVPrinter printer = new CSVPrinter(out, CSVFormat.DEFAULT.withHeader(HEADERS_TIKTOK)))
        {tiktokList.forEach( tiktokScraper -> {
            try {
                printer.printRecord(tiktokScraper.getId(),
                                    tiktokScraper.getText(),
                                    tiktokScraper.getCreateTime(),
                                    tiktokScraper.getAuthorId(),
                                    tiktokScraper.getAuthorName(),
                                    tiktokScraper.getAuthorNickName(),
                                    tiktokScraper.getAuthorFollowers(),
                                    tiktokScraper.getAuthorFans(),
                                    tiktokScraper.getAuthorHeart(),
                                    tiktokScraper.getAuthorVideo(),
                                    tiktokScraper.getAuthorDigg(),
                                    tiktokScraper.getAuthorVerified(),
                                    tiktokScraper.getAuthorPrivate(),
                                    tiktokScraper.getAuthorSignature(),
                                    tiktokScraper.getAuthorAvatar(),
                                    tiktokScraper.getMusicId(),
                                    tiktokScraper.getMusicName(),
                                    tiktokScraper.getMusicAuthor(),
                                    tiktokScraper.getMusicOriginal(),
                                    tiktokScraper.getPlayUrl(),
                                    tiktokScraper.getCoversDefault(),
                                    tiktokScraper.getCoversOrigin(),
                                    tiktokScraper.getCoversDynamic(),
                                    tiktokScraper.getImageUrl(),
                                    tiktokScraper.getWebVideoUrl(),
                                    tiktokScraper.getVideoUrl(),
                                    tiktokScraper.getVideoWidth(),
                                    tiktokScraper.getVideoHeight(),
                                    tiktokScraper.getVideoRatio(),
                                    tiktokScraper.getVideoDuration(),
                                    tiktokScraper.getDiggCount(),
                                    tiktokScraper.getShareCount(),
                                    tiktokScraper.getPlayCount(),
                                    tiktokScraper.getCommentCount(),
                                    tiktokScraper.getDownloaded(),
                                    tiktokScraper.getMentions(),
                                    tiktokScraper.getHashtags(),
                                    tiktokScraper.getCountry(),
                                    tiktokScraper.getHashtagName());
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        }
    }
}
