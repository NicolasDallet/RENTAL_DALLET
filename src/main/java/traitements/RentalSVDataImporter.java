package traitements;

import exceptions.InvalidRentalException;
import impl.*;
import io.CSVLoader;
import modele.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This class loads and import all data from RentalSV CSV files.
 * It must complete the imports, in particular for {@link impl.VideoRateImpl}
 */
public final class RentalSVDataImporter {
    private static final Logger LOGGER = LoggerFactory.getLogger(RentalSVDataImporter.class);
    public static final String MEMBERS_CSV = "members.csv";
    public static final String VIDEOS_CSV = "videos.csv";
    public static final String RATES_CSV = "rates.csv";
    public static final String VIDEORATES_CSV = "videorates.csv";
    public static final String RENTALS_CSV = "rentals.csv";
    public static final String RENTALORDERS_CSV = "rentalorders.csv";
    public static final String RENTAL_HISTORIES_CSV = "rentalhistories.csv";
    private final String dataPath;
    private List<Member> members;
    private Map<String, Member> membersMap;
    private List<Video> videos;
    private Map<String, Video> videosMap;
    private List<Rate> rates;
    private Map<String, Rate> ratesMap;
    private List<VideoRate> videoRates;
    private List<Rental> rentals;
    private Map<String, Rental> rentalsMap;
    private List<RentalOrderImpl> rentalOrders;
    private List<RentalOrderImpl> rentalHistories;

    public RentalSVDataImporter(String dataPath){
        this.dataPath = dataPath;
    }

    public void loadImportAllData() throws FileNotFoundException {
        CSVLoader loader = new CSVLoader(this.dataPath);
        // Load Members
        members = loader.loadRentalSVData(MEMBERS_CSV, MemberImpl.class);
        LOGGER.info("Loaded members from CSV:\n{}", members.toString());
        membersMap = new HashMap<>();
        members.forEach(m -> membersMap.put(m.getId(), m));

        // Load Videos
        videos = loader.loadRentalSVData(VIDEOS_CSV, VideoImpl.class);
        LOGGER.info("Loaded videos from CSV:\n{}", videos.toString());
        videosMap = new HashMap<>();
        videos.forEach(v -> videosMap.put(v.getId(), v));

        // Load Rates
        rates = loader.loadRentalSVData(RATES_CSV, RateImpl.class);
        LOGGER.info("Loaded rates from CSV:\n{}", rates.toString());
        ratesMap = new HashMap<>();
        rates.forEach(r -> ratesMap.put(r.getId(), r));

        // Load video rates
        videoRates = loader.loadRentalSVData(VIDEORATES_CSV, VideoRateImpl.class);
        videoRates.forEach(vr -> {
            vr.setVideo(videosMap.get(vr.getVideoId()));
            vr.setRate(ratesMap.get(vr.getRateId()));
        });
        LOGGER.info("Loaded video rates from CSV:\n{}", videoRates.toString());

        // Load Rentals
        rentals = loader.loadRentalSVData(RENTALS_CSV, RentalImpl.class);
        rentalsMap = new HashMap<>();
        rentals.forEach(r -> {
            rentalsMap.put(r.getId(), r);
            r.setVideo(videosMap.get(r.getVideoId()));
            try {
                r.setRate(ratesMap.get(r.getRateId()));
            } catch (InvalidRentalException e) {
                LOGGER.error("Error while loading rentals: {}", e.getMessage(), e);
            }
        });
        LOGGER.info("Loaded rentals from CSV:\n{}", rentals.toString());

        // Load RentalOrders
        rentalOrders = loader.loadRentalSVData(RENTALORDERS_CSV, RentalOrderImpl.class);
        rentalOrders.forEach(ro -> {
            Member member = membersMap.get(ro.getMemberId());
            ro.setMember(member);
            member.setCurrentRentalOrder(ro);
            ro.getRentalsId().forEach(id -> ro.addRental(rentalsMap.get(id)));
        });
        LOGGER.info("Loaded rentals orders from CSV:\n{}", rentalOrders.toString());

        // Load Rental histories
        rentalHistories = loader.loadRentalSVData(RENTAL_HISTORIES_CSV, RentalOrderImpl.class);
        rentalHistories.forEach(roh -> {
            Member member = membersMap.get(roh.getMemberId());
            roh.setMember(member);
            member.addToRentalHistory(roh);
            roh.getRentalsId().forEach(id -> roh.addRental(rentalsMap.get(id)));
        });
        LOGGER.info("Loaded rentals histories from CSV:\n{}", rentalHistories.toString());

        LOGGER.info("Rentals histories from members:");
        membersMap.forEach((k,v)->{
            LOGGER.info("Rental history of member {}: {}", v.getName(), v.getRentalHistory());
        });
    }

    public List<Video> getVideos() {
        return videos;
    }

    public List<VideoRate> getVideoRates() {
        return videoRates;
    }

    public List<RentalOrderImpl> getRentalOrders() {
        return rentalOrders;
    }

    public List<RentalOrderImpl> getRentalHistories() {
        return rentalHistories;
    }

    public List<Rental> getRentals() {
        return rentals;
    }

    public List<Member> getMembers() {
        return members;
    }

    public List<Rate> getRates() {
        return rates;
    }
}
