package fish.eyebrow.queryj.querytree;

import javafx.fxml.FXMLLoader;
import javafx.scene.control.TabPane;
import javafx.scene.control.TreeView;

import java.io.IOException;

public class QueryTree extends TreeView<String> {
    private final QueryTreeController controller;

    public QueryTree() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("QueryTree.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.load();

        controller = fxmlLoader.getController();
    }

    public void setQueryTabPane(TabPane queryTabPane) {
        controller.setQueryTabPane(queryTabPane);
    }
}
