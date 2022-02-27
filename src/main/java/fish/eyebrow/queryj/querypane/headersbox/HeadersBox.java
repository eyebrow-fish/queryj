package fish.eyebrow.queryj.querypane.headersbox;

import fish.eyebrow.queryj.util.FXMLLoaderUtil;
import javafx.beans.property.SimpleMapProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableMap;
import javafx.fxml.FXML;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.VBox;

import java.util.Map;

public class HeadersBox extends TitledPane {
    @FXML
    private VBox headersContent;

    private final SimpleMapProperty<String, String> headers;

    public HeadersBox() {
        headers = new SimpleMapProperty<>(FXCollections.observableHashMap());

        FXMLLoaderUtil.loadFromResource(this);
    }

    @FXML
    private void initialize() {
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
                            HeaderItem i = new HeaderItem(this);
                            headersContent.getChildren().add(i);
                            return i;
                        });

                headerItem.getKeyField().setText(entry.getKey());
                headerItem.getValueField().setText(entry.getValue());
                headerItem.getRemoveButton().setDisable(headers.size() == 1);
            }
        });
    }

    public void putHeader(String key, String value) {
        ObservableMap<String, String> currentHeaders = headers.get();
        currentHeaders.put(key, value);
        headers.set(FXCollections.observableMap(currentHeaders));
    }

    public void removeHeader(String key) {
        ObservableMap<String, String> currentHeaders = headers.get();
        currentHeaders.remove(key);
        headers.set(FXCollections.observableMap(currentHeaders));
    }

    public SimpleMapProperty<String, String> headersProperty() {
        return headers;
    }
}
