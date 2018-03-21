package com.jeecms.cms.entity.main.cxj;

import java.io.Serializable;

/**
 * 查询机-系统用户角色菜单关联表
 * @author swc 2018-03-20
 *
 */
public class TSysUserroleass implements Serializable {
    private static final long serialVersionUID = -7874169279120683223L;
    private String id; // 主键
    private String userid; // 用户ID
    private String roleid; // 角色ID
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getUserid() {
        return userid;
    }
    public void setUserid(String userid) {
        this.userid = userid;
    }
    public String getRoleid() {
        return roleid;
    }
    public void setRoleid(String roleid) {
        this.roleid = roleid;
    }

}