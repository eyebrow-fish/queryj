package fish.eyebrow.queryj.persist.item;

import fish.eyebrow.queryj.persist.Persistence;
import fish.eyebrow.queryj.querytree.QueryTree;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TreeItem;

import java.util.Optional;

public class ItemPersistenceWriterFactory {
    private ItemPersistenceWriterFactory() {
    }

    public static ItemPersistenceWriter<QueryTreeItem> queryTree(QueryTree queryTree) {
        return new ItemPersistenceWriter<>(new QueryTreeItemWriter(), Persistence.SAVED_PATH,
                () -> Optional.ofNullable(queryTree.getRoot()).map(TreeItem::getValue)
        );
    }

    public static ItemPersistenceWriter<StateItem> stateItemBuilder(TabPane queryTabPane) {
        return new ItemPersistenceWriter<>(new StateItemWriter(), Persistence.STATE_PATH,
                () -> Optional.of(new StateItem(
                        queryTabPane.getTabs().stream().map(Tab::getText).toList(),
                        queryTabPane.getSelectionModel().getSelectedIndex()
                ))
        );
    }
}
