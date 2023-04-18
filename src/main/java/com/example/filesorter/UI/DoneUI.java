package com.example.filesorter.UI;

import com.example.filesorter.Main;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;

public class DoneUI extends VBox {
    ImageView logo;
    ImageView content;
    Button ReSort;

    public DoneUI() {
        logo=new ImageView(new Image("C:\\Users\\ASUS\\IdeaProjects\\FileSorter\\src\\main\\resources\\logo.png"));
        logo .setFitHeight(50);
        HBox logoContainer=new HBox(logo);
        logoContainer.setAlignment(Pos.CENTER_LEFT);
        logoContainer.setPrefWidth(1200);
        logoContainer.setPrefHeight(50);

        content=new ImageView(new Image("C:\\Users\\ASUS\\IdeaProjects\\FileSorter\\src\\main\\resources\\done.png"));
        content .setFitHeight(600);
        content.setFitWidth(600);
        HBox contentContainer=new HBox(content);
        contentContainer.setAlignment(Pos.CENTER);
        contentContainer.setPrefWidth(1200);
        contentContainer.setPrefHeight(200);
        ReSort=new Button("Sort Another Directory !");
        ReSort.setFont(Font.font(20));
        ReSort.setPrefWidth(1200);
        ReSort.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Main.STAGE.setScene(new Scene(new MainUI(),1200,700));
            }
        });
        ReSort.setBackground(Background.fill(Paint.valueOf("#43655A")));
        HBox sortContainer=new HBox(ReSort);
        sortContainer.setPadding(new Insets(10,30,10,30));
        sortContainer.setPrefWidth(1000);
        sortContainer.setAlignment(Pos.CENTER_LEFT);
        this.getChildren().addAll(logoContainer,contentContainer,sortContainer);
        this.setBackground(Background.fill(Paint.valueOf("#FFFFFF")));

    }
}
