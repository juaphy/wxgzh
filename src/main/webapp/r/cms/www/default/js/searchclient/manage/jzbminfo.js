/*================================================
 *   进驻部门使用信息化开展审批服务情况统计表 管理系统 
 ================================================*/

// 2017-11-03 swc

var jzbminfo = {};

/**
 * 显示实时的日期时间信息
 * @param id 显示日期时间信息的元素
 */
jzbminfo.getTimeInfo = function(id) {
    var mydate;
    var myInterval;
    var fullyear;
    var month;
    var day;
    var hours;
    var minutes;
    var seconds;
    try {
        myInterval = window.setInterval(function(){
            mydate = new Date();
            fullyear = mydate.getFullYear();
            month = mydate.getMonth() + 1;
            day = mydate.getDate();
            hours = mydate.getHours();
            minutes = mydate.getMinutes();
            seconds = mydate.getSeconds();
            if (month < 10) {
               month = "0" + month;
            }
            if (day < 10) {
               day = "0" + day;
            }
            if (hours < 10 ) {
               hours = "0" + hours;
            }
            if (minutes < 10 ) {
               minutes = "0" + minutes;
            }
            if (seconds < 10 ) {
               seconds = "0" + seconds;
            }
            $("#" + id).html(fullyear + "-" + month
                + "-" + day + " " + hours
                + ":" + minutes + ":" + seconds);
        }, 1000);
    } catch(e) {
       if (myInterval != null) {
           window.clearInterval(myInterval);
       }
    }
}

/**
 * 初始化nicescroll插件配置
 * @selector 元素选择器（class：请带英文句号，id：请带井号#）
 */
jzbminfo.initNiceScroll = function(selector) {
    $(selector).niceScroll({
        cursorcolor: "rgb(178, 182, 183)", // 改变滚动条颜色，使用16进制颜色值
        cursoropacitymin: 0, // 当滚动条是隐藏状态时改变透明度, 值范围 1 到 0
        cursoropacitymax: 1, // 当滚动条是显示状态时改变透明度, 值范围 1 到 0
        cursorwidth: "5px", // 滚动条的宽度，单位：便素
        cursorborder: "1px solid #fff", // CSS方式定义滚动条边框
        cursorborderradius: "5px", // 滚动条圆角（像素）
        zindex: "auto" | 1999999999, // 改变滚动条的DIV的z-index值
        scrollspeed: 60, // 滚动速度
        mousescrollstep: 40, // 鼠标滚轮的滚动速度 (像素)
        touchbehavior: false, // 激活拖拽滚动
        hwacceleration: true, // 激活硬件加速
        boxzoom: false, // 激活放大box的内容
        dblclickzoom: true, // (仅当 boxzoom=true时有效)双击box时放大
        gesturezoom: true, // (仅 boxzoom=true 和触屏设备时有效) 激活变焦当out/in（两个手指外张或收缩）
        grabcursorenabled: true // (仅当 touchbehavior=true) 显示“抓住”图标display "grab" icon
    }); 
}
function autoSetHeightForIframe(iframeId, initHeight) {
    document.getElementById(iframeId).height=0;
    document.getElementById(iframeId).height=document.getElementById(iframeId).contentWindow.document.body.scrollHeight;
    document.getElementById(iframeId).width=1180;
    if (document.getElementById(iframeId).height > 100) {
        //$(".fwdx_sort").show();
    }
    if (document.getElementById(iframeId).height < 100) {
        document.getElementById(iframeId).height = initHeight;
    }
    // document.getElementById("sxlist_iframe").width=document.getElementById("sxlist_iframe").contentWindow.document.body.scrollWidth;
}