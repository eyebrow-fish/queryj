package fish.eyebrow.queryj.querytree.renamedialog;

import fish.eyebrow.queryj.persist.item.QueryTreeItem;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeItem;
import javafx.stage.Stage;

public class RenameDialogController {
    @FXML
    private TextField renameField;

    private Stage stage;
    private TreeItem<QueryTreeItem> treeItem;

    @FXML
    private void submit() throws CloneNotSupportedException {
        String newName = renameField.getText();

        QueryTreeItem value = treeItem.getValue();
        QueryTreeItem newQueryTreeItem = (QueryTreeItem) value.clone();
        newQueryTreeItem.setName(newName);
        treeItem.setValue(newQueryTreeItem);

        if (treeItem.getParent() != null) {
            QueryTreeItem.QueryGroup parentGroup = (QueryTreeItem.QueryGroup) treeItem.getParent().getValue();
            parentGroup.getChildren().remove(value);
            parentGroup.getChildren().add(newQueryTreeItem);
        }

        close();
    }

    @FXML
    private void close() {
        stage.hide();
        treeItem = null;
    }

    public TextField getRenameField() {
        return renameField;
    }

    public TreeItem<QueryTreeItem> getTreeItem() {
        return treeItem;
    }

    public void setTreeItem(TreeItem<QueryTreeItem> treeItem) {
        this.treeItem = treeItem;
    }

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }
}
