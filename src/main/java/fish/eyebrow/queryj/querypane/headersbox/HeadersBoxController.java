package fish.eyebrow.queryj.querypane.headersbox;

import javafx.beans.property.SimpleMapProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableMap;
import javafx.fxml.FXML;
import javafx.scene.layout.VBox;

public class HeadersBoxController {
    @FXML
    private HeadersBox headersBox;
    @FXML
    private VBox headersContent;
    private SimpleMapProperty<String, String> headers;

    @FXML
    private void initialize() {
        headers = new SimpleMapProperty<>(FXCollections.observableHashMap());
        headers.addListener((__, prev, current) -> {
            headersContent.getChildren().clear();
            current.entrySet()
                    .stream()
                    .map(header -> {
                        HeaderItem headerItem = new HeaderItem();
                        headerItem.setHeadersBox(headersBox);
                        headerItem.getKeyField().setText(header.getKey());
                        headerItem.getValueField().setText(header.getValue());
                        return headerItem;
                    })
                    .forEach(h -> headersContent.getChildren().add(h));
        });
    }

    public void putHeader(String key, String value) {
        ObservableMap<String, String> currentHeaders = headersBox.headersProperty().get();
        currentHeaders.put(key, value);
        headersBox.headersProperty().set(FXCollections.observableMap(currentHeaders));
    }

    public void removeHeader(String key) {
        ObservableMap<String, String> currentHeaders = headersBox.headersProperty().get();
        currentHeaders.remove(key);
        headersBox.headersProperty().set(FXCollections.observableMap(currentHeaders));
    }

    public SimpleMapProperty<String, String> headersProperty() {
        return headers;
    }
}
