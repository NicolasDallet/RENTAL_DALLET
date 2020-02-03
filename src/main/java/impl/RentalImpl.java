package impl;

import com.opencsv.bean.CsvBindByName;
import exceptions.InvalidRentalException;
import modele.Rate;
import modele.Rental;
import modele.Video;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public final class RentalImpl implements Rental {
    private static int idGen = 0;

    @CsvBindByName
    private String id;
    @CsvBindByName
    private String videoId;
    @CsvBindByName
    private String rateId;

    private VideoImpl video;
    private RateImpl rate;

    private LocalDateTime returnDate;

    public RentalImpl() {
        this.id = "rent" + ++idGen;
    }

    public RentalImpl(Video video, Rate rate) throws InvalidRentalException {
        this();
        this.video = (VideoImpl) video;
        setRate(rate);
    }

    @Override
    public LocalDateTime getReturnDate() {
        return this.returnDate;
    }

    @Override
    public void setOrderDate(LocalDateTime orderDate){
        int duration = rate.getDuration();
        returnDate = orderDate.plusHours(duration);
    }

    @Override
    public double getPrice() {
        return rate != null ? rate.getPrice() : 0d;
    }

    @Override
    public String getId() {
        return this.id;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }

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
        this.video = (VideoImpl) video;
        this.videoId = video.getId();
    }

    @Override
    public Rate getRate() {
        return this.rate;
    }

    @Override
    public void setRate(Rate rate) throws InvalidRentalException {
        if (rate != null) {
            this.rate = (RateImpl) rate;
            this.rateId = rate.getId();
        } else {
            throw new InvalidRentalException("A rate must be set first for this video before rental is allowed: " + video.getTitle());
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RentalImpl rental = (RentalImpl) o;
        return Objects.equals(id, rental.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("\nRental {");
        sb.append(video);
        sb.append(",\n").append(rate);
        if (returnDate != null) {
            DateTimeFormatter dtf = DateTimeFormatter.ISO_DATE_TIME;
            sb.append(", \nreturn date=").append(dtf.format(returnDate));
        } else {
            sb.append(", \nreturn date=").append("No return date available");
        }
        sb.append("\n}");
        return sb.toString();
    }
}
