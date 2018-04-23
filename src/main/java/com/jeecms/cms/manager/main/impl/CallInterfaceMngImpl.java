package com.jeecms.cms.manager.main.impl;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeecms.cms.JsonUtil;
import com.jeecms.cms.ListUtils;
import com.jeecms.cms.manager.main.CallInterfaceMng;
import com.jeecms.cms.rest2.CallRestMng;
import com.jeecms.cms.rest2.entity.BsznInfo;
import com.jeecms.cms.rest2.entity.BusinessInfo;
import com.jeecms.cms.rest2.entity.DeptInfo;
import com.jeecms.cms.rest2.entity.Logs;
import com.jeecms.cms.rest2.entity.PermissionItemInfo;
import com.jeecms.cms.rest2.entity.SxClxx;
import com.jeecms.cms.rest2.entity.SxClxxUploadInfo;
import com.jeecms.cms.rest2.entity.SxInfo;
import com.jeecms.cms.rest2.entity.WUser;
import com.jeecms.cms.rest2.entity.WssbBusinessinfo;
//import com.jeecms.cms.rest2.entity.SxSqclInfo;
import com.jeecms.common.util.StringUtils;

@Service
@Transactional
public class CallInterfaceMngImpl implements CallInterfaceMng {

    @Autowired
    private CallRestMng callRestMng;

    private static String serviceName = "RestRegionService";

    /** 网厅接口返回值参数名: ReturnValue*/
    public static final String IO_RESULT = "ReturnValue";

    /** 提交网上申报信息接口token值 */
    public static final String SUBMIT_WSSB_TOKEN = "2C4FDB6D-9F78-40A7-97F6-8AD71F6AD5A8";

    @SuppressWarnings({ "unused", "unchecked" })
    @Override
    public List<DeptInfo> findDeptListByAreaid(String areaId, String isZsbm) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("AREAID", areaId);
        params.put("PAGENO", "1");
        params.put("PAGESIZE", "1000");
        if (!StringUtils.isEmpty(isZsbm)) {
            params.put("ISZSBM", isZsbm);
        }
        params.put("SFYDSB", "1");
        String result = callRestMng.execute("getDeptlistByAreaid", serviceName, params);
        if (result == null || "".equals(result)) {
            return null;
        }
        JSONObject jsonObject = new JSONObject(result);
        if (jsonObject == null) {
            return null;
        }
        List<DeptInfo> deptInfos = null;
        try {
            JSONArray jsonArray = (JSONArray) jsonObject.get(IO_RESULT);
            if (jsonArray != null) {
                List<?> list = jsonArray.toList();
                DeptInfo deptInfo = null;
                deptInfos = new ArrayList<DeptInfo>();
                Map<String, String> map = null;
                for (int i = 0; i < list.size(); i++) {
                    deptInfo = new DeptInfo();
                    map = (Map<String, String>) list.get(i);
                    setDeptInfo(deptInfo, map);
                    deptInfos.add(deptInfo);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (deptInfos != null) {
            new ListUtils<DeptInfo>().mySort(deptInfos, "orderId", "asc");
        }
        return deptInfos;
    }

    /**
     * 设置部门信息
     * @param deptInfo
     * @param map
     */
    private void setDeptInfo(DeptInfo deptInfo, Map<String, String> map) {
        deptInfo.setAreaId(mapToUpperCase(map, "areaId"));
        deptInfo.setDeptId(mapToUpperCase(map, "deptid"));
        deptInfo.setName(mapToUpperCase(map, "name"));
        deptInfo.setShortName(mapToUpperCase(map, "shortname"));
        deptInfo.setOrderId(mapToUpperCase(map, "orderid"));
        deptInfo.setCnum(mapToUpperCase(map, "cnum"));
        deptInfo.setReservone(mapToUpperCase(map, "reservone"));
        deptInfo.setPermnum(mapToUpperCase(map, "permnum"));
        deptInfo.setIszsbm(mapToUpperCase(map, "iszsbm"));
    }

    /**
     * 设置事项信息
     * @param deptInfo
     * @param map
     */
    private void setSxInfo(SxInfo sxInfo, Map<String, String> map) {
        sxInfo.setId(mapToUpperCase(map, "id"));
        sxInfo.setLargeitemid(mapToUpperCase(map, "largeitemid"));
        sxInfo.setSmallitemid(mapToUpperCase(map, "smallitemid"));
        sxInfo.setSxzxname(mapToUpperCase(map, "sxzxname"));
        sxInfo.setItemversion(mapToUpperCase(map, "itemversion"));
        sxInfo.setItemlimittime(mapToUpperCase(map, "itemlimittime"));
        sxInfo.setItemlimitunit(mapToUpperCase(map, "itemlimitunit"));
        sxInfo.setRegionid(mapToUpperCase(map, "regionid"));
        sxInfo.setDeptid(mapToUpperCase(map, "deptid"));
        sxInfo.setDeptname(mapToUpperCase(map, "deptname"));
        sxInfo.setLawaddr(mapToUpperCase(map, "lawaddr"));
        sxInfo.setRealaddr(mapToUpperCase(map, "realaddr"));
        sxInfo.setXzxk(mapToUpperCase(map, "xzxk"));
        sxInfo.setSfydsb(mapToUpperCase(map, "sfydsb"));
        sxInfo.setIsreserve(mapToUpperCase(map, "isreserve"));
    }

    /**
     * 设置业务基本信息数据
     * @param deptInfo
     * @param map
     */
    @SuppressWarnings("unused")
    private void setBusiInfo(BusinessInfo info, Map<String, String> map) {
        info.setBsnum(mapToUpperCase(map, "bsnum"));
        info.setSxzxname(mapToUpperCase(map, "sxzxname"));
        info.setDeptid(mapToUpperCase(map, "deptid"));
        info.setDeptname(mapToUpperCase(map, "deptname"));
        info.setAppname(mapToUpperCase(map, "appname"));
        info.setAppcompany(mapToUpperCase(map, "appcompany"));
        info.setApplytime(mapToUpperCase(map, "applytime"));
        info.setStatus(mapToUpperCase(map, "status"));
        info.setCstatus(mapToUpperCase(map, "cstatus"));
    }

    /**
     * 设置业务办理过程数据
     * @param deptInfo
     * @param map
     */
    @SuppressWarnings("unused")
    private void setLogs(Logs logs, Map<String, String> map) {
        logs.setLid(mapToUpperCase(map, "lid"));
        logs.setNodename(mapToUpperCase(map, "nodename"));
        logs.setIdea(mapToUpperCase(map, "idea"));
        logs.setHandletime(mapToUpperCase(map, "handletime"));
    }

    /**
     * 从map中获取value，如果原key获取不到值，则将key转换成大写再获取
     * @param map
     * @param column
     * @return
     */
    private String mapToUpperCase(Map<String, String> map, String column) {
        if (map.get(column) == null) {
            column = column.toUpperCase();
        }
        return map.get(column);
    }

    /**
     * 从JSONObject中获取value，如果原key获取不到值，则将key转换成大写再获取
     * @param map
     * @param column
     * @return
     */
    private String jsonObjToUpperCase(JSONObject jsonObj, String column) {
        String value = "";
        try {
            if (!isExistsColumn(jsonObj, column)) {
                column = column.toUpperCase();
            }
            Object obj = jsonObj.get(column);
            if (obj != null) {
                value = String.valueOf(obj);
            }
            if ("null".equals(value)) {
                value = "";
            }
        } catch (Exception e) {
        }
        return value;
    }

    /**
     * 判断JSONOBject中是否存在column
     * @param jsonObj
     * @param column
     * @return 存在column这个key，则返回true；发生异常，则返回false
     */
    private boolean isExistsColumn(JSONObject jsonObj, String column) {
        boolean isCheck = false;
        try {
            jsonObj.get(column);
            isCheck = true;
        } catch (Exception e) {
        }
        return isCheck;
    }

    @SuppressWarnings({ "unused", "unchecked" })
    @Override
    public List<SxInfo> findSxlbByDeptid(String deptid) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("DEPTID", deptid);
        params.put("PAGENO", "1");
        params.put("PAGESIZE", "1000");
        params.put("ISZSBM", "6");
        String result = callRestMng.execute("getPermlistByDeptid", "RestSysDeptService", params);
        if (result == null || "".equals(result)) {
            return null;
        }
        JSONObject jsonObject = new JSONObject(result);
        if (jsonObject == null) {
            return null;
        }
        List<SxInfo> sxInfos = null;
        try {
            JSONArray jsonArray = (JSONArray) jsonObject.get(IO_RESULT);
            if (jsonArray != null) {
                List<?> list = jsonArray.toList();
                SxInfo sxInfo = null;
                sxInfos = new ArrayList<SxInfo>();
                Map<String, String> map = null;
                for (int i = 0; i < list.size(); i++) {
                    map = (Map<String, String>) list.get(i);

                    // 排除行政处罚 4、行政强制 5、行政检查 14、行政奖励 11、行政裁决 8
                    if ("4".equals(mapToUpperCase(map, "xzxk"))
                            || "5".equals(mapToUpperCase(map, "xzxk"))
                            || "14".equals(mapToUpperCase(map, "xzxk"))
                            || "11".equals(mapToUpperCase(map, "xzxk"))
                            || "8".equals(mapToUpperCase(map, "xzxk"))) {
                        continue;
                    }
                    sxInfo = new SxInfo();
                    setSxInfo(sxInfo, map);
                    sxInfos.add(sxInfo);
                }
            }
            new ListUtils<SxInfo>().mySort(sxInfos, "largeitemid", "asc");
            // new ListUtils<SxInfo>().mySort(sxInfos, "smallitemid", "asc");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sxInfos;
    }

    @SuppressWarnings({ "unused"})
    @Override
    public BusinessInfo findBusiInfo(String bsnum, String appname) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("BSNUM", bsnum);
        params.put("APPNAME", appname);
        //String result = callRestMng.execute("search", "RestBussinessService", params);
        String result = callRestMng.execute("searchByBsnum", "RestBussinessService", params);
        if (result == null || "".equals(result)) {
            return null;
        }
        JSONObject jsonObject = new JSONObject(result);
        if (jsonObject == null) {
            return null;
        }
        BusinessInfo info = new BusinessInfo();

        List<Logs> logsList = null;
        try {
            JSONObject bjInfoJsonObj = (JSONObject) jsonObject.get(IO_RESULT);
            setInfo(info, bjInfoJsonObj);
            // setBusiInfo(info, (Map<String, String>) jsonObject.get(IO_RESULT));
            /*JSONArray jsonArray = (JSONArray) jsonObject.get("LOGS");
            if (jsonArray != null) {
                List<?> list = jsonArray.toList();
                Logs logs = null;
                logsList = new ArrayList<Logs>();
                Map<String, String> map = null;
                for (int i = 0; i < list.size(); i++) {
                    logs = new Logs();
                    map = (Map<String, String>) list.get(i);
                    setLogs(logs, map);
                    logsList.add(logs);
                }
            }*/
        } catch (Exception e) {
            System.out.println(e.getMessage());;
        }
        return info;
    }

    @SuppressWarnings({ "unused" })
    @Override
    public BsznInfo findBsznInfo(String sxid) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("PERMID", sxid);
        String result = callRestMng.execute("getNewPermissionByPermid", "RestPermissionitemService", params);
        if (result == null || "".equals(result)) {
            return null;
        }
        JSONObject jsonObject = new JSONObject(result);
        if (jsonObject == null) {
            return null;
        }
        BsznInfo info = new BsznInfo();

        List<Logs> logsList = null;
        try {
            JSONObject sxInfJsonObj = (JSONObject) jsonObject.get(IO_RESULT); // 事项详情
            PermissionItemInfo sxInfo = new PermissionItemInfo();
            setInfo(sxInfo, sxInfJsonObj);
            info.setPermissionItemInfo(sxInfo);
            //JSONArray sqcl = (JSONArray) jsonObject.get("SQCLLIST"); // 申请材料列表
            //JSONArray sdyj = (JSONArray) jsonObject.get("SDYJLIST"); // 设定依据列表
            //JSONArray slhj = (JSONArray) jsonObject.get("NODELIST"); // 受理环节列表
            //JSONArray tshj = (JSONArray) jsonObject.get("SPERHJ"); // 特殊环节列表
        } catch (Exception e) {
            e.printStackTrace();
        }
        return info;
    }

    /**
     * 设置数据
     * @param deptInfo
     * @param map
     */
    private void setInfo(Object obj, JSONObject map) {
        Class<?> cl = obj.getClass();
        if (cl != null) {
            Field[] fields = cl.getDeclaredFields();
            if (fields != null && fields.length > 0) {
                Field field = null;
                for (int i = 0; i < fields.length; i++) {
                    field = fields[i];
                    field.setAccessible(true);
                    if ("serialVersionUID".equals(field.getName())) {
                        continue;
                    }
                    try {
                        field.set(obj, jsonObjToUpperCase(map, field.getName()));
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                        //e.printStackTrace();
                    }
                }
            }
        }

    }

    /**
     * 将数据设置成xml字符串
     * @param deptInfo
     * @param map
     * @param isIdConvert 是否转换id
     * @param convertText 要转换的内容
     */
    private String setInfo4Xml(Object obj, JSONObject map, boolean isIdConvert, String convertText) {
        StringBuffer sbf = new StringBuffer();
        Class<?> cl = obj.getClass();
        if (cl != null) {
            Field[] fields = cl.getDeclaredFields();
            if (fields != null && fields.length > 0) {
                Field field = null;
                for (int i = 0; i < fields.length; i++) {
                    field = fields[i];
                    // field.setAccessible(true);
                    if ("serialVersionUID".equals(field.getName())) {
                        continue;
                    }
                    try {
                        // field.set(obj, jsonObjToUpperCase(map, field.getName()));
                        sbf.append("<");
                        String name = field.getName();
                        if (isIdConvert && "id".equals(field.getName())) {
                            name = convertText;
                        }
                        sbf.append(name);
                        sbf.append(">");
                        sbf.append(jsonObjToUpperCase(map, field.getName()));
                        sbf.append("</");
                        sbf.append(name);
                        sbf.append(">");
                    } catch (Exception e) {
                        System.out.println("将Object通过反射设置属性值时发生异常：" + e.getMessage());
                    }
                }
            }
        }
        return sbf.toString();
    }

    /**
     * 转换事项类型
     * @param xzxk
     * @return
     */
    @SuppressWarnings("unused")
    private String procXzxk(String xzxk) {
        String value = "";
        if ("1".equals(xzxk)) {
            value = "";
        }
        return value;
    }

    @SuppressWarnings("unused")
    public static void main(String[] args) {
        SxInfo sxinfo = new SxInfo();
        Map<String, String> map = new HashMap<String, String>();
        map.put("sx", "1");
        map.put("deptname", "12");
        CallInterfaceMngImpl tt = new CallInterfaceMngImpl();
        //tt.findSxClxx("520321201712101615003");
        //tt.setInfo(sxinfo, map);
    }

    @SuppressWarnings("unused")
    @Override
    public String findSxClxx4XmlString(String sxid) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("PERMID", sxid);
        params.put("PAGENO", "1");
        params.put("PAGESIZE", "1000");
        String result = callRestMng.execute("getClxxByPermid", "RestPermissionitemService", params);
        if (result == null || "".equals(result)) {
            return null;
        }
        JSONObject jsonObject = new JSONObject(result);
        if (jsonObject == null) {
            return null;
        }
        SxClxx sxClxx = new SxClxx();

        List<SxClxx> sxClxxList = null;
        try {
            JSONArray sxClxxJsonObj = (JSONArray) jsonObject.get(IO_RESULT); // 事项详情
            return sxClxxJsonObj == null?"" : JsonUtil.toJson(sxClxxJsonObj);
        } catch (Exception e) {
            System.out.println("获取事项材料信息时发生异常: " + e.getMessage());
        }
        return null;
    }

    @SuppressWarnings("unused")
    @Override
    public List<SxClxx> findSxClxxList(String sxid) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("PERMID", sxid);
        params.put("PAGENO", "1");
        params.put("PAGESIZE", "1000");
        String result = callRestMng.execute("getClxxByPermid", "RestPermissionitemService", params);
        if (result == null || "".equals(result)) {
            return null;
        }
        JSONObject jsonObject = new JSONObject(result);
        if (jsonObject == null) {
            return null;
        }
        List<SxClxx> sxClxxList = new ArrayList<SxClxx>();
        try {
            if (jsonObject.get(IO_RESULT) == null) {
                return sxClxxList;
            }
            JSONArray sxClxxJsonObj = (JSONArray) jsonObject.get(IO_RESULT); // 事项详情
            if (sxClxxJsonObj != null && sxClxxJsonObj.length() > 0) {
                JSONObject json = null;
                SxClxx sxClxx = null;
                for (int i = 0; i < sxClxxJsonObj.length(); i++) {
                    sxClxx = new SxClxx();
                    json = (JSONObject) sxClxxJsonObj.get(i);
                    setInfo(sxClxx, json);
                    sxClxxList.add(sxClxx);
                }
            }
            return sxClxxList;
        } catch (Exception e) {
            System.out.println("获取事项材料信息时发生异常: " + e.getMessage());
        }
        return null;
    }

    @SuppressWarnings("unused")
    @Override
    public String findSxClxxList4Xml(String sxid) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("PERMID", sxid);
        params.put("PAGENO", "1");
        params.put("PAGESIZE", "1000");
        String result = callRestMng.execute("getClxxByPermid", "RestPermissionitemService", params);
        if (result == null || "".equals(result)) {
            return null;
        }
        JSONObject jsonObject = new JSONObject(result);
        if (jsonObject == null) {
            return null;
        }
        String xmlStr = null;
        try {
            if (jsonObject.get(IO_RESULT) == null) {
                return xmlStr;
            }
            JSONArray sxClxxJsonObj = (JSONArray) jsonObject.get(IO_RESULT); // 事项详情
            if (sxClxxJsonObj != null && sxClxxJsonObj.length() > 0) {
                JSONObject json = null;
                SxClxx sxClxx = null;
                StringBuffer xml = new StringBuffer();
                xml.append("<cllist>\n");
                for (int i = 0; i < sxClxxJsonObj.length(); i++) {
                    sxClxx = new SxClxx();
                    xml.append("<cl>");
                    json = (JSONObject) sxClxxJsonObj.get(i);
                    // setInfo(sxClxx, json);
                    xml.append(setInfo4Xml(sxClxx, json, true, "clid"));
                    xml.append("</cl>\n");
                }
                xml.append("<length>" + sxClxxJsonObj.length() + "</length>\n");
                xml.append("</cllist>");
                xmlStr = xml.toString();
            }
            return xmlStr;
        } catch (Exception e) {
            System.out.println("获取事项材料信息时发生异常: " + e.getMessage());
        }
        return null;
    }

    @SuppressWarnings("unused")
    @Override
    public SxClxxUploadInfo uploadSxcl(HttpServletRequest request, Map<String, String> param) {
        String result = callRestMng.uploadFile(request, param);
        if (result == null || "".equals(result)) {
            return null;
        }
        try {
            JSONObject jsonObject = new JSONObject(result);
            if (jsonObject == null) {
                return null;
            }
            SxClxxUploadInfo info = new SxClxxUploadInfo();
            if (jsonObject.get(IO_RESULT) == null) {
                return null;
            }
            JSONObject infoJsonObj = (JSONObject) jsonObject.get(IO_RESULT);
            setInfo(info, infoJsonObj);
            return info;
        } catch (Exception e) {
            System.out.println("上传事项材料到远程服务后发生异常：" + e.getMessage());
        }
        return null;
    }

    @Override
    public WssbBusinessinfo submitWssb(Map<String, Object> params) {
        if (params == null || params.isEmpty()) {
            return null;
        }
        params.put("token", SUBMIT_WSSB_TOKEN);
        String result = callRestMng.executeLocal("AppSubmitSb", "RestOnlineDeclareService", params);
        if (result == null || "".equals(result)) {
            return null;
        }
        JSONObject jsonObject = new JSONObject(result);
        if (jsonObject == null) {
            return null;
        }
        WssbBusinessinfo info = new WssbBusinessinfo();
        try {
            JSONObject bjInfoJsonObj = (JSONObject) jsonObject.get(IO_RESULT);
            setInfo(info, bjInfoJsonObj);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return info;
    }

    @Override
    public WUser login4Wt(Map<String, Object> params) {
        if (params == null || params.isEmpty()) {
            return null;
        }
        String result = callRestMng.execute("login", "RestUserService", params);
        if (result == null || "".equals(result)) {
            return null;
        }
        JSONObject jsonObject = new JSONObject(result);
        if (jsonObject == null) {
            return null;
        }
        WUser info = new WUser();
        try {
            JSONObject bjInfoJsonObj = (JSONObject) jsonObject.get(IO_RESULT);
            setInfo(info, bjInfoJsonObj);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return info;
    }

}