package com.jeecms.cms.manager.main.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeecms.cms.dao.main.CxjManageDao;
import com.jeecms.cms.entity.main.TCxjXjfwpd;
import com.jeecms.cms.entity.main.cxj.TCxjZxjjckbj;
import com.jeecms.cms.entity.main.cxj.TSysMenu;
import com.jeecms.cms.manager.main.CxjManageMng;
import com.jeecms.common.page.Pagination;

@Service
@Transactional
public class CxjManageMngImpl implements CxjManageMng {

    private CxjManageDao dao;
    @Autowired
    public void setDao(CxjManageDao dao) {
        this.dao = dao;
    }

    @Override
    public List<TSysMenu> findTSysMenuList(String where) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public TSysMenu findTSysMenu(String menuId) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public TSysMenu saveTSysMenu(TSysMenu tSysMenu) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public TSysMenu updateTSysMenu(TSysMenu tSysMenu) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean deleteTSysMenu(TSysMenu tSysMenu) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public TCxjZxjjckbj findTCxjZxjjckbjByType(String areaId, String type) {
        return dao.findTCxjZxjjckbjByType(areaId, type);
    }

    @Override
    public List<TCxjZxjjckbj> findZxjjCkbjList(String areaId, String type, String status) {
        return dao.findZxjjCkbjList(areaId, type, status);
    }

    @Override
    public Pagination findZxjjckbjListForPage(String areaId, String type, String status, int pageNo, int pageSize) {
        return dao.findZxjjckbjListForPage(areaId, type, status, pageNo, pageSize);
    }

    @Override
    public TCxjZxjjckbj findTCxjZxjjckbjInfo(String id) {
        return dao.findTCxjZxjjckbjInfo(id);
    }
    @Override
    public void updateTCxjZxjjckbj(TCxjZxjjckbj info) {
        dao.updateTCxjZxjjckbj(info);
    }

    @Override
    public TCxjXjfwpd findTCxjXjfwpdInfo(String id) {
        return dao.findTCxjXjfwpdInfo(id);
    }

    @Override
    public Pagination findXjfwpdListForPage(String areaId, String type, String status, int pageNo, int pageSize) {
        return dao.findXjfwpdListForPage(areaId, type, status, pageNo, pageSize);
    }

    @Override
    public void updateTCxjXjfwpd(TCxjXjfwpd info) {
        dao.updateTCxjXjfwpd(info);
    }

    @Override
    public void saveTCxjXjfwpd(TCxjXjfwpd info) {
        dao.saveTCxjXjfwpd(info);
    }

}