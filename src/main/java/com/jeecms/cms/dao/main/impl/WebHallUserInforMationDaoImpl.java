package com.jeecms.cms.dao.main.impl;

import org.hibernate.FlushMode;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.jeecms.cms.dao.main.WebHallUserInforMationDao;
import com.jeecms.cms.entity.main.wssb.TWebHallUserInforMation;
import com.jeecms.common.hibernate4.HibernateBaseDao;

@Repository
public class WebHallUserInforMationDaoImpl extends HibernateBaseDao<TWebHallUserInforMation, Integer> implements WebHallUserInforMationDao {

	public TWebHallUserInforMation findById(Integer id) {
		TWebHallUserInforMation entity = get(id);
			return entity;
	}

	public TWebHallUserInforMation save(TWebHallUserInforMation bean) {
		getSession().save(bean);
		return bean;
	}
	public TWebHallUserInforMation update(TWebHallUserInforMation bean) {
		 Session session= this.getSession();
		 session.setFlushMode(FlushMode.AUTO);
	     session.update(bean);
	     session.flush();
		return bean;
	}
	@Override
	protected Class<TWebHallUserInforMation> getEntityClass() {
		return TWebHallUserInforMation.class;
	}
	
	public TWebHallUserInforMation saveOrupdate(TWebHallUserInforMation bean) {
		 Session session= this.getSession();
		 session.setFlushMode(FlushMode.AUTO);
	     session.saveOrUpdate(bean);
	     session.flush();
		return bean;
	}
}
