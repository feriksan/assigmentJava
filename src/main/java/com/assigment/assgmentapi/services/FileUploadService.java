package com.assigment.assgmentapi.services;

import com.assigment.assgmentapi.payload.UploadFileResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@Service
public class FileUploadService {
    
    @Autowired
    private FileStorageService fileStorageService;
    
    public UploadFileResponse uploadFile(MultipartFile file){
        String fileName = fileStorageService.storeFile(file);
        
        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/dokumen/")
                .path(fileName)
                .toUriString();
        
        return new UploadFileResponse(fileName, fileDownloadUri,
                file.getContentType(), file.getSize());
        
    }
}
