package fish.eyebrow.queryj.querypane;

import fish.eyebrow.queryj.persist.item.QueryTreeItem;
import fish.eyebrow.queryj.querypane.headersbox.HeadersBox;
import fish.eyebrow.queryj.util.FXMLLoaderUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableMap;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import kong.unirest.HttpRequestWithBody;
import kong.unirest.HttpResponse;
import kong.unirest.Unirest;

import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.CompletableFuture;

public class QueryPane extends VBox {
    @FXML
    private ComboBox<String> methodSelect;
    @FXML
    private TextField urlField;
    @FXML
    public Button sendButton;
    @FXML
    private TextArea bodyArea;
    @FXML
    private HeadersBox headersBox;

    private final QueryTreeItem.Query query;
    private final OutputPane outputPane;

    public QueryPane(QueryTreeItem.Query query, OutputPane outputPane) {
        FXMLLoaderUtil.loadFromResource(this);

        this.query = query;
        this.outputPane = outputPane;
        String method = query.getMethod();

        methodSelect.setValue(method);
        urlField.setText(query.getUrl());
        bodyArea.setText(query.getBody());
        bodyArea.setDisable(!method.equals("PUT") && !method.equals("POST"));

        if (query.getHeaders().isEmpty()) {
            headersBox.putHeader("", "");
        } else {
            ObservableMap<String, String> headers = FXCollections.observableMap(query.getHeaders());
            headersBox.headersProperty().set(headers);
        }
    }

    @FXML
    private void initialize() {
        methodSelect.getItems().addAll("PUT", "POST", "GET", "DELETE", "OPTIONS");
        methodSelect.valueProperty().addListener((__, ___, s) -> {
            query.setMethod(s);
            bodyArea.setDisable(!s.equals("PUT") && !s.equals("POST"));
        });
        urlField.textProperty().addListener((__, ___, s) -> query.setUrl(s));
        bodyArea.textProperty().addListener((__, ___, s) -> query.setBody(s));
        headersBox.headersProperty().addListener((__, ___, h) -> query.setHeaders(h));
    }

    @FXML
    private void sendRequest() {
        sendButton.setDisable(true);

        Instant startTime = Instant.now();
        HttpRequestWithBody request = Unirest.request(query.getMethod(), query.getUrl());

        request.headers(headersBox.headersProperty());

        CompletableFuture<HttpResponse<String>> responseFuture;
        if (query.getMethod().equals("PUT") || query.getMethod().equals("POST")) {
            responseFuture = request.body(query.getBody()).asStringAsync();
        } else {
            responseFuture = request.asStringAsync();
        }

        responseFuture.thenCompose(response -> {
            Task<QueryResponse> task = new Task<>() {
                @Override
                protected QueryResponse call() {
                    QueryResponse queryResponse;
                    try {
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
                    return queryResponse;
                }
            };

            task.setOnSucceeded(event -> {
                outputPane.queryResponseProperty().setValue((QueryResponse) event.getSource().getValue());
                sendButton.setDisable(false);
            });

            return CompletableFuture.runAsync(task);
        });
    }

    @FXML
    private void bodyAreaKeyPressed(KeyEvent event) {
        if (event.isControlDown() && event.getCode() == KeyCode.ENTER) {
            sendRequest();
        }
    }
}
