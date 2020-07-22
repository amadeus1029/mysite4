package com.javaex.service;

import com.javaex.dao.GalleryDao;
import com.javaex.vo.GalleryVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.UUID;

@Service
public class GalleryService {

    @Autowired
    private GalleryDao galleryDao;

    public List<GalleryVo> getList() {
        return galleryDao.getList();
    }

    public void add(MultipartFile file, GalleryVo galleryVo) {

        //파일 카피
        String saveDir = "/Users/apolion/work/upload";

        //파일 이름
        String orgName = file.getOriginalFilename();

        //파일 확장자
        String exName = orgName.substring(orgName.lastIndexOf("."));

        //저장파일 이름
        String saveName = System.currentTimeMillis() + UUID.randomUUID().toString() + exName;

        //파일경로
        String filePath = saveDir + "/" + saveName;

        //파일사이즈
        long fileSize = file.getSize();

        //파일 서버에 복사
        try {
            byte[] fileData = file.getBytes();
            OutputStream out = new FileOutputStream(filePath);
            BufferedOutputStream bufferedOut = new BufferedOutputStream(out);

            bufferedOut.write(fileData);
            bufferedOut.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        galleryVo.setFilePath(filePath);
        galleryVo.setFileSize(fileSize);
        galleryVo.setOrgName(orgName);
        galleryVo.setSaveName(saveName);

        galleryDao.add(galleryVo);

    }

    public GalleryVo getView(GalleryVo galleryVo) {
        return galleryDao.getView(galleryVo);
    }

    public boolean delete(GalleryVo galleryVo) {
        int count = galleryDao.delete(galleryVo);
        return (count == 1);
    }
}
