package com.jeecms.cms.manager.main;

import java.util.List;

import com.jeecms.cms.rest2.entity.BusinessInfo;
import com.jeecms.cms.rest2.entity.DeptInfo;
import com.jeecms.cms.rest2.entity.SxInfo;

/**
 * 调用网厅接口
 * 
 */
public interface CallInterfaceMng {

    /**
     * 根据区划id获取部门列表（调用接口）
     * @param areaId
     * @param isZsbm 是否直属部门：6区县级 、7乡镇级、8村居
     * @return
     */
    public List<DeptInfo> findDeptListByAreaid(String areaId, String isZsbm);

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

}