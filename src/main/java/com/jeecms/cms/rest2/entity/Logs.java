package com.jeecms.cms.rest2.entity;

import java.io.Serializable;

/**
 * 网厅接口数据-办理信息
 * @author swc 2018-03-20
 *
 */
public class Logs implements Serializable {

    private static final long serialVersionUID = 3810949768262560812L;
    private String lid; // 主键
    private String nodename; // 环节名称
    private String idea; // 办理意见
    private String handletime; // 办理时间
    public String getLid() {
        return lid;
    }
    public void setLid(String lid) {
        this.lid = lid;
    }
    public String getNodename() {
        return nodename;
    }
    public void setNodename(String nodename) {
        this.nodename = nodename;
    }
    public String getIdea() {
        return idea;
    }
    public void setIdea(String idea) {
        this.idea = idea;
    }
    public String getHandletime() {
        return handletime;
    }
    public void setHandletime(String handletime) {
        this.handletime = handletime;
    }

}
