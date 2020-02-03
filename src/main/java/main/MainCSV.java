package main;

import modele.Member;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import traitements.RentalSVDataImporter;
import visitor.VisitorClasse;

import java.io.FileNotFoundException;
import java.util.List;

public final class MainCSV {

    private static final Logger LOGGER = LoggerFactory.getLogger(MainCSV.class);

    public static void main(String[] args) throws FileNotFoundException {
        RentalSVDataImporter importer = new RentalSVDataImporter("data");
        importer.loadImportAllData();

        System.out.println("VISITEUR \n");
        List<Member> m = importer.getMembers();

        VisitorClasse VC = new VisitorClasse();
        for (int i = 0; i<m.size();i++){
            VC.visit(m.get(i));
        }

    }

}
