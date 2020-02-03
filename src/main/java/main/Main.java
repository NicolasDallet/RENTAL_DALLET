package main;

import exceptions.InvalidRentalException;
import impl.*;
import modele.*;

import java.util.Arrays;

public final class Main {

    public static void main(String[] args) {
        /* Création de videos */
        Video parrain = new VideoImpl("Le Parrain", "Crime | Drama", 175, 1972,
                Arrays.asList("Marlon Brando", "Al Pacino", "James Caan"), "Francis Ford Coppola");
        parrain.setRating(4.6d);
        Rate leParrainRate = new RateImpl(4.5d, 72);

        Video bbt = new VideoImpl("Le bon, la brute et le truand", "Western", 186, 1966,
                Arrays.asList("Clint Eastwood", "Eli Wallach", "Lee Van Cleef"), "Sergio Leone");
        bbt.setRating(4.4d);
        Rate bbtRate = new RateImpl(4d, 96);

        /* Création d'abonné */
        Member cecilia = new MemberImpl("Cécilia Leclercq", "cleclercq@gmail.com",
                "17 rue de la Ferrière", "2341983266440021" );
        /* Location */
        try {
            Rental rentalParrain =  new RentalImpl(parrain, leParrainRate);
            Rental rentalBbt =  new RentalImpl(bbt, bbtRate);
            RentalOrder ro = new RentalOrderImpl();
            ro.addRental(rentalParrain);
            ro.addRental(rentalBbt);
            cecilia.setCurrentRentalOrder(ro);
            ro.pay();
        } catch (InvalidRentalException e) {
            e.printStackTrace();
        }

        cecilia.updateRentalHistory();
        System.out.println(cecilia);
    }
}
