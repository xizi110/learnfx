package xyz.yuelai.learnfx.util;

import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;

import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * @author zhong
 * @date 2020/2/7 18:14
 */
public class ViewUtil {

    public static void getViewAsync(Supplier<Node> supplier, Consumer<Node> consumer) {
        CompletableFuture.supplyAsync(supplier)
                .thenAcceptAsync(consumer, Platform::runLater)
                .exceptionally(throwable -> {
                    throwable.printStackTrace();
                    return null;
                });
    }

}
