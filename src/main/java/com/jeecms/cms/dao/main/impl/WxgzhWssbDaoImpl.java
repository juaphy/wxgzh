package com.jeecms.cms.dao.main.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.jeecms.cms.dao.main.WxgzhWssbDao;
import com.jeecms.cms.entity.main.cxj.TCxjZwzxconfig;
import com.jeecms.common.hibernate4.HibernateBaseDao;

@SuppressWarnings("rawtypes")
@Repository
public class WxgzhWssbDaoImpl extends HibernateBaseDao implements WxgzhWssbDao {

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

    @Override
    public TCxjZwzxconfig findZwzxconfigById(String id) {
        return (TCxjZwzxconfig) getSession().get(TCxjZwzxconfig.class, id);
    }

    @Override
    public void updateBackgroundImg(TCxjZwzxconfig info) {
        getSession().update(info);
    }

    @Override
    protected Class getEntityClass() {
        // TODO Auto-generated method stub
        return null;
    }

}