package com.jeecms.cms.manager.main.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.jeecms.cms.dao.main.WebHallUserInforMationDao;
import com.jeecms.cms.entity.main.wssb.TWebHallUserInforMation;
import com.jeecms.cms.manager.main.WebHallUserInforMationMng;
import com.jeecms.common.hibernate4.Updater;
import com.jeecms.core.entity.CmsUser;

public class WebHallUserInforMationMngImpl implements WebHallUserInforMationMng {

	@Autowired
	private WebHallUserInforMationDao dao;
	
	public TWebHallUserInforMation save(TWebHallUserInforMation userinfo,
			CmsUser user) {
		userinfo.blankToNull();
		userinfo.setUser(user);
		dao.save(userinfo);
		return userinfo;
	}

	public TWebHallUserInforMation update(TWebHallUserInforMation userinfo,
			CmsUser user) {
		/*userinfo.blankToNull();
		userinfo.setUser(user);
		return dao.update(userinfo);*/
		TWebHallUserInforMation entity = dao.findById(user.getId());
		if (entity == null) {
			entity = save(userinfo, user);
			user.getUserinfoSet().add(entity);
			return entity;
		} else {
			Updater<TWebHallUserInforMation> updater = new Updater<TWebHallUserInforMation>(userinfo);
			updater.include("userdate");
			userinfo = dao.updateByUpdater(updater);
			userinfo.blankToNull();
			return userinfo;
		}
	}

	public TWebHallUserInforMation getById(Integer id) {
		return dao.findById(id);
	}

	public TWebHallUserInforMation update(TWebHallUserInforMation userinfo) {
		return dao.update(userinfo);
	}
	
	public TWebHallUserInforMation saveOrupdate(TWebHallUserInforMation userinfo) {
		return dao.saveOrupdate(userinfo);
	}

}
