package fish.eyebrow.queryj.querypane;

import fish.eyebrow.queryj.querytree.QueryTreeItem;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import kong.unirest.HttpRequestWithBody;
import kong.unirest.HttpResponse;
import kong.unirest.Unirest;

import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.CompletableFuture;

public class QueryPaneController {
    @FXML
    private ComboBox<String> methodSelect;
    @FXML
    private TextField urlField;
    @FXML
    private TextArea bodyArea;
    @FXML
    private OutputPane outputPane;

    private QueryTreeItem.Query query;

    @FXML
    private void initialize() {
        methodSelect.getItems().addAll("PUT", "POST", "GET", "DELETE", "OPTIONS");
        methodSelect.valueProperty().addListener((__, ___, s) -> {
            query.setMethod(s);
            bodyArea.setDisable(!s.equals("PUT") && !s.equals("POST"));
        });
        urlField.textProperty().addListener((__, ___, s) -> query.setUrl(s));
        bodyArea.textProperty().addListener((__, ___, s) -> query.setBody(s));
    }

    @FXML
    private void sendRequest() {
        Instant startTime = Instant.now();
        HttpRequestWithBody request = Unirest.request(query.getMethod(), query.getUrl());

        CompletableFuture<HttpResponse<String>> responseFuture;
        if (query.getMethod().equals("PUT") || query.getMethod().equals("POST")) {
            responseFuture = request.body(query.getBody()).asStringAsync();
        } else {
            responseFuture = request.asStringAsync();
        }

        // CompletableFutures run in a separate thread, which means JavaFX will have some issues.
        // To move around this problem, we need to use "Platform.runLater".
        Platform.runLater(() -> {
            QueryResponse queryResponse;
            try {
                HttpResponse<String> response = responseFuture.get();
                queryResponse = new QueryResponse(
                        Duration.between(startTime, Instant.now()),
                        response.getStatus() + " " + response.getStatusText(),
                        response.getBody(),
                        false
                );
            } catch (Exception e) {
                queryResponse = new QueryResponse(
                        Duration.between(startTime, Instant.now()),
                        null,
                        e.getMessage(),
                        true
                );
            }

            outputPane.getQueryResponseProperty().setValue(queryResponse);
        });
    }

    public void setQuery(QueryTreeItem.Query query) {
        this.query = query;
        String method = query.getMethod();

        methodSelect.setValue(method);
        urlField.setText(query.getUrl());
        bodyArea.setText(query.getBody());
        bodyArea.setDisable(!method.equals("PUT") && !method.equals("POST"));
    }
}
