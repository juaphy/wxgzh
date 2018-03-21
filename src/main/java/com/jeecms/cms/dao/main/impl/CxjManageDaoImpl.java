package com.jeecms.cms.dao.main.impl;

import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.jeecms.cms.dao.main.CxjDao;
import com.jeecms.cms.dao.main.CxjManageDao;
import com.jeecms.cms.entity.main.TCxjXjfwpd;
import com.jeecms.cms.entity.main.cxj.TCxjMenu;
import com.jeecms.cms.entity.main.cxj.TCxjZwzxconfig;
import com.jeecms.cms.entity.main.cxj.TCxjZxjjckbj;
import com.jeecms.cms.entity.main.cxj.TSysMenu;
import com.jeecms.common.hibernate4.Finder;
import com.jeecms.common.hibernate4.HibernateBaseDao;
import com.jeecms.common.hibernate4.Updater;
import com.jeecms.common.page.Pagination;

@SuppressWarnings("rawtypes")
@Repository
public class CxjManageDaoImpl extends HibernateBaseDao implements CxjManageDao {

    @Override
    public List<TSysMenu> findTSysMenuList(String where) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public TSysMenu findTSysMenu(String menuId) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public TSysMenu saveTSysMenu(TSysMenu tSysMenu) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public TSysMenu updateTSysMenu(TSysMenu tSysMenu) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean deleteTSysMenu(TSysMenu tSysMenu) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public TCxjZxjjckbj findTCxjZxjjckbjByType(String areaId, String type) {
        List<TCxjZxjjckbj> list = findZxjjCkbjList(areaId, type, "1");
        if (list != null && list.size() > 0) {
            return list.get(0);
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<TCxjZxjjckbj> findZxjjCkbjList(String areaId, String type, String status) {
        if (isEmpty(areaId) || isEmpty(type)) {
            return null;
        }
        String sql = "from TCxjZxjjckbj where areaid='" + areaId + "' and type='" + type + "'";
        if (!isEmpty(status)) {
            sql += " and status ='" + status + "'";
        }
        sql += "order by orderid, createTime desc";
        Query query = getSession().createQuery(sql);
        List<TCxjZxjjckbj> list = query.list();
        return list;
    }

    @Override
    public Pagination findZxjjckbjListForPage(String areaId, String type, String status, int pageNo, int pageSize) {
        StringBuffer hql = new StringBuffer();

        hql.append("from TCxjZxjjckbj bean where bean.areaid = '");
        hql.append(areaId);
        hql.append("'");
        hql.append(" and type = '");
        hql.append(type);
        hql.append("'");
        if (!isEmpty(status)) {
            hql.append(" and status = '");
            hql.append(status);
            hql.append("'");
        }
        hql.append(" order by orderid, createTime desc");

        Finder f = Finder.create(hql.toString());
        f.setCacheable(false);
        return find(f, pageNo, pageSize);
    }

    @Override
    public TCxjZxjjckbj findTCxjZxjjckbjInfo(String id) {
        return (TCxjZxjjckbj) getSession().get(TCxjZxjjckbj.class, id);
    }
    @Override
    public void updateTCxjZxjjckbj(TCxjZxjjckbj info) {
        getSession().update(info);
    }

    @Override
    public Pagination findXjfwpdListForPage(String areaId, String type, String status, int pageNo, int pageSize) {
        StringBuffer hql = new StringBuffer();
        
        hql.append("from TCxjXjfwpd bean where bean.areaid = '");
        hql.append(areaId);
        hql.append("'");
        if (!isEmpty(type)) {
            hql.append(" and type = '");
            hql.append(type);
            hql.append("'");
        }
        if (!isEmpty(status)) {
            hql.append(" and status = '");
            hql.append(status);
            hql.append("'");
        }
        hql.append(" order by year desc, month desc, createTime desc");

        Finder f = Finder.create(hql.toString());
        f.setCacheable(false);
        return find(f, pageNo, pageSize);
    }
    
    @Override
    public TCxjXjfwpd findTCxjXjfwpdInfo(String id) {
        return (TCxjXjfwpd) getSession().get(TCxjXjfwpd.class, id);
    }
    @Override
    public void updateTCxjXjfwpd(TCxjXjfwpd info) {
        getSession().update(info);
    }

    @Override
    public void saveTCxjXjfwpd(TCxjXjfwpd info) {
        getSession().save(info);
    }

    @Override
    protected Class getEntityClass() {
        // TODO Auto-generated method stub
        return null;
    }

}