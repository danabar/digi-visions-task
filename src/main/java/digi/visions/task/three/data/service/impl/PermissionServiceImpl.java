package digi.visions.task.three.data.service.impl;

import digi.visions.task.three.data.entity.Permission;
import digi.visions.task.three.data.repository.PermissionGroupRepository;
import digi.visions.task.three.data.service.PermissionGroupService;
import digi.visions.task.three.data.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class PermissionServiceImpl implements PermissionService {

    @Autowired
    PermissionService permissionService;

    @Override
    public void save(Permission newPermission) {
        permissionService.save(newPermission);
    }

    @Override
    public Optional<Permission> findByUserEmailAndPermissionGroupId(String userEmail, Long parentSpaceId) {
        return Optional.empty();
    }
}
