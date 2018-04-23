package com.jeecms.cms.dao.main.impl;

import org.hibernate.FlushMode;
import org.hibernate.Session;

import com.jeecms.cms.dao.main.WebHallIncInforMationDao;
import com.jeecms.cms.entity.main.wssb.TWebHallIncInforMation;
import com.jeecms.common.hibernate4.HibernateBaseDao;
public class WebHallIncInforMationDaoImpl extends HibernateBaseDao<TWebHallIncInforMation, Integer> implements WebHallIncInforMationDao {

	public TWebHallIncInforMation findById(Integer id) {
		TWebHallIncInforMation entity = get(id);
			return entity;
	}

	public TWebHallIncInforMation save(TWebHallIncInforMation bean) {
		getSession().save(bean);
		return bean;
	}
	protected Class<TWebHallIncInforMation> getEntityClass() {
		return TWebHallIncInforMation.class;
	}

	public void updateIncInforMation(TWebHallIncInforMation IncInforMation) {
		 Session session= this.getSession();
		 session.setFlushMode(FlushMode.AUTO);
	       session.update(IncInforMation);
	       session.flush();
	}
	
	public void saveOrupdateIncInforMation(TWebHallIncInforMation IncInforMation) {
		 Session session= this.getSession();
		 session.setFlushMode(FlushMode.AUTO);
	       session.saveOrUpdate(IncInforMation);
	       session.flush();
	}

}
