package fish.eyebrow.queryj.querypane.headersbox;

import javafx.beans.property.SimpleMapProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableMap;
import javafx.fxml.FXML;
import javafx.scene.layout.VBox;

import java.util.Map;

public class HeadersBoxController {
    @FXML
    private HeadersBox headersBox;
    @FXML
    private VBox headersContent;
    private SimpleMapProperty<String, String> headers;

    @FXML
    private void initialize() {
        headers = new SimpleMapProperty<>(FXCollections.observableHashMap());
        headers.addListener((__, ___, headers) -> {
            // Remove deleted children.
            headersContent.getChildren().removeIf(child ->
                    !(child instanceof HeaderItem headerItem) ||
                            !headers.containsKey(headerItem.getKeyField().getText())
            );

            // Update or insert new children.
            for (Map.Entry<String, String> entry : headers.entrySet()) {
                HeaderItem headerItem = headersContent.getChildren()
                        .stream()
                        .map(i -> (HeaderItem) i)
                        .filter(i -> i.getKeyField().getText().equals(entry.getKey()))
                        .findFirst()
                        .orElseGet(() -> {
                            HeaderItem i = new HeaderItem();
                            headersContent.getChildren().add(i);
                            return i;
                        });

                headerItem.setHeadersBox(headersBox);
                headerItem.getKeyField().setText(entry.getKey());
                headerItem.getValueField().setText(entry.getValue());
                headerItem.getRemoveButton().setDisable(headers.size() == 1);
            }
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
