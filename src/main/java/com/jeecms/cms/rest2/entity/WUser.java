package com.jeecms.cms.rest2.entity;

import java.io.Serializable;

/**
 * 通过接口登录后返回的网厅用户信息
 * @author swc 2018-04-23
 *
 */
public class WUser implements Serializable {
    private static final long serialVersionUID = 7134591287794387199L;
    private String userId; // 用户ID
    private String username; // 账号
    private String realname; // 姓名
    private String token; // 身份令牌
    private String type; // 账号类型，1个人，2企业
    private String code; // 证件号码或组织机构代码
    public String getUserId() {
        return userId;
    }
    public void setUserId(String userId) {
        this.userId = userId;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getRealname() {
        return realname;
    }
    public void setRealname(String realname) {
        this.realname = realname;
    }
    public String getToken() {
        return token;
    }
    public void setToken(String token) {
        this.token = token;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }

}