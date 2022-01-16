package fish.eyebrow.queryj.querytree;

import fish.eyebrow.queryj.querytree.util.TreeItemUtil;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeItem;
import javafx.stage.Stage;

import java.util.ArrayList;

public class RenameDialogController {
    @FXML
    private TextField renameField;

    private Stage stage;
    private TreeItem<String> treeItem;
    private ArrayList<QueryTreeItem> queryTreeItems;

    @FXML
    private void submit() {
        String newName = renameField.getText();

        String qualifiedName = TreeItemUtil.qualifiedNameOf(treeItem);
        QueryTreeItem queryTreeItem = QueryTreeItem.findQueryTreeItem(queryTreeItems, qualifiedName);

        queryTreeItem.setName(newName);
        treeItem.setValue(newName);

        close();
    }

    @FXML
    private void close() {
        stage.hide();
        treeItem = null;
        queryTreeItems = null;
    }

    public TextField getRenameField() {
        return renameField;
    }

    public TreeItem<String> getTreeItem() {
        return treeItem;
    }

    public void setTreeItem(TreeItem<String> treeItem) {
        this.treeItem = treeItem;
    }

    public ArrayList<QueryTreeItem> getQueryTreeItems() {
        return queryTreeItems;
    }

    public void setQueryTreeItems(ArrayList<QueryTreeItem> queryTreeItems) {
        this.queryTreeItems = queryTreeItems;
    }

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }
}
