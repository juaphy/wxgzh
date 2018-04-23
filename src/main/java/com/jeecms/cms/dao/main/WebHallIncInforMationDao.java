package com.jeecms.cms.dao.main;

import com.jeecms.cms.entity.main.wssb.TWebHallIncInforMation;
import com.jeecms.common.hibernate4.Updater;

public interface WebHallIncInforMationDao {
	public TWebHallIncInforMation findById(Integer id);

	public TWebHallIncInforMation save(TWebHallIncInforMation bean);

	public TWebHallIncInforMation updateByUpdater(Updater<TWebHallIncInforMation> updater);
	
	public void updateIncInforMation(TWebHallIncInforMation IncInforMation);
	
	public void saveOrupdateIncInforMation(TWebHallIncInforMation IncInforMation);
}