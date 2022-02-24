package fish.eyebrow.queryj.querytree;

import fish.eyebrow.queryj.persist.item.QueryTreeItem;
import fish.eyebrow.queryj.querypane.OutputPane;
import fish.eyebrow.queryj.util.FXMLLoaderUtil;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TabPane;
import javafx.scene.control.TreeView;

public class QueryTree extends TreeView<QueryTreeItem> {
    private final QueryTreeController controller;

    public QueryTree() {
        FXMLLoader fxmlLoader = FXMLLoaderUtil.loadFromResource(this, getClass().getResource("QueryTree.fxml"));
        controller = fxmlLoader.getController();
    }

    public void setQueryTabPane(TabPane queryTabPane) {
        controller.setQueryTabPane(queryTabPane);
    }

    public void setOutputPane(OutputPane outputPane) {
        controller.setOutputPane(outputPane);
    }
}
