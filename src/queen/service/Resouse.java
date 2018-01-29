package queen.service;

import queen.common.define.FileType;
import queen.common.utils.Object2JSON;
import queen.common.entity.FileStruct;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Resouse {

    public FileStruct getFils(String currentPaht){
        File file = new File("E:\\EBook");
        FileStruct fileStruct = null;
        if (file.isDirectory()){
            fileStruct =  new FileStruct(file.getName(),FileType.DIRECTORY,file.getAbsolutePath().replace("\\","/"));
            fileStruct.getChildFile().add(getFileList(fileStruct.getFilePath()));
        }else {
            fileStruct = new FileStruct(file.getName(),FileType.FILE,file.getAbsolutePath());
        }
        return  fileStruct;
    }
    //获取文件子目录，json
    public String getChildList(String currentPath){
        //currentPath = "E:\\EBook";
        List<FileStruct> result = new ArrayList();
        if(currentPath != null && !"".equals(currentPath)){
            File file = new File(currentPath);
            if (!file.exists() || file.isFile()){
                return null;
            }
            File[] childFils = file.listFiles();
            for (File f : childFils){
                result.add(new FileStruct(f.getName(),f.isFile()? FileType.FILE:FileType.DIRECTORY,f.getAbsolutePath().replace("\\","/").replace(" ","%20")));
            }
        }
        return Object2JSON.Ojb2JsonStr(result);
    }

    public String getChildList(){
        //currentPath = "E:\\EBook";
        ReadConfigServcie readConfigServcie = new ReadConfigServcie();
        String currentPath = readConfigServcie.getSharePath();
        List<FileStruct> result = new ArrayList();
        if(currentPath != null && !"".equals(currentPath)){
            File file = new File(currentPath);
            if (!file.exists() || file.isFile()){
                return null;
            }
            File[] childFils = file.listFiles();
            for (File f : childFils){
                result.add(new FileStruct(f.getName(),f.isFile()? FileType.FILE:FileType.DIRECTORY,f.getAbsolutePath().replace("\\","/").replace(" ","%20")));
            }
        }
        return Object2JSON.Ojb2JsonStr(result);
    }
    //获取文件子目录，json
    public String getChildList(File file){
        //currentPath = "E:\\EBook";
        List<FileStruct> result = new ArrayList();

        if (!file.exists() || file.isFile()){
            return null;
        }
        File[] childFils = file.listFiles();
        for (File f : childFils){
            result.add(new FileStruct(f.getName(),f.isFile()? FileType.FILE:FileType.DIRECTORY,f.getAbsolutePath().replace("\\","/")));
        }

        return Object2JSON.Ojb2JsonStr(result);
    }

    //递归遍历文件
    public FileStruct getFileList(String dirPath){

        File fileOrDir = new File(dirPath);

        if(fileOrDir.isFile()){
            FileStruct fileStructFile = new FileStruct(fileOrDir.getName(), FileType.FILE,fileOrDir.getTotalSpace(),fileOrDir.getAbsolutePath().replace("\\","/"));
            return fileStructFile;
        }else{
            File[] files = fileOrDir.listFiles();
            FileStruct fileStructDir = new FileStruct(fileOrDir.getName(),FileType.DIRECTORY,fileOrDir.getAbsolutePath().replace("\\","/"));
            for(File file : files){
                fileStructDir.getChildFile().add(getFileList(file.getAbsolutePath()));
            }
            return fileStructDir;
        }
    }



}
