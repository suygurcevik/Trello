package APIsTrello;

import io.restassured.RestAssured;
import Utilities.Logger;
import org.json.simple.JSONObject;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.http.ContentType;
import java.util.Map;

public class TrelloBoardTransactions {


    private final String apiKey;
    private final String token;
    private String boardId;
    private String listId;
    private String firstCardId;
    private String secondCardId;

    public TrelloBoardTransactions(String apiKey, String token) {
        this.apiKey = apiKey;
        this.token = token;
        this.boardId = null;
        this.listId = null;
        this.firstCardId = null;
        this.secondCardId = null;
    }
    private Response PostRequest(String uri, Map<String, ?> params) {
        return RestAssured.
                given().
                when().
                baseUri(uri).
                queryParams(params).
                contentType(ContentType.JSON).
                post();
    }

    private Response PutRequest(String uri, Map<String, ?> params, String body) {
        return RestAssured.
                given().
                when().
                baseUri(uri).
                queryParams(params).
                header("Content-Type", "application/json").body(body).
                put();
    }

    private Response DeleteRequest(String uri, Map<String, ?> params) {
        return RestAssured.
                given().
                when().
                baseUri(uri).
                queryParams(params).
                header("Content-Type", "application/json").
                delete();
    }


    public void createCard(String cardName) {
        JSONObject expectedBody = new JSONObject();
        expectedBody.put("name", cardName);

        Response response1 = PostRequest("https://api.trello.com/1/cards",
                Map.of("name", cardName, "idList", listId, "key", apiKey, "token", token));

        handleResponse("Cart has been created!", response1, expectedBody);

        if (firstCardId == null) {
            firstCardId = response1.jsonPath().getString("id");

            Logger.info("First Card ID: " + firstCardId);
        } else {
            secondCardId = response1.jsonPath().getString("id");

            Logger.info("Second Card ID: " + secondCardId);
        }

    }

    public void createCards(String[] cardNames) {
        for (String cardName : cardNames) {
            createCard(cardName);
        }
    }


    public void createBoard(String boardName) {
        JSONObject expectedBody = new JSONObject();
        expectedBody.put("name", boardName);

        Response response = PostRequest("https://api.trello.com/1/boards/",
                Map.of("name", boardName, "key", apiKey, "token", token));

        handleResponse("Board has been created!", response, expectedBody);

        boardId = response.jsonPath().getString("id");
        Logger.info("Board ID: " + boardId);
    }


    public void randomUpdateCardName(String randomUpdateCardName) {
        JSONObject expectedBody = new JSONObject();
        expectedBody.put("name", randomUpdateCardName);

        Response response = PutRequest("https://api.trello.com/1/cards/" + firstCardId,
                Map.of("key", apiKey, "token", token),
                "{\"name\":\"" + randomUpdateCardName+ "\"}");

        handleResponse("Card updated!", response, expectedBody);
    }


    public void createList(String listName) {
        JSONObject expectedBody = new JSONObject();
        expectedBody.put("name", listName);

        Response response1 = PostRequest("https://api.trello.com/1/lists",
                Map.of("name", listName, "idBoard", boardId, "key", apiKey, "token", token));

        handleResponse("List has been created!", response1, expectedBody);

        listId = response1.jsonPath().getString("id");
        Logger.info("List ID: " + listId);
    }
    public void createLists(String[] listNames) {
        for (String listName : listNames) {
            createList(listName);
        }
    }
    public void deleteCard() {
        JSONObject expectedBody = new JSONObject();
        expectedBody.put("name", null);

        Response response1 = DeleteRequest("https://api.trello.com/1/cards/" + firstCardId,
                Map.of("key", apiKey, "token", token));
        Response response2 = DeleteRequest("https://api.trello.com/1/cards/" + secondCardId,
                Map.of("key", apiKey, "token", token));

        handleResponse("First Card deleted!", response1, expectedBody);
        handleResponse("Second Card deleted!", response2, expectedBody);
    }

    public void deleteBoard() {
        JSONObject expectedBody = new JSONObject();
        expectedBody.put("name", null);

        Response response = DeleteRequest("https://api.trello.com/1/boards/" + boardId,
                Map.of("key", apiKey, "token", token));

        handleResponse("Board deleted!", response, expectedBody);
    }


    private void handleResponse(String successMessage, Response response, JSONObject expected) {
        int statusCode = response.getStatusCode();
        if (statusCode != 200) {
            Logger.assertWithLogging("Error! HTTP Status Code: " + statusCode);
        }
        checkResponseBody(response, expected);
        Logger.info(successMessage);
    }

    private void checkResponseBody(Response responses, JSONObject jsonObject) {
        JsonPath actualBody = responses.jsonPath();
        Logger.assertEquals(jsonObject.get("name"), actualBody.get("name"));
    }
}
