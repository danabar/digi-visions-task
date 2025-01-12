package digi.visions.task.three.api;


public class FileRequest {
    private String fileName;
    private byte[] fileData;
    private Long parentSpaceId;
    private String userEmail;

    // Getters and Setters
    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public byte[] getFileData() {
        return fileData;
    }

    public void setFileData(byte[] fileData) {
        this.fileData = fileData;
    }

    public Long getParentSpaceId() {
        return parentSpaceId;
    }

    public void setParentSpaceId(Long parentSpaceId) {
        this.parentSpaceId = parentSpaceId;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }
}