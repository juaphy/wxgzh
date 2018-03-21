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
import com.jeecms.common.page.Pagination;

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

    /**
     * 根据区划id、类型获取中心简介或者窗口布局内容列表
     * @param areaId
     * @param type
     * @param status 记录状态（0-无效、1-有效）
     * @return
     */
    public List<TCxjZxjjckbj> findZxjjCkbjList(String areaId, String type, String status);

    /**
     * 根据区划id、类型获取中心简介或者窗口布局内容列表 分页
     * @param areaId
     * @param type
     * @param status 记录状态（0-无效、1-有效）
     * @param pageNo 当前页码
     * @param pageSize 每页显示多少条数据
     * @return
     */
    public Pagination findZxjjckbjListForPage(String areaId, String type, String status, int pageNo, int pageSize);

    /**
     * 显示中心简介或者窗口布局详情
     * @param areaId
     * @param type
     * @return
     */
    public TCxjZxjjckbj findTCxjZxjjckbjInfo(String id);

    /**
     * 更新中心简介/窗口布局详情
     * @param info
     */
    public void updateTCxjZxjjckbj(TCxjZxjjckbj info);


    /**
     * 获取星级服务评定列表 分页
     * @param areaId
     * @param type
     * @param status 记录状态（0-无效、1-有效）
     * @param pageNo 当前页码
     * @param pageSize 每页显示多少条数据
     * @return
     */
    public Pagination findXjfwpdListForPage(String areaId, String type, String status, int pageNo, int pageSize);

    /**
     * 显示星级服务评定详情
     * @param areaId
     * @param type
     * @return
     */
    public TCxjXjfwpd findTCxjXjfwpdInfo(String id);

    /**
     * 更新星级服务评定信息
     * @param info
     */
    public void updateTCxjXjfwpd(TCxjXjfwpd info);

    /**
     * 保存星级服务评定信息
     * @param info
     */
    public void saveTCxjXjfwpd(TCxjXjfwpd info);

}