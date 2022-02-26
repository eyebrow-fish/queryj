package fish.eyebrow.queryj.querypane.headersbox;

import fish.eyebrow.queryj.util.FXMLLoaderUtil;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

public class HeaderItem extends HBox {
    private final HeaderItemController controller;

    public HeaderItem() {
        FXMLLoader fxmlLoader = FXMLLoaderUtil.loadFromResource(this, getClass().getResource("HeaderItem.fxml"));
        controller = fxmlLoader.getController();
    }

    public TextField getKeyField() {
        return controller.getKeyField();
    }

    public TextField getValueField() {
        return controller.getValueField();
    }

    public Button getRemoveButton() {
        return controller.getRemoveButton();
    }

    public void setHeadersBox(HeadersBox headersBox) {
        controller.setHeadersBox(headersBox);
    }
}
