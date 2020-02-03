package impl;

import com.opencsv.bean.CsvBindByName;
import modele.Rate;
import modele.Video;
import modele.VideoRate;

import java.util.Objects;

public class VideoRateImpl implements VideoRate {
    @CsvBindByName
    private String videoId;
    @CsvBindByName
    private String rateId;

    private VideoImpl video;
    private RateImpl rate;

    public VideoRateImpl(){ }

    @Override
    public String getVideoId() {
        return videoId;
    }

    @Override
    public void setVideoId(String videoId) {
        this.videoId = videoId;
    }

    @Override
    public String getRateId() {
        return rateId;
    }

    @Override
    public void setRateId(String rateId) {
        this.rateId = rateId;
    }

    @Override
    public Video getVideo() {
        return this.video;
    }

    @Override
    public void setVideo(Video video) {
        this.video = (VideoImpl)video;
        this.videoId = this.video.getId();
    }

    @Override
    public Rate getRate() {
        return this.rate;
    }

    @Override
    public void setRate(Rate rate) {
        this.rate = (RateImpl)rate;
        this.rateId = this.rate.getId();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VideoRateImpl videoRate = (VideoRateImpl) o;
        return Objects.equals(video, videoRate.video);
    }

    @Override
    public int hashCode() {
        return Objects.hash(video);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("VideoRate {");
        sb.append("video=").append(video);
        sb.append(", rate=").append(rate);
        sb.append('}');
        return sb.toString();
    }
}
