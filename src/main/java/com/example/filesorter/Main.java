package com.example.filesorter;

import com.example.filesorter.UI.MainUI;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main extends Application {
    public static Stage STAGE;
    @Override
    public void start(Stage stage) throws IOException {
        STAGE=stage;
        stage.setTitle("FileSorter");
        stage.setScene(new Scene(new MainUI(),1200,700));
        stage.setResizable(false);
        stage.getIcons().add(new Image("C:\\Users\\ASUS\\IdeaProjects\\FileSorter\\src\\main\\resources\\logo.png"));
        stage.show();
    }
    public static void main(String[] args) {
        launch();
    }
}