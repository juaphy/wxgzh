package com.jeecms.common.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.ModelMap;

import com.jeecms.cms.CTools;
import com.jeecms.cms.entity.main.wssb.TSysDsfxtConfig;
import com.jeecms.core.entity.CmsUser;

/**
 * HttpRequest 相关操作实现
 * @author swc 20180416
 *
 */
public class HttpRequest {

    /**
     * 向指定URL发送GET方法的请求
     * 
     * @param url 发送请求的URL
     * @param param 请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
     * @return URL 所代表远程资源的响应结果
     */
    public static String sendGet(String url, String param) {
        String result = "";
        BufferedReader in = null;
        try {
            String urlNameString = url + "?" + param;
            URL realUrl = new URL(urlNameString);
            // 打开和URL之间的连接
            URLConnection connection = realUrl.openConnection();
            // 设置通用的请求属性
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            connection.setReadTimeout(2000);//设置超时 时间
            // 建立实际的连接
            connection.connect();
            // 获取所有响应头字段
            Map<String, List<String>> map = connection.getHeaderFields();
            // 遍历所有的响应头字段
            for (String key : map.keySet()) {
                System.out.println(key + "--->" + map.get(key));
            }
            // 定义 BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(
                    connection.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送GET请求出现异常！" + e);
            e.printStackTrace();
        }
        // 使用finally块来关闭输入流
        finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return result;
    }

    /**
     * 向指定 URL 发送POST方法的请求
     * 
     * @param url
     *            发送请求的 URL
     * @param param
     *            请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
     * @return 所代表远程资源的响应结果
     */
    public static String sendPost(String url, String param) {
        PrintWriter out = null;
        BufferedReader in = null;
        String result = "";
        try {
            URL realUrl = new URL(url);
            // 打开和URL之间的连接
            URLConnection conn = realUrl.openConnection();
            // 设置通用的请求属性 
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            conn.setRequestProperty("Accept-Charset", "utf-8");
            conn.setRequestProperty("contentType", "utf-8");

            conn.setReadTimeout(2000);//设置超时 时间
            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            // 获取URLConnection对象对应的输出流
            out = new PrintWriter(conn.getOutputStream());
            // 发送请求参数
            out.print(param);
            // flush输出流的缓冲
            out.flush();
            // 定义BufferedReader输入流来读取URL的响应
//            in = new BufferedReader(
//                    new InputStreamReader(conn.getInputStream()));

             in=new BufferedReader(new InputStreamReader(conn.getInputStream(),"UTF-8"));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送 POST 请求出现异常！"+e);
            e.printStackTrace();
        }
        //使用finally块来关闭输出流、输入流
        finally{
            try{
                if(out!=null){
                    out.close();
                }
                if(in!=null){
                    in.close();
                }
            }
            catch(IOException ex){
                ex.printStackTrace();
            }
        }
        return result;
    }

    /**
     * 拼接  发送请求的参数 
     * @param values
     * @return
     */
    public static String getParam(Map<String, String> map){
        String client_id="GAIIMAIQP";
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
        String time =formatter.format(new Date());           //yyyyMMddhhmmss
        System.out.println(time);
        String key="RT9IKTYLI29AG42IAX59";
        String signNew=client_id+key+time;
        String sign="";//令牌生成：MD5(应用编码+应用密钥+时间戳)
        try {
            sign=CTools.getMD5Digest(signNew);
            System.out.println(sign);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        map.put("client_id", client_id);
        map.put("kye", key);
        map.put("time", time);
        map.put("sign", sign);
        
        String param="";
        for(String i:map.keySet()){
            param=param+i+"="+map.get(i)+"&";
        }
        return param;
    }

    public static void main(String[] args) {
        /*//发送 GET 请求
        String s=HttpRequest.sendGet("http://localhost:6144/Home/RequestString", "key=123&v=456");
        System.out.println(s);

        //发送 POST 请求
        String sr=HttpRequest.sendPost("http://localhost:6144/Home/RequestPostString", "key=123&v=456");
        System.out.println(sr);*/
        Map<String , String> map=new HashMap<String, String>();

        String login_name="13530403059";
        String login_pwd="123456";
        String user_name="wangjing";
        String mobile="13530403059";
        String card_no="430621199105225413";
        String bank_no="6214837802485241";
        String address="深圳";

        map.put("login_name", login_name);
        map.put("login_pwd", login_pwd);
        map.put("user_name", user_name);
        map.put("mobile", mobile);
        map.put("card_no", card_no);
        map.put("bank_no", bank_no);
        map.put("address", address);

        //String param="client_id="+client_id+"&time="+time+"&sign="+sign;
        String url="http://42.123.101.61:83/rest/registerUser";
        String param=getParam(map);

        String sr=HttpRequest.sendPost(url, param);
    //    String sr=HttpRequest.sendGet(url, param);
        System.out.println("url:"+url);
        System.out.println("param:"+param);
        System.out.println("result:"+sr);
    }

    public static void setparamurl(CmsUser user,ModelMap model){
        String utype="";
        if(user.getUserType()!=null&&"1".equals(user.getUserType())){
            utype="0";
        }else{
            utype="1";
        }
        Map<String,String> mapurl=new HashMap<String, String>();
        mapurl.put("utype", utype);
        mapurl.put("uid", user.getUsername()!=null?user.getUsername():"");
        String urlparam=HttpRequest.getParam(mapurl);
        model.addAttribute("urlparam", urlparam);
        
    }

    public static void setUserParam(CmsUser user,ModelMap model,HttpServletRequest request){
        String utype="";
        if(user.getUserType()!=null&&"1".equals(user.getUserType())){
            utype="0";
        }else{
            utype="1";
        }
        Map<String,String> mapurl=new HashMap<String, String>();
        mapurl.put("utype", utype);
        mapurl.put("uid", user.getUsername()!=null?user.getUsername():"");
        String urlparam=HttpRequest.getParam(mapurl);
        //model.addAttribute("urlparam", urlparam);
        request.getSession().setAttribute("USER_PARAM", urlparam);
    }
 
    public static void setPzPath(ModelMap model,HttpServletRequest request){
        String jiaotongtinghttp = request.getSession().getServletContext().getInitParameter("jiaotongtinghttp");
        String zfcxjsthttp = request.getSession().getServletContext().getInitParameter("zfcxjsthttp");
        String huanbaohttpone = request.getSession().getServletContext().getInitParameter("huanbaohttpone");
        String huanbaohttptwo = request.getSession().getServletContext().getInitParameter("huanbaohttptwo");
        String gsxzsphttp =request.getSession().getServletContext().getInitParameter("gsxzsphttp");
        model.addAttribute("jiaotongtinghttp", jiaotongtinghttp);
        model.addAttribute("zfcxjsthttp", zfcxjsthttp);
        model.addAttribute("huanbaohttpone", huanbaohttpone);
        model.addAttribute("huanbaohttptwo", huanbaohttptwo);
        model.addAttribute("gsxzsphttp", gsxzsphttp);
    }

    /**
     * 获取第三方服务地址配置，并将这些信息设置到页面变量中
     * @param model
     * @param request
     * @param list
     */
    public static void setPzPathNew(ModelMap model,HttpServletRequest request,List<TSysDsfxtConfig> list){
        if(list.size() > 0){
            for (int i = 0; i < list.size(); i++){
                TSysDsfxtConfig config = list.get(i);
                model.addAttribute(config.getBsname(), config.getUrl() );
            }
        }else{
            //默认原来配置
            String jiaotongtinghttp = request.getSession().getServletContext().getInitParameter("jiaotongtinghttp");
            String zfcxjsthttp = request.getSession().getServletContext().getInitParameter("zfcxjsthttp");
            String huanbaohttpone = request.getSession().getServletContext().getInitParameter("huanbaohttpone");
            String huanbaohttptwo = request.getSession().getServletContext().getInitParameter("huanbaohttptwo");
            String gsxzsphttp = request.getSession().getServletContext().getInitParameter("gsxzsphttp");
            model.addAttribute("jiaotongtinghttp", jiaotongtinghttp);
            model.addAttribute("zfcxjsthttp", zfcxjsthttp);
            model.addAttribute("huanbaohttpone", huanbaohttpone);
            model.addAttribute("huanbaohttptwo", huanbaohttptwo);
            model.addAttribute("gsxzsphttp", gsxzsphttp);
        }
    }

}
