package xyz.yuelai.learnfx.component.introduction;

import com.sun.glass.ui.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Hyperlink;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.awt.*;
import java.io.IOException;
import java.net.MalformedURLException;
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
        String txt = link.getText();
        try {
            String urlTxt = txt.substring(txt.indexOf("：") + 1);
            URI url = new URL(urlTxt).toURI();
            Desktop.getDesktop().browse(url);
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }

    }
}
