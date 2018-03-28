package com.jeecms.cms.manager.main;

import com.jeecms.cms.entity.main.cxj.TCxjZwzxconfig;

/**
 * 微信公众号-网上申报管理接口
 * 
 */
public interface WxgzhWssbMng {

    /**
     * 获取查询机配置信息
     * @param areaId
     * @return 只取最新添加的查询机配置信息
     */
    public TCxjZwzxconfig findZwzxconfig();

    /**
     * 根据区划id获取查询机配置信息
     * @param areaId
     * @return
     */
    public TCxjZwzxconfig findZwzxconfig(String areaId);

    /**
     * 更新查询机背景图路径
     * @param info
     */
    public void updateBackgroundImg(TCxjZwzxconfig info);

    /**
     * 根据id获取查询机配置信息
     * @param areaId
     * @return
     */
    public TCxjZwzxconfig findZwzxconfigById(String id);

}