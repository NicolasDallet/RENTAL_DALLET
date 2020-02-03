package modele;

public interface Rate {
    String getId();
    void setId(String id);
    void setPrice(double price);
    double getPrice();
    void setDuration(int duration);
    int getDuration();
}
