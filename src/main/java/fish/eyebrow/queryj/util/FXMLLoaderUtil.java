package fish.eyebrow.queryj.util;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;

import java.io.IOException;

public class FXMLLoaderUtil {
    private FXMLLoaderUtil() {
    }

    public static void loadFromResource(Node root) {
        FXMLLoader fxmlLoader = new FXMLLoader(root.getClass().getResource(root.getClass().getSimpleName() + ".fxml"));
        fxmlLoader.setRoot(root);
        fxmlLoader.setController(root);
        try {
            fxmlLoader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
