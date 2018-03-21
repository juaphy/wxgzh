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
import com.jeecms.common.hibernate4.HibernateBaseDao;
import com.jeecms.common.hibernate4.Updater;

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
        if (isEmpty(areaId) || isEmpty(type)) {
            return null;
        }
        String sql = "from TCxjZxjjckbj where status='1' and areaid='" + areaId + "' and type='" + type + "'";
        sql += "order by orderid, createTime desc";
        Query query = getSession().createQuery(sql);
        List<TCxjZxjjckbj> list = query.list();
        if (list != null && list.size() > 0) {
            return list.get(0);
        }
        return null;
    }

    @Override
    protected Class getEntityClass() {
        // TODO Auto-generated method stub
        return null;
    }

}