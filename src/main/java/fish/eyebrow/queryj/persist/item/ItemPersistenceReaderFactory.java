package fish.eyebrow.queryj.persist.item;

import fish.eyebrow.queryj.persist.Persistence;
import fish.eyebrow.queryj.persist.item.util.QueryTreeItemUtil;
import fish.eyebrow.queryj.querypane.OutputPane;
import fish.eyebrow.queryj.querypane.QueryPane;
import fish.eyebrow.queryj.querytree.QueryTree;
import fish.eyebrow.queryj.querytree.util.TreeViewUtil;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

public class ItemPersistenceReaderFactory {
    private ItemPersistenceReaderFactory() {
    }

    public static ItemPersistenceReader<QueryTreeItem> queryTree(QueryTree queryTree) {
        return new ItemPersistenceReader<>(new QueryTreeItemReader(), Persistence.SAVED_PATH,
                queryTreeItem -> queryTree.setRoot(QueryTreeItemUtil.makeTreeItem(queryTreeItem))
        );
    }

    public static ItemPersistenceReader<StateItem> stateItem(QueryTree queryTree, OutputPane outputPane, TabPane queryTabPane) {
        return new ItemPersistenceReader<>(new StateItemReader(), Persistence.STATE_PATH,
                stateItem -> {
                    for (String tab : stateItem.tabs()) {
                        QueryTreeItem.Query query = TreeViewUtil.queryFromName(queryTree.getRoot(), tab);
                        QueryPane queryPane = new QueryPane(query, outputPane);
                        queryTabPane.getTabs().add(new Tab(tab, queryPane));
                    }
                    queryTabPane.getSelectionModel().select(stateItem.currentTab());
                }
        );
    }
}
