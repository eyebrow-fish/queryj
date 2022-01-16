package fish.eyebrow.queryj.querypane;

import fish.eyebrow.queryj.querytree.QueryTreeItem;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class QueryPaneController {
    @FXML
    private ComboBox<String> methodSelect;
    @FXML
    private TextField urlField;
    @FXML
    private Button sendButton;
    @FXML
    private TextArea bodyArea;

    private QueryTreeItem.Query query;

    @FXML
    private void initialize() {
        methodSelect.getItems().addAll("PUT", "GET", "DELETE");
    }

    @FXML
    private void sendRequest() {
        System.out.printf("%s %s - %s\n", methodSelect.getValue(), urlField.getText(), bodyArea.getText());
    }

    @FXML
    private void updateQuery() {
        query.setMethod(methodSelect.getValue());
        query.setUrl(urlField.getText());
    }

    public void setQuery(QueryTreeItem.Query query) {
        this.query = query;
        methodSelect.setValue(query.getMethod());
        urlField.setText(query.getUrl());
        bodyArea.setText(query.getBody());
    }
}
