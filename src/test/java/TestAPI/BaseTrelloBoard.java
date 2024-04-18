package TestAPI;


import APIsTrello.TrelloBoardTransactions;
import Utilities.Logger;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import io.restassured.RestAssured;


import java.text.SimpleDateFormat;
import java.util.Date;

public class BaseTrelloBoard {

    public static final String API_KEY = "209d7b616df0db700b84e30764f77477";
    public static final String TOKEN = "ATTAfe49669ea9f41b244eee11ec10d45f6257e456bdbc9a50e96ac52485ae0defb9499959CE";
    public static TrelloBoardTransactions trelloBoardTransactions;

    static SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss:SSS");

    @BeforeClass
    public static void setUp() {
        RestAssured.baseURI = "https://api.trello.com/1";
        trelloBoardTransactions = new TrelloBoardTransactions(API_KEY, TOKEN);

        Date start = new Date();
        String startTime = dateFormat.format(start);

        Logger.info("Test Başladı" + startTime);
    }

    @AfterClass
    public static void tearDown() {
        RestAssured.reset();

        Date finish = new Date();
        String finishTime = dateFormat.format(finish);
        Logger.info("Test Bitti" + finishTime);
    }

}