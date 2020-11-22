package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.FilesMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.sdFile;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class FileService {
    private FilesMapper filesMapper;

    public FileService(FilesMapper filesMapper) {
        this.filesMapper = filesMapper;
    }

    public List<sdFile> getFiles(int userId) {
        return filesMapper.getFiles(userId);
    }

    public void addFile(MultipartFile multipartfile, int userId) throws IOException {
        sdFile file = new sdFile();
        try {
            file.setContentType(multipartfile.getContentType());
            file.setFileData(multipartfile.getBytes());
            file.setFileName(multipartfile.getOriginalFilename());
            file.setFileSize(Long.toString(multipartfile.getSize()));
            file.setUserId(userId);
        } catch (IOException e) {
            throw e;
        }
        filesMapper.insert(file);
    }

    public void deleteFile(int fileId) {
        filesMapper.delete(fileId);
    }
}