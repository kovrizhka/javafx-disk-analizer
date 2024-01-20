package ru.kovrizhkin.study.javafxdiskanalyzer;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Map;

public class HelloApplication extends Application {

    private Stage mainStage;
    private Map<String, Long> sizes;

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) throws IOException {
        showApp(stage);
    }

    private Button createDirectoryAskingButton() {
        Button button = new Button("Выбери директорию для подсчёта");
        button.setOnAction(event -> {
            File file = new DirectoryChooser().showDialog(mainStage);
            String path = file.getAbsolutePath();

            sizes = new AnalyzerLogic().calculateDirectorySize(Path.of(path));
        });
        return button;
    }

    private void showApp(Stage stage) {
        mainStage = stage;
        mainStage.setTitle("АНАЛизатор диска");

        StackPane mainPane = new StackPane();
        mainPane.getChildren().add(createDirectoryAskingButton());

        mainStage.setScene(new Scene(mainPane, 300, 300));
        mainStage.show();
    }

}