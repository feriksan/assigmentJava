package com.assigment.assgmentapi.services;

import com.assigment.assgmentapi.exception.FileStorageException;
import com.assigment.assgmentapi.properties.FileStorageProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class FileStorageService {
    private final Path fileUpload;
    
    @Autowired
    public FileStorageService(FileStorageProperties fileStorageProperties){
        Path fileStorageLocation = Paths.get(fileStorageProperties.getUploadDir()).toAbsolutePath().normalize();
        this.fileUpload = Paths.get(fileStorageProperties.getUploadDir());
        try{
            Files.createDirectories(fileStorageLocation);
            Files.createDirectories(this.fileUpload);
        }catch (Exception ex){
            throw new FileStorageException("Could not create the directory where the uploaded files will be stored.", ex);
        }
    }
    
    public String storeFile(MultipartFile file){
        String fileName;
        if(file.getOriginalFilename() != null){
            fileName = StringUtils.cleanPath(file.getOriginalFilename());
        }else{
            throw new FileStorageException("Sorry! Filename contains invalid path sequence");
        }
        try{
            if(fileName.contains("..")){
                throw new FileStorageException("Sorry! Filename contains invalid path sequence " + fileName);
            }
            Path targetLocation = fileUpload.resolve(fileName);
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
            return fileName;
        }catch (IOException ex){
            throw new FileStorageException("Could not store file " + fileName + ". Please try again!", ex);
        }
    }
}
