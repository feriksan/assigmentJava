package com.assigment.assgmentapi.services;

import com.assigment.assgmentapi.models.BannersEntity;
import com.assigment.assgmentapi.repositories.BannerRepositories;
import org.apache.tomcat.util.http.fileupload.FileUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class BannerService {
    
    @Autowired
    BannerRepositories bannerRepositories;
    @Autowired
    FileUploadService fileUploadService;
    
    public List<BannersEntity> getAll(){
        return bannerRepositories.getAllBanner();
    }
    public String insertBanner(BannersEntity bannersEntity){
        return bannerRepositories.insertBanner(bannersEntity);
    }
    public int uploadBannerImage(int banners, MultipartFile file){
        fileUploadService.uploadFile(file);
        return bannerRepositories.updateBannerPict(banners, file.getOriginalFilename());
    }

    public BannersEntity getBanner(int bannerId){
        return bannerRepositories.getBanner(bannerId);
    }
}
