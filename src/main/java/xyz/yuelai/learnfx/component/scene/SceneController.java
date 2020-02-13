package xyz.yuelai.learnfx.component.scene;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * @author zhong
 * @date 2020/2/13 21:08
 */
public class SceneController implements Initializable {

    @FXML
    private VBox root;
    private Scene oneScene;

    public void showNoSceneStage(ActionEvent event) {
        Stage stage = new Stage();
        stage.setWidth(800);
        stage.setHeight(300);
        stage.setTitle("没有场景的stage，不能设置任何控件，不会显示任何内容，所以stage必须要有一个scene");
        System.out.println(stage.getWidth());
        System.out.println(stage.getHeight());
        stage.show();
        Scene scene = stage.getScene();
        System.out.println(scene);
    }

    public void showOneStage(ActionEvent event) {
        Stage stage = new Stage();
        stage.setWidth(600);
        stage.setHeight(300);
        stage.setScene(oneScene);

        stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        VBox vBox = new VBox();
        Button button = new Button("close");
        Text text = new Text("我只能同时附加在一个stage上，不信你点击另一个按钮，创建另一个窗体后，再点击close按钮。");
        button.setOnAction(event1 -> {
            Stage window = (Stage) button.getScene().getWindow();
            window.close();
        });
        vBox.getChildren().addAll(text, button);
        oneScene = new Scene(vBox);
    }

    private int i = 0;
    private double width = 0;
    public void resizeScene(ActionEvent event) {
        if (i == 0) {
            width = root.getPrefWidth();
            root.setPrefWidth(width + 100);
            i = 1;
        }else {
            root.setPrefWidth(width);
            i = 0;
        }
    }

    public void getFocusOwner(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText("场景中的焦点在：" + root.getScene().getFocusOwner().toString());
        alert.showAndWait();
    }

    public void setFocusOwner(ActionEvent event) {
        Stage stage = new Stage();
        stage.setWidth(800);
        stage.setHeight(300);
        TextField textField = new TextField();
        TextField textField1 = new TextField("焦点在我这，要先设置焦点，必须先设置scene。");
        Scene scene = new Scene(new VBox(textField, textField1));
        stage.setScene(scene);
        // 先设置scene，再设置焦点
        textField1.requestFocus();
//        scene.();
        stage.show();
    }
}
