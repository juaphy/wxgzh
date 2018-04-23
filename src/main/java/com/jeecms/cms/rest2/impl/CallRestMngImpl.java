package com.jeecms.cms.rest2.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLDecoder;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.xmlpull.v1.XmlPullParserException;

import com.jeecms.cms.CTools;
import com.jeecms.cms.JsonUtil;
import com.jeecms.cms.action.wxgzh.wssb.MyAct;
import com.jeecms.cms.rest2.CallRestMng;
import com.jeecms.cms.rest2.entity.ReturnVo;

public class CallRestMngImpl implements CallRestMng {

    public static final String token = "AF3D7EE5-1F56-4F62-B225-174165BEF8FC";
    // public static final String token = "4D751735-73F7-4BFF-B7DD-C6252C0C64ED";
    // //10.1.48.199/cms

    // WebService地址
    // private static String URL = "http://10.99.76.9:8083/services/";
    // private static String URL = "http://127.0.0.1:7777/services/";
    private static String URL2 = "http://127.0.0.1:8083/services/";
    // private static String URL = "http://220.197.219.92:2222/services/";
    private static String URL = "http://220.197.219.92:5555/services/";
    // private static String URL = "http://59.215.226.63:8888/services/";
    // private static String URL = "http://10.99.62.1:8090/services/";
    // private static String URL = "http://www.gzegn.gov.cn:8888/services/";
    // private static String URL = "http://220.197.219.92:8888/services/";
    private static final String NAMESPACE = "http://service.rest2.cms.jeecms.com/";

    /**
     * 网厅服务接口默认地址
     */
    public static final String WTIOHTTP_ADRR = "http://220.197.219.92:5555";
 // public static final String WTIOHTTP_ADRR = "http://127.0.0.1:8083";

    public static void excute(String methodName, String serviceName, Map<String, Object> map) {
        System.out.println("\n\n" + URL + serviceName);
        HttpTransportSE ht = new HttpTransportSE(URL + serviceName);
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        SoapObject request = new SoapObject(NAMESPACE, methodName);
        request.addProperty("param", JsonUtil.toJson(map));
        envelope.bodyOut = request;
        try {
            ht.call(null, envelope);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        }

        SoapObject result = (SoapObject) envelope.bodyIn;
        String name = result.getProperty(0).toString();
        System.out.println("\ncall " + serviceName + "." + methodName + "() Result : ");
        System.out.println(name);

    }

    public static void main(String[] args) {
        long a = System.currentTimeMillis();
        String s = CTools.getBsnum();
        long b = System.currentTimeMillis();
        System.out.println(s + "aaaaa" + (b - a));
    }

    @Override
    public String execute(String methodName, String serviceName, Map<String, Object> map) {
        System.out.println("\n\n" + URL + serviceName);
        HttpTransportSE ht = new HttpTransportSE(URL + serviceName);
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        SoapObject request = new SoapObject(NAMESPACE, methodName);
        request.addProperty("param", JsonUtil.toJson(map));
        envelope.bodyOut = request;
        try {
            ht.call(null, envelope);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        }

        SoapObject result = (SoapObject) envelope.bodyIn;
        String name = result.getProperty(0).toString();
        System.out.println("\ncall " + serviceName + "." + methodName + "() Result : ");
        System.out.println(name);
        return name;
    }

    @Override
    public String executeLocal(String methodName, String serviceName, Map<String, Object> map) {
        System.out.println("\n\n" + URL + serviceName);
        HttpTransportSE ht = new HttpTransportSE(URL2 + serviceName);
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        SoapObject request = new SoapObject(NAMESPACE, methodName);
        request.addProperty("param", JsonUtil.toJson(map));
        envelope.bodyOut = request;
        try {
            ht.call(null, envelope);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        }
        
        SoapObject result = (SoapObject) envelope.bodyIn;
        String name = result.getProperty(0).toString();
        System.out.println("\ncall " + serviceName + "." + methodName + "() Result : ");
        System.out.println(name);
        return name;
    }

    @SuppressWarnings({ "deprecation", "resource" })
    @Override
    public String uploadFile(HttpServletRequest request, Map<String, String> param) {
        HttpClient httpclient = new DefaultHttpClient();
        ReturnVo res = new ReturnVo();
        String result = "";
        try {
            String fileName = param.get("filename");
            String sxid = param.get("filename");
            String userid = param.get("userid");
            if (fileName == null || "".equals(fileName)) {
                res.setCode(500);
                res.setError("材料名称为空");
                return JsonUtil.toJson(res);
            }
            fileName = URLDecoder.decode(fileName,"UTF-8");
            request.setCharacterEncoding("utf-8");
            String url = WTIOHTTP_ADRR;
            String getURL = url + "/servlet/uploadMobileFileServlet?sxid=" + sxid + "&filename=" + fileName + "&userid=" + userid;
            HttpPost httppost = new HttpPost(getURL);
            MultipartEntity reqEntity = new MultipartEntity();

            // 获取文件信息并封装到请求中
            CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());
            if (request instanceof MultipartHttpServletRequest) {
                MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
                Iterator<String> iter = multiRequest.getFileNames(); 
                multiRequest.getFileNames();
                if (multipartResolver.isMultipart(request) && iter.hasNext()) {
                    while (iter.hasNext()) {
                        List<MultipartFile> fileRows = multiRequest.getFiles(iter.next().toString());
                        if (fileRows != null && fileRows.size() != 0) {
                            for (MultipartFile file : fileRows) {
                                if (file != null && !file.isEmpty()) {
                                    String key = file.getOriginalFilename();
                                    FileBody filebdody = new FileBody(inputstreamtofile(file.getInputStream(), key));
                                    reqEntity.addPart(key, filebdody);
                                }
                            }
                        }
                    }
                }
            }

            // 发送请求
            httppost.setEntity(reqEntity);
            HttpResponse responseHttp = httpclient.execute(httppost);
            int statusCode = responseHttp.getStatusLine().getStatusCode(); // 获取服务器响应状态
            if (statusCode == HttpStatus.SC_OK) {
                System.out.println("服务器正常响应.....");
                HttpEntity resEntity = responseHttp.getEntity();
                result = EntityUtils.toString(resEntity,"UTF-8"); // httpclient自带的工具类读取返回数据
                System.out.println(result);
                EntityUtils.consume(resEntity); // 关闭底层的流
            }
        } catch(Exception e) {
            System.out.println("系统异常：" + e.getMessage());
        } finally {
            try {
                httpclient.getConnectionManager().shutdown();
            } catch (Exception e) {
                System.out.println("关闭httpClient连接时发生异常：" + e.getMessage());
            }
        }
        return result;
    }

    private File inputstreamtofile(InputStream ins, String filename) throws IOException {
        File file = new File(filename);  
        OutputStream os = new FileOutputStream(file);
        int bytesRead = 0;  
        byte[] buffer = new byte[8192];
        while ((bytesRead = ins.read(buffer, 0, 8192)) != -1) {
            os.write(buffer, 0, bytesRead);  
        }
        os.close();
        ins.close();
        return file;
    }

}
