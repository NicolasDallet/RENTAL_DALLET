package modele;

import exceptions.InvalidRentalException;

import java.time.LocalDateTime;

public interface Rental {
    String getId();
    void setId(String id);
    String getVideoId();
    void setVideoId(String videoId);
    String getRateId();
    void setRateId(String rateId);
    Video getVideo();
    void setVideo(Video video);
    Rate getRate();
    void setRate(Rate rate) throws InvalidRentalException;
    double getPrice();
    void setOrderDate(LocalDateTime orderDate);
    LocalDateTime getReturnDate();
}
