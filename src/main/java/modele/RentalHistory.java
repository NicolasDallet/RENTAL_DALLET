package modele;

import java.util.Set;

public interface RentalHistory {
    boolean addRentalOrder(RentalOrder rentalOrder);
    Set<RentalOrder> getPreviousOrders();
}
