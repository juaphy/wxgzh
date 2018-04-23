package com.jeecms.cms.rest2.entity;

import java.io.Serializable;

import com.google.gson.annotations.Expose;
import com.jeecms.cms.SystemConstant;

public class ReturnVo implements Serializable {
    private static final long serialVersionUID = -4157513632644213584L;
    @Expose
    private Integer code;
    @Expose
    private String error;
    @Expose
    private Long count;
    @Expose
    private Object ReturnValue;
 
    public ReturnVo() {
        this.code = SystemConstant.INTERNAL_SERVER_ERROR;
    }

    public ReturnVo(Integer code) {
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
        if (SystemConstant.NOT_AUTHORIZED == code) {
            this.error = "登录已超时，请重新登录";
        } else if (SystemConstant.FORBIDDEN == code) {
            this.error = "请先登录";
        }
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public Object getReturnValue() {
        return ReturnValue;
    }

    public void setReturnValue(Object returnValue) {
        ReturnValue = returnValue;
    }

	public Long getCount() {
		return count;
	}

	public void setCount(Long count) {
		this.count = count;
	}
    
}
