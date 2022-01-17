package fish.eyebrow.queryj.querypane;

import fish.eyebrow.queryj.querytree.QueryTreeItem;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import kong.unirest.HttpRequestWithBody;
import kong.unirest.HttpResponse;
import kong.unirest.Unirest;

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
        methodSelect.valueProperty().addListener((__, ___, s) -> query.setMethod(s));
        urlField.textProperty().addListener((__, ___, s) -> query.setUrl(s));
        bodyArea.textProperty().addListener((__, ___, s) -> query.setBody(s));
    }

    @FXML
    private void sendRequest() {
        HttpRequestWithBody request = Unirest.request(query.getMethod(), query.getUrl());

        CompletableFuture<HttpResponse<String>> responseFuture;
        if (query.getMethod().equals("PUT") || query.getMethod().equals("POST")) {
            responseFuture = request.body(query.getBody()).asStringAsync();
        } else {
            responseFuture = request.asStringAsync();
        }

        responseFuture.thenAccept(response -> {
            outputPane.setResponseStatusText(response.getStatusText());
            outputPane.printLine(response.getBody());
        });
    }

    public void setQuery(QueryTreeItem.Query query) {
        this.query = query;
        methodSelect.setValue(query.getMethod());
        urlField.setText(query.getUrl());
        bodyArea.setText(query.getBody());
    }
}
