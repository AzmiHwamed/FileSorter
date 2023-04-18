package com.example.filesorter.Sorter;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.*;

public class Utilities {
    public static ArrayList<String> getFilesByDir(String dir){
        File directory = new File(dir);
        File[] files = directory.listFiles();
        ArrayList<String> fileNames=new ArrayList<>();
        if (files != null) {
            for (File file : files) {
                fileNames.add(file.getName());
            }
        }
    return fileNames;
    }
    public static Map<String, List<String>> SplitByExtension(ArrayList<String> filenames){
        Map<String, List<String>> map = new HashMap<>();

        for (String filename : filenames) {
            int dotIndex = filename.lastIndexOf(".");
            if (dotIndex != -1) {
                String extension = filename.substring(dotIndex + 1);
                List<String> list = map.getOrDefault(extension, new ArrayList<>());
                list.add(filename);
                map.put(extension, list);
            }
        }
        return map;
    }
    public static ArrayList<String> getSubDirectories(ArrayList<String> filenames){
        ArrayList<String> result=new ArrayList<>();
        for (String filename : filenames) {
            int dotIndex = filename.lastIndexOf(".");
            if (dotIndex == -1) {
                result.add(filename);
            }
        }
        return result;
    }
    public static void createDirsAndMoveByExt(Map<String,List<String>> map,String Dir,ArrayList<String> importantfiles){
        for (Map.Entry<String, List<String>> entry : map.entrySet()) {
            if(!"".equals(entry.getKey())){
                File dir = new File(Dir+"/"+entry.getKey() +" files");
                dir.mkdirs();
                for (String fileName : entry.getValue()) {
                    try {
                        if(!importantfiles.contains(fileName)){
                            Files.move(Paths.get(Dir+"/"+fileName), Paths.get(Dir+"/"+entry.getKey() +" files"+"/"+fileName), StandardCopyOption.REPLACE_EXISTING);
                        }
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }}
        }
    }
    public static String getTypeByExt(String filename){
        List<String> Docs = Arrays.asList(".doc", ".docx", ".dot", ".dotx", ".docm", ".dotm", ".rtf", ".txt", ".htm", ".html");
        List<String> Sheets = Arrays.asList(".xls", ".xlsx", ".xlsm", ".xlsb", ".xlt", ".xltx", ".xltm", ".csv", ".xml");
        List<String> Slides = Arrays.asList(".ppt", ".pptx", ".pptm", ".pot", ".potx", ".potm", ".pps", ".ppsx", ".ppsm");
        List<String> Images = Arrays.asList(".bmp", ".gif", ".ico", ".jpeg", ".jpg", ".png", ".tif", ".tiff",".webp");
        List<String> Movies = Arrays.asList(".3g2", ".3gp", ".avi", ".flv", ".m4v", ".mkv", ".mov", ".mp4", ".mpeg", ".mpg", ".webm", ".wmv");
        List<String> Musics = Arrays.asList(".aif", ".aifc", ".aiff", ".au", ".flac", ".m4a", ".m4b", ".mp3", ".ogg", ".wav", ".wma");
        String[] extensions = {".zip", ".rar", ".7z", ".gz", ".tar", ".cab", ".iso", ".lzh"};
        ArrayList<String> Compressed = new ArrayList<String>(Arrays.asList(extensions));
        String[] extensions1 = {".mdb", ".accdb", ".db", ".dbf", ".mde", ".accde", ".mdf", ".ldf", ".sql"};
        ArrayList<String> DataBase = new ArrayList<String>(Arrays.asList(extensions1));

        if(Docs.contains(filename)){
            return "Document";
        }
        if(Sheets.contains(filename)){
            return "Sheets";
        }
        if(Slides.contains(filename)){
            return "Slides";
        }
        if(Images.contains(filename)){
            return "Images";
        }
        if(Movies.contains(filename)){
            return "Movies";
        }
        if(Musics.contains(filename)){
            return "Music";
        }
        if(Compressed.contains(filename)){
            return "Compressed";
        }
        if(DataBase.contains(filename)){
            return "DataBase";
        }
        return "Other";
    }
}
