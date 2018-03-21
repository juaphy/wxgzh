package com.jeecms.cms.manager.main;

import java.util.List;
import java.util.Map;

import com.jeecms.cms.entity.main.TCxjXjfwpd;
import com.jeecms.cms.entity.main.cxj.TCxjMenu;
import com.jeecms.cms.entity.main.cxj.TCxjZwzxconfig;
import com.jeecms.cms.entity.main.cxj.TCxjZxjjckbj;
import com.jeecms.cms.rest2.entity.BusinessInfo;
import com.jeecms.cms.rest2.entity.DeptInfo;
import com.jeecms.cms.rest2.entity.SxInfo;
import com.jeecms.common.hibernate4.Updater;

/**
 * 查询机管理接口
 * 
 */
public interface CxjMng {

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

    // 调用网厅接口
    /**
     * 根据区划id获取部门列表（调用接口）
     * @param areaId
     * @return
     */
    public List<DeptInfo> findDeptListByAreaid(String areaId);

    /**
     * 根据部门id获取事项列表（调接口）
     * @param deptid
     * @return
     */
    public List<SxInfo> findSxlbByDeptid(String deptid);

    /**
     * 根据业务id获取业务相关信息（调接口）
     * @param bsnum
     * @return
     */
    public BusinessInfo findBusiInfo(String bsnum);

    // 本地service
    /**
     * 获取查询机配置信息
     * @param areaId
     * @return 只取最新添加的查询机配置信息
     */
    public TCxjZwzxconfig findZwzxconfig();

    /**
     * 根据区划id获取查询机配置信息
     * @param areaId
     * @return
     */
    public TCxjZwzxconfig findZwzxconfig(String areaId);

    /**
     * 根据区划id获取该区划的菜单
     * @param areaId
     * @return
     */
    public List<TCxjMenu> findCxjMenuList(String areaId);

    /**
     * 根据id获取查询机专用菜单信息
     * @param id
     * @return
     */
    public TCxjMenu findCxjMenu(String id);

    // 中心简介/窗口布局
    /**
     * 获取中心简介/窗口布局列表
     * @return
     */
    public List<TCxjZxjjckbj> findTCxjZxjjckbjList(String areaId);

    /**
     * 获取中心简介/窗口布局
     * @param areaId
     * @param type
     * @return
     */
    public TCxjZxjjckbj findTCxjZxjjckbj(String areaId, String type);

    /**
     * 保存中心简介/窗口布局信息
     * @param tCxjZxjjckbj
     * @return
     */
    public TCxjZxjjckbj saveTCxjZxjjckbj(TCxjZxjjckbj tCxjZxjjckbj);

    /**
     * 更新中心简介/窗口布局信息
     * @param tCxjZxjjckbj
     * @return
     */
    public TCxjZxjjckbj updateTCxjZxjjckbj(TCxjZxjjckbj tCxjZxjjckbj);

    /**
     * 删除中心简介/窗口布局信息
     * @param id
     * @return
     */
    public boolean deleteTCxjZxjjckbj(String id);

}