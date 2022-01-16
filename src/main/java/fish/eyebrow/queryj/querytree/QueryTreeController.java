package fish.eyebrow.queryj.querytree;

import fish.eyebrow.queryj.querypane.QueryPane;
import fish.eyebrow.queryj.querytree.util.TreeItemUtil;
import fish.eyebrow.queryj.querytree.util.TreeViewUtil;
import javafx.fxml.FXML;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.control.cell.TextFieldTreeCell;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.util.ArrayList;

public class QueryTreeController {
    @FXML
    private QueryTree queryTree;
    private QueryPane queryPane;
    private RenameDialog renameDialog;

    private QueryTreeContextMenu queryTreeContextMenu;
    private final ArrayList<QueryTreeItem> queryTreeItems = new ArrayList<>();

    @FXML
    private void initialize() throws IOException {
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
                    queryPane.setMethod(query.method());
                    queryPane.setURL(query.url());
                    queryPane.setBody(query.body());
                }
            }
            case SECONDARY -> {
                queryTreeContextMenu = new QueryTreeContextMenu(queryTree, queryTreeItems, renameDialog);
                queryTreeContextMenu.show(queryPane, event.getScreenX(), event.getScreenY());
            }
        }
    }

    public void setQueryPane(QueryPane queryPane) {
        this.queryPane = queryPane;
    }
}
