package fish.eyebrow.queryj.querytree.renamedialog;

import fish.eyebrow.queryj.persist.item.QueryTreeItem;
import fish.eyebrow.queryj.util.FXMLLoaderUtil;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeItem;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class RenameDialog extends BorderPane {
    @FXML
    private TextField renameField;

    private final Stage stage;
    private TreeItem<QueryTreeItem> treeItem;

    public RenameDialog() {
        stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Rename field");
        stage.setScene(new Scene(this));

        FXMLLoaderUtil.loadFromResource(this);
    }

    public void show(TreeItem<QueryTreeItem> treeItem) {
        renameField.setText(treeItem.getValue().getName());
        renameField.requestFocus();
        renameField.selectAll();
        this.treeItem = treeItem;
        stage.show();
    }

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
}
