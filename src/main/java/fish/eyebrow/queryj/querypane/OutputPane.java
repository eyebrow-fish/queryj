package fish.eyebrow.queryj.querypane;

import fish.eyebrow.queryj.util.FXMLLoaderUtil;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.VBox;

public class OutputPane extends VBox {
    private final OutputPaneController controller;

    public OutputPane() {
        FXMLLoader fxmlLoader = FXMLLoaderUtil.loadFromResource(this, getClass().getResource("OutputPane.fxml"));

        controller = fxmlLoader.getController();
    }

    public void printLine(String s) {
        String currentText = controller.getOutputArea().getText();
        controller.getOutputArea().setText(currentText + s + "\n");
    }

    public void setResponseStatusText(String s) {
        controller.getResponseStatusLabel().setText(s);
    }
}
