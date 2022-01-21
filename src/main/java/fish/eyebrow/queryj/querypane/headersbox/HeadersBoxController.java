package fish.eyebrow.queryj.querypane.headersbox;

import javafx.fxml.FXML;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class HeadersBoxController {
    @FXML
    private VBox headersContent;

    @FXML
    private void initialize() {
        headersContent.getChildren().add(new HeaderItem());
    }

    public ArrayList<HeaderItem> getHeaderItems() {
        return (ArrayList<HeaderItem>) headersContent.getChildren()
                .stream()
                .filter(c -> c instanceof HeaderItem)
                .map(c -> (HeaderItem) c)
                .collect(Collectors.toList());
    }
}
