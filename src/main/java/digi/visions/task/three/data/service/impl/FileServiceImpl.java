package digi.visions.task.three.data.service.impl;

import digi.visions.task.three.data.entity.FileEntity;
import digi.visions.task.three.data.repository.FileRepository;
import digi.visions.task.three.data.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FileServiceImpl implements FileService {

    @Autowired
    private FileRepository fileRepository;

    @Override
    public void save(FileEntity fileEntity) {
        fileRepository.save(fileEntity);
    }

    @Override
    public Optional<FileEntity> findById(Long fileId) {
        return fileRepository.findById(fileId);
    }
}
