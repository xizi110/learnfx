package xyz.yuelai.learnfx.component.introduction;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Hyperlink;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * @author zhong
 * @date 2020/2/7 22:09
 */
public class IntroductionController implements Initializable {

    @FXML
    private VBox root;
    @FXML
    private AnchorPane container;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

    public void openUrl(ActionEvent actionEvent) {
        Hyperlink link = (Hyperlink) actionEvent.getSource();
        String id = link.getId();
        try {
            String uri = null;
            if (id != null) {
                // 打开qq群链接
                if ("qq".equals(id)) {
                    uri = "https://jq.qq.com/?_wv=1027&k=5JKWZSc";
                }
            } else {
                // 资料链接
                String txt = link.getText();
                uri = txt.substring(txt.indexOf("：") + 1);
            }
            if (uri != null) {
                Desktop.getDesktop().browse(new URI(uri));
            }
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
    }
}
