package queen.common.utils;

import queen.common.define.FileType;
import queen.common.entity.FileStruct;

import java.util.List;

public class Object2JSON {

    public static String getJsonFromObj(FileStruct fileStruct){
        String value = Json2Obj(fileStruct);
        //SSvalue.replaceAll("\\\\","");
        System.out.println(value);
        return value;
    }
    //list --> JsonStr
    public static String Ojb2JsonStr(List<FileStruct> list){
        StringBuffer data =  new StringBuffer("{\"options\":[");
        if(!list.isEmpty()){
            for (FileStruct fileStruct : list){
                String fileType = fileStruct.getFileType();
                String filePath = fileStruct.getFilePath();;
                String fileName = fileStruct.getFileName();
                long fileSize = fileStruct.getFileSize();
                data.append("{ \"fileType\":\""+fileType+"\",\"filePath\":\""+filePath+"\",\"fileName\":\""+fileName+"\",\"fileSize\":\""+fileSize+"\",\"childFile\":[]"+"},");
            }
            //add one more ,
            int size = data.length();
            data.deleteCharAt(size-1);
        }
        data.append("]}");
        return data.toString();
    }

    //递归遍历递归对象
    public static String Json2Obj(FileStruct fileStruct){

        //{fileName:"",filePaht:"",fileType:"",childFiles:""}
        //1 如果是file 则获取文件信息
        if (FileType.FILE.equals(fileStruct.getFileType())){
            String fileType = fileStruct.getFileType();
            String filePath = fileStruct.getFilePath();;
            String fileName = fileStruct.getFileName();
            long fileSize = fileStruct.getFileSize();
            String retureValue = "{ \"fileType\":\""+fileType+"\",\"filePath\":\""+filePath+"\",\"fileName\":\""+fileName+"\",\"fileSize\":\""+fileSize+"\",\"childFile\":[]"+"}";
            return retureValue;
        }else {
        //2 如果是dir ,则获取文件信息，递归遍历 ChildFils；
            String fileType = fileStruct.getFileType();
            String filePath = fileStruct.getFilePath();
            String fileName = fileStruct.getFileName();
            long fileSize = fileStruct.getFileSize();

            StringBuffer returnValue = new StringBuffer("{\"fileType\":\""+fileType+"\",\"filePath\":\""+filePath+"\",\"fileName\":\""+fileName+"\",\"fileSize\":\""+fileSize+"\",\"fileChild\":[");

            List<FileStruct> fileStructs = fileStruct.getChildFile();
            int count = fileStruct.getChildFile().size();
            for(int i = 0; i < count ; i++){
                returnValue.append(Json2Obj(fileStructs.get(i)));
                if(i<(count-1)){
                    returnValue.append(",");
                }
            }
//            for (FileStruct fs : fileStruct.getChildFile()){
//                returnValue.append(Json2Obj(fs));
//            }
            returnValue.append("]}");
            return returnValue.toString();
        }
    }

}
