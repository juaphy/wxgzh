package com.jeecms.cms.rest2.entity;
   
import javax.xml.bind.annotation.XmlRootElement;

import com.jeecms.cms.rest2.common.JaxbTool;
@XmlRootElement(name="baseForm")
public class BaseForm {
      
     private Data data;
     private String dataid;
     private String pid;// 411600005887544001-001
     private String bsnum;// 13060416165400020
     private String formid;// 20100708180715106800
     private String version;// 1
     private String formtype;// 0 基本业务表单；1 在线填报表单  ；10
     private String bstype;// 个人:P   组织:I
     private String dataXml;
     

     public Data getData() {
         return data;
     }

     public void setData(Data data) {
         this.data = data;
     }

    public String getDataid() {
        return dataid;
    }


    public void setDataid(String dataid) {
        this.dataid = dataid;
    }


    public String getPid() {
        return pid;
    }


    public void setPid(String pid) {
        this.pid = pid;
    }


    public String getBsnum() {
        return bsnum;
    }


    public void setBsnum(String bsnum) {
        this.bsnum = bsnum;
    }


    public String getFormid() {
        return formid;
    }


    public void setFormid(String formid) {
        this.formid = formid;
    }


    public String getVersion() {
        return version;
    }


    public void setVersion(String version) {
        this.version = version;
    }


    public String getFormtype() {
        return formtype;
    }


    public void setFormtype(String formtype) {
        this.formtype = formtype;
    }
    

    public String getBstype() {
        return bstype;
    }


    public void setBstype(String bstype) {
        this.bstype = bstype;
    }
    
    
    public String getDataXml() {
        return dataXml;
    }


    public void setDataXml(String dataXml) {
        this.dataXml = dataXml;
    }



       @XmlRootElement(name="data")
        public static class Data {
            private String jbr_mc;
            private String jbr_sfzjhm;
            private String jbr_yddh;
            private String sqsxmc;
            private String sqr_mc;
            private String sqr_sfzjhm;
            private String sqr_yddh;
            private String sqr_gddh;
            
            private String per_zztype;
            private String per_name;
            private String per_idcard;
            private String per_phone;
            
            private String ler_cname;
            private String ler_type;
            private String ler_addr;
            private String ler_regmoney;
            private String ler_pname;
            private String ler_createdate;
            private String ler_jyarea;
            private String ler_zjhm;
            private String ler_zztype;
            private String ler_zzjgdm;
            
            private String sqr_type;

            public String getJbr_mc() {
                return jbr_mc;
            }

            public void setJbr_mc(String jbr_mc) {
                this.jbr_mc = jbr_mc;
            }

            public String getJbr_sfzjhm() {
                return jbr_sfzjhm;
            }

            public void setJbr_sfzjhm(String jbr_sfzjhm) {
                this.jbr_sfzjhm = jbr_sfzjhm;
            }

            public String getJbr_yddh() {
                return jbr_yddh;
            }

            public void setJbr_yddh(String jbr_yddh) {
                this.jbr_yddh = jbr_yddh;
            }

            public String getSqsxmc() {
                return sqsxmc;
            }

            public void setSqsxmc(String sqsxmc) {
                this.sqsxmc = sqsxmc;
            }

            public String getSqr_mc() {
                return sqr_mc;
            }

            public void setSqr_mc(String sqr_mc) {
                this.sqr_mc = sqr_mc;
            }

            public String getSqr_sfzjhm() {
                return sqr_sfzjhm;
            }

            public void setSqr_sfzjhm(String sqr_sfzjhm) {
                this.sqr_sfzjhm = sqr_sfzjhm;
            }

            public String getSqr_yddh() {
                return sqr_yddh;
            }

            public void setSqr_yddh(String sqr_yddh) {
                this.sqr_yddh = sqr_yddh;
            }

            public String getSqr_gddh() {
                return sqr_gddh;
            }

            public void setSqr_gddh(String sqr_gddh) {
                this.sqr_gddh = sqr_gddh;
            }

            public String getPer_zztype() {
                return per_zztype;
            }

            public void setPer_zztype(String per_zztype) {
                this.per_zztype = per_zztype;
            }

            public String getPer_name() {
                return per_name;
            }

            public void setPer_name(String per_name) {
                this.per_name = per_name;
            }

            public String getPer_idcard() {
                return per_idcard;
            }

            public void setPer_idcard(String per_idcard) {
                this.per_idcard = per_idcard;
            }

            public String getPer_phone() {
                return per_phone;
            }

            public void setPer_phone(String per_phone) {
                this.per_phone = per_phone;
            }

            public String getLer_cname() {
                return ler_cname;
            }

            public void setLer_cname(String ler_cname) {
                this.ler_cname = ler_cname;
            }

            public String getLer_type() {
                return ler_type;
            }

            public void setLer_type(String ler_type) {
                this.ler_type = ler_type;
            }

            public String getLer_addr() {
                return ler_addr;
            }

            public void setLer_addr(String ler_addr) {
                this.ler_addr = ler_addr;
            }

            public String getLer_regmoney() {
                return ler_regmoney;
            }

            public void setLer_regmoney(String ler_regmoney) {
                this.ler_regmoney = ler_regmoney;
            }

            public String getLer_pname() {
                return ler_pname;
            }

            public void setLer_pname(String ler_pname) {
                this.ler_pname = ler_pname;
            }

            public String getLer_createdate() {
                return ler_createdate;
            }

            public void setLer_createdate(String ler_createdate) {
                this.ler_createdate = ler_createdate;
            }

            public String getLer_jyarea() {
                return ler_jyarea;
            }

            public void setLer_jyarea(String ler_jyarea) {
                this.ler_jyarea = ler_jyarea;
            }

            public String getLer_zjhm() {
                return ler_zjhm;
            }

            public void setLer_zjhm(String ler_zjhm) {
                this.ler_zjhm = ler_zjhm;
            }

            public String getLer_zztype() {
                return ler_zztype;
            }

            public void setLer_zztype(String ler_zztype) {
                this.ler_zztype = ler_zztype;
            }

            public String getLer_zzjgdm() {
                return ler_zzjgdm;
            }

            public void setLer_zzjgdm(String ler_zzjgdm) {
                this.ler_zzjgdm = ler_zzjgdm;
            }

            public String getSqr_type() {
                return sqr_type;
            }

            public void setSqr_type(String sqr_type) {
                this.sqr_type = sqr_type;
            }
     }

       @SuppressWarnings("unused")
    public static void main(String[] args) throws Exception {
           String str="<data>"+
            "<jbr_mc>oyc</jbr_mc>"+
            "<jbr_sfzjhm>430223198509014512</jbr_sfzjhm>"+
            "<jbr_yddh>13632568956</jbr_yddh>"+
            "<sqsxmc>oyc申请qqqqq</sqsxmc>"+
            "<sqr_mc>oyc</sqr_mc>"+
            "<sqr_sfzjhm>430223198509014512</sqr_sfzjhm>"+
            "<sqr_yddh>13632568956</sqr_yddh>"+
            "<sqr_gddh>13632568956</sqr_gddh>"+
            "<per_zztype>10</per_zztype>"+
            "<per_name>oyc</per_name>"+
            "<per_idcard>430223198509014512</per_idcard>"+
            "<per_phone>13632568956</per_phone>"+
            "<sqr_type>P</sqr_type>"+
        "</data>";
           String strzz="<data>"+
                   "<jbr_mc>oyc</jbr_mc>"+
                    "<jbr_sfzjhm>430223198509014512</jbr_sfzjhm>"+
                    "<jbr_yddh>13632568956</jbr_yddh>"+
                    "<sqsxmc>dddddd申请qqqqq</sqsxmc>"+
                    "<sqr_mc>dddddd</sqr_mc>"+
                    "<sqr_sfzjhm></sqr_sfzjhm>"+
                    "<sqr_yddh></sqr_yddh>"+
                    "<sqr_gddh></sqr_gddh>"+

                    "<inc_zzjgdm></inc_zzjgdm>"+
                    "<inc_jgmc></inc_jgmc>"+
                    "<inc_gszch></inc_gszch>"+
                    "<inc_jycs></inc_jycs>"+

                    "<ler_cname>dddddd</ler_cname>"+
                    "<ler_type>1</ler_type>"+
                    "<ler_addr>undefined</ler_addr>"+
                    "<ler_regmoney>undefined</ler_regmoney>"+
                    "<ler_pname>aaaaa</ler_pname>"+
                    "<ler_createdate>undefined</ler_createdate>"+
                    "<ler_jyarea>undefined</ler_jyarea>"+
                    "<ler_zjhm>450803199403227590</ler_zjhm>"+
                    "<ler_zztype>10</ler_zztype>"+
                    "<ler_zzjgdm>123131</ler_zzjgdm>"+
                    "<sqr_type>I</sqr_type>"+
                "</data>";
        String xml="<baseForm>"+
                    "<dataid>dataid</dataid>"+
                    "<pid>pid</pid>"+
                    "<bsnum>123213</bsnum>"+
                    "<formid>formid</formid>"+
                    "<version>1</version>"+
                    "<formtype>10</formtype>"+str+
                    //"<datas>" +str+strzz+
                    /*"<datas>" +str+
                    "</datas>"+*/
                    "</baseForm>";
        System.out.println(xml);
        BaseForm b=(BaseForm) JaxbTool.getObject(BaseForm.class, xml);
        //System.out.println(b.getBsnum()+"  "+b.getDatas().getDatalist().get(0).getJbr_mc()+"  "+b.getDatas().getDatalist().get(1).getLer_cname());
        System.out.println(b.getData().getJbr_mc());
    }

}
