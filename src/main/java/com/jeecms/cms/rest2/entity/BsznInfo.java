package com.jeecms.cms.rest2.entity;

import java.io.Serializable;
import java.util.List;

/**
 * 调用接口返回的办事指南事项详细信息-信息封装
 * @author swc 2018-03-29
 *
 */
public class BsznInfo implements Serializable{
    private static final long serialVersionUID = 1001033922795287831L;

    /**
     * 事项详情
     */
    private PermissionItemInfo permissionItemInfo;

    /**
     * 申请材料列表
     */
    private List<SxSqclInfo> sxSqclList;

    /**
     * 设定依据列表
     */
    private List<SxSdyjInfo> sxSdyjList;

    /**
     * 受理环节列表
     */
    private List<SxSlhjInfo> sxSlhjList;

    /**
     * 特殊环节（封装好）
     */
    private List<SxTshjInfo> sxTshjList;

    /**
     * 获取：事项详情
     */
    public PermissionItemInfo getPermissionItemInfo() {
        return permissionItemInfo;
    }

    /**
     * 设定：事项详情
     */
    public void setPermissionItemInfo(PermissionItemInfo permissionItemInfo) {
        this.permissionItemInfo = permissionItemInfo;
    }

    /**
     * 获取：申请材料列表
     */
    public List<SxSqclInfo> getSxSqclList() {
        return sxSqclList;
    }

    /**
     * 设定：申请材料列表
     */
    public void setSxSqclList(List<SxSqclInfo> sxSqclList) {
        this.sxSqclList = sxSqclList;
    }

    /**
     * 获取：设定依据列表
     */
    public List<SxSdyjInfo> getSxSdyjList() {
        return sxSdyjList;
    }

    /**
     * 设定：设定依据列表
     */
    public void setSxSdyjList(List<SxSdyjInfo> sxSdyjList) {
        this.sxSdyjList = sxSdyjList;
    }

    /**
     * 获取：受理环节列表
     */
    public List<SxSlhjInfo> getSxSlhjList() {
        return sxSlhjList;
    }

    /**
     * 设定：受理环节列表
     */
    public void setSxSlhjList(List<SxSlhjInfo> sxSlhjList) {
        this.sxSlhjList = sxSlhjList;
    }

    /**
     * 获取：特殊环节列表
     */
    public List<SxTshjInfo> getSxTshjList() {
        return sxTshjList;
    }

    /**
     * 设定：特殊环节列表
     */
    public void setSxTshjList(List<SxTshjInfo> sxTshjList) {
        this.sxTshjList = sxTshjList;
    }

}
