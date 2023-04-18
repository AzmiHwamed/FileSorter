package com.example.filesorter.Controllers;

import com.example.filesorter.UI.FileElement;
import com.example.filesorter.UI.MainUI;

import java.util.ArrayList;

public class MainController {
    MainUI obj;

    public MainController(MainUI obj) {
        this.obj = obj;
    }
    public void ChangeFiles(ArrayList<String> names){
        obj.filesContainer.getChildren().clear();
        for (String name:names) {
            obj.filesContainer.getChildren().add(new FileElement(obj,name));
        }

    }
}
