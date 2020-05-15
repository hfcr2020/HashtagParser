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
    String text;
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
    String authorId;
    String authorNickName;
    String authorVideo;
    String authorDigg;
    String authorVerified;
    String authorPrivate;
    String authorSignature;
    String authorAvatar;
    String musicId;
    String musicName;
    String musicAuthor;
    String musicOriginal;
    String playUrl;
    String coversDefault;
    String coversOrigin;
    String coversDynamic;
    String imageUrl;
    String videoUrlNoWaterMark;
    String videoWidth;
    String videoHeight;
    String videoRatio;
    String downloaded;
    String mentions;
    String webVideoUrl;
}
