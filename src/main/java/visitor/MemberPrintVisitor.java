package visitor;

import modele.Member;
import modele.RentalHistory;
import modele.RentalOrder;

public class MemberPrintVisitor implements MemberVisitor{

    @Override
    public void visit(Member m) {
        System.out.println("Visiting "
                + m.toString()
                + " Member");
    }

    @Override
    public void visit(RentalOrder RO) {
        System.out.println("Visiting "
                + RO.toString()
                + "Rental Order");
    }

    @Override
    public void visit(RentalHistory RH) {
        System.out.println("Visiting "
                + RH.toString()
                + "Rental Order");
    }
}
