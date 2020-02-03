package modele;

public interface VideoRate {
    String getVideoId();
    void setVideoId(String videoId);
    String getRateId();
    void setRateId(String rateId);
    Video getVideo();
    void setVideo(Video video);
    Rate getRate();
    void setRate(Rate rate);
}
