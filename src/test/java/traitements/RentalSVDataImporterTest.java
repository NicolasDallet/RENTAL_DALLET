package traitements;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;
import traitements.RentalSVDataImporter;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RentalSVDataImporterTest {²

    private RentalSVDataImporter RSI;

    @BeforeAll
    static void initAll() {

    }

    @BeforeEach
    void init() throws FileNotFoundException {
        RSI = new RentalSVDataImporter("data");
        RSI.loadImportAllData();
    }

    @AfterEach
    void tearDown() {
        System.out.println("afterEach \n");
    }

    @AfterAll
    static void tearDownAll() {
        System.out.println("afterAll \n");
    }

    /**le test doit passer */
    @Test
    public void testSameMembers(){
        RentalSVDataImporter RSTest = new RentalSVDataImporter("datatest");
        ArrayList<String> idMember = new ArrayList<String>();
        ArrayList<String> idMemberTest =new ArrayList<String>();
        for (int i=0; i < RSI.getMembers().size(); i++){
            idMember.add(RSI.getMembers().get(i).getId());
            idMemberTest.add(RSTest.getMembers().get(i).getId());
            Assertions.assertLinesMatch(idMember,idMemberTest);
        }
    }

    /**le test doit échouer */
    @Test
    public void testSameMembersIdERROR(){
        RentalSVDataImporter RSTest = new RentalSVDataImporter("datatestERROR");
        ArrayList<String> idMember =new ArrayList<String>();
        ArrayList<String> idMemberTest =new ArrayList<String>();
        for (int i=0; i < RSI.getMembers().size(); i++){
            idMember.add(RSI.getMembers().get(i).getId());
            idMemberTest.add(RSTest.getMembers().get(i).getId());
            Assertions.assertLinesMatch(idMember,idMemberTest);
        }
    }

    /**le test doit passer */
    @Test
    public void testSameVideo(){
        RentalSVDataImporter RSTest = new RentalSVDataImporter("datatest");
        ArrayList<String> idVideo = new ArrayList<String>();
        ArrayList<String> idVisitorTest = new ArrayList<String>();
        for (int i=0; i < RSI.getVideos().size(); i++){
            idVideo.add(RSI.getVideos().get(i).getId());
            idVisitorTest.add(RSTest.getMembers().get(i).getId());
            Assertions.assertLinesMatch(idVideo,idVisitorTest);
        }
    }

}