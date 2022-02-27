package fish.eyebrow.queryj;

import fish.eyebrow.queryj.persist.ScheduledPersistenceWriter;
import fish.eyebrow.queryj.persist.item.ItemPersistenceReaderFactory;
import fish.eyebrow.queryj.persist.item.ItemPersistenceWriterFactory;
import fish.eyebrow.queryj.querypane.OutputPane;
import fish.eyebrow.queryj.querytree.QueryTree;
import javafx.fxml.FXML;
import javafx.scene.control.Control;
import javafx.scene.control.TabPane;

import java.io.IOException;
import java.util.List;

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

        ItemPersistenceReaderFactory.queryTree(queryTree).read();
        ItemPersistenceReaderFactory.stateItem(queryTree, outputPane, queryTabPane).read();

        new ScheduledPersistenceWriter(List.of(
                ItemPersistenceWriterFactory.queryTree(queryTree),
                ItemPersistenceWriterFactory.stateItemBuilder(queryTabPane)
        )).start();
    }
}
