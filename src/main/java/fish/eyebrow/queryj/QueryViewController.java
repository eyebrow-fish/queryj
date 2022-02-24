package fish.eyebrow.queryj;

import fish.eyebrow.queryj.persist.PersistenceLoader;
import fish.eyebrow.queryj.persist.ScheduledPersistenceWriter;
import fish.eyebrow.queryj.persist.item.QueryTreeItem;
import fish.eyebrow.queryj.querypane.OutputPane;
import fish.eyebrow.queryj.querytree.QueryTree;
import javafx.fxml.FXML;
import javafx.scene.control.Control;
import javafx.scene.control.TabPane;
import javafx.scene.control.TreeItem;

import java.io.IOException;

public class QueryViewController extends Control {
    @FXML
    private TabPane queryTabPane;
    @FXML
    private OutputPane outputPane;
    @FXML
    private QueryTree queryTree;

    @FXML
    private void initialize() throws IOException {
        queryTree.setQueryTabPane(queryTabPane);
        queryTree.setOutputPane(outputPane);

        TreeItem<QueryTreeItem> treeItem = PersistenceLoader.getInstance().loadQueryTree();
        ScheduledPersistenceWriter.getInstance().startWithSupplier(() -> queryTree.getRoot().getValue());
        queryTree.setRoot(treeItem);
    }
}
