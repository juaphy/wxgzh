package com.jeecms.cms.manager.main;

import com.jeecms.cms.entity.main.wssb.TWebHallIncInforMation;
import com.jeecms.core.entity.CmsUser;
public interface WebHallIncInforMationMng {
	public TWebHallIncInforMation save(TWebHallIncInforMation incinfo, CmsUser user);

	public TWebHallIncInforMation update(TWebHallIncInforMation incinfo, CmsUser user);

	public TWebHallIncInforMation getById(Integer id);
	
	public void updateIncInforMation(TWebHallIncInforMation IncInforMation);
	
	public void saveOrupdateIncInforMation(TWebHallIncInforMation IncInforMation) ;
}