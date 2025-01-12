package digi.visions.task.three.data.repository;

import digi.visions.task.three.data.entity.Permission;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PermissionRepository extends JpaRepository<Permission, Long> {
    Optional<Permission> findByUserEmailAndPermissionGroupId(String userEmail, Long groupId);
}