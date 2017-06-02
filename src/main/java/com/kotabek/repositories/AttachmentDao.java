package com.kotabek.repositories;

import com.kotabek.to.AttachmentFilterTO;
import com.kotabek.to.AttachmentTO;

import java.util.List;

/**
 * Created by kotabek on 6/1/17.
 */
public interface AttachmentDao {
    int add(AttachmentTO to);

    AttachmentTO getByName(String name);

    AttachmentTO getById(Long id);

    List<AttachmentTO> getAll();

    List<AttachmentTO> search(AttachmentFilterTO filterTO);
}
