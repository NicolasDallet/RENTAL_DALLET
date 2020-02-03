package modele;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

public interface RentalOrder {
    String getId();
    void setId(String id);
    Set<String> getRentalsId();
    void setRentalsId(List<String> rentalsId);
    String getMemberId();
    void setMemberId(String memberId);
    boolean isPayed();
    void setPayed(boolean payed);
    LocalDateTime getOrderDate();
    boolean addRental(Rental rental);
    Set<Rental> getRentals();
    void setRentals(Set<Rental> rentals);
    Member getMember();
    void setMember(Member member);
    Payment getPayment();
    void pay();
}
