package com.kotabek.controllers;

import com.kotabek.services.StorageService;
import com.kotabek.to.AttachmentFilterTO;
import com.kotabek.to.AttachmentTO;
import com.kotabek.utils.DG;
import com.kotabek.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.util.List;

/**
 * Created by kotabek on 6/1/17.
 */
@Controller
public class FileUploadController {
    @Autowired
    private StorageService storageService;

    @GetMapping("/files")
    public String listUploadedFiles(Model model,
                                    @RequestParam(value = "name", required = false) String name,
                                    @RequestParam(value = "minSize", required = false) String minSize,
                                    @RequestParam(value = "maxSize", required = false) String maxSize) throws IOException {
        AttachmentFilterTO filterTO = new AttachmentFilterTO();
        if (StringUtils.isNotEmpty(name)) {
            filterTO.setName(name);
        }
        if (StringUtils.isNotEmpty(minSize)) {
            filterTO.setMinSize(DG.getLong(minSize));
        }
        if (StringUtils.isNotEmpty(maxSize)) {
            filterTO.setMaxSize(DG.getLong(maxSize));
        }
        List<AttachmentTO> files = null;
        if (filterTO.isEmpty()) {
            files = storageService.loadAll();
        } else {
            files = storageService.search(filterTO);
        }
        model.addAttribute("files", files);
        model.addAttribute("filter", filterTO);

        return "uploadForm";
    }

    @GetMapping("/api/file/stream/{fileId}")
    @ResponseBody
    public ResponseEntity<Resource> getFile(
            @PathVariable String fileId) {
        long id = DG.getLong(fileId);
        AttachmentTO file = storageService.getFileById(id);
        if (file != null && StringUtils.isNotEmpty(file.getPath())) {
            Resource resource = storageService.getResourceByPath(file.getPath());
            if (resource != null) {
                return ResponseEntity
                        .ok()
                        .header(HttpHeaders.CONTENT_TYPE, file.getMime())
                        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getName() + "\"")
                        .body(resource);
            }
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/api/file/meta/{fileId}")
    @ResponseBody
    public ResponseEntity<AttachmentTO> getFileMeta(
            @PathVariable String fileId) {
        long id = DG.getLong(fileId);
        AttachmentTO file = storageService.getFileById(id);

        if (file == null) {
            file = new AttachmentTO();
        } else {
            file.setPath(null);
        }
        return ResponseEntity
                .ok()
                .body(file);
    }

    @PostMapping("/")
    public String handleFileUpload(
            @RequestParam("file") MultipartFile file,
            RedirectAttributes redirectAttributes) {

        try {
            storageService.saveFile(file);
            redirectAttributes.addFlashAttribute("message",
                                                 "You successfully uploaded " + file.getOriginalFilename() + "!");
        } catch (Exception ex) {
            redirectAttributes.addFlashAttribute("message",
                                                 "Error " + ex.getMessage() + "!");
        }

        return "redirect:/files";
    }
}
