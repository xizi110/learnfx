package xyz.yuelai.learnfx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * @author zhong
 * @date 2020/2/7 17:13
 */
public class App extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/xyz/yuelai/learnfx/component/main/Main.fxml"));
        fxmlLoader.load();
        BorderPane root = fxmlLoader.getRoot();
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
