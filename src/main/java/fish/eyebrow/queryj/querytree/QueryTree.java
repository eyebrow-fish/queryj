package fish.eyebrow.queryj.querytree;

import fish.eyebrow.queryj.querypane.QueryPane;
import javafx.fxml.FXMLLoader;
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

    public void setQueryPane(QueryPane queryPane) {
        controller.setQueryPane(queryPane);
    }
}
