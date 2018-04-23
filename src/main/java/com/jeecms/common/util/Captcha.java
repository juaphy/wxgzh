package com.jeecms.common.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jeecms.common.web.ResponseUtils;
import com.jeecms.core.entity.CmsSite;
import com.jeecms.core.web.WebErrors;
import com.jeecms.core.web.util.CmsUtils;
import com.jeecms.core.web.util.FrontUtils;
import com.octo.captcha.service.image.ImageCaptchaService;

@Controller
public class Captcha {

    public static final String CAPTCHA ="CAPTCHA_VALIDATE_CODE";

    @Autowired
    private ImageCaptchaService imageCaptchaService;

    /**
     * 做异步的验证　成功后存入一个校验值。
     * 在实际的提交保存数据前，用　Captcha.validate(request,value)　进行验证　value为返回的校验值。
     * @param request
     * @param response
     * @param captcha　验证码
     */
    @RequestMapping("/captcha/syncvalidate.jspx")
    public void doSyncValidate(HttpServletRequest request,HttpServletResponse response, String captcha){
        String rStr = "";
        try {
            if(imageCaptchaService.validateResponseForID(request.getSession().getId(), captcha)){
                int ri = (int) (Math.random()*1000000);
                rStr =  Integer.toString(ri);
                rStr  += captcha;
                HttpSession session = request.getSession();
                session.setAttribute(CAPTCHA, rStr);
            }
        } catch (Exception e) {
            
        }finally{
            JSONObject json = new JSONObject();
            try {
                json.put("valivalue", rStr);
                ResponseUtils.renderJson(response, json.toString());
            } catch (JSONException e) { 
                e.printStackTrace();
            }
        }
    }

    /**
     * 做异步后的２次验证。
     * 这个验证也只做一次。　
     * @param request
     * @param value
     * @return
     */
    public static boolean validate(HttpServletRequest request,String value){
        boolean rboolean = false;
        HttpSession session = request.getSession();

        if(session.getAttribute(CAPTCHA)!=null){
            String cstr = (String)session.getAttribute(CAPTCHA);
            if(cstr!=null&&cstr.length()>0&&cstr.equals(value)){
                rboolean = true;
            }
            session.removeAttribute(CAPTCHA);
        }
        return rboolean;
    }

    /**
     * 直接做验证；
     * @param request
     * @param captcha_name
     * @param model
     * @return
     */
    public boolean validate(HttpServletRequest request,String captcha_name,ModelMap model){  
        String captcha = request.getParameter(captcha_name);
        WebErrors errors = WebErrors.create(request);
        boolean vboolean = true;
        if(StringUtils.isBlank(captcha)){ 
            vboolean = false;
        }else{ 
            if(request.getSession()==null||request.getSession().getId()==null){
                vboolean = false;
            }else{ 
                try{
                    boolean valboolean = imageCaptchaService.validateResponseForID(request.getSession().getId(), captcha);
                    if(!valboolean){
                        vboolean = false;
                    }
                }catch (Exception e) {
                    vboolean = false;
                }
                
            }
        }
        if(!vboolean){
            errors.addError("error.invalidCaptcha");
            model.addAttribute("captcha_name", captcha_name);
            model.addAttribute("captcha_invali", "false");
            errors.toModel(model);
            CmsSite site = CmsUtils.getSite(request);
            FrontUtils.frontData(request, model, site);
        }
        
        return vboolean;
    }

    public ImageCaptchaService getImageCaptchaService() {
        return imageCaptchaService;
    }
}
