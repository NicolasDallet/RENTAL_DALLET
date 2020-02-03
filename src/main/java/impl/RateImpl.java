package impl;

import com.opencsv.bean.CsvBindByName;
import modele.Rate;

import java.util.Objects;

public final class RateImpl implements Rate {
    private static int idGen = 0;

    @CsvBindByName
    private String id;
    @CsvBindByName
    private double price;
    @CsvBindByName
    private int duration;

    public RateImpl(){ this.id = "r" + ++idGen;}

    public RateImpl(double price, int rentalDuration) {
        this();
        this.price = price;
        this.duration = rentalDuration;
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
    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public double getPrice() {
        return this.price;
    }

    @Override
    public void setDuration(int duration) {
        this.duration = duration;
    }

    @Override
    public int getDuration() {
        return this.duration;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RateImpl rate = (RateImpl) o;
        return Objects.equals(id, rate.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Rate {");
        sb.append("price=").append(price);
        sb.append(", rentalDuration=").append(duration);
        sb.append('}');
        return sb.toString();
    }
}
