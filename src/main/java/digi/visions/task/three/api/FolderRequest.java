package digi.visions.task.three.api;


public class FolderRequest {
    private String name;
    private Long parentSpaceId;
    private String userEmail;

    // Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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