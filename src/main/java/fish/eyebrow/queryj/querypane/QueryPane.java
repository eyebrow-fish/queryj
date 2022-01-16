package fish.eyebrow.queryj.querypane;

import fish.eyebrow.queryj.querytree.QueryTreeItem;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class QueryPane extends VBox {

    public QueryPane(QueryTreeItem.Query query) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("QueryPane.fxml"));
        fxmlLoader.setRoot(this);
        try {
            fxmlLoader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        QueryPaneController controller = fxmlLoader.getController();
        controller.setQuery(query);
    }
}
