package com.jeecms.cms.manager.main.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.jeecms.cms.dao.main.WebHallIncInforMationDao;
import com.jeecms.cms.entity.main.wssb.TWebHallIncInforMation;
import com.jeecms.cms.manager.main.WebHallIncInforMationMng;
import com.jeecms.common.hibernate4.Updater;
import com.jeecms.core.entity.CmsUser;

public class WebHallIncInforMationMngImpl implements WebHallIncInforMationMng {

	@Autowired
	private WebHallIncInforMationDao dao;
	
	public TWebHallIncInforMation save(TWebHallIncInforMation incinfo,
			CmsUser user) {
		incinfo.blankToNull();
		incinfo.setUser(user);
		dao.save(incinfo);
		return incinfo;
	}

	public TWebHallIncInforMation update(TWebHallIncInforMation incinfo,
			CmsUser user) {
		
		TWebHallIncInforMation entity = dao.findById(user.getId());
		if (entity == null) {
			entity = save(incinfo, user);
			user.getIncinfoSet().add(entity);
			return entity;
		} else {
			Updater<TWebHallIncInforMation> updater = new Updater<TWebHallIncInforMation>(incinfo);
			//updater.include("userdate");
			incinfo = dao.updateByUpdater(updater);
			incinfo.blankToNull();
			return incinfo;
		}
		
	}

	public TWebHallIncInforMation getById(Integer id) {
		return dao.findById(id);
	}

	public void updateIncInforMation(TWebHallIncInforMation IncInforMation) {
		dao.updateIncInforMation(IncInforMation);
	}
	public void saveOrupdateIncInforMation(TWebHallIncInforMation IncInforMation) {
		dao.saveOrupdateIncInforMation(IncInforMation);
	}

}
