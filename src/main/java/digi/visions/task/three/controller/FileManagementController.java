package digi.visions.task.three.controller;

import digi.visions.task.three.api.FileRequest;
import digi.visions.task.three.api.FolderRequest;
import digi.visions.task.three.api.SpaceRequest;
import digi.visions.task.three.data.entity.FileEntity;
import digi.visions.task.three.data.entity.Item;
import digi.visions.task.three.data.entity.Permission;
import digi.visions.task.three.data.entity.PermissionGroup;
import digi.visions.task.three.data.repository.FileRepository;
import digi.visions.task.three.data.repository.ItemRepository;
import digi.visions.task.three.data.repository.PermissionGroupRepository;
import digi.visions.task.three.data.repository.PermissionRepository;
import digi.visions.task.three.data.service.FileService;
import digi.visions.task.three.data.service.ItemService;
import digi.visions.task.three.data.service.PermissionGroupService;
import digi.visions.task.three.data.service.PermissionService;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.util.Optional;


@RestController
@RequestMapping("/api")
public class FileManagementController {
    @Autowired
    private ItemService itemService;

    @Autowired
    private FileService fileService;

    @Autowired
    private PermissionService permissionService;

    @Autowired
    private PermissionGroupService permissionGroupService;

    public FileManagementController() {




    }

    @PostMapping("/create-space")
    @Transactional
    public ResponseEntity<String> createSpace(@RequestBody SpaceRequest request) {
        // Create a PermissionGroup for the space
        PermissionGroup permissionGroup = new PermissionGroup();
        permissionGroup.setGroupName(request.getName());
        permissionGroupService.save(permissionGroup);

        // Assign permissions to users
        request.getPermissions().forEach(permission -> {
            Permission newPermission = new Permission();
            newPermission.setUserEmail(permission.getUserEmail());
            newPermission.setPermissionLevel(permission.getPermissionLevel());
            newPermission.setPermissionGroup(permissionGroup);
            permissionService.save(newPermission);
        });

        return ResponseEntity.ok("Space created with name: " + request.getName());
    }

    @PostMapping("/create-folder")
    @Transactional
    public ResponseEntity<String> createFolder(@RequestBody FolderRequest request) {
        // Check if the user has EDIT access to the parent space
        Optional<Permission> permission = permissionService.findByUserEmailAndPermissionGroupId(
                request.getUserEmail(), request.getParentSpaceId());

        if (permission.isPresent() && "EDIT".equals(permission.get().getPermissionLevel())) {
            Item folder = new Item();
            folder.setType("FOLDER");
            folder.setName(request.getName());
            folder.setPermissionGroup(permission.get().getPermissionGroup());
            itemService.save(folder);
            return ResponseEntity.ok("Folder created under space: " + request.getParentSpaceId());
        } else {
            return ResponseEntity.status(403).body("Access denied");
        }
    }

    @PostMapping("/create-file")
    @Transactional
    public ResponseEntity<String> createFile(@RequestBody FileRequest request) {
        // Check if the user has EDIT access to the parent space
        Optional<Permission> permission = permissionService.findByUserEmailAndPermissionGroupId(
                request.getUserEmail(), request.getParentSpaceId());

        if (permission.isPresent() && "EDIT".equals(permission.get().getPermissionLevel())) {
            Item fileItem = new Item();
            fileItem.setType("FILE");
            fileItem.setName(request.getFileName());
            fileItem.setPermissionGroup(permission.get().getPermissionGroup());
            itemService.save(fileItem);

            FileEntity fileEntity = new FileEntity();
            fileEntity.setBinary(request.getFileData());
            fileEntity.setItem(fileItem);
            fileService.save(fileEntity);

            return ResponseEntity.ok("File created with name: " + request.getFileName());
        } else {
            return ResponseEntity.status(403).body("Access denied");
        }
    }

    @GetMapping("/view-file-metadata")
    public ResponseEntity<String> viewFileMetadata(@RequestParam Long fileId) {
        Optional<FileEntity> fileEntityOptional = fileService.findById(fileId);

        if (fileEntityOptional.isPresent()) {
            FileEntity fileEntity = fileEntityOptional.get();
            Item item = fileEntity.getItem();
            return ResponseEntity.ok("File name: " + item.getName() + ", Type: " + item.getType());
        } else {
            return ResponseEntity.status(404).body("File not found");
        }
    }

    @GetMapping("/download-file")
    public ResponseEntity<byte[]> downloadFile(@RequestParam Long fileId, @RequestParam String userEmail) {
        Optional<FileEntity> fileEntityOptional = fileService.findById(fileId);

        if (fileEntityOptional.isPresent()) {
            FileEntity fileEntity = fileEntityOptional.get();
            PermissionGroup permissionGroup = fileEntity.getItem().getPermissionGroup();

            Optional<Permission> permission = permissionService.findByUserEmailAndPermissionGroupId(userEmail, permissionGroup.getId());

            if (permission.isPresent() && ("VIEW".equals(permission.get().getPermissionLevel()) || "EDIT".equals(permission.get().getPermissionLevel()))) {
                return ResponseEntity.ok(fileEntity.getBinary());
            } else {
                return ResponseEntity.status(403).body(null);
            }
        } else {
            return ResponseEntity.status(404).body(null);
        }
    }

}

