package com.kotabek.services.impl;

import com.kotabek.repositories.AttachmentDao;
import com.kotabek.services.StorageService;
import com.kotabek.to.AttachmentFilterTO;
import com.kotabek.to.AttachmentTO;
import com.kotabek.utils.IdGenerator;
import com.kotabek.utils.PathUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

/**
 * Created by kotabek on 6/1/17.
 */
@Service
public class StorageServiceImpl implements StorageService {
    @Autowired
    private AttachmentDao attachmentDao;

    @Override
    public List<AttachmentTO> loadAll() {
        return attachmentDao.getAll();
    }

    @Override
    public AttachmentTO getFileById(Long id) {
        return attachmentDao.getById(id);
    }

    @Override
    public Resource getResourceByPath(String path) {
        return new FileSystemResource(new File(path));
    }

    @Override
    public void saveFile(MultipartFile file) throws IOException {
        AttachmentTO to = new AttachmentTO();
        to.setName(file.getOriginalFilename());
        to.setSize(file.getSize());
        to.setMime(file.getContentType());

        System.out.println("Start save process: " + to.getName());
        to.setPath(saveToFS(file, new IdGenerator().nextId()));
        System.out.println("file was saved: " + to.getName());
        attachmentDao.add(to);
        System.out.println("Uploaded2: " + to.getName());
    }

    @Override
    public List<AttachmentTO> search(AttachmentFilterTO filterTO) {
        if (filterTO == null
            || filterTO.isEmpty()) {
            return this.attachmentDao.getAll();
        }
        return this.attachmentDao.search(filterTO);
    }

    private String saveToFS(MultipartFile file, String key) throws IOException {
        final String path = PathUtils.getRootPath() + key;
        FileOutputStream fos = new FileOutputStream(new File(path));
        fos.write(file.getBytes());
        fos.flush();
        fos.close();
        return path;
    }
}
