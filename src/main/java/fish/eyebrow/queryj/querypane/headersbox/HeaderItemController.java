package fish.eyebrow.queryj.querypane.headersbox;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class HeaderItemController {
    @FXML
    private TextField keyField;
    @FXML
    private TextField valueField;

    public TextField getKeyField() {
        return keyField;
    }

    public TextField getValueField() {
        return valueField;
    }
}
