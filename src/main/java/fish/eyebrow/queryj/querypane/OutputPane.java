package fish.eyebrow.queryj.querypane;

import fish.eyebrow.queryj.util.FXMLLoaderUtil;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;

public class OutputPane extends VBox {
    @FXML
    private Label responseStatusLabel;
    @FXML
    private TextArea outputArea;

    private final ObjectProperty<QueryResponse> queryResponse;

    public OutputPane() {
        queryResponse = new SimpleObjectProperty<>(null);

        FXMLLoaderUtil.loadFromResource(this);
    }

    @FXML
    private void initialize() {
        queryResponse.addListener((__, ___, queryResponse) -> {
            if (queryResponse.isError()) {
                outputArea.setStyle("-fx-text-fill: #d00");
                responseStatusLabel.setStyle("-fx-text-fill: #d00");
            } else {
                outputArea.setStyle("");
                responseStatusLabel.setStyle("");
            }
            responseStatusLabel.setText(queryResponse.statusText());
            outputArea.setText(queryResponse.body());
        });
    }

    @FXML
    private void clear() {
        outputArea.clear();
        responseStatusLabel.setText("");
        responseStatusLabel.textProperty();
    }

    public ObjectProperty<QueryResponse> queryResponseProperty() {
        return queryResponse;
    }
}
