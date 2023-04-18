package com.example.filesorter.UI;

import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class FileElement extends BorderPane {
    MainUI obj;
    String fileName;
    Text add;
    Text fileNameText;
    boolean added=false;
    public FileElement(MainUI obj, String filename) {
        this.obj = obj;
        this.fileName = filename;
        this.setBackground(Background.fill(Paint.valueOf("#C3C5CB")));
        this.setPrefSize(1000,30);
        add=new Text("+");
        add.setFont(Font.font(30));
        fileNameText=new Text(fileName);
        this.setPadding(new Insets(5,0,0,5));
        add.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                added=!added;
                add.setText(added?"-":"+");
                if(added){
                    obj.importantFiles.add(fileName);
                }
                else{
                    obj.importantFiles.remove(fileName);
                }
            }

        });
        this.setLeft(fileNameText);
        this.setRight(add);
        this.setStyle("-fx-background-radius:5;-fx-background-color:#C3C5CB;");

    }
}
