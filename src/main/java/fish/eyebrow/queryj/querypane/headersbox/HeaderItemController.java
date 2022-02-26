package fish.eyebrow.queryj.querypane.headersbox;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

import java.util.Objects;

public class HeaderItemController {
    @FXML
    public HBox headerItem;
    @FXML
    private TextField keyField;
    @FXML
    private TextField valueField;
    @FXML
    private Button removeButton;
    private HeadersBox headersBox;

    private String updatedKey = null;

    @FXML
    private void initialize() {
        keyField.focusedProperty().addListener((__, prev, current) -> {
            if (!prev && current) {
                // When focusing the keyField, persist the value.
                updatedKey = keyField.getText();
                return;
            }
            // When blurring the keyField, remove the old value, and put the new one.
            if (!Objects.equals(updatedKey, keyField.getText())) {
                headersBox.putHeader(keyField.getText(), valueField.getText());
                headersBox.removeHeader(updatedKey);
            }
            updatedKey = null;
        });
        valueField.focusedProperty().addListener((__, ___, focused) -> {
            // Put the header with the new value on blur.
            if (focused) return;
            headersBox.putHeader(keyField.getText(), valueField.getText());
        });
    }

    @FXML
    private void createNewHeader() {
        headersBox.putHeader("", "");
    }

    @FXML
    private void removeHeader() {
        headersBox.removeHeader(keyField.getText());
    }

    public TextField getKeyField() {
        return keyField;
    }

    public TextField getValueField() {
        return valueField;
    }

    public Button getRemoveButton() {
        return removeButton;
    }

    public void setHeadersBox(HeadersBox headersBox) {
        this.headersBox = headersBox;
    }
}
