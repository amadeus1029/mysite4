package com.javaex.service;

import com.javaex.dao.GalleryDao;
import com.javaex.vo.GalleryVo;
import com.javaex.vo.SearchVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class GalleryService {

    @Autowired
    private GalleryDao galleryDao;

    public Map<String, Object> getPageInfo(SearchVo searchVo) {
        int pageView = 8; //한 페이지에 표시할 게시물 수
        int pageNum = 10; //화면 하단에 표시할 페이지 최대 갯수
        int currPage = searchVo.getPage() > 0 ? searchVo.getPage() : 1;
        int totalPage = (galleryDao.getCount(searchVo)-1)/pageView + 1;
        int _currPage = (currPage - 1)/pageNum;
        int beginPage = _currPage*pageNum+1;
        int endPage = Math.min(_currPage * pageNum + pageNum, totalPage);

        searchVo.setPageView(pageView);
        List<GalleryVo> galleryList = galleryDao.getList(searchVo);

        Map<String, Object> galleryPaging = new HashMap<String, Object>();

        galleryPaging.put("pageNum", pageNum);
        galleryPaging.put("currPage", currPage);
        galleryPaging.put("totalPage", totalPage);
        galleryPaging.put("beginPage", beginPage);
        galleryPaging.put("endPage", endPage);
        galleryPaging.put("galleryList", galleryList);


        return galleryPaging;
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
