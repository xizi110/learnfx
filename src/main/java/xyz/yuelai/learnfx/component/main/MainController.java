package xyz.yuelai.learnfx.component.main;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import xyz.yuelai.learnfx.util.ViewUtil;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * @author zhong
 * @date 2020/2/7 17:16
 */
public class MainController implements Initializable {


    @FXML
    private Pane content;
    @FXML
    private ScrollPane container;
    @FXML
    private BorderPane root;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // 右侧内容展示区，默认居中
        content.translateXProperty().bind(container.widthProperty().subtract(content.widthProperty()).divide(2));
    }

    public void loadView(ActionEvent actionEvent) {

        Button btn = (Button) actionEvent.getSource();
        String fxmlPath = null;
        // 点击不同按钮，加载不同视图
        switch (btn.getText()) {
            case "起步介绍": {
                fxmlPath = "/xyz/yuelai/learnfx/component/introduction/Introduction.fxml";
                break;
            }
            case "起步介绍1": {
                fxmlPath = "/xyz/yuelai/learnfx/component/main/Main1.fxml";
                break;
            }
            default: {
                fxmlPath = null;
            }
        }
        loadFXML(fxmlPath);
    }

    private void loadFXML(String fxmlPath){
        // 异步加载fxml文件
        ViewUtil.getViewAsync(() -> {
            if (fxmlPath == null || fxmlPath.isBlank()) {
                return null;
            }
            URL location = getClass().getResource(fxmlPath);
            if (location == null) {
                return null;
            }
            FXMLLoader fxmlLoader = new FXMLLoader(location);
            try {
                fxmlLoader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return fxmlLoader.getRoot();
        }, node -> {
            if (node != null) {
                ObservableList<Node> children = content.getChildren();
                children.clear();
                children.add(node);
            }
        });
    }
}
