package com.kotabek.services;

import com.kotabek.to.AttachmentFilterTO;
import com.kotabek.to.AttachmentTO;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * Created by kotabek on 6/1/17.
 */
public interface StorageService {
    List<AttachmentTO> loadAll();

    AttachmentTO getFileById(Long id);

    Resource getResourceByPath(String path);

    void saveFile(MultipartFile file) throws IOException;

    List<AttachmentTO> search(AttachmentFilterTO filterTO);
}
