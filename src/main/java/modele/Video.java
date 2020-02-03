package modele;

import java.util.List;

public interface Video {
    String getId();
    void setId(String id);
    String getTitle();
    void setTitle(String title);
    boolean addGenre(String genre);
    List<String> getGenres();
    void setGenres(List<String> genres);
    int getLength();
    void setLength(int length);
    int getYear();
    void setYear(int year);
    List<String> getActors();
    void setActors(List<String> actors);
    String getDirector();
    void setDirector(String director);
    double getRating();
    void setRating(double rating);
    void addRating(double rating);
}
