package digi.visions.task.three.data.service.impl;

import digi.visions.task.three.data.entity.PermissionGroup;
import digi.visions.task.three.data.repository.PermissionGroupRepository;
import digi.visions.task.three.data.repository.PermissionRepository;
import digi.visions.task.three.data.service.PermissionGroupService;
import digi.visions.task.three.data.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PermissionGroupServiceImpl implements PermissionGroupService {

    @Autowired
    private PermissionGroupRepository permissionGroupRepository;

    @Override
    public void save(PermissionGroup permissionGroup) {
        permissionGroupRepository.save(permissionGroup);
    }
}
