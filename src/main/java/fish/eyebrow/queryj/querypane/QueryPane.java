package fish.eyebrow.queryj.querypane;

import fish.eyebrow.queryj.querytree.QueryTreeItem;
import fish.eyebrow.queryj.util.FXMLLoaderUtil;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.VBox;

public class QueryPane extends VBox {
    private final QueryPaneController controller;

    public QueryPane(QueryTreeItem.Query query) {
        FXMLLoader fxmlLoader = FXMLLoaderUtil.loadFromResource(this, getClass().getResource("QueryPane.fxml"));

        controller = fxmlLoader.getController();
        controller.setQuery(query);
    }

    public void setOutputPane(OutputPane outputPane) {
        controller.setOutputPane(outputPane);
    }
}
