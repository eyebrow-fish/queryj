package fish.eyebrow.queryj.querypane;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class QueryPane extends VBox {
    private final QueryPaneController controller;

    public QueryPane() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("QueryPane.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.load();

        controller = fxmlLoader.getController();
    }

    public String getMethod() {
        return controller.getMethodSelect().getValue();
    }

    public void setMethod(String method) {
        controller.getMethodSelect().setValue(method);
    }

    public String getUrl() {
        return controller.getUrlField().getText();
    }

    public void setURL(String url) {
        controller.getUrlField().setText(url);
    }

    public String getBody() {
        return controller.getBodyArea().getText();
    }

    public void setBody(String body) {
        controller.getBodyArea().setText(body);
    }
}
