package fish.eyebrow.queryj.querypane.headersbox;

import javafx.fxml.FXML;
import javafx.scene.layout.VBox;

import java.util.List;

public class HeadersBoxController {
    @FXML
    private VBox headersContent;

    @FXML
    private void initialize() {
        HeaderItem headerItem = new HeaderItem();
        headerItem.getRemoveButton().setDisable(true); // That would be bad.
        headersContent.getChildren().add(headerItem);
    }

    public List<HeaderItem> getHeaderItems() {
        return headersContent.getChildren()
                .stream()
                .filter(c -> c instanceof HeaderItem)
                .map(c -> (HeaderItem) c)
                .toList();
    }

    public VBox getHeadersContent() {
        return headersContent;
    }
}
