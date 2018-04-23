package com.jeecms.cms.rest2.entity;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 调用接口返回的办事指南事项-申请材料信息
 * @author swc 2018-03-29
 *
 */
public class SxSqclInfo implements Serializable {
    private static final long serialVersionUID = 5581473959168602016L;
    private String clmc; // 材料名称
    private String detailrequer; // 材料要求说明
    private String bz; // 法定依据及描述
    private String emptyid; // 空本文件ID
    private String modelid; // 范本文件ID
    private String clly; // 材料来源
    private String describe; // 描述

    public String getClmc() {
        return clmc;
    }
    public void setClmc(String clmc) {
        this.clmc = clmc;
    }
    public String getDetailrequer() {
        return detailrequer;
    }
    public void setDetailrequer(String detailrequer) {
        this.detailrequer = detailrequer;
    }
    public String getBz() {
        return bz;
    }
    public void setBz(String bz) {
        this.bz = bz;
    }
    public String getEmptyid() {
        return emptyid;
    }
    public void setEmptyid(String emptyid) {
        this.emptyid = emptyid;
    }
    public String getModelid() {
        return modelid;
    }
    public void setModelid(String modelid) {
        this.modelid = modelid;
    }
    public String getClly() {
        return clly;
    }
    public void setClly(String clly) {
        this.clly = clly;
    }
    public String getDescribe() {
        return describe;
    }
    public void setDescribe(String describe) {
        this.describe = describe;
    }

    @SuppressWarnings("unchecked")
    public static void main(String[] args) {
        SxSqclInfo obj = new SxSqclInfo();
        Class cl = SxSqclInfo.class;
        Field[] fields = cl.getDeclaredFields();
        Method[] m = cl.getDeclaredMethods();
        Method mm = null;
        Field filed = null;
        for (int i=0; i < fields.length; i++) {
            filed = fields[i];
            String paramName = filed.getName();
            // String methodName = "set" + paramName.substring(0,1).toUpperCase() + paramName.substring(1, paramName.length());
            if ("serialVersionUID".equals(paramName)) {
                continue;
            }
            try {
                filed.set(obj, "3");
                //Method method = cl.getMethod(methodName, String.class);
                //method.invoke("2");
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

}