package com.jeecms.cms.dao.main;

import com.jeecms.cms.entity.main.wssb.TWebHallUserInforMation;
import com.jeecms.common.hibernate4.Updater;

public interface WebHallUserInforMationDao {
	public TWebHallUserInforMation findById(Integer id);

	public TWebHallUserInforMation save(TWebHallUserInforMation bean);
	
	public TWebHallUserInforMation update(TWebHallUserInforMation bean);

	public TWebHallUserInforMation updateByUpdater(Updater<TWebHallUserInforMation> updater);
	
	public TWebHallUserInforMation saveOrupdate(TWebHallUserInforMation bean);
}