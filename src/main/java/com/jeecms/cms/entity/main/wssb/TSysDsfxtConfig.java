package com.jeecms.cms.entity.main.wssb;
 
import java.io.Serializable;

/**
 * 网厅嵌入第三方系统地址信息配置表
 * @author swc 2018-04-16
 *
 */
public class TSysDsfxtConfig implements Serializable {
    private static final long serialVersionUID = 445894220876293462L;
    private String id;
	private String url;
	private String describe; // 描述
	private String status;
	private String type;
	private String bsname;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getDescribe() {
		return describe;
	}
	public void setDescribe(String describe) {
		this.describe = describe;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getBsname() {
		return bsname;
	}
	public void setBsname(String bsname) {
		this.bsname = bsname;
	}
}

