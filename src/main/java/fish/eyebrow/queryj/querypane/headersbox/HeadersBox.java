package fish.eyebrow.queryj.querypane.headersbox;

import fish.eyebrow.queryj.util.FXMLLoaderUtil;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TitledPane;

import java.util.List;

public class HeadersBox extends TitledPane {
    private final HeadersBoxController controller;

    public HeadersBox() {
        FXMLLoader fxmlLoader = FXMLLoaderUtil.loadFromResource(this, getClass().getResource("HeadersBox.fxml"));
        controller = fxmlLoader.getController();
    }

    public List<HeaderItem> getHeaderItems() {
        return controller.getHeaderItems();
    }
}
