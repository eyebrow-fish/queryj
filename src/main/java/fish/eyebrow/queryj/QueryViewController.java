package fish.eyebrow.queryj;

import fish.eyebrow.queryj.querypane.QueryPane;
import fish.eyebrow.queryj.querytree.QueryTree;
import javafx.fxml.FXML;
import javafx.scene.control.Control;

public class QueryViewController extends Control {
    @FXML
    private QueryTree queryTree;
    @FXML
    private QueryPane queryPane;

    @FXML
    private void initialize() {
        queryTree.setQueryPane(queryPane);
    }
}
