package com.javaex.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.UUID;

@Service
public class FileUploadService {


    public String restore(MultipartFile file) {
        //파일 카피
        String saveDir = "/Users/apolion/work/upload";

        //파일 정보 추출해서 DB에 저장

        //파일 이름
        String originName = file.getOriginalFilename();

        //파일 확장자
        String exName = originName.substring(originName.lastIndexOf("."));

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

        return saveName;
    }
}
