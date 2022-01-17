package fish.eyebrow.queryj.querypane;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

public class OutputPaneController {
    @FXML
    private Label responseStatusLabel;

    @FXML
    private TextArea outputArea;

    @FXML
    private void clear() {
        outputArea.clear();
        responseStatusLabel.setText("");
    }

    public TextArea getOutputArea() {
        return outputArea;
    }

    public Label getResponseStatusLabel() {
        return responseStatusLabel;
    }
}
