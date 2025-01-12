package digi.visions.task.three.data.service;

import digi.visions.task.three.data.entity.Permission;
import digi.visions.task.three.data.repository.PermissionRepository;

import java.util.Optional;

public interface PermissionService {
    void save(Permission newPermission);

    Optional<Permission> findByUserEmailAndPermissionGroupId(String userEmail, Long parentSpaceId);
}
