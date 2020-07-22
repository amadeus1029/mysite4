package com.javaex.dao;

import com.javaex.vo.GalleryVo;
import com.javaex.vo.SearchVo;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class GalleryDao {

    @Autowired
    private SqlSession sqlSession;

    public List<GalleryVo> getList(SearchVo searchVo) {
        return sqlSession.selectList("gallery.getList", searchVo);
    }

    public int getCount (SearchVo searchVo) {
        return sqlSession.selectOne("gallery.getCount", searchVo);
    }

    public void add(GalleryVo galleryVo) {
        sqlSession.insert("gallery.insert", galleryVo);
    }

    public GalleryVo getView(GalleryVo galleryVo) {
        return sqlSession.selectOne("gallery.select",galleryVo);
    }

    public int delete(GalleryVo galleryVo) {
        return sqlSession.delete("gallery.delete",galleryVo);
    }

}
