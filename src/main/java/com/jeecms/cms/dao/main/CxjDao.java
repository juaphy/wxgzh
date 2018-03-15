package com.jeecms.cms.dao.main;

import java.util.List;
import java.util.Map;

import com.jeecms.cms.entity.main.Channel;
import com.jeecms.cms.entity.main.TCxjXjfwpd;
import com.jeecms.common.hibernate4.Updater;

/**
 * 查询机DAO
 * @author swc 2018-03-15
 *
 */
public interface CxjDao {

    /**
     * 获取星级服务评定 列表
     * @param params
     * @return
     */
    public List<TCxjXjfwpd> findXjfwpdList(Map<String, String> params);

    public TCxjXjfwpd findTCxjXjfwpdById(String id);

    public TCxjXjfwpd saveTCxjXjfwpd(TCxjXjfwpd bean);

    public TCxjXjfwpd updateTCxjXjfwpd(Updater<TCxjXjfwpd> updater);

    public TCxjXjfwpd deleteTCxjXjfwpdById(String id);

}