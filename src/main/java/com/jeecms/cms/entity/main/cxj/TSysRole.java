package com.jeecms.cms.entity.main.cxj;

import java.io.Serializable;

/**
 * 查询机-系统角色表
 * @author swc 2018-03-20
 *
 */
public class TSysRole implements Serializable {
    private static final long serialVersionUID = 7828071576088085423L;
    private String id; // 编号
    private String roleid; // 角色编号
    private String menuid; // 菜单编号
    private String powerid; // 权限编号
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getRoleid() {
        return roleid;
    }
    public void setRoleid(String roleid) {
        this.roleid = roleid;
    }
    public String getMenuid() {
        return menuid;
    }
    public void setMenuid(String menuid) {
        this.menuid = menuid;
    }
    public String getPowerid() {
        return powerid;
    }
    public void setPowerid(String powerid) {
        this.powerid = powerid;
    }

}