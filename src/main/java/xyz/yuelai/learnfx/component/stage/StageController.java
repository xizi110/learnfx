package xyz.yuelai.learnfx.component.stage;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * @author zhong
 * @date 2020/2/8 17:23
 */
public class StageController {

    @FXML
    private TextField num1;
    @FXML
    private TextField num2;


    private double offsetX;
    private double offsetY;

    /**
     * 窗体显示
     *
     * @param actionEvent
     */
    @FXML
    public void show(ActionEvent actionEvent) {
        Button btn = ((Button) actionEvent.getSource());
        String text = btn.getText();
        Label label = new Label();
        label.setPrefWidth(300);
        label.setWrapText(true);
        StageStyle style = null;
        switch (text) {
            case "Decorated": {
                style = StageStyle.DECORATED;
                label.setText("默认样式：具有白色背景和平台装饰的窗体。");
                break;
            }
            case "Undecorated": {
                style = StageStyle.UNDECORATED;
                label.setText("具有白色背景没有平台装饰的窗体。");
                break;
            }
            case "Transparent": {
                style = StageStyle.TRANSPARENT;
                label.setText("透明背景，没有装饰。需要判断平台是否支持：Platform.isSupported(ConditionalFeature.TRANSPARENT_WINDOW);如果不支持则是降级为UNDECORATED样式。");
                break;
            }
            case "Unified": {
                style = StageStyle.UNIFIED;
                label.setText("内容区与装饰区背景相同。需要判断平台是否支持：Platform.isSupported(ConditionalFeature.TRANSPARENT_WINDOW);如果不支持则是降级为DECORATED样式。");
                break;
            }
            case "Utility": {
                style = StageStyle.UTILITY;
                label.setText("具有白色背景，平台最低使用要求装饰的窗体。");
                break;
            }
            default: {
                style = StageStyle.DECORATED;
            }
        }

        Stage stage = new Stage(style);
        stage.setTitle("窗口标题");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setWidth(400);
        stage.setHeight(300);

        Pane pane = new Pane();
        pane.setStyle("-fx-background-color: transparent;");


        Scene scene = new Scene(pane);
        if (stage.getStyle() == StageStyle.TRANSPARENT){
            scene.setFill(null);
        }else if ((stage.getStyle() == StageStyle.UNIFIED)){
            scene.setFill(Color.TRANSPARENT);
        }
        stage.setScene(scene);
        pane.getChildren().addAll(label, addCloseBtn(stage));
        stage.show();
    }

    /**
     * 返回一个按钮，把传递进来的stage关闭
     * @param stage 要关闭的stage
     * @return
     */
    private Button addCloseBtn(Stage stage){
        Button close = new Button("Close Stage");

        close.setOnAction(event -> stage.close());
        close.translateXProperty().bind(stage.getScene().widthProperty().subtract(close.widthProperty()).divide(2));
        close.translateYProperty().bind(stage.getScene().heightProperty().subtract(close.heightProperty()).divide(2));
        return close;
    }

    @FXML
    public void showUndecorated(ActionEvent event) {
        Stage stage = new Stage(StageStyle.UNDECORATED);
        stage.initModality(Modality.APPLICATION_MODAL);

        stage.setWidth(400);
        stage.setHeight(300);
        Pane pane = new Pane();
        pane.setStyle("-fx-background-color: transparent;");
        Scene scene = new Scene(pane);
        scene.setFill(null);
        stage.setScene(scene);
        pane.getChildren().add(addCloseBtn(stage));
        stage.show();

        // 设置窗体移动
        setMobile(stage);
    }

    private void setMobile(Stage stage){
        Pane root = (Pane) stage.getScene().getRoot();
        root.setOnMousePressed(event -> {
            this.offsetX = event.getScreenX() - stage.getX();
            this.offsetY = event.getScreenY() - stage.getY();
        });

        root.setOnMouseDragged(event -> {
            stage.setX(event.getScreenX() - this.offsetX);
            stage.setY(event.getScreenY() - this.offsetY);
        });

    }

    @FXML
    public void showModalityStage(ActionEvent event) {
        Button btn = (Button) event.getSource();
        Stage stage = new Stage();
        stage.setWidth(400);
        stage.setHeight(300);

        String modality = btn.getText();

        stage.initModality(Modality.valueOf(modality));

        Pane pane = new Pane();
        pane.setStyle("-fx-background-color: transparent;");
        Scene scene = new Scene(pane);
        scene.setFill(null);
        stage.setScene(scene);
        pane.getChildren().add(addCloseBtn(stage));
        stage.show();
    }

    @FXML
    public void showNotResizableStage(ActionEvent event) {
        Stage stage = new Stage();
        stage.setResizable(false);
        stage.setWidth(400);
        stage.setHeight(300);

        Pane pane = new Pane();
        pane.setStyle("-fx-background-color: transparent;");
        Scene scene = new Scene(pane);
        scene.setFill(null);
        stage.setScene(scene);
        pane.getChildren().add(addCloseBtn(stage));
        stage.show();
    }

    @FXML
    public void showAndWaitStage(ActionEvent event) {
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("showAndWait");
        stage.setResizable(false);
        stage.setWidth(400);
        stage.setHeight(300);

        Pane pane = new Pane();
        pane.setStyle("-fx-background-color: transparent;");
        Scene scene = new Scene(pane);
        scene.setFill(null);
        stage.setScene(scene);
        Label label = new Label("此窗体是调用showAndWait显示的窗体，当关闭此窗体时，会继续执行后续代码，显示一个正常窗体。");
        label.setWrapText(true);
        label.setPrefWidth(300);
        label.setLayoutY(30);
        pane.getChildren().addAll(label, addCloseBtn(stage));
        stage.showAndWait();
        label.setText("正常调用show显示的窗体");
        stage.setTitle("show");
        stage.show();
    }


    public void sum(ActionEvent event) {
        String number1 = num1.getText();
        String number2 = num2.getText();

        if (!"".equals(number1) && !"".equals(number2)){
            int sum = Integer.valueOf(number1) + Integer.valueOf(number2);
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            // 数据存储在stage的properties中
            stage.setUserData(sum);
            int sum1 = (int) stage.getUserData();

            VBox vBox = new VBox();
            Text text = new Text("当需要在多个窗体之间传递值时，可以使用setUserData()将数据存储在stage中，需要用时，使用getUserData()获取即可。");
            Button button = new Button(String.valueOf(sum1));
            button.setPrefWidth(100);
            button.setPrefHeight(100);
            vBox.getChildren().addAll(text, button);
            Scene scene = new Scene(vBox);
            stage.setScene(scene);
            stage.show();
        }
    }

    public void maxMinClose(ActionEvent event) {
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);

        HBox hBox = new HBox(10);
        Text text = new Text("最大化：setMaximized(true); 最小化：setIconified(true); 全屏：setFullScreen(true); 关闭：close()");
        Button max = new Button("最大化");
        Button min = new Button("最小化");
        Button fullScreen = new Button("全屏");
        Button close = new Button("关闭");

        stage.maximizedProperty().addListener((observableValue, oldVal, newVal) -> {
            if (newVal){
                max.setText("取消最大化");
            }else {
                max.setText("最大化");
            }
        });

        stage.fullScreenProperty().addListener((observableValue, oldVal, newVal) -> {
            if (newVal){
                fullScreen.setText("取消全屏");
            }else {
                fullScreen.setText("全屏");
            }
        });

        max.setOnAction(event1 -> {
            stage.setMaximized(stage.maximizedProperty().not().get());
        });

        min.setOnAction(event1 -> {
            stage.setIconified(stage.iconifiedProperty().not().get());
        });

        fullScreen.setOnAction(event1 -> {
            stage.setFullScreen(stage.fullScreenProperty().not().get());
        });

        close.setOnAction(event1 -> {
            stage.close();
        });


        hBox.getChildren().addAll(max, min, fullScreen, close);

        VBox vBox = new VBox();
        vBox.getChildren().addAll(text, hBox);
        Scene scene = new Scene(vBox);
        stage.setScene(scene);
        stage.show();
    }
}
