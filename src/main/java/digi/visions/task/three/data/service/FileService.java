package digi.visions.task.three.data.service;

import digi.visions.task.three.data.entity.FileEntity;

import java.util.Optional;

public interface FileService {

    void save(FileEntity fileEntity);

    Optional<FileEntity> findById(Long fileId);
}
