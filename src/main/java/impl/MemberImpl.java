package impl;

import com.opencsv.bean.CsvBindByName;
import modele.Member;
import modele.Rental;
import modele.RentalHistory;
import modele.RentalOrder;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public final class MemberImpl implements Member {
    private static int idGen = 0;

    @CsvBindByName
    private String id;
    @CsvBindByName
    private String name;
    @CsvBindByName
    private String email;
    @CsvBindByName
    private String address;
    @CsvBindByName
    private String creditCard;

    private RentalOrder rentalOrder;
    private RentalHistory rentalHistory;

    public MemberImpl(){
        this.id = "m" + ++idGen;
        this.rentalHistory = new RentalHistoryImpl();
    }

    public MemberImpl(String name, String email, String address, String creditCardNumber) {
        this();
        this.name = name;
        this.email = email;
        this.address = address;
        this.creditCard = creditCardNumber;
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
    public String getName() {
        return this.name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getEmail() {
        return this.email;
    }

    @Override
    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String getAddress() {
        return this.address;
    }

    @Override
    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String getCreditCard() {
        return this.creditCard;
    }

    @Override
    public void setCreditCard(String creditCard) {
        this.creditCard = creditCard;
    }

    @Override
    public RentalOrder getCurrentRentalOrder() {
        return this.rentalOrder;
    }

    @Override
    public void setCurrentRentalOrder(RentalOrder rentalOrder) {
        this.rentalOrder = rentalOrder;
    }

    @Override
    public void updateRentalHistory() {
        LocalDateTime now = LocalDateTime.now();
        List<Rental> remaining = this.rentalOrder.getRentals().stream()
                            .filter(rental -> now.compareTo(rental.getReturnDate()) <= 0).collect(Collectors.toList());
        if (remaining.isEmpty()) {
            this.rentalHistory.addRentalOrder(this.rentalOrder);
            this.rentalOrder = null;
        }
    }

    @Override
    public boolean addToRentalHistory(RentalOrder order) {
        return this.rentalHistory.addRentalOrder(order);
    }

    @Override
    public RentalHistory getRentalHistory() {
        return this.rentalHistory;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MemberImpl member = (MemberImpl) o;
        return Objects.equals(id, member.id) &&
                Objects.equals(name, member.name) &&
                Objects.equals(email, member.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, email);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Member Description {");
        sb.append("name=").append(name)
                .append(", email=").append(email)
                .append(", address=").append(address)
                .append(", creditcard=").append(creditCard);
        sb.append(",").append(rentalOrder);
        sb.append(", \n").append(rentalHistory);
        sb.append("\n}");
        return sb.toString();
    }
}
