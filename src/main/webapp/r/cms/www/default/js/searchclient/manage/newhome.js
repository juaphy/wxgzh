// 新版首页js

function loadXqFromSz(objThis, areaid) {
    // 市州  ==> 县区
    // $(".fwdh_content_area_line_xq").slideUp(200);
    $(".fl_area_list_xq_active").removeClass("fl_area_list_xq_active");
    var obj = $("." + objThis.attr("to"));
    if (objThis.attr("nochirld") == "true" || objThis.attr("nochirld") == true) {
        $(".fwdh_content_area_xz").html("");
        $(".fwdh_content_area_cj").html("");
        $(".fwdh_content_area_line_xz,.fwdh_content_area_line_cj,.fwdh_content_area_line_xq").slideUp(200);
        return false;
    }
    var xqObj = $(".fl_area_list_xq");
    xqObj.hide();
    if (objThis.attr("load") == "loaded") {
        $(".area_xq_" + objThis.attr("data")).show();
        $(".fwdh_content_area_line_xq").slideDown(200);
        return false;
    }
    // var areaid = objThis.attr("data");
    if (obj != null && obj != "") {
        /* if (objThis.attr("status") == "exists") {
            return false;
        } */
        var basePath = "${base!}";
        $.ajax({
            type:"POST",
            dataType:"json",
            url:"/findAreaList.jspx",
            data:{"areaid":areaid},
            success:function(data) {
                if (data != null && data.length > 0) {
                    // alert(data);
                    var str = "<table> <tr>";
                    var tdCount = 0;
                    $.each(data, function(index, region) {
                        var areaname = region.name;
                        var style = "";
                       /*  if (areaname != null && areaname.length > 4) {
                            style="style='width:109px;'"
                        } */
                        var xqAreaObj = $("#area_xq_id_" + region.areaid);
                        if (xqAreaObj == null || xqAreaObj == "" || xqAreaObj.length <= 0) {
                            var a_text_length = region.name.length * 14;
                            if (a_text_length <= 14) {
                                a_text_length = 14;
                            }
                            a_text_length = "width:" + a_text_length + "px;";
                            tdCount ++; // padding-right: 42px;min-width:85px;max-width:115px;
                            str += "<td style='padding: 5px 8px 5px 8px;width:auto;' class='fl_area_list fl_area_list_xq area_xq_" + areaid + "' id='area_xq_id_" + region.areaid + "' layer='3' data='" + region.areaid + "' >"
                                + "<a style='text-align:left;height:20px;line-height:20px;" + a_text_length + "' class='font_area_xq' "+style+" target='_blank' href='/bmshowsearch.jspx?iszsbm=6&areaId="+region.areaid+"'onmouseover=\"_loadXzFromXq('#area_xq_id_" + region.areaid + "');showXzCj('"+region.areaid+"','7','fwdh_content_area_xz', '#area_xq_id_" + region.areaid + "')\">"
                                + region.name + "</a></td>";
                        }
                        if (tdCount % 9 == 0) { // 分行
                            str + "</tr><tr>";
                        }
                    })
                    str += "</tr></table>";
                    $(".fwdh_content_area_line_xq").slideDown(200);
                    $(objThis).attr("load","loaded");
                    obj.append(str);
                    /* if (tdCount > 9) {
                        $(".area_xq").css("height", "121px");
                    } */
                } else {
                    $(".fwdh_content_area_line_xq").slideUp(200);
                }
                $(".fwdh_content_area_line_xz,.fwdh_content_area_line_cj").slideUp(200);
                //obj.html("");
                //$(".fwdh_content_area_xz").html("");
                //$(".fwdh_content_area_cj").html("");
            },
            error:function(data) {
            }
        });
    }
}
/**
 * 显示县区的乡镇列表
 * @param areaid 区划id
 * @param iszsbm 直属部门：7--乡镇 8--村居
 * @param to
 * @param objThis
 */
function loadXzFromXq(areaid, iszsbm, to, objThis) {
    var obj = $("." + to);
    if ((obj == null || obj == "") && (objThis == null || objThis == "")) {
        return false;
    }
    objThis = $(objThis);
    var iszsbmClassName = "";
    $(".fl_area_list_xq_active").removeClass("fl_area_list_xq_active");
    iszsbmClassName = " fl_area_list_xz";
    objThis.addClass("fl_area_list_xq_active");
    $(".fwdh_content_area_cj").slideUp(200);
    $(".area_xz_li").hide();
    if (objThis.attr("load") == "loaded") {
          $(".area_xz_" + objThis.attr("data")).slideDown(200);
          $(".fwdh_content_area_line_xz").slideDown(200);
          var _obj = $(".area_xz_" + objThis.attr("data"));
          if (_obj == null || _obj.length <= 0) {
              $(".fwdh_content_area_line_xz").slideUp(200);
          }
          return false;
    }
    var index = layer.load();
    $.ajax({
        type:"POST",
        dataType:"json",
        url:"/findAreaList.jspx",
        data:{"areaid":areaid, "iszsbm":iszsbm},
        success:function(data) {
            if (data != null && data.length > 0) {
                // alert(data);
                var str = "<table><tr>";
                var tdCount = 0;
                $.each(data, function(index, dept) {
                    tdCount ++;
                    var xzname = dept.shortname;
                    var yxzname = xzname;
                    if (xzname != null) {
                        xzname = xzname.replace("人民政府", "");
                        xzname = xzname.replace("便民服务中心", "");
                        xzname = xzname.replace("服务中心", "");
                        xzname = xzname.replace("办事处", "");
                      if (xzname.indexOf("县") > 1) {
                            xzname = xzname.substring(xzname.indexOf("县")+1, xzname.length);
                        }
                        /*if (xzname.indexOf("市") > 1) {
                            xzname = xzname.substring(xzname.indexOf("市")+1, xzname.length);
                        } */
                    }
                    var a_text_length = xzname.length * 14;
                    if (a_text_length <= 14) {
                        a_text_length = 14;
                    }
                    a_text_length = "width:" + a_text_length + "px;"; // padding-right: 42px;min-width:85px;max-width:115px;
                    str += "<td style='padding: 5px 8px 5px 3px;width:auto;' class='fl_area_list area_xz_li area_xz_" + areaid + iszsbmClassName + "' data='" + dept.deptid + "' id='area_xz_td_" + dept.deptid + "'>"
                        +"<a title='" + yxzname + "' style='text-align:left;text-align:left;height:20px;line-height:20px;" + a_text_length + "' class='font_area_xq' onmouseover=\"_loadCjFromXz('#area_xz_td_" + dept.deptid + "');showCj('"+ dept.deptid +"','8','fwdh_content_area_cj', '#area_xz_td_" + dept.deptid + "')\" target='_blank' href='/permissionitem/" + dept.deptid + ".jspx?permtype=13'>" + xzname + "</a></td>";
                    /* if (tdCount % 9 == 0) {
                        str += "</tr><tr>";
                    } */
                });
                $(".fwdh_content_area_line_xz").slideDown(200);
                objThis.attr("load","loaded");
                str += "</tr></table>"
                obj.append(str);
            } else {
                $(".fwdh_content_area_line_xz").slideUp(200)
            }
            $(".fwdh_content_area_line_cj").slideUp(200)
            layer.close(index);
        },
        error:function(data) {
            layer.close(index);
        }
    });
}

/**
 * 显示村居
 * @param parentid
 * @param iszsbm
 * @param to
 * @param objThis
 * @returns {Boolean}
 */
function loadCjFromXz(parentid, iszsbm, to, objThis) {// 乡镇 ==> 村居
    var obj = $("." + to);
    if ((obj == null || obj == "") && (objThis == null || objThis == "")) {
        return false;
    }
    objThis = $(objThis);
    var iszsbmClassName = "";
    $(".fl_area_list_xz_active").removeClass("fl_area_list_xz_active");
    iszsbmClassName = "fl_area_list_cj";
    objThis.addClass("fl_area_list_xz_active");
    $(".area_cj_li").hide();
    if (objThis.attr("load") == "loaded") {
          $(".area_cj_" + parentid).slideDown(200);
          $(".fwdh_content_area_line_xz").slideDown(200);
          return false;
    }
    var index = layer.load();
    $.ajax({
        type:"POST",
        dataType:"json",
        url:"/findAreaList.jspx",
        data:{"parentid":parentid, "iszsbm":"8"},
        success:function(data) {
            if (data != null) {
                var str = "";
                $.each(data, function(index, dept) {
                    str += "<li class='area_cj_li fl_area_list "+ iszsbmClassName + " area_cj_" + parentid + "'><a target='_blank' href='/permissionitem/" + dept.deptid + ".jspx?permtype=13'>" + dept.shortname + "</a></li>";
                })
                $(".fwdh_content_area_line_cj").slideDown(200)
                $(".fwdh_content_area_cj").slideDown(200)
                objThis.attr("load","loaded");
                obj.append(str);
            } else {
                $(".fwdh_content_area_line_cj").slideUp(200)
                $(".fwdh_content_area_cj").slideUp(200)
            }
            layer.close(index);
        },
        error:function(data) {
            layer.close(index);
        }
    });
}
/**
 * 显示证照
 * @param areaid
 * @param objClassName
 * @param objThis
 * @returns {Boolean}
 */
function showZz(areaid, objClassName,objThis) {
    var obj = $(objClassName);
    if ((obj == null || obj == "") && (objThis == null || objThis == "")) {
        return false;
    }
    if ($(objThis).attr("status") == "exists") {
        return false;
    }
    var index = layer.load();
    obj.show();
    obj.html("");
    $.ajax({
        type:"POST",
        dataType:"json",
        url:"/findZzList.jspx",
        data:{"areaid":areaid},
        success:function(data) {
            if (data != null) {

                // 抽取行业列表
                var hylist = new Array();
                hylist = data.hy;
                var listIndex = 0;
                /* $.each(data, function(index, hy) {
                    if (hylist.indexOf(hy.hyname) <= -1) {
                        hylist[listIndex] = hy.hyname;
                        listIndex ++;
                    }
                }) */

                var str = "<ul class='zz_ul zz_ul_1' style='overflow-y: auto; width: 100%;'>";
                var dataRows = 0; // 行数
                var pageCount = 1; // 页数
                for (var i = 0; i <= hylist.length; i++) {
                    if (hylist[i] != null && hylist[i] != "") {
                        var _height = "26px";
                        /* if (i == 0) {
                            _height = "66px";
                        } */
                        str += "<li class='hy_li'>"+hylist[i].hyname+"：</li>";
                        str += "<li style='margin-left: 85px;margin-bottom:5px;'>";
                        str += "<table><tr>";
                        var fontSizeSum = 0;
                        var zzLiCount = 0;
                        //dataRows ++;
                        $.each(data.zz, function(index, zz) {
                            if (zz.hyid == hylist[i].hyid) {
                            	var zzname = zz.zzname;
                                if (zz.zzname.length > 6) {
                                    fontSizeSum += 6;
                                    zzname = zzname.substring(0,6) + "...";
                                } else {
                                    fontSizeSum += zz.zzname.length;
                                }
                                str += "<td class='fl_zz_list '><a title='"+zz.zzname+"' class='zz_li_a fwdh_search_txt'  toparent='zz_ul_" + pageCount + "' toparentClass='zz_ul' target='_blank' href='/bmshow_hy.jspx?zzid=" + zz.zzid + "&procType=2'>" + zzname + "</a></td>";
                                zzLiCount ++;
                                if (zzLiCount%5==0 || (zzLiCount == hylist[i].zzsum)) {
                                    dataRows ++;
                                    str += "</tr><tr>";
                                    if (dataRows % 14 == 0 && dataRows >= 14) {
                                       pageCount ++;
                                       str += "</tr></table>";
                                       str += "</li>";
                                       str += "</ul><ul style='display:none;overflow-y: auto; width: 100%;' class='zz_ul zz_ul_" +pageCount+ "'>";
                                       if (zzLiCount != hylist[i].zzsum) {
                                           str += "<li class='hy_li'>"+hylist[i].hyname+"：</li>";
                                           str += "<li style='margin-left: 85px;margin-bottom:5px;'>";
                                           str += "<table><tr>";
                                       }
                                    }
                                }
                            }
                        });
                        str += "</tr></table></li>";
                    }
                }
                str += "</ul>";
                if (pageCount > 1) { // 分页
                    var marginleft = (820 - pageCount * 40)/2;
                    str += '<div style="margin-top: 9px;width: 500px;">'
                        +  '<li class="fwdh_content_zt_li" style="width: 100%;height: 30px;padding-top: 0px;">'
                        +  '<div class="page_line_main" style="margin-left: ' + marginleft +'px;padding-left:0px;">';

                        // 页数
                        for (var ii = 1; ii <= pageCount; ii ++) {
                           if (ii == 1) {
                               str += '<div class="page_line_zz page_line_prev page_line_active" searchfrom="zz_ul_1" onclick="showCompanybsUl(\'.zz_ul\',\'.zz_ul_' + ii + '\',this,\'.page_line_zz\')">&nbsp;</div>'
                           } else {
                               str += '<div style="margin-left: 5px;" class="page_line_zz page_line_prev"  searchfrom="zz_ul_' + ii + '" onclick="showCompanybsUl(\'.zz_ul\',\'.zz_ul_' + ii + '\',this,\'.page_line_zz\')">&nbsp;</div>'
                           }
                        }
                        
                    str +=  '</div>'
                        +  '</li></div>';
                }
            }
            $(objThis).attr("status","exists");
            obj.html(str);
            layer.close(index);
        },
        error:function(data) {
            layer.close(index);
        }
    });
}

/**
 * 显示行业
 * @param areaid
 * @param objClassName
 * @param objThis
 * @returns {Boolean}
 */
function showHy(areaid, objClassName,objThis) {
    var obj = $(objClassName);
    if ((obj == null || obj == "") && (objThis == null || objThis == "")) {
        return false;
    }
    if ($(objThis).attr("status") == "exists") {
        return false;
    }
    obj.show();
    obj.html("");
    $.ajax({
        type:"POST",
        dataType:"json",
        url:"/findHyList.jspx",
        data:{"areaid":areaid},
        success:function(data) {
            if (data != null) {

                var dataRows = 1; // 行数
                var pageCount = 1; // 页数
                var str = "<ul class='hy_ul hy_ul_1'>";
                str += "<li><table><tr>";
                var areaId="${areaId!}";
                var hyindex = 0;
                $.each(data, function(index, hy) {
                    var _href = "href=\"/bmshow_hy.jspx?hyid=" + hy.hyid +"\"";
                    if (hy.hyid == 23) {
                        // _href = "/toDishui.jspx";
                    }
                    str += '<td><li class="rdfw_li" style="padding-right: 12px;padding-top: 16px;"><ul>'; // 
                    str +=  '<li style="height:60px;">';
                    str +=  '<a class="" target="_blank" '+_href +'>';
                    str +=  '<img width="60" src="' + hy.icon_url + '"/></a>';
                    str +=  '</li>';
                    str +=  '<li class="rdfw_li_text hy_ul_li_text fwdh_search_txt"  toparent="hy_ul_' + pageCount + '" toparentClass="hy_ul" >'+ hy.name + '</li>';
                    str +=  '</ul>';
                    str +=  '</li></td>';
                    hyindex ++;
                    if (hyindex % 8 == 0) {
                        str += '</tr><tr>'; // 换行
                        dataRows ++;
                        if (dataRows > 3 && dataRows % 4 == 0) {
                           pageCount ++;
                           str += "</tr></table></li>";
                           str += "</ul><ul style='display:none;' class='hy_ul hy_ul_" + pageCount + "'>";
                        }
                    }
                });
                str += "</tr></table></li></ul>";

                if (pageCount > 1) { // 分页
                    str += '<div style="margin-top: 9px;margin-left: 143px;width: 500px;">'
                        +  '<li class="fwdh_content_zt_li" style="width: 100%;height: 30px;padding-top: 0px;">'
                        +  '<div class="page_line_main">';

                        // 页数
                        for (var ii = 1; ii <= pageCount; ii ++) {
                           if (ii == 1) {
                               str += '<div searchfrom="hy_ul_1" class="page_line_hy page_line_prev page_line_active" onclick="showCompanybsUl(\'.hy_ul\',\'.hy_ul_' + ii + '\',this,\'.page_line_hy\')">&nbsp;</div>'
                           } else {
                               str += '<div searchfrom="hy_ul_' + pageCount + '" style="margin-left: 5px;" class="page_line_hy page_line_prev" onclick="showCompanybsUl(\'.hy_ul\',\'.hy_ul_' + ii + '\',this,\'.page_line_hy\')">&nbsp;</div>'
                           }
                        }

                    str +=  '</div>'
                        +  '</li></div>';
                }
            }
            $(objThis).attr("status","exists");
            obj.html(str);
        },
        error:function(data) {
        }
    });
}

// 切换显示事项列表
function showSxDiv(obj, idname, classname, deptid) {debugger;
    // layer.msg(idname);
    $("." + classname).hide();
    $("#" + idname).show();

    // 选项卡显示控制：三角形和红底
    //$(".active").removeClass("active");
    $.each($(".active[deptid]"), function() {
    	if ($(this).attr("deptid") == deptid) {
    		$(this).find(".img_sx_xxsjx").hide();
    		$(this).removeClass("active");
    	}
    });
    //$(".img_sx_xxsjx").hide();
    $(obj).parent().addClass("active");
    $(obj).parent().find("img").show();
}

/**
 * 按行业、证照加载事项列表
 * @param deptid
 * @param obj
 * @param showdiv
 * @param type 操作类型：1--按行业部门显示事项列表；2--按证照显示事项列表
 * @param value type为1时，value则表示为行业id；type为2时，value则表示为证照id
 * @returns {Boolean}
 */
function loadSx2(deptid, obj, showdiv, type, value) {

    $(".pid_div").hide(200);
    if ($(obj).attr("exists") == "exists") { // 防止重复加载
       $("#permissionitem_" + deptid).show(200); // 显示已经存在的事项列表

       // 显示上一次点击的事项类型选项卡所对应的事项列表
       $(".active[deptid='" + deptid + "'] a").click();
       return false;
    }
    var url = "";
    if ("1" == type) {
        url = "/findDeptListHy.jspx";
    } else if ("2" == type) {
        url = "/findDeptListZz.jspx";
    } else {
        layer.msg("参数异常！");
        return false;
    }
    var layerindex = layer.load();
    $.ajax({
        type:"POST",
        dataType:"json",
        url:url,
        data:{"deptid":deptid, "hyid":value,"zzid":value, "type":"3"},
        success:function(data) {
            if (data != null) {
                var divStr = "<div class='pid_div' id='permissionitem_" + deptid + "' style='width: 100%;display:block;"
                       +"'>"
                var __xzxklist = {
                    "1_1":"行政许可"
                    ,"2_4":"行政处罚"
                    ,"3_5":"行政强制"
                    ,"4_6":"行政征收"
                    ,"5_7":"行政给付"
                    ,"6_8":"行政裁决"
                    ,"7_9":"行政确认"
                    ,"8_14":"行政检查"
                    ,"9_11":"行政奖励"
                    ,"10_12":"其他权力"
                    ,"11_13":"公共服务"
                };
                var __htmlsx = "";
                var sxlist = new Array();
                var isExitDataXzxk = ""; // 存在数据的事项类型
                var tab_str = "";
                var _existsDataXzxkList = new Array(); // 存在数据的事项类型列表
                for (xzxk in __xzxklist) {
                    var _strXzxk = __xzxklist[xzxk];
                    var _xzxk = xzxk;
                    var str = "";
                    var isExitData = false;
                    var dataCount = 0;

                    // 抽取大项信息：大项名称、事项编号
                    var dxlist = new Array();
                    var sxbhList = ""; // 用英文逗号分隔多个事项编号
                    var dxCount = 1;
                    
                    xzxk = xzxk.substring(xzxk.indexOf("_")+1);
                    $.each(data, function(index, sx){
                        if (sx.xzxk == xzxk && sxbhList.indexOf(sx.sxbh) <= -1) {
                            // var sxObj = new Object();
                            // sxObj.sxbh = sx.sxbh;
                            // sxObj.sxname = sx.sxname;
                            if (_existsDataXzxkList != null && !_existsDataXzxkList.hasOwnProperty(_xzxk)) {
                            	_existsDataXzxkList[_xzxk]=_strXzxk;
                            }

                            // 获取子项数
                            var sxzxNum = 0;
                            var zxStr = "";
                            $.each(data, function(index, sx2){
                                if (sx.sxbh == sx2.sxbh) {
                                    sxzxNum ++;
                                    dataCount ++;
                                    zxStr += setSxInfo(sx2,sxzxNum);
                                    isExitData = true;
                                    if (isExitDataXzxk == "") {
                                        isExitDataXzxk = xzxk;
                                    }
                                }
                            });
                            var zxCountInfo = "(无子项)"; // 是否有子项、子项数情况信息
                            if (sxzxNum > 1) {
                                zxCountInfo = "(含" + sxzxNum + "个子项)";
                            } else {
                                zxStr = zxStr.replace("<font ", "<font style='display:none;' ");
                            }
                            zxCountInfo = "<span class='c_current'>" + zxCountInfo + "</span>";
                            str += '<div class="area_dept_div_dept_sx" >'
                                + "<table>"
                                + "<thead><tr><th class='pid_div_sx_th'> " + dxCount + "." + sx.sxname + zxCountInfo + "</th></tr></thead>";

                            str += "<tbody>" + zxStr + "</tbody></table></div>";
                            // sxObj.sxzxNum = sxzxNum;
                            // dxlist[dxCount] = sxObj;
                            dxCount ++;
                            sxbhList += "," + sx.sxbh;
                        }
                    });

                    // 将拼接好的html字符串添加到sxlist中
                    var divDisplay = "";
                    if (isExitDataXzxk == xzxk) {
                       divDisplay = "display: block;";
                    }
                    if (!isExitData) {
                       str = "<div class='pid_div_blank'>对应本部门权力清单暂无该类权力事项</div>";
                    } else {
                       str += "<div class='pid_div_blank'>共" + (dxCount - 1) + "个大项</div>";
                    }
                    __htmlsx += "<div style='" + divDisplay + "' class='hysx_sx_div' id='permissionitem_"+deptid+"_" + xzxk + "'>"
                             + str
                             + "</div>";
                }
                //divStr = divStr + str + "&nbsp;</div>";
                $(obj).attr("exists", "exists");
                var _activCount = 0;
                var _activ = new Array();
                var _activImg = new Array();
                for (xzxk in __xzxklist) {
                	var _xzxk = xzxk;
                    xzxk = xzxk.substring(xzxk.indexOf("_")+1);
                    if (isExitDataXzxk == xzxk) {
                        _activ[_xzxk] = "active";
                        _activImg[_xzxk] = "style='display: inline;'";
                    } else {
                        _activ[_xzxk] = "";
                        _activImg[_xzxk] = "";
                    }
                }
                var _html_tab = '<ul class="pid_div_ul">';
                for (var _xzxkObj in _existsDataXzxkList) {
                	var _xzxk = _xzxkObj.substring(_xzxkObj.indexOf("_") + 1);
                	_html_tab += '<li deptid="' + deptid + '" class="tabqh_' + _xzxkObj + ' ' + _activ[_xzxkObj]
                		      + '"><a href="javascript:void(0);" onclick="showSxDiv(this,\'permissionitem_'+deptid+'_'
                			  + _xzxk + '\',\'hysx_sx_div\',\'' + deptid + '\')">'
                			  + _existsDataXzxkList[_xzxkObj] + '</a>';
                	_html_tab += '<img class="img_sx_xxsjx" ' + _activImg[_xzxkObj] + ' src="/r/cms/www/blue_webhall/images/tz_sanjiao.png" width="19" height="10" alt=""></li>';
                }
                _html_tab += "</ul>";
                $(showdiv).append(divStr + _html_tab + __htmlsx +  "&nbsp;</div>");
                layer.close(layerindex);
            }
        },
        error:function(data) {
             layer.close(layerindex);
        }
    });
}
/**
 * 加载部门列表
 * @param areaid
 * @param obj
 * @param showDiv
 * @param totype
 * @param type 1--行业事项部门；2--证照事项部门
 * @param value
 * @returns {Boolean}
 */
function loadDepts2(areaid, obj, showDiv, totype,type,value) {
   $(".deptsdiv2").hide(200);
   $(".show_sx_bj").hide();
   $(".pid_div").hide(200);
   if (totype != "1") {
        $(".hysx_deptlist_sz_main").css("min-height","0px");
        $(".hysx_deptlist_sz").slideUp(100);
   }
   if ($(obj).attr("exists") == "exists") { // 防止重复加载
       $("#depts_" + areaid).show(200); // 显示已经存在的部门列表
       return false;
   }
   var url = "";
   if ("1" == type) {
       url = "/findDeptListHy.jspx";
   } else if ("2" == type) {
       url = "/findDeptListZz.jspx";
   } else {
       layer.msg("参数异常！");
       return false;
   }
   var layerIndex = layer.load();
    $.ajax({
        type:"POST",
        dataType:"json",
        url:url,
        data:{"areaid":areaid, "hyid":value,"zzid":value,"type":"2"},
        success:function(data) {
            if (data != null && data.length > 0) {
                var divStr = "<div class='deptsdiv2' id='depts_" + areaid + "' style='display:block;overflow: auto;"
                       +"'>"
                var str = "";
                $.each(data, function(index, dept) {
                    str += '<div class="area_dept_div_dept" id="deptid_' + dept.deptid + '" onclick="deptClick(\''+dept.deptid+'\',\'#deptid_' + dept.deptid + '\')" data="' + dept.deptid +'" title="' + dept.name + '">'
                        + dept.name
                        + '</div>';
                });
                divStr = divStr + str + "&nbsp;</div>";
                $(obj).attr("exists", "exists"); 
                $(showDiv).append(divStr);
                if (totype == "1") {
                    $(".hysx_deptlist_sz_main").css("min-height","100px");
                    $(".hysx_deptlist_sz").slideDown(200);
               } else {
                    $(".hysx_deptlist_xq_main").slideDown(200);
               }
            } else {
                $(".hysx_deptlist_sz_main").css("min-height","0px");
                $(".hysx_deptlist_sz").slideUp(100);
            }
            layer.close(layerIndex);
        },
        error:function(data) {
            layer.close(layerIndex);
        }
    });
}

/**
 * 按行业、证照加载事项列表（备份）
 * @param deptid
 * @param obj
 * @param showdiv
 * @param type 操作类型：1--按行业部门显示事项列表；2--按证照显示事项列表
 * @param value type为1时，value则表示为行业id；type为2时，value则表示为证照id
 * @returns {Boolean}
 */
function loadSx2_backup(deptid, obj, showdiv, type, value) {
    
    $(".pid_div").hide(200);
    if ($(obj).attr("exists") == "exists") { // 防止重复加载
        $("#permissionitem_" + deptid).show(200); // 显示已经存在的事项列表
        return false;
    }
    var url = "";
    if ("1" == type) {
        url = "/findDeptListHy.jspx";
    } else if ("2" == type) {
        url = "/findDeptListZz.jspx";
    } else {
        layer.msg("参数异常！");
        return false;
    }
    var layerindex = layer.load();
    $.ajax({
        type:"POST",
        dataType:"json",
        url:url,
        data:{"deptid":deptid, "hyid":value,"zzid":value, "type":"3"},
        success:function(data) {
            if (data != null) {
                var divStr = "<div class='pid_div' id='permissionitem_" + deptid + "' style='width: 100%;display:block;"
                +"'>"
                var __xzxklist = {
                     "1":"行政许可"
                    ,"4":"行政处罚"
                    ,"5":"行政强制"
                    ,"6":"行政征收"
                    ,"7":"行政给付"
                    ,"8":"行政裁决"
                    ,"9":"行政确认"
                    ,"14":"行政检查"
                    ,"11":"行政奖励"
                    ,"12":"其他行政权力"
                    ,"13":"公共服务"
                };
                var __htmlsx = "";
                var sxlist = new Array();
                var isExitDataXzxk = ""; // 存在数据的事项类型
                var tab_str = "";
                for (xzxk in __xzxklist) {
                    var str = "";
                    var isExitData = false;
                    var dataCount = 0;
                    $.each(data, function(index, sx) {
                        if (sx.xzxk == xzxk) {
                            dataCount ++;
                            isExitData = true;
                            if (isExitDataXzxk == "") {
                                isExitDataXzxk = xzxk;
                            }
                            // 到现场次数
                            var dxccsStr = "";
                            var dxccs = "";
                            if (sx.dxccs != null && sx.dxccs != "") {
                                dxccs = sx.dxccs.substring(1,2);
                            }
                            if (dxccs == 0 || dxccs == '0') {
                                dxccsStr = "本事项为全程网办事项，若您通过网上申请，不需要到现场即可办结";
                            } else if (dxccs == 1 || dxccs == '1') {
                                dxccsStr = "本事项为原件核验事项，若您通过网上申请，最多到现场1次即可办结";
                            } else if (dxccs == 2 || dxccs == '2') {
                                dxccsStr = "本事项为原件预审事项，若您通过网上申请，最多到现场2次即可办结";
                            } else if (dxccs == 3 || dxccs == '3') {
                                dxccsStr = "本事项为原件预审事项，若您通过网上申请，最多到现场2次即可办结";
                            }
                            
                            var bszn_url = ""; // 办事指南url
                            if (sx.qlxf != "2") {
                                bszn_url = " href='${base}/lawguide/" + sx.pid + ".jspx' target='_blank' ";
                            } else {
                                bszn_url = " href='#' onclick='showqlxf(\'" + sx.qlxftcnr + "\');return false;' ";
                            }

                            // 在线申请
                            var zxsq_url = "<a class='sx_bj_a ' target='_blank' ";
                            if (sx.qlxf == "1" || sx.qlxf == "2") {
                                zxsq_url += ' href="javascript:showqlxf(\'' + sx.qlxftcnr + '\');">在线申请</a>';
                            } else if ((sx.xzxk == "1" || sx.xzxk == "2" || sx.xzxk == "3" || sx.xzxk == "4"
                                || sx.xzxk == "5" || sx.xzxk == "6" || sx.xzxk == "7"|| sx.xzxk == "8"|| sx.xzxk == "9"
                                    || sx.xzxk == "10" || sx.xzxk == "11" || sx.xzxk == "12"|| sx.xzxk == "13"|| sx.xzxk == "14")
                                    && sx.sfwssb == "1") {
                                if (sx.wssbdz != null && sx.wssbdz != "" && sx.wssbdz != "无" && sx.wssbdz != sx.jiaotongtinghttp
                                        && sx.wssbdz != sx.jiaotongtinghttp
                                        && sx.wssbdz != sx.zfcxjsthttp
                                        && sx.wssbdz != sx.huanbaohttpone
                                        && sx.wssbdz != sx.huanbaohttptwo
                                        && sx.wssbdz != sx.gsxzsphttp) {
                                    var wssbdz = "";
                                    if (sx.wssbdz.indexOf("https") <= -1) {
                                        wssbdz = "http://";
                                    }
                                    zxsq_url += ' href="' + wssbdz + sx.wssbdz + '">在线申请</a>';
                                } else {
                                    zxsq_url += ' href="${base}/wssb/matter.jspx?sxid=' + sx.pid + '">在线申请</a>';
                                }
                            }
                            
                            // 我要咨询
                            var wyzx = "<a class='sx_bj_a' target='_blank' ";
                            if (sx.wszxdz != null && sx.wszxdz != "" && sx.wszxdz != "无") {
                                if (sx.wszxdz.indexOf("") < 0) {
                                    wyzx += "http://";
                                }
                                wyzx += sx.wszxdz;
                            } else {
                                wyzx += " href='${base}/hdjl/wszx/toadd.jspx?sxid="+sx.pid + "'";
                            }
                            wyzx += ">我要咨询</a>";
                            str += '<div class="area_dept_div_dept_sx" onmouseover="onhover_sx(this)">'
                                + "<table>"
                                + "<thead><tr><th class='pid_div_sx_th'> " + dataCount + "." + sx.sxzxname + "</th></tr></thead>"
                                + "<tr class='sxname'><td>" + sx.sxzxname + "</td></tr>"
                                + "<tr class='sxinfo' onmouseover='showSxBj(this);'>"
                                + "    <td>事项编码："
                                +          sx.sxbh + "-" + sx.sxzxbh
                                +          "&nbsp;&nbsp;&nbsp;&nbsp;" + dxccsStr
                                + "    </td>"
                                + "</tr>"
                                + "<tr class='show_sx_bj'>"
                                + "<td class='sx_bj_td'>"
                                + "<a class='sx_bj_a' " + bszn_url + ">办事指南</a>"
                                + zxsq_url
                                + '<a target="_blank" class="sx_bj_a" href="${base}/spsxbjpy/' + sx.pid + '.jspx">查看评价</a>'
                                + wyzx
                                + '<a target="_blank" class="sx_bj_a" href="${base}/businessinfolist/' + sx.pid + '.jspx">办件公告</a>'
                                + '<a target="_blank" class="sx_bj_a" href="http://58.16.65.112:88/?Temp=2025" target="_blank">邮寄送达</a>'
                                + "</td>"
                                + "</tr>"
                                + "</table>"
                                + '</div>';
                        }
                    });
                    
                    // 将拼接好的html字符串添加到sxlist中
                    var divDisplay = "";
                    if (isExitDataXzxk == xzxk) {
                        divDisplay = "display: block;";
                    }
                    if (!isExitData) {
                        str = "<div class='pid_div_blank'>对应本部门权力清单暂无该类权力事项</div>";
                    } else {
                        str += "<div class='pid_div_blank'>共"+dataCount+"个大项</div>";
                    }
                    __htmlsx += "<div style='" + divDisplay + "' class='hysx_sx_div' id='permissionitem_"+deptid+"_" + xzxk + "'>"
                    + str
                    + "</div>";
                }
                //divStr = divStr + str + "&nbsp;</div>";
                $(obj).attr("exists", "exists");
                var _activCount = 0;
                var _activ = new Array();
                var _activImg = new Array();
                for (xzxk in __xzxklist) {
                    
                    if (isExitDataXzxk == xzxk) {
                        _activ[_activCount] = "active";
                        _activImg[_activCount] = "style='display: inline;'";
                    } else {
                        _activ[_activCount] = "";
                        _activImg[_activCount] = "";
                    }
                    _activCount ++;
                }
                var _html_tab = '<ul class="pid_div_ul">'
                    + '<li class="tabqh_1 ' + _activ[0] + '"><a href="javascript:void(0);" onclick="showSxDiv(this,\'permissionitem_'+deptid+'_1\',\'hysx_sx_div\')">行政许可</a>'
                    + '<img class="img_sx_xxsjx" ' + _activImg[0] + ' src="/r/cms/www/blue_webhall/images/tz_sanjiao.png" width="19" height="10" alt=""></li>'
                    + '<li class="tabqh_4 ' + _activ[1] + '"><a href="javascript:void(0);" onclick="showSxDiv(this,\'permissionitem_'+deptid+'_4\',\'hysx_sx_div\')">行政处罚</a>'
                    + '<img class="img_sx_xxsjx" ' + _activImg[1] + ' src="/r/cms/www/blue_webhall/images/tz_sanjiao.png" width="19" height="10" alt=""></li>'
                    + '<li class="tabqh_5 ' + _activ[2] + '"><a href="javascript:void(0);" onclick="showSxDiv(this,\'permissionitem_'+deptid+'_5\',\'hysx_sx_div\')">行政强制</a>'
                    + '<img class="img_sx_xxsjx" ' + _activImg[2] + ' src="/r/cms/www/blue_webhall/images/tz_sanjiao.png" width="19" height="10" alt=""></li>'
                    + '<li class="tabqh_6 ' + _activ[3] + '"><a href="javascript:void(0);" onclick="showSxDiv(this,\'permissionitem_'+deptid+'_6\',\'hysx_sx_div\')">行政征收</a>'
                    + '<img class="img_sx_xxsjx" ' + _activImg[3] + ' src="/r/cms/www/blue_webhall/images/tz_sanjiao.png" width="19" height="10" alt=""></li>'
                    + '<li class="tabqh_7 ' + _activ[4] + '"><a href="javascript:void(0);" onclick="showSxDiv(this,\'permissionitem_'+deptid+'_7\',\'hysx_sx_div\')">行政给付</a>'
                    + '<img class="img_sx_xxsjx" ' + _activImg[4] + ' src="/r/cms/www/blue_webhall/images/tz_sanjiao.png" width="19" height="10" alt=""></li>'
                    + '<li class="tabqh_8 ' + _activ[5] + '"><a href="javascript:void(0);" onclick="showSxDiv(this,\'permissionitem_'+deptid+'_8\',\'hysx_sx_div\')">行政裁决</a>'
                    + '<img class="img_sx_xxsjx" ' + _activImg[5] + ' src="/r/cms/www/blue_webhall/images/tz_sanjiao.png" width="19" height="10" alt=""></li>'
                    + '<li class="tabqh_9 ' + _activ[6] + '"><a href="javascript:void(0);" onclick="showSxDiv(this,\'permissionitem_'+deptid+'_9\',\'hysx_sx_div\')">行政确认</a>'
                    + '<img class="img_sx_xxsjx" ' + _activImg[6] + ' src="/r/cms/www/blue_webhall/images/tz_sanjiao.png" width="19" height="10" alt=""></li>'
                    + '<li class="tabqh_14 ' + _activ[7] + '"><a href="javascript:void(0);" onclick="showSxDiv(this,\'permissionitem_'+deptid+'_14\',\'hysx_sx_div\')">行政检查</a>'
                    + '<img class="img_sx_xxsjx" ' + _activImg[7] + ' src="/r/cms/www/blue_webhall/images/tz_sanjiao.png" width="19" height="10" alt=""></li>'
                    + '<li class="tabqh_11 ' + _activ[8] + '"><a href="javascript:void(0);" onclick="showSxDiv(this,\'permissionitem_'+deptid+'_11\',\'hysx_sx_div\')">行政奖励</a>'
                    + '<img class="img_sx_xxsjx" ' + _activImg[8] + ' src="/r/cms/www/blue_webhall/images/tz_sanjiao.png" width="19" height="10" alt=""></li>'
                    + '<li class="tabqh_12 ' + _activ[9] + '"><a href="javascript:void(0);" onclick="showSxDiv(this,\'permissionitem_'+deptid+'_12\',\'hysx_sx_div\')">其他行政权力</a>'
                    + '<img style="left: 39px;" ' + _activImg[9] + ' class="img_sx_xxsjx" src="/r/cms/www/blue_webhall/images/tz_sanjiao.png" width="19" height="10" alt=""></li>'
                    + '<li class="tabqh_13 ' + _activ[10] + '"><a href="javascript:void(0);" onclick="showSxDiv(this,\'permissionitem_'+deptid+'_13\',\'hysx_sx_div\')">公共服务</a>'
                    + '<img class="img_sx_xxsjx" ' + _activImg[10] + ' src="/r/cms/www/blue_webhall/images/tz_sanjiao.png" width="19" height="10" alt=""></li>'
                    + '<div class="clear"></div>'
                    + '</ul>';
                $(showdiv).append(divStr + _html_tab + __htmlsx +  "&nbsp;</div>");
                layer.close(layerindex);
            }
        },
        error:function(data) {
            layer.close(layerindex);
        }
    });
}

/**
 * 拼接事项信息
 * @param sx 事项信息
 * @param zxxh 子项序号
 * @returns {String}
 */
function setSxInfo(sx, zxxh) {
    if (zxxh == null || zxxh == "") {
        zxxh = "";
    } else {
        zxxh = "<font class='sx_xh'>(" + zxxh + ")</font>";
    }
    var sxInfo = "";
    // 到现场次数
    var dxccsStr = "";
    var dxccs = "";
    if (sx.dxccs != null && sx.dxccs != "") {
        dxccs = sx.dxccs.substring(1,2);
    }
    if (dxccs == 0 || dxccs == '0') {
        dxccsStr = "本事项为全程网办事项，若您通过网上申请，不需要到现场即可办结";
    } else if (dxccs == 1 || dxccs == '1') {
        dxccsStr = "本事项为原件核验事项，若您通过网上申请，最多到现场1次即可办结";
    } else if (dxccs == 2 || dxccs == '2') {
        dxccsStr = "本事项为原件预审事项，若您通过网上申请，最多到现场2次即可办结";
    } else if (dxccs == 3 || dxccs == '3') {
        dxccsStr = "本事项为原件预审事项，若您通过网上申请，最多到现场2次即可办结";
    }

    var bszn_url = ""; // 办事指南url
    if (sx.qlxf != "2") {
        bszn_url = " href='/lawguide/" + sx.pid + ".jspx' target='_blank' ";
    } else {
        bszn_url = " href='#' onclick='showqlxf(\'" + sx.qlxftcnr + "\');return false;' ";
    }

    // 在线申请
    var zxsq_url = "<a class='sx_bj_a ' target='_blank' ";
    if (sx.qlxf == "1" || sx.qlxf == "2") {
        zxsq_url += ' href="javascript:showqlxf(\'' + sx.qlxftcnr + '\');">在线申请</a>';
    } else if ((sx.xzxk == "1" || sx.xzxk == "2" || sx.xzxk == "3" || sx.xzxk == "4"
        || sx.xzxk == "5" || sx.xzxk == "6" || sx.xzxk == "7"|| sx.xzxk == "8"|| sx.xzxk == "9"
        || sx.xzxk == "10" || sx.xzxk == "11" || sx.xzxk == "12"|| sx.xzxk == "13"|| sx.xzxk == "14")
        && sx.sfwssb == "1") {
        if (sx.wssbdz != null && sx.wssbdz != "" && sx.wssbdz != "无" && sx.wssbdz != sx.jiaotongtinghttp
             && sx.wssbdz != sx.jiaotongtinghttp
             && sx.wssbdz != sx.zfcxjsthttp
             && sx.wssbdz != sx.huanbaohttpone
             && sx.wssbdz != sx.huanbaohttptwo
             && sx.wssbdz != sx.gsxzsphttp) {
             var wssbdz = "";
             if (sx.wssbdz.indexOf("https") <= -1) {
                wssbdz = "http://";
             }
            zxsq_url += ' href="' + wssbdz + sx.wssbdz + '">在线申请</a>';
        } else {
            zxsq_url += ' href="/wssb/matter.jspx?sxid=' + sx.pid + '">在线申请</a>';
        }
    }

    // 我要咨询
    var wyzx = "<a class='sx_bj_a' target='_blank' ";
    if (sx.wszxdz != null && sx.wszxdz != "" && sx.wszxdz != "无") {
        if (sx.wszxdz.indexOf("") < 0) {
            wyzx += "http://";
        }
        wyzx += sx.wszxdz;
    } else {
        wyzx += " href='/hdjl/wszx/toadd.jspx?sxid="+sx.pid + "'";
    }
    wyzx += ">我要咨询</a>";
    sxInfo += "<tr class='tr_hover' onmouseout=\"$('.show_sx_bj').hide();\" onmouseover=\"showSxBj2('" + sx.sxbh + "_" + sx.sxzxbh + "')\">"
        + "<td><table style='table-layout: fixed;'><tbody>"
        + "<tr class='sxname showDown'><td style='line-height: 22px;'>" + zxxh + "" + sx.sxzxname + "</td></tr>"
        + "<tr class='sxinfo showDown'>"
        + "    <td>事项编码："
        +          sx.sxbh + "-" + sx.sxzxbh
        +          "&nbsp;&nbsp;&nbsp;&nbsp;" + dxccsStr
        + "    </td>"
        + "</tr>"
        + "<tr class='show_sx_bj' onmouseover='showThis(this);' id='" + sx.sxbh + "_" + sx.sxzxbh + "'>"
        + "<td class='sx_bj_td'>"
        + "<a class='sx_bj_a' " + bszn_url + ">办事指南</a>"
        + zxsq_url
        + '<a target="_blank" class="sx_bj_a" href="/spsxbjpy/' + sx.pid + '.jspx">查看评价</a>'
        + wyzx
        + '<a target="_blank" class="sx_bj_a" href="/businessinfolist/' + sx.pid + '.jspx">办件公告</a>'
        + '<a target="_blank" class="sx_bj_a" href="http://58.16.65.112:88/?Temp=2025" target="_blank">邮寄送达</a>'
        + "</td>"
        + "</tr>"
        + "</tbody></table></td></tr>";
    return sxInfo;
}
/**
 * 显示事项办件信息（按钮）
 * @param obj
 */
function showSxBj2(toDiv) {
    //$(".show_sx_bj").hide();
    $("#" + toDiv).show(200);
}
function showThis(obj) {
    $(obj).show();
}
function showqlxfTop(qlxftcnr){
    if (qlxftcnr == null || qlxftcnr == "") {
         qlxftcnr = "该事项已下放！";
    }
    layer.alert(qlxftcnr, {
      icon: 7,
      skin: 'layer-ext-moon' //该皮肤由layer.seaning.com友情扩展。关于皮肤的扩展规则，去这里查阅
    });
}

//注册数
function viewZcs(obj) {
    var objThis = $(obj);
    $.ajax({
        url:"/findZcs.jspx",
        type:"POST",
        data:{},
        dataType:"json",
        success:function(data) {
            if (data != null) {
                objThis.text(data);
            }
        },
        error:function() {
        }
    });
}

/**
 * 加载首页专用的js
 * 
 */
function loadHomeFunction() {
    var timmerRdfwGzdt; // 定时任务

    // 首页：热点服务、工作动态选项卡切换
    $(".home_tab").click(function() {
        /*if (timmerRdfwGzdt != null && timmerRdfwGzdt != "") {
            clearTimeout(timmerRdfwGzdt);
        }*/
        var obj = $(this);
        /*timmerRdfwGzdt = setTimeout(function() {
            selectRdfwGzdtTab(obj, "home_tab_active");
        }, 500);*/
        selectRdfwGzdtTab(obj, "home_tab_active");
    });

    // 首页：工作动态 翻页
    $(".gzdt_content_list_page").click(function() {
        var _str = $(this).attr("to");
        if (_str != null && "" != _str) {
           var _str = _str.split(",");
           var _divObj = $("." + _str[0]);
           var index = parseInt(_str[1] - 1);
           _divObj.hide();
           $(".gzdt_content_list_page_active").removeClass("gzdt_content_list_page_active");
           _divObj.eq(index).show();
           $(this).addClass("gzdt_content_list_page_active");
        }
        
    });

    // 解决ie下专题专栏图片之间没有间隔的问题
    if (!!window.ActiveXObject || "ActiveXObject" in window) {
        $(".home_ztzl_li").css("margin-bottom","7px");
    }

}
// 首页-热点服务/工作动态：切换选项卡
function selectRdfwGzdtTab(obj, classname) {
    $("." + classname).removeClass(classname);
    obj.addClass(classname);
    var contentDiv = $("#" + $(obj).attr("to"));
    contentDiv.parent().find(".rdfw_gzdt").hide();
    contentDiv.show();
}

// 获取热点服务事项
function findHotSx(areaid, writeToDivId) {
    var url = "/findHotPermissionitem.jspx";
    $.ajax({
        url: url,
        type: "POST",
        dataType: "json",
        data: {
            "areaid": areaid
        },
        success: function(data) {
            if (data != null && "" != null) {
                var _html = ""; // html代码
                $.each(data, function(index, sx) {
                    if (index > 6) {
                        _html += '<a class="L_but_blue_1 mo_current" title="查看更多热点事项" style="float: left; margin-top: 2px; line-height: 20px;" href="/isHotPermissionitemList.jspx?areaid=' + areaid + '" target="_blank">更多</a>';
                        return false;
                    }
                    var sxname = sx.sxzxname;
                    if (sxname == null || "" == sxname) {
                        sxname = sx.sxname;
                    }
                    var sxnameShort = sxname;
                    if (sxname != null && sxname.length > 25) {
                        sxnameShort = sxnameShort.substring(0,24) + "...";
                    }
                    var openBszn = "onclick=\"window.open('/lawguide/" + sx.sxid + ".jspx');\""; // 打开办事指南
                    if ("2" == sx.qlxf) {
                        openBszn = "onclick=\"showqlxfTop('" + sx.qlxftcnr + "');\"";
                    }
                    //_html += "<li class='rdfw_bszn' title='" + sxname + "' " + openBszn + ">" + sxnameShort + "</li>";
                    if (sxname != null && sxname.length > 16) {
                        sxnameShort = sxnameShort.substring(0,16) + "...";
                    }
                    var openBszn = "onclick=\"window.open('/lawguide/" + sx.sxid + ".jspx');\""; // 打开办事指南
                    if ("2" == sx.qlxf) {
                        openBszn = "onclick=\"showqlxfTop('" + sx.qlxftcnr + "');\"";
                    }
                    _html += "<li class='rdfw_bszn' style='cursor: initial;position: relative;'>"
                        + "<font title='" + sxname + "'>" + sxnameShort + "</font>"
                        + "<font class='L_but_blue_1 hotsx_btn_sq'"
                        + openBszn + ">申请</font></li>";
                });
                if (_html != null && "" != _html) {
                    _html = "<ul>" + _html + "</ul>";
                    $("#" + writeToDivId).html(_html);
                } else {
                    $("#" + writeToDivId).html("暂无内容");
                }
            }
        },
        error: function() {
            
        }
    });

}

/**
 * 加载js： 会员中心专用
 */
function loadJsForMember() {
    $(function() {
        $(".new_leftmenu a:first-child").click(function() {
            var classAttr = $(this).find("font").attr("class");
            if (classAttr != null && classAttr.indexOf("show") > -1) {
                $(this).find("font").text("+");
                $(this).find("font").removeClass("show");
                $(this).parent().find("ul").hide(300);
            } else {
                $(".new_leftmenu ul").hide(300);
                $(".new_leftmenu font").text("+");
                $(".new_leftmenu font").removeClass("show");
                $(this).find("font").text("-");
                $(this).find("font").addClass("show");
                $(this).parent().find("ul").show(300);
            }
        });
    });
}

/**
 * 点击全文检索结果统计的数字跳到详细页面
 */
var newhome = {};
newhome.toSearchAll = function(areaid, sxname) {
    
}

/**
 * 加载办理对象页面专用js
 */
function loadBldxPage() {
    $(".fwdx_btn_more").click(function() {
        var className = $(this).attr("class");
        if (className != null && "" != className) {
            if (className.indexOf("show") > -1) {
                $(this).removeClass("show");
                $(this).parent().css("height", "27px");
                $(this).html('更多<img src="/r/cms/www/blue_webhall/images/more.png"/>');
            } else {
                $(this).addClass("show");
                $(this).html('收起<img src="/r/cms/www/blue_webhall/images/less.png"/>');
                $(this).parent().css("height", "auto");
            }
        }
    });
    $(".them_type").click(function() {
        var className = $(this).attr("class");
        /*if (className !=null &&  className.indexOf("ptcs") > -1) {
            $(".ptcs").removeClass("them_type_active");
        } else {
            $(".them_type_active").removeClass("them_type_active");
        }*/
        $(this).parent().find("li").removeClass("them_type_active");
        $(this).addClass("them_type_active");
    });
    
    // 默认显示第一各部门
    $(".them_type_dept").eq(0).find("a").click();
}
function autoHeightForIframe() {
    document.getElementById("sxlist_iframe").height=0;
    document.getElementById("sxlist_iframe").height=document.getElementById("sxlist_iframe").contentWindow.document.body.scrollHeight;
    document.getElementById("sxlist_iframe").width=1180;
    if (document.getElementById("sxlist_iframe").height > 100) {
        //$(".fwdx_sort").show();
    }
    if (document.getElementById("sxlist_iframe").height < 100) {
        document.getElementById("sxlist_iframe").height = 100;
    }
    // document.getElementById("sxlist_iframe").width=document.getElementById("sxlist_iframe").contentWindow.document.body.scrollWidth;
}
/**
 * 办理对象：查询事项
 */
newhome.toSearchSx = function (type, sortcode, deptid, areaId, ptcs, orderby) {

    // 设置通用参数
    if (deptid != null && "" != deptid && deptid.length > 0) {
        $("#search_deptid").val(deptid);
    } else if ("3" != type) { // 清空
        $("#search_deptid").val("");
    }
    if (sortcode != null && "" != sortcode && sortcode.length > 0) {
        $("#search_sortcode").val(sortcode);
    } else if ("3" != type) { // 清空
        $("#search_sortcode").val("");
    }
    if ("3" == type) {
        areaId = "";
    }

    $(".fwdx_sort").hide();
    var url = "/bldxpermissionitem.jspx";
    url += "?searchType=" + type;
    url += "&sortcode=" + $("#search_sortcode").val();
    url += "&deptid=" + $("#search_deptid").val();
    url += "&areaId=" + areaId;
    url += "&ptcs=" + ptcs;
    url += "&orderby=" + orderby;
    var obj = document.getElementById("sxlist_iframe");
    var doc = obj.contentDocument || document.frames["sxlist_iframe"].document;
    doc.body.innerHTML = "<div style='width: 150px; text-align: center; margin: 9px auto; box-shadow: 1px 1px 10px 0px #a1a1a1; height: 50px; padding: 25px 0 0 0;'>加载中。。。</div>";
    document.getElementById("sxlist_iframe").src = url;
}

/**
 * 办理对象：查询事项
 */
newhome.toSearchSx2 = function (param) {
    
    if (param == null || param == "" || param.length < 1) {
        layer.msg("参数异常");
        return false;
    }

    $(".fwdx_sort").hide();
    var url = "/bldxpermissionitem.jspx";
    url += param;
    var obj = document.getElementById("sxlist_iframe");
    var doc = obj.contentDocument || document.frames["sxlist_iframe"].document;
    doc.body.innerHTML = "<div style='width: 150px; text-align: center; margin: 9px auto; box-shadow: 1px 1px 10px 0px #a1a1a1; height: 50px; padding: 25px 0 0 0;'>加载中。。。</div>";
    document.getElementById("sxlist_iframe").src = url;
}
/**
 * 办理对象：设定查询条件
 * @param paramname 参数名
 * @param labelStr 显示内容
 * @param value 设定值
 */
newhome.toSetSearch = function(id,paramname, labelStr, value) {
    var _bar = $("#search_param_bar .selected_content");
    if (_bar != null && "" != _bar && _bar.length > 0) {
        _bar.parent().show();
        var _param = $("#" + id);
        if (_param != null && _param != "" && _param.length > 0) {
            _param.attr("value", value);
            _param.html(labelStr + "<label class='searchparam_close' onclick=\"newhome.removeParam(this, '" + id + "');\">X</label>");
        } else {
            _bar.append("<font class='searchparam' id='" + id + "' value='" + value + "'>" + labelStr
                    + "<label class='searchparam_close' onclick=\"newhome.removeParam(this, '" + id + "');\">X</label>"
                    + "</font>");
        }
    }

    // 获取参数
    var param = "";
    var sortcode = ""; // 主题分类code
    $(".searchparam").each(function() {
        if (param == null || "" == param) {
            param = "?";
        } else {
            param += "&";
        }
        var _id = $(this).attr("id");
        if (_id != null && _id.indexOf("sortcode") > -1) {
            if (sortcode == null || sortcode == "") {
                sortcode = $(this).attr("value");
            } else {
                sortcode += "," + $(this).attr("value");
            }
        } else {
            param += $(this).attr("id") + "=" + $(this).attr("value");
        }
    });
    if (sortcode != null && sortcode != "" && sortcode.length > 0) {
        if (param != null && param != "" && param.length > -1) {
            param += "&sortcode=" + sortcode;
        } else {
            param += "?sortcode=" + sortcode;
        }
    }

    // 获取事项信息列表
    newhome.toSearchSx2(param);
}
// 移除条件
newhome.removeParam = function(objThis, id) {
   $(objThis).parent().remove();
   $("li[from='" + id + "']").removeClass("them_type_active");
   var _bar = $("#search_param_bar .selected_content");
   var _param = _bar.find("font");
   if (_param == null || _param == "" || _param.length < 1) {
       _bar.parent().hide();
       // 默认显示第一各部门
       newhome.toSearchSx2("?areaid=" + $("#search_areaid").val() + "&201710312313");
       return false;
   }
   
   // 获取参数
   var param = "";
   var sortcode = ""; // 主题分类code
   $(".searchparam").each(function() {
       if (param == null || "" == param) {
           param = "?";
       } else {
           param += "&";
       }
       var _id = $(this).attr("id");
       if (_id != null && _id.indexOf("sortcode") > -1) {
           if (sortcode == null || sortcode == "") {
               sortcode = $(this).attr("value");
           } else {
               sortcode += "," + $(this).attr("value");
           }
       } else {
           param += $(this).attr("id") + "=" + $(this).attr("value");
       }
   });
   if (sortcode != null && sortcode != "" && sortcode.length > 0) {
       if (param != null && param != "" && param.length > -1) {
           param += "&sortcode=" + sortcode;
       } else {
           param += "?sortcode=" + sortcode;
       }
   }

   // 获取事项信息列表
   newhome.toSearchSx2(param);
}

//函数说明：合并指定表格（表格id为_w_table_id）指定列（列数为_w_table_colnum）的相同文本的相邻单元格  
//参数说明：_w_table_id 为需要进行合并单元格的表格的id。如在HTMl中指定表格 id="data" ，此参数应为 #data
//参数说明：_w_table_colnum 为需要合并单元格的所在列。为数字，从最左边第一列为1开始算起。  
newhome._w_table_rowspan = function(_w_table_id,_w_table_colnum){
    _w_table_firsttd = "";
    _w_table_currenttd = "";
    _w_table_SpanNum = 0;
    _w_table_Obj = $(_w_table_id + " tr td:nth-child(" + _w_table_colnum + ")");

    _w_table_Obj.each(function(i){

        // 处理模版渲染数据行合并后的合并情况
        var objThis = $(this);
        if (_w_table_colnum == 5 && $(this).parent().find("td").length < 5) {
            objThis = objThis.parent().find("td:nth-child(4)");
        }
        if(i==0){
            _w_table_firsttd = objThis;
            _w_table_SpanNum = 1;
        }else{
            _w_table_currenttd = objThis;
            if(_w_table_firsttd.text()==_w_table_currenttd.text()){
                _w_table_SpanNum++;
                _w_table_currenttd.hide(); //remove();
                _w_table_firsttd.attr("rowSpan",_w_table_SpanNum);
            }else{
                _w_table_firsttd = objThis;
                _w_table_SpanNum = 1;
            }
        }
    });
}

newhome.loadJzbmsyxxhxtManagePage = function() {
    
}
/**
 * 数据表格自适应显示
 * 概述：
 * 1.仅当数据table的高度大于外层div的高度时才显示垂直滚动条；
 * 2.出现垂直滚动条后，题头的table宽度需要重设成数据区域table的宽度（滚动条占用了一定的宽度）
 */
newhome.datadivautoView = function  () {
    var titleTable = $(".table_v1");
    var dataDiv = $(".div_v2");
    var dataTable = $(".table_v2");

    if (dataDiv != null && dataTable != null
        && dataDiv.height() < dataTable.height()) {
        dataDiv.css("overflow-y", "scroll");
        var tableWidth = dataTable.width();
        titleTable.css("width", tableWidth + "px");
        dataTable.css("width", tableWidth + "px");
    } else {
        dataDiv.css("overflow-y", "hidden");
        dataTable.css({"width": titleTable.width() - 1 + "px"});
        titleTable.css({"width": dataTable.width() + 1 + "px"});
    }

    $(".merge_qd a").click(function() {
        layer.load();
    });
}

newhome._gotoPage = function(pageNo, searchUrl) {
    try {
        var tableForm = document.getElementById("tableForm");
        $("input[name=pageNo]").val(pageNo);
        tableForm.action = searchUrl;
        tableForm.onsubmit = null;
        tableForm.submit();
    } catch (e) {
        alert('_gotoPage(pageNo)方法出错');
    }
}

/**
 * 参数统一check与弹出提示信息函数
 */
newhome.checkparamLayer = function (obj, msg) {
	if (obj == null || obj.val() == null || obj.val() == "" || obj.val().length < 1) {
        layer.tips(msg, obj, {
            tips: [4, 'red'],
            time: 4000,
            tipsMore: true
        });
        obj.focus();
        return false;
    }
	return true;
}

newhome.tipsForError = function(obj, msg) {
	if (!newhome.isEmpty(obj)) {
		layer.tips(msg, obj, {
			tips: [4, 'red'],
			tipsMore: true,
			time: 4000
		});
		obj.focus();
	} else {
		layer.msg(msg, {
			tips: [4, 'red'],
			tipsMore: true,
			time: 4000
		});
	}
    return false;
};

// 字符空判断
newhome.isEmpty = function(obj) {
	return obj == null || "" == obj || obj.length <= 0;
}

/*---------------------------------------------------*/
newhome.gstips = function() { // 工商页面弹窗
	var _tel = "0851-85850068";
	var _msghtml = "有关工商的任何业务申请，遇到问题或者需要咨询相关信息，请联系如下工商技术支持电话：<br/><br/>" + _tel;		
	var _msg01 = "<font class='msg_layer'>" + _msghtml + "</font>";
	var msg_layer = layer.alert(_msg01, {
		title: false,
		area:['50%','auto'],
		/*btn:['确认'],*/
		btn: false,
		time:5000,
		closeBtn:0,
		icon: 7,
		yes: function() {
			layer.close(msg_layer);
			$("body").append("<div class='msg_layer_div'><div class='msg_icon_div' status='0'>工商技术支持电话<br/> " + _tel + "</div>");
			var _msg_icon_div = $(".msg_icon_div");
			/* window.setInterval(function(){						
				var status = _msg_icon_div.attr("status");
				if ("0" == status) {
					_msg_icon_div.css("color", "green");
					_msg_icon_div.attr("status", "1");
				} else {
					_msg_icon_div.css("color", "red");
					_msg_icon_div.attr("status", "0");
				}
			}, 500);
			 */
		}
	});
	$("body").append("<div class='msg_layer_div'><div class='msg_icon_div' status='0'>工商技术支持电话<br/> " + _tel + "</div>");
}