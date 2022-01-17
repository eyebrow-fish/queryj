package fish.eyebrow.queryj.querytree;

import fish.eyebrow.queryj.querypane.QueryPane;
import fish.eyebrow.queryj.querytree.util.TreeItemUtil;
import fish.eyebrow.queryj.querytree.util.TreeViewUtil;
import javafx.fxml.FXML;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TreeItem;
import javafx.scene.control.cell.TextFieldTreeCell;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;

public class QueryTreeController {
    @FXML
    private QueryTree queryTree;
    private TabPane queryTabPane;
    private RenameDialog renameDialog;

    private QueryTreeContextMenu queryTreeContextMenu;
    private final ArrayList<QueryTreeItem> queryTreeItems = new ArrayList<>();

    @FXML
    private void initialize() {
        renameDialog = new RenameDialog();

        queryTree.setOnMouseClicked(this::handleTreeClick);
        queryTree.setCellFactory(TextFieldTreeCell.forTreeView());

        ArrayList<QueryTreeItem> exampleQueries = new ArrayList<>();
        QueryTreeItem.QueryGroup examples = new QueryTreeItem.QueryGroup("examples", null, exampleQueries);
        exampleQueries.add(new QueryTreeItem.Query("duckduckgo", "GET", "https://duckduckgo.com", "", examples));
        queryTreeItems.add(examples);

        TreeItem<String> root = new TreeItem<>("examples");
        root.getChildren().add(new TreeItem<>("duckduckgo"));
        queryTree.setRoot(root);
    }

    private void handleTreeClick(MouseEvent event) {
        if (queryTreeContextMenu != null) {
            queryTreeContextMenu.hide();
            queryTreeContextMenu = null;
        }

        switch (event.getButton()) {
            case PRIMARY -> {
                if (event.getClickCount() < 2) return;

                TreeItem<String> treeItem = TreeViewUtil.currentSelection(queryTree);
                if (treeItem == null) return;

                String qualifiedName = TreeItemUtil.qualifiedNameOf(TreeViewUtil.currentSelection(queryTree));
                QueryTreeItem queryTreeItem = QueryTreeItem.findQueryTreeItem(queryTreeItems, qualifiedName);
                if (queryTreeItem instanceof (QueryTreeItem.Query query)) {
                    Optional<Tab> optionalTab = queryTabPane.getTabs()
                            .stream()
                            .filter(tab -> tab.getText().equals(query.getName()))
                            .findFirst();


                    if (optionalTab.isEmpty()) {
                        queryTabPane.getTabs().add(new Tab(query.getName(), new QueryPane(query)));
                    } else {
                        queryTabPane.getSelectionModel().select(optionalTab.get());
                    }
                }
            }
            case SECONDARY -> {
                queryTreeContextMenu = new QueryTreeContextMenu(queryTree, queryTreeItems, renameDialog);
                queryTreeContextMenu.show(queryTabPane, event.getScreenX(), event.getScreenY());
            }
        }
    }

    public void setQueryTabPane(TabPane queryTabPane) {
        this.queryTabPane = queryTabPane;
    }
}
