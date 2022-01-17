package fish.eyebrow.queryj.querypane;

import fish.eyebrow.queryj.querytree.QueryTreeItem;
import fish.eyebrow.queryj.util.FXMLLoaderUtil;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.SplitPane;

public class QueryPane extends SplitPane {
    public QueryPane(QueryTreeItem.Query query) {
        FXMLLoader fxmlLoader = FXMLLoaderUtil.loadFromResource(this, getClass().getResource("QueryPane.fxml"));

        QueryPaneController controller = fxmlLoader.getController();
        controller.setQuery(query);
    }
}
