package digi.visions.task.three.api;

import java.util.List;

public class SpaceRequest {
    private String name;
    private List<PermissionRequest> permissions;

    // Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<PermissionRequest> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<PermissionRequest> permissions) {
        this.permissions = permissions;
    }
}
