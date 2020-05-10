package type;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class TiktokScraper {

    String hashtagName;
    String country;
    String id;
    String createTime;
    String authorName;
    String authorFollowers;
    String authorFans;
    String authorHeart;
    String videoUrl;
    String videoDuration;
    String diggCount;
    String shareCount;
    String playCount;
    String commentCount;
    String hashtags;
}
