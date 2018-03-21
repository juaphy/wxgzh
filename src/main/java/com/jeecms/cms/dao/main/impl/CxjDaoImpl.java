package com.jeecms.cms.dao.main.impl;

import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.jeecms.cms.dao.main.CxjDao;
import com.jeecms.cms.entity.main.TCxjXjfwpd;
import com.jeecms.cms.entity.main.cxj.TCxjMenu;
import com.jeecms.cms.entity.main.cxj.TCxjZwzxconfig;
import com.jeecms.cms.entity.main.cxj.TCxjZxjjckbj;
import com.jeecms.common.hibernate4.HibernateBaseDao;
import com.jeecms.common.hibernate4.Updater;

@Repository
public class CxjDaoImpl extends HibernateBaseDao implements CxjDao {

    @SuppressWarnings("unchecked")
    @Override
    public List<TCxjXjfwpd> findXjfwpdList(Map<String, String> params) {
        if (params != null && !params.isEmpty()) {
            
        }
        String sql = "from TCxjXjfwpd where 1=1";
        Query query = getSession().createQuery(sql);
        return query.list();
    }

    @Override
    public TCxjXjfwpd findTCxjXjfwpdById(String id) {
        TCxjXjfwpd entity = (TCxjXjfwpd) getSession().get(TCxjXjfwpd.class, id);
        return entity;
    }

    @Override
    public TCxjXjfwpd saveTCxjXjfwpd(TCxjXjfwpd bean) {
        getSession().save(bean);
        return bean;
    }

    @Override
    public TCxjXjfwpd deleteTCxjXjfwpdById(String id) {
        TCxjXjfwpd entity = (TCxjXjfwpd) getSession().get(TCxjXjfwpd.class, id);
        if (entity != null) {
            getSession().delete(entity);
        }
        return entity;
    }

    @Override
    public TCxjXjfwpd updateTCxjXjfwpd(Updater<TCxjXjfwpd> updater) {
        // TODO Auto-generated method stub
        return null;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<TCxjMenu> findCxjMenuList(String areaId) {
        String sql = "from TCxjMenu where status='1'";
        sql += " and areaid='" + areaId + "'";
        sql += " order by ordernum";
        Query query = getSession().createQuery(sql);
        return query.list();
    }

    @Override
    public TCxjMenu findCxjMenu(String id) {
        return (TCxjMenu) getSession().get(TCxjMenu.class, id);
    }

    @SuppressWarnings("unchecked")
    @Override
    public TCxjZwzxconfig findZwzxconfig() {
        String sql = "from TCxjZwzxconfig where status='1'";
        sql += " order by createtime desc";
        Query query = getSession().createQuery(sql);
        List<TCxjZwzxconfig> list = query.list();
        if (list == null || list.size() <= 0) {
            return null;
        }
        return list.get(0);
    }

    @SuppressWarnings("unchecked")
    @Override
    public TCxjZwzxconfig findZwzxconfig(String areaId) {
        String sql = "from TCxjZwzxconfig where status='1'";
        sql += " and areaid='" + areaId + "'";
        sql += " order by createtime desc";
        Query query = getSession().createQuery(sql);
        List<TCxjZwzxconfig> list = query.list();
        if (list == null || list.size() <= 0) {
            return null;
        }
        return list.get(0);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<TCxjZxjjckbj> findTCxjZxjjckbjList(String areaId) {
        String sql = "from TCxjZxjjckbj where status='1'";
        sql += " and areaid='" + areaId + "'";
        sql += " order by orderid, createtime desc";
        Query query = getSession().createQuery(sql);
        return query.list();
    }

    @SuppressWarnings("unchecked")
    @Override
    public TCxjZxjjckbj findTCxjZxjjckbj(String areaId, String type) {
        String sql = "from TCxjZxjjckbj where status='1'";
        sql += " and areaid='" + areaId + "'";
        sql += " and type='" + type + "'";
        sql += " order by orderid, createtime desc";
        Query query = getSession().createQuery(sql);
        List<TCxjZxjjckbj> list = query.list();
        if (list == null || list.size() <= 0) {
            return null;
        }
        return list.get(0);
    }

    @Override
    public TCxjZxjjckbj saveTCxjZxjjckbj(TCxjZxjjckbj tCxjZxjjckbj) {
        getSession().save(tCxjZxjjckbj);;
        return tCxjZxjjckbj;
    }

    @Override
    public TCxjZxjjckbj updateTCxjZxjjckbj(TCxjZxjjckbj tCxjZxjjckbj) {
        getSession().update(tCxjZxjjckbj);
        return tCxjZxjjckbj;
    }

    @Override
    public boolean deleteTCxjZxjjckbj(String id) {
        getSession().delete(getSession().get(TCxjZxjjckbj.class, id));
        return false;
    }

    @Override
    protected Class getEntityClass() {
        // TODO Auto-generated method stub
        return null;
    }

}