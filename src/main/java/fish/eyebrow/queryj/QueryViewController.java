package fish.eyebrow.queryj;

import fish.eyebrow.queryj.querypane.OutputPane;
import fish.eyebrow.queryj.querytree.QueryTree;
import javafx.fxml.FXML;
import javafx.scene.control.Control;
import javafx.scene.control.TabPane;

public class QueryViewController extends Control {
    @FXML
    private TabPane queryTabPane;
    @FXML
    private OutputPane outputPane;
    @FXML
    private QueryTree queryTree;

    @FXML
    private void initialize() {
        queryTree.setQueryTabPane(queryTabPane);
        queryTree.setOutputPane(outputPane);
    }
}
