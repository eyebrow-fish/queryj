package fish.eyebrow.queryj.querypane;

import fish.eyebrow.queryj.util.FXMLLoaderUtil;
import javafx.beans.property.ObjectProperty;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.VBox;

public class OutputPane extends VBox {
    private final OutputPaneController controller;

    public OutputPane() {
        FXMLLoader fxmlLoader = FXMLLoaderUtil.loadFromResource(this, getClass().getResource("OutputPane.fxml"));

        controller = fxmlLoader.getController();
    }

    public ObjectProperty<QueryResponse> queryResponseProperty() {
        return controller.queryResponseProperty();
    }
}
