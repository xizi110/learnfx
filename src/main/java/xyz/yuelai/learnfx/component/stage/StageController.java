package xyz.yuelai.learnfx.component.stage;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * @author zhong
 * @date 2020/2/8 17:23
 */
public class StageController {

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
}
