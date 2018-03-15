package com.jeecms.cms.rest2.entity;

import java.io.Serializable;

/**
 * 调用接口返回的部门信息
 * @author swc 2018-03-15
 *
 */
public class DeptInfo implements Serializable{
    private static final long serialVersionUID = -6780813892785671312L;

    private String deptId; // 部门id
    private String name; // 部门名称
    private String shortName; // 部门简称
    private String areaId; // 区划id
    private String orderId; // 排序id
    private String cnum; // 
    private String reservone; // 
    private String permnum; // 事项数
    private String iszsbm; // 是否是直属部门（6：县区直属部门、7：乡镇、8：村居）

    public String getDeptId() {
        return deptId;
    }
    public void setDeptId(String deptId) {
        this.deptId = deptId;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getShortName() {
        return shortName;
    }
    public void setShortName(String shortName) {
        this.shortName = shortName;
    }
    public String getAreaId() {
        return areaId;
    }
    public void setAreaId(String areaId) {
        this.areaId = areaId;
    }
    public String getOrderId() {
        return orderId;
    }
    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }
    public String getCnum() {
        return cnum;
    }
    public void setCnum(String cnum) {
        this.cnum = cnum;
    }
    public String getPermnum() {
        return permnum;
    }
    public void setPermnum(String permnum) {
        this.permnum = permnum;
    }
    public String getIszsbm() {
        return iszsbm;
    }
    public void setIszsbm(String iszsbm) {
        this.iszsbm = iszsbm;
    }
    public String getReservone() {
        return reservone;
    }
    public void setReservone(String reservone) {
        this.reservone = reservone;
    }

}
