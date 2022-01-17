package fish.eyebrow.queryj.querytree;

import fish.eyebrow.queryj.util.FXMLLoaderUtil;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TreeItem;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.ArrayList;

public class RenameDialog extends BorderPane {
    private final Stage stage;
    private final RenameDialogController controller;

    public RenameDialog() {
        FXMLLoader fxmlLoader = FXMLLoaderUtil.loadFromResource(this, getClass().getResource("RenameDialog.fxml"));
        stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Rename field");
        stage.setScene(new Scene(this));

        controller = fxmlLoader.getController();
        controller.setStage(stage);
    }

    public void show(TreeItem<String> treeItem, ArrayList<QueryTreeItem> queryTreeItems) {
        controller.getRenameField().setText(treeItem.getValue());
        controller.getRenameField().requestFocus();
        controller.getRenameField().selectAll();
        controller.setTreeItem(treeItem);
        controller.setQueryTreeItems(queryTreeItems);
        stage.show();
    }
}
