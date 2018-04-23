package com.jeecms.cms.rest2;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

/**
 * 调用rest（webservice）接口管理IO
 * @author swc 2018-03-15
 *
 */
public interface CallRestMng {

    /**
     * 执行调用
     * @param methodName 方法名
     * @param serviceName 服务类名
     * @param map 参数
     * @return
     */
    public String execute(String methodName, String serviceName, Map<String, Object> map);

    /**
     * 执行调用本地接口服务器
     * @param methodName 方法名
     * @param serviceName 服务类名
     * @param map 参数
     * @return
     */
    public String executeLocal(String methodName, String serviceName, Map<String, Object> map);

    /**
     * 远程调用UploadMobileFileServlet执行事项材料文件上传
     * @param request
     * @param param 参数：sxid 事项id、filename 事项材料名称、userid：用户id
     * @return
     */
    public String uploadFile(HttpServletRequest request, Map<String, String> param);
}
