package impl;

import com.opencsv.bean.CsvBindAndSplitByName;
import com.opencsv.bean.CsvBindByName;
import modele.Video;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public final class VideoImpl implements Video {
    private static int idGen = 0;
    @CsvBindByName
    private String id;
    @CsvBindByName
    private String title;
    @CsvBindAndSplitByName(elementType = String.class, splitOn = "\\|", writeDelimiter = "\\|", collectionType = ArrayList.class)
    private List<String> genres;
    @CsvBindByName
    private int length;
    @CsvBindByName
    private int year;
    @CsvBindAndSplitByName(elementType = String.class, splitOn = ";+", writeDelimiter = ";", collectionType = ArrayList.class)
    private List<String> actors;
    @CsvBindByName
    private String director;
    @CsvBindByName
    private double rating;

    public VideoImpl() {
        this.id = "v" + ++idGen;
        this.genres = new ArrayList<>();
    }

    public VideoImpl(String title, String genre, int length, int year, List<String> actors, String director) {
        this();
        this.title = title;
        this.genres.add(genre);
        this.length = length;
        this.year = year;
        this.actors = new ArrayList<>();
        this.actors.addAll(actors);
        this.director = director;
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
    public String getTitle() {
        return this.title;
    }

    @Override
    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public boolean addGenre(String genre) {
        return this.genres.add(genre);
    }

    @Override
    public List<String> getGenres() {
        return new ArrayList<>(this.genres);
    }

    @Override
    public void setGenres(List<String> genres) {
        this.genres = new ArrayList<>(genres);
    }

    @Override
    public int getLength() {
        return this.length;
    }

    @Override
    public void setLength(int length) {
        this.length = length;
    }

    @Override
    public int getYear() {
        return this.year;
    }

    @Override
    public void setYear(int year) {
        this.year = year;
    }

    @Override
    public List<String> getActors() {
        return new ArrayList<>(actors);
    }

    @Override
    public void setActors(List<String> actors) {
        this.actors = new ArrayList<>(actors);
    }

    @Override
    public String getDirector() {
        return this.director;
    }

    @Override
    public void setDirector(String director) {
        this.director = director;
    }

    @Override
    public double getRating() {
        return this.rating;
    }

    @Override
    public void setRating(double rating) {
        this.rating = rating;
    }

    @Override
    public void addRating(double rating) {
        this.rating = this.rating == 0d ? rating : (this.rating + rating) / 2.0d;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VideoImpl video = (VideoImpl) o;
        return year == video.year &&
                Objects.equals(id, video.id) &&
                Objects.equals(title, video.title) &&
                Objects.equals(actors, video.actors) &&
                Objects.equals(director, video.director);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, year, actors, director);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("\nVideo Description {");
        sb.append("title=").append(title);
        sb.append(", genres=").append(genres);
        sb.append(", length=").append(length);
        sb.append(", year=").append(year);
        sb.append(", actors=").append(actors);
        sb.append(", director=").append(director);
        sb.append(", rating=").append(rating);
        sb.append('}');
        return sb.toString();
    }
}
