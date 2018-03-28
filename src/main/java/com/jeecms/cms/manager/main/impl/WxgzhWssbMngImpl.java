package com.jeecms.cms.manager.main.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeecms.cms.dao.main.WxgzhWssbDao;
import com.jeecms.cms.entity.main.cxj.TCxjZwzxconfig;
import com.jeecms.cms.manager.main.WxgzhWssbMng;

@Service
@Transactional
public class WxgzhWssbMngImpl implements WxgzhWssbMng {

	private WxgzhWssbDao dao;
	@Autowired
	public void setDao(WxgzhWssbDao dao) {
	    this.dao = dao;
	}

    @Override
    public TCxjZwzxconfig findZwzxconfig() {
        return dao.findZwzxconfig();
    }

    @Override
    public TCxjZwzxconfig findZwzxconfig(String areaId) {
        return dao.findZwzxconfig(areaId);
    }

    @Override
    public TCxjZwzxconfig findZwzxconfigById(String id) {
        return dao.findZwzxconfigById(id);
    }

    @Override
    public void updateBackgroundImg(TCxjZwzxconfig info) {
        dao.updateBackgroundImg(info);
    }

}