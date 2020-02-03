package modele;

public interface Member {
    String getId();
    void setId(String id);
    String getName();
    void setName(String name);
    String getEmail();
    void setEmail(String email);
    String getAddress();
    void setAddress(String address);
    String getCreditCard();
    void setCreditCard(String creditCard);
    RentalOrder getCurrentRentalOrder();
    void setCurrentRentalOrder(RentalOrder rentalOrder);
    void updateRentalHistory();
    boolean addToRentalHistory(RentalOrder order);
    RentalHistory getRentalHistory();
}
