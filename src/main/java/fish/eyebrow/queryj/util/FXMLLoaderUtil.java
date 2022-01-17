package fish.eyebrow.queryj.util;

import javafx.fxml.FXMLLoader;

import java.io.IOException;
import java.net.URL;

public class FXMLLoaderUtil {
    public static FXMLLoader loadFromResource(Object root, URL resource) {
        FXMLLoader fxmlLoader = new FXMLLoader(resource);
        fxmlLoader.setRoot(root);
        try {
            fxmlLoader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return fxmlLoader;
    }
}
