package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.services.FileService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller()

public class FilesController {
    private FileService fileService;
    private UserService userService;

    public FilesController(FileService fileService, UserService userService) {
        this.userService = userService;
        this.fileService = fileService;
    }

    @GetMapping("/files")
    public String getFiles(Authentication authentication) {
        return "files";
    }

    @GetMapping("/files/delete")
    public String deleteFile(@RequestParam("id") int fileid) {
        fileService.deleteFile(fileid);
        return "redirect:/result?delfilesuccess";
    }

    @PostMapping("/files")
    public String postFiles(MultipartFile fileUpload, Authentication authentication) throws IOException {
        int userId = userService.getUserId(authentication.getName());
        if (fileUpload.isEmpty()) {
            return "redirect:/result?errornofileselected";
        } else {
            fileService.addFile(fileUpload, userId);
            return "redirect:/result?addfilesuccess";
        }
    }
}

