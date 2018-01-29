package queen.common.entity;

import java.util.LinkedList;
import java.util.List;

public class FileStruct {

    private String fileName;

    private String fileType;

    private long fileSize;

    private String filePath;

    private List<FileStruct> childFile = new LinkedList<>();

    public FileStruct() {
    }
    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public FileStruct(String filePath) {
        this.filePath = filePath;
    }

    public FileStruct(String fileName, String fileType, String filePath) {
        this.fileName = fileName;
        this.fileType = fileType;
        this.filePath = filePath;
    }

    public FileStruct(String fileName, String fileType, long fileSize, String filePath) {
        this.fileName = fileName;
        this.fileType = fileType;
        this.fileSize = fileSize;
        this.filePath = filePath;
    }

    public List<FileStruct> getChildFile() {
        return childFile;
    }

    public void setChildFile(List<FileStruct> childFile) {
        this.childFile = childFile;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public long getFileSize() {
        return fileSize;
    }

    public void setFileSize(long fileSize) {
        this.fileSize = fileSize;
    }

    @Override
    public String toString() {
        return "FileStruct{" +
                "fileName='" + fileName + '\'' +
                ", fileType='" + fileType + '\'' +
                ", fileSize=" + fileSize +
                ", filePath='" + filePath + '\'' +
                ", childFile=" + childFile +
                '}';
    }
}
