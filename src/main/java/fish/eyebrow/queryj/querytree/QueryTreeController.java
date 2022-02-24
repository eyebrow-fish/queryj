package fish.eyebrow.queryj.querytree;

import fish.eyebrow.queryj.persist.item.QueryTreeItem;
import fish.eyebrow.queryj.querypane.OutputPane;
import fish.eyebrow.queryj.querypane.QueryPane;
import fish.eyebrow.queryj.querytree.renamedialog.RenameDialog;
import fish.eyebrow.queryj.querytree.util.TreeViewUtil;
import javafx.fxml.FXML;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TreeItem;
import javafx.scene.input.MouseEvent;

import java.util.Optional;

public class QueryTreeController {
    @FXML
    private QueryTree queryTree;
    private OutputPane outputPane;
    private TabPane queryTabPane;
    private RenameDialog renameDialog;
    private QueryTreeContextMenu queryTreeContextMenu;

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

                TreeItem<QueryTreeItem> treeItem = TreeViewUtil.currentSelection(queryTree);
                if (treeItem == null) return;

                if (treeItem.getValue() instanceof (QueryTreeItem.Query query)) {
                    Optional<Tab> optionalTab = queryTabPane.getTabs()
                            .stream()
                            .filter(tab -> tab.getText().equals(query.getName()))
                            .findFirst();

                    if (optionalTab.isEmpty()) {
                        QueryPane queryPane = new QueryPane(query);
                        queryPane.setOutputPane(outputPane);
                        queryTabPane.getTabs().add(new Tab(query.getName(), queryPane));
                    } else {
                        queryTabPane.getSelectionModel().select(optionalTab.get());
                    }
                }
            }
            case SECONDARY -> {
                queryTreeContextMenu = new QueryTreeContextMenu(queryTree, renameDialog);
                queryTreeContextMenu.show(queryTabPane, event.getScreenX(), event.getScreenY());
            }
        }
    }

    public QueryTree getQueryTree() {
        return queryTree;
    }

    public void setQueryTabPane(TabPane queryTabPane) {
        this.queryTabPane = queryTabPane;
    }

    public void setOutputPane(OutputPane outputPane) {
        this.outputPane = outputPane;
    }
}
