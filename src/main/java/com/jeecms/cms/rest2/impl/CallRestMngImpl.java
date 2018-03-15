package com.jeecms.cms.rest2.impl;

import java.io.IOException;
import java.util.Map;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.xmlpull.v1.XmlPullParserException;

import com.jeecms.cms.CTools;
import com.jeecms.cms.JsonUtil;
import com.jeecms.cms.rest2.CallRestMng;

public class CallRestMngImpl implements CallRestMng {

    public static final String token = "AF3D7EE5-1F56-4F62-B225-174165BEF8FC";
    // public static final String token = "4D751735-73F7-4BFF-B7DD-C6252C0C64ED";
    // //10.1.48.199/cms

    // WebService地址
    // private static String URL = "http://10.99.76.9:8083/services/";
    // private static String URL = "http://127.0.0.1:7777/services/";
    // private static String URL = "http://127.0.0.1:80/services/";
    // private static String URL = "http://220.197.219.92:2222/services/";
    private static String URL = "http://220.197.219.92:5555/services/";
    // private static String URL = "http://59.215.226.63:8888/services/";
    // private static String URL = "http://10.99.62.1:8090/services/";
    // private static String URL = "http://www.gzegn.gov.cn:8888/services/";
    // private static String URL = "http://220.197.219.92:8888/services/";
    private static final String NAMESPACE = "http://service.rest2.cms.jeecms.com/";

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

}
