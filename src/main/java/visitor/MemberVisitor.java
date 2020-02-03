package visitor;

import modele.Member;
import modele.RentalHistory;
import modele.RentalOrder;

public interface MemberVisitor {
    void visit(Member m );
    void visit (RentalOrder RO);
    void visit (RentalHistory RH);
}
