package fish.eyebrow.queryj;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class QueryJApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("QueryJView.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 600);
        stage.setTitle("QueryJ");
        stage.setScene(scene);
        stage.setForceIntegerRenderScale(true);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
