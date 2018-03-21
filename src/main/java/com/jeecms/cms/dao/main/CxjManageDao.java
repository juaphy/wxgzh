package com.jeecms.cms.dao.main;

import java.util.List;
import java.util.Map;

import com.jeecms.cms.entity.main.Channel;
import com.jeecms.cms.entity.main.TCxjXjfwpd;
import com.jeecms.cms.entity.main.cxj.TCxjMenu;
import com.jeecms.cms.entity.main.cxj.TCxjZwzxconfig;
import com.jeecms.cms.entity.main.cxj.TCxjZxjjckbj;
import com.jeecms.cms.entity.main.cxj.TSysMenu;
import com.jeecms.common.hibernate4.Updater;

/**
 * 查询机-后台管理模块DAO
 * @author swc 2018-03-15
 *
 */
public interface CxjManageDao {

    // 系统菜单
    /**
     * 获取系统菜单列表
     * @param where
     * @return
     */
    public List<TSysMenu> findTSysMenuList(String where);

    /**
     * 根据菜单id获取菜单信息
     * @param menuId
     * @return
     */
    public TSysMenu findTSysMenu(String menuId);

    /**
     * 保存系统菜单信息
     * @param tSysMenu
     * @return
     */
    public TSysMenu saveTSysMenu(TSysMenu tSysMenu);

    /**
     * 更新系统菜单信息
     * @param tSysMenu
     * @return
     */
    public TSysMenu updateTSysMenu(TSysMenu tSysMenu);

    /**
     * 
     * @param tSysMenu
     * @return
     */
    public boolean deleteTSysMenu(TSysMenu tSysMenu);

    /**
     * 根据区划id、类型获取中心简介或者窗口布局内容
     * @param areaId
     * @param type
     * @return
     */
    public TCxjZxjjckbj findTCxjZxjjckbjByType(String areaId, String type);

}