package com.jeecms.cms.manager.main;

import java.util.List;
import java.util.Map;

import com.jeecms.cms.entity.main.TCxjXjfwpd;
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

}