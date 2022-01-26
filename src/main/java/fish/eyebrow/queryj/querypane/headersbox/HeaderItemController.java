package fish.eyebrow.queryj.querypane.headersbox;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.List;

public class HeaderItemController {
    @FXML
    public HBox headerItem;
    @FXML
    private TextField keyField;
    @FXML
    private TextField valueField;
    @FXML
    private Button removeButton;

    @FXML
    private void createNewHeader() {
        VBox headersBoxContent = (VBox) headerItem.getParent();

        HeadersBox headersBox = (HeadersBox) headersBoxContent.getParent().getParent().getParent();
        List<HeaderItem> headerItems = headersBox.getHeaderItems();

        HeaderItem lastHeaderItem = headerItems.get(headerItems.size() - 1);
        if (lastHeaderItem.getValueField().getText().isEmpty() || lastHeaderItem.getKeyField().getText().isEmpty()) {
            return;
        }

        headersBoxContent.getChildren().add(new HeaderItem());
        headerItems.forEach(h -> h.getRemoveButton().setDisable(false));
    }

    @FXML
    private void removeHeader() {
        VBox headersBoxContent = (VBox) headerItem.getParent();
        if (headersBoxContent.getChildren().size() < 2) return;

        headersBoxContent.getChildren().remove(headerItem);

        if (headersBoxContent.getChildren().size() == 1) {
            HeaderItem headerItem = (HeaderItem) headersBoxContent.getChildren().get(0);
            headerItem.getRemoveButton().setDisable(true);
        }
    }

    public TextField getKeyField() {
        return keyField;
    }

    public TextField getValueField() {
        return valueField;
    }

    public Button getRemoveButton() {
        return removeButton;
    }
}
