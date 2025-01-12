package digi.visions.task.three.data.repository;

import digi.visions.task.three.data.entity.FileEntity;
import org.springframework.data.jpa.repository.JpaRepository;
public interface FileRepository extends JpaRepository<FileEntity, Long> {
}
