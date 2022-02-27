package fish.eyebrow.queryj.querytree;

import fish.eyebrow.queryj.persist.item.QueryTreeItem;
import fish.eyebrow.queryj.querypane.OutputPane;
import fish.eyebrow.queryj.querypane.QueryPane;
import fish.eyebrow.queryj.querytree.renamedialog.RenameDialog;
import fish.eyebrow.queryj.querytree.util.TreeViewUtil;
import fish.eyebrow.queryj.util.FXMLLoaderUtil;
import javafx.fxml.FXML;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.input.MouseEvent;

public class QueryTree extends TreeView<QueryTreeItem> {
    private OutputPane outputPane;
    private TabPane queryTabPane;
    private RenameDialog renameDialog;
    private QueryTreeContextMenu queryTreeContextMenu;

    public QueryTree() {
        FXMLLoaderUtil.loadFromResource(this);
    }

    @FXML
    private void initialize() {
        renameDialog = new RenameDialog();
    }

    @FXML
    private void handleTreeClick(MouseEvent event) {
        if (queryTreeContextMenu != null) {
            queryTreeContextMenu.hide();
            queryTreeContextMenu = null;
        }

        switch (event.getButton()) {
            case PRIMARY -> {
                if (event.getClickCount() < 2) return;

                TreeItem<QueryTreeItem> treeItem = TreeViewUtil.currentSelection(this);
                if (treeItem == null) return;

                if (treeItem.getValue() instanceof (QueryTreeItem.Query query)) {
                    // Opens the tab as a QueryPane.
                    // This is done by either selecting an already existent tab, or by making a new one.
                    Tab tab = queryTabPane.getTabs()
                            .stream()
                            .filter(t -> t.getText().equals(query.getName()))
                            .findFirst()
                            .orElseGet(() -> {
                                QueryPane queryPane = new QueryPane(query, outputPane);

                                Tab t = new Tab(query.getName(), queryPane);
                                queryTabPane.getTabs().add(t);
                                return t;
                            });

                    queryTabPane.getSelectionModel().select(tab);
                }
            }
            case SECONDARY -> {
                queryTreeContextMenu = new QueryTreeContextMenu(this, renameDialog);
                queryTreeContextMenu.show(queryTabPane, event.getScreenX(), event.getScreenY());
            }
        }
    }

    public void setQueryTabPane(TabPane queryTabPane) {
        this.queryTabPane = queryTabPane;
    }

    public void setOutputPane(OutputPane outputPane) {
        this.outputPane = outputPane;
    }
}
