package impl;

import modele.Payment;

public class PaymentImpl implements Payment {
    private double totalCost;

    public PaymentImpl(){
    }

    @Override
    public void addRentalCost(double price) {
        this.totalCost += price;
    }

    @Override
    public double getTotalCost() {
        return this.totalCost;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Payment {");
        sb.append("totalCost=").append(totalCost);
        sb.append('}');
        return sb.toString();
    }
}
