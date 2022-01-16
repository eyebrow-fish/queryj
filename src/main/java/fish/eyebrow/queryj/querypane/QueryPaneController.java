package fish.eyebrow.queryj.querypane;

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

    @FXML
    private void initialize() {
        methodSelect.getItems().addAll("PUT", "GET", "DELETE");
    }

    @FXML
    private void sendRequest() {
        System.out.printf("%s %s - %s\n", methodSelect.getValue(), urlField.getText(), bodyArea.getText());
    }

    public ComboBox<String> getMethodSelect() {
        return methodSelect;
    }

    public void setMethodSelect(ComboBox<String> methodSelect) {
        this.methodSelect = methodSelect;
    }

    public TextField getUrlField() {
        return urlField;
    }

    public void setUrlField(TextField urlField) {
        this.urlField = urlField;
    }

    public Button getSendButton() {
        return sendButton;
    }

    public void setSendButton(Button sendButton) {
        this.sendButton = sendButton;
    }

    public TextArea getBodyArea() {
        return bodyArea;
    }

    public void setBodyArea(TextArea bodyArea) {
        this.bodyArea = bodyArea;
    }
}
