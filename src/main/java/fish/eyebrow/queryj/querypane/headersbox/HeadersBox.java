package fish.eyebrow.queryj.querypane.headersbox;

import fish.eyebrow.queryj.util.FXMLLoaderUtil;
import javafx.beans.property.SimpleMapProperty;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TitledPane;

public class HeadersBox extends TitledPane {
    private final HeadersBoxController controller;

    public HeadersBox() {
        FXMLLoader fxmlLoader = FXMLLoaderUtil.loadFromResource(this, getClass().getResource("HeadersBox.fxml"));
        controller = fxmlLoader.getController();
    }

    public void putHeader(String key, String value) {
        controller.putHeader(key, value);
    }

    public void removeHeader(String key) {
        controller.removeHeader(key);
    }

    public SimpleMapProperty<String, String> headersProperty() {
        return controller.headersProperty();
    }
}
