package com.example.filesorter.UI;

import com.example.filesorter.Controllers.MainController;
import com.example.filesorter.Main;
import com.example.filesorter.Sorter.Sorter;
import com.example.filesorter.Sorter.Utilities;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.DirectoryChooser;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class MainUI extends VBox {
    ImageView logo;
    Button chooseDirectory;
    Text directoryName;
    CheckBox important;
    VBox importantFilesContainer;
    ScrollPane files;
    public VBox filesContainer;
    CheckBox dup;
    Text chooseTypeLabel;
    ToggleGroup Type;
    RadioButton fileType;
    RadioButton fileExt;
    CheckBox reccur;
    ArrayList<String> importantFiles=new ArrayList<>();
    Button sort;
    String path="";
    MainController controller;

    public MainUI() {
        controller=new MainController(this);
        logo=new ImageView(new Image("C:\\Users\\ASUS\\IdeaProjects\\FileSorter\\src\\main\\resources\\logo.png"));
        logo .setFitHeight(50);
        HBox logoContainer=new HBox(logo);
        logoContainer.setAlignment(Pos.CENTER_LEFT);
        logoContainer.setPrefWidth(1200);
        logoContainer.setPrefHeight(50);
        chooseDirectory=new Button("Choose Directory");
        chooseDirectory.setBackground(Background.fill(Paint.valueOf("#7D949A")));
        chooseDirectory.setTextFill(Paint.valueOf("#000000"));
        directoryName=new Text("C://");
        directoryName.setFill(Paint.valueOf("#7D949A"));
        chooseDirectory.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                DirectoryChooser directoryChooser=new DirectoryChooser();
                directoryChooser.setInitialDirectory(new File("C://"));
                path=directoryChooser.showDialog(Main.STAGE).getAbsolutePath();
                controller.ChangeFiles(Utilities.getFilesByDir(path));
                directoryName.setText(path);
            }
        });
        HBox chooseDirContainer=new HBox(chooseDirectory,directoryName);
        chooseDirContainer.setPrefHeight(50);
        chooseDirContainer.setPrefWidth(1200);
        chooseDirContainer.setPadding(new Insets(10,0,0,30));
        chooseDirContainer.setSpacing(80);
        chooseDirContainer.setAlignment(Pos.CENTER_LEFT);
        important=new CheckBox("Create an 'Important' Folder");
        important.setTextFill(Paint.valueOf("#43655A"));
        HBox importantContainer=new HBox(important);
        importantContainer.setPadding(new Insets(10,0,0,30));
        importantContainer.setAlignment(Pos.CENTER_LEFT);
        dup=new CheckBox("Duplicate files");
        dup.setTextFill(Paint.valueOf("#43655A"));
        filesContainer=new VBox();
        files=new ScrollPane(filesContainer);
        files.setFitToWidth(true);
        files.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        importantFilesContainer=new VBox(files,dup);
        importantFilesContainer.setPadding(new Insets(10,0,0,30));
        important.setSelected(true);
        filesContainer.setSpacing(10);
        important.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                importantFilesContainer.setVisible(important.isSelected());
            }
        });







        chooseTypeLabel=new Text("Choose Sorting Type");
        HBox orderingLabelContainer=new HBox(chooseTypeLabel);
        orderingLabelContainer.setPadding(new Insets(10,0,0,30));
        orderingLabelContainer.setAlignment(Pos.CENTER_LEFT);
        fileExt=new RadioButton("By File Extension");
        fileType=new RadioButton("By File Type");
        fileExt.setTextFill(Paint.valueOf("#43655A"));
        fileType.setTextFill(Paint.valueOf("#43655A"));
        Type=new ToggleGroup();
        Type.getToggles().addAll(fileExt,fileType);
        fileExt.setSelected(true);
        HBox radioButtonsContainer=new HBox(fileExt,fileType);
        radioButtonsContainer.setAlignment(Pos.CENTER_LEFT);
        radioButtonsContainer.setPadding(new Insets(10,0,0,60));
        radioButtonsContainer.setSpacing(80);
        reccur=new CheckBox("Apply sorting to subDirectories");
        reccur.setTextFill(Paint.valueOf("#43655A"));
        HBox reccurContainer=new HBox(reccur);
        reccurContainer.setPadding(new Insets(10,0,0,30));
        reccurContainer.setAlignment(Pos.CENTER_LEFT);
        sort=new Button("Sort Directory");
        sort.setFont(Font.font(20));
        sort.setPrefWidth(1200);
        sort.setBackground(Background.fill(Paint.valueOf("#43655A")));
        HBox sortContainer=new HBox(sort);
        sortContainer.setPadding(new Insets(10,30,10,30));
        sortContainer.setPrefWidth(1000);
        sortContainer.setAlignment(Pos.CENTER_LEFT);
        sort.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Sorter sorter=new Sorter();
                sorter.setDirPath(path);
                sorter.setDup(dup.isSelected());
                sorter.setImportantChecked(important.isSelected());
                sorter.setImportatFiles(importantFiles);
                sorter.setReccur(reccur.isSelected());
                if(fileExt.isSelected()){
                    try {
                        sorter.sortByExt();
                    } catch (IOException e) {
                        Dialog<String> dialog = new Dialog<String>();
                        //Setting the title
                        dialog.setTitle("Dialog");
                        ButtonType type = new ButtonType("Ok", ButtonBar.ButtonData.OK_DONE);
                        //Setting the content of the dialog
                        dialog.setContentText("This is a sample dialog");
                        //Adding buttons to the dialog pane
                        dialog.getDialogPane().getButtonTypes().add(type);
                        dialog.show();
                    }
                }
                else {
                    try {
                        sorter.sortByType();
                    } catch (IOException e) {
                        Dialog<String> dialog = new Dialog<String>();
                        //Setting the title
                        dialog.setTitle("Error!!");
                        ButtonType type = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
                        //Setting the content of the dialog
                        dialog.setContentText("An error occured while sorting your directory !!");
                        //Adding buttons to the dialog pane
                        dialog.getDialogPane().getButtonTypes().add(type);
                        dialog.show();
                    }
                }
                Main.STAGE.setScene(new Scene(new DoneUI(),1200,700));
            }
        });
        this.setSpacing(20);
        this.getChildren().addAll(logoContainer,chooseDirContainer,importantContainer,importantFilesContainer,orderingLabelContainer,radioButtonsContainer,reccurContainer,sortContainer);
    }
}
