package com.jeecms.cms.dao.main.impl;
 
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;

import com.jeecms.cms.dao.main.DsfxtConfigService;
import com.jeecms.cms.entity.main.wssb.TSysDsfxtConfig;
import com.jeecms.common.hibernate4.HibernateBaseDao;

@SuppressWarnings("rawtypes")
public class DsfxtConfigServiceImpl extends HibernateBaseDao implements DsfxtConfigService {

    @SuppressWarnings("unchecked")
    @Override
    public List<TSysDsfxtConfig> getDsfxtConfigList() {
        String hql=" from TSysDsfxtConfig where status='1' ";
        Query query = getSession().createQuery(hql);
        List<TSysDsfxtConfig> list = query.list();
        if(list.size()==0){
            list=new ArrayList<TSysDsfxtConfig>();
        }
        return list;
    }

    @Override
    protected Class getEntityClass() {
        return TSysDsfxtConfig.class;
    }

}
