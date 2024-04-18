package TestAPI;

import org.junit.Test;


public class TrelloTestSenarios extends BaseTrelloBoard {

    @Test
    public void trelloTest() {
        trelloBoardTransactions.createBoard("My_New_Panos");

        trelloBoardTransactions.createLists(new String[]{"New_Listem1", "New_Listem2"});

        trelloBoardTransactions.createCards(new String[]{"New_Card1", "New_Card2"});

        trelloBoardTransactions.randomUpdateCardName("Random_Updated_Card");

        trelloBoardTransactions.deleteCard();

        trelloBoardTransactions.deleteBoard();
    }

}