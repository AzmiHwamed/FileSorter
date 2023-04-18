package com.example.filesorter.Sorter;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.IdentityHashMap;
import java.util.List;
import java.util.Map;

public class Sorter {
    ArrayList<String> importatFiles=new ArrayList<>();
    boolean importantChecked=false;
    boolean dup=false;
    String dirPath;
    boolean reccur=false;
    public void setImportatFiles(ArrayList<String> importatFiles) {
        this.importatFiles = importatFiles;
    }
    public void setImportantChecked(boolean importantChecked) {
        this.importantChecked = importantChecked;
    }

    public void setDup(boolean dup) {
        this.dup = dup;
    }

    public void setDirPath(String dirPath) {
        this.dirPath = dirPath;
    }
    public void setReccur(boolean reccur) {
        this.reccur = reccur;
    }
    public void addToImportantFiles(String fileName){
        this.importatFiles.add(fileName);
    }


    public void sortByExt() throws IOException {
        System.out.println(dirPath);
        ArrayList<String> filesNames=Utilities.getFilesByDir(this.dirPath);
        Map<String,List<String>> map=Utilities.SplitByExtension(filesNames);
        if(this.importantChecked){
            File dir = new File(this.dirPath+"/importantFiles");
            dir.mkdirs();
            if(this.dup){
                for (String s:this.importatFiles) {

                        Files.copy(Paths.get(this.dirPath+"/"+s), Paths.get(this.dirPath+"/importantFiles/"+s), StandardCopyOption.REPLACE_EXISTING);

                }
                Utilities.createDirsAndMoveByExt(map,this.dirPath,new ArrayList<>());
            }
            else{
                for (String s:this.importatFiles) {

                        Files.move(Paths.get(this.dirPath+"/"+s), Paths.get(this.dirPath+"/importantFiles/"+s), StandardCopyOption.REPLACE_EXISTING);

                }
                Utilities.createDirsAndMoveByExt(map,this.dirPath,importatFiles);
            }
        }
        else{
            Utilities.createDirsAndMoveByExt(map,this.dirPath,new ArrayList<>());
        }
        if(reccur)
        for(String s:Utilities.getSubDirectories(filesNames)){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    Sorter sorter=new Sorter();
                    sorter.setDup(dup);
                    sorter.setImportatFiles(new ArrayList<>());
                    sorter.setImportantChecked(importantChecked);
                    sorter.setDirPath(dirPath+"/"+s);
                    sorter.setReccur(reccur);
                    try {
                        sorter.sortByExt();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            }).start();
            System.out.println(s);
        }

    }


    public void sortByType() throws IOException {
        ArrayList<String> filesNames=Utilities.getFilesByDir(this.dirPath);
        Map<String,List<String>> map=Utilities.SplitByExtension(filesNames);
        Map<String,List<String>> typeMap=new IdentityHashMap<>();
        for (String key:map.keySet()) {
            String type=Utilities.getTypeByExt("."+key);
            if (typeMap.containsKey(type)){
                typeMap.get(type).addAll(map.get(key));
            }
            else{
                typeMap.put(type,new ArrayList<String>(map.get(key)));
            }
        }
        if(this.importantChecked){
            File dir = new File(this.dirPath+"/importantFiles");
            dir.mkdirs();
            if(this.dup){
                for (String s:this.importatFiles) {

                        Files.copy(Paths.get(this.dirPath+"/"+s), Paths.get(this.dirPath+"/importantFiles/"+s), StandardCopyOption.REPLACE_EXISTING);

                }
                Utilities.createDirsAndMoveByExt(typeMap,this.dirPath,new ArrayList<>());
            }
            else{
                for (String s:this.importatFiles) {

                        Files.move(Paths.get(this.dirPath+"/"+s), Paths.get(this.dirPath+"/importantFiles/"+s), StandardCopyOption.REPLACE_EXISTING);

                }
                Utilities.createDirsAndMoveByExt(typeMap,this.dirPath,importatFiles);
            }
        }
        else{
            Utilities.createDirsAndMoveByExt(typeMap,this.dirPath,new ArrayList<>());
        }
        if(reccur)
        for(String s:Utilities.getSubDirectories(filesNames)){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    Sorter sorter=new Sorter();
                    sorter.setDup(dup);
                    sorter.setImportatFiles(new ArrayList<>());
                    sorter.setImportantChecked(importantChecked);
                    sorter.setDirPath(dirPath+"/"+s);
                    sorter.setReccur(reccur);
                    try {
                        sorter.sortByType();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            }).start();
        }
    }
}
