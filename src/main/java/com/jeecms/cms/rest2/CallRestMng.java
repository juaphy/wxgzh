package com.jeecms.cms.rest2;

import java.util.Map;

/**
 * 调用rest（webservice）接口管理IO
 * @author swc 2018-03-15
 *
 */
public interface CallRestMng {

    public String execute(String methodName, String serviceName, Map<String, Object> map);
}
