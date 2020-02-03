package impl;

import modele.RentalHistory;
import modele.RentalOrder;

import java.util.HashSet;
import java.util.Set;

public class RentalHistoryImpl implements RentalHistory {

    private final Set<RentalOrder> rentalOrders;

    public RentalHistoryImpl() {
        this.rentalOrders = new HashSet<>();
    }

    @Override
    public boolean addRentalOrder(RentalOrder rentalOrder) {
        return this.rentalOrders.add(rentalOrder);
    }

    @Override
    public Set<RentalOrder> getPreviousOrders() {
        return new HashSet<>(this.rentalOrders);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Rental History {\n");
        sb.append(rentalOrders);
        sb.append("\n}");
        return sb.toString();
    }
}
