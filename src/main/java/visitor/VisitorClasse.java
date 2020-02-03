package visitor;

import modele.Member;

public class VisitorClasse implements IVisitor {
    @Override
    public void visit(Member m) {
        MemberVisitor MV = new MemberPrintVisitor();
        MV.visit(m);
        MV.visit(m.getCurrentRentalOrder());
        MV.visit(m.getRentalHistory());
    }
}
