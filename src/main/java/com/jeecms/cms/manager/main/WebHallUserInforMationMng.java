package com.jeecms.cms.manager.main;

import com.jeecms.cms.entity.main.wssb.TWebHallUserInforMation;
import com.jeecms.core.entity.CmsUser;

public interface WebHallUserInforMationMng {
	public TWebHallUserInforMation save(TWebHallUserInforMation userinfo, CmsUser user);

	public TWebHallUserInforMation update(TWebHallUserInforMation userinfo, CmsUser user);

	public TWebHallUserInforMation getById(Integer id);
	
	public TWebHallUserInforMation update(TWebHallUserInforMation userinfo);
	
	public TWebHallUserInforMation saveOrupdate(TWebHallUserInforMation userinfo);
}