package impl;

import com.opencsv.bean.CsvBindAndSplitByName;
import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvDate;
import modele.Member;
import modele.Payment;
import modele.Rental;
import modele.RentalOrder;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

/**
 * A rental order consists of several rentals.
 */
public final class RentalOrderImpl implements RentalOrder {
    private static int idGen = 0;

    @CsvBindByName
    private String id;
    @CsvBindAndSplitByName(elementType = String.class, splitOn = ";+", writeDelimiter = ";", collectionType = HashSet.class)
    private Set<String> rentalsId;
    @CsvBindByName
    private String memberId;
    @CsvBindByName
    private boolean payed;
    @CsvBindByName
    @CsvDate("yyyy-MM-dd\'T\'HH:mm:ss")
    //"yyyyMMdd\'T\'HHmmss"
    private LocalDateTime orderDate;

    private Set<Rental> rentals;
    private Member member;

    private Payment payment;

    public RentalOrderImpl() {
        this.id = "ro" + ++idGen;
        this.orderDate = LocalDateTime.now();
        this.rentals = new HashSet<>();
        this.payment = new PaymentImpl();
    }

    public RentalOrderImpl(Rental rental, Member member) {
        this();
        rentals.add(rental);
        rental.setOrderDate(this.orderDate);
        this.member = member;
        member.setCurrentRentalOrder(this);
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }

    @Override
    public Set<String> getRentalsId() {
        return new HashSet<>(rentalsId);
    }

    @Override
    public void setRentalsId(List<String> rentalsId) {
        this.rentalsId = new HashSet<>(rentalsId);
    }

    @Override
    public String getMemberId() {
        return memberId;
    }

    @Override
    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    @Override
    public boolean isPayed() {
        return payed;
    }

    @Override
    public void setPayed(boolean payed) {
        this.payed = payed;
    }

    @Override
    public LocalDateTime getOrderDate() {
        return this.orderDate;
    }

    @Override
    public boolean addRental(Rental rental) {
        this.payment.addRentalCost(rental.getPrice());
        rentalsId.add(rental.getId());
        rental.setOrderDate(this.orderDate);
        return rentals.add(rental);
    }

    @Override
    public Set<Rental> getRentals() {
        return new HashSet<>(this.rentals);
    }

    @Override
    public void setRentals(Set<Rental> rentals) {
        this.rentals = new HashSet<>(rentals);
    }

    @Override
    public Member getMember() {
        return this.member;
    }

    @Override
    public void setMember(Member member) {
        this.member = member;
    }

    @Override
    public Payment getPayment() {
        return this.payment;
    }

    @Override
    public void pay() {
        this.setPayed(true);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RentalOrderImpl that = (RentalOrderImpl) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("\nRental Order {");
        sb.append(rentals);
        sb.append(", \norder date=").append(orderDate);
        sb.append(", \n").append(payment);
        sb.append(", \npaid=").append(payed);
        sb.append("\n}");
        return sb.toString();
    }
}
