package fish.eyebrow.queryj.querytree;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeItem;
import javafx.stage.Stage;

import java.util.ArrayList;

public class RenameDialogController {
    @FXML
    private TextField renameField;

    private Stage stage;
    private TreeItem<QueryTreeItem> treeItem;

    @FXML
    private void submit() {
        String newName = renameField.getText();
        treeItem.getValue().setName(newName);
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
