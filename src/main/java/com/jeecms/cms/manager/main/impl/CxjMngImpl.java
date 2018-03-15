package com.jeecms.cms.manager.main.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeecms.cms.CTools;
import com.jeecms.cms.ListUtils;
import com.jeecms.cms.dao.main.CxjDao;
import com.jeecms.cms.entity.main.TCxjXjfwpd;
import com.jeecms.cms.manager.main.CxjMng;
import com.jeecms.cms.rest2.CallRestMng;
import com.jeecms.cms.rest2.entity.DeptInfo;
import com.jeecms.cms.rest2.entity.SxInfo;
import com.jeecms.common.hibernate4.Updater;

@Service
@Transactional
public class CxjMngImpl implements CxjMng {

	private CxjDao dao;
	@Autowired
	public void setDao(CxjDao dao) {
		this.dao = dao;
	}
    
    @Autowired
    private CallRestMng callRestMng;

    @Override
    public List<TCxjXjfwpd> findXjfwpdList(Map<String, String> params) {
        return dao.findXjfwpdList(params);
    }

    @Override
    public TCxjXjfwpd findTCxjXjfwpdById(String id) {
        return dao.findTCxjXjfwpdById(id);
    }

    @Override
    public TCxjXjfwpd saveTCxjXjfwpd(TCxjXjfwpd bean) {
        return dao.saveTCxjXjfwpd(bean);
    }

    @Override
    public TCxjXjfwpd deleteTCxjXjfwpdById(String id) {
        return dao.deleteTCxjXjfwpdById(id);
    }

    @Override
    public TCxjXjfwpd updateTCxjXjfwpd(Updater<TCxjXjfwpd> updater) {
        // TODO Auto-generated method stub
        return null;
    }

    private static String serviceName = "RestRegionService";

    @SuppressWarnings({ "unused", "unchecked" })
    @Override
    public List<DeptInfo> findDeptListByAreaid(String areaId) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("AREAID", areaId);
        params.put("PAGENO", "1");
        params.put("PAGESIZE", "1000");
        params.put("ISZSBM", "6");
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
            JSONArray jsonArray = (JSONArray) jsonObject.get("ReturnValue");
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
            JSONArray jsonArray = (JSONArray) jsonObject.get("ReturnValue");
            if (jsonArray != null) {
                List<?> list = jsonArray.toList();
                SxInfo sxInfo = null;
                sxInfos = new ArrayList<SxInfo>();
                Map<String, String> map = null;
                for (int i = 0; i < list.size(); i++) {
                    sxInfo = new SxInfo();
                    map = (Map<String, String>) list.get(i);
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

}