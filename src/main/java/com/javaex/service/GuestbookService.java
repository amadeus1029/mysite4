package com.javaex.service;


import com.javaex.dao.GuestbookDao;
import com.javaex.vo.GuestbookVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GuestbookService {

    @Autowired
    private GuestbookDao guestbookDao;

    public List<GuestbookVo> getList() {
        return guestbookDao.getList();
    }

    public int write(GuestbookVo guestbookVo) {
        return guestbookDao.insert(guestbookVo);
    }

    public GuestbookVo addGuest(GuestbookVo guestbookVo) {
        guestbookDao.insertSelectKey(guestbookVo);
        int no = guestbookVo.getNo();
        return guestbookDao.selectByNo(no);
    }

    public int delete(GuestbookVo guestbookVo) {
        return guestbookDao.delete(guestbookVo);
    }
}
