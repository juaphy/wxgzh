if(!_CaPtChA_Flag){
	var _CaPtChA_Flag = true;
	var captchaInit = function(){
		_captcha_obj.init();
	}
	var addCaptchaError = function(id,msg){
		_captcha_obj.addCaptchaError(id,msg);
	}
    var syncCaptcha = function(captcha_id,submitfnuc){
    	if(captcha_id=="noCaptcha"){
    		return true;
    	}
		var orgid = $("#"+captcha_id).attr("oid"); 
		if(!orgid){
			var orgid = captcha_id+"_org";
			$("#"+captcha_id).attr("name",orgid);
			$("#"+captcha_id).attr("id",orgid);
			$("#"+orgid).after("<input type='hidden' oid='"+orgid+"' id='"+captcha_id+"' name='"+captcha_id+"'>");
		}
		var captcha = $("#"+orgid).val();
		if(captcha==""){
			alert("请输入验证码！");
			$("#"+orgid).focus();
			return false;
		}
		$("#"+captcha_id).val("");
		$.post("/login/captcha.do",{captcha:captcha},function(data){
			var rv = data["valivalue"];
			if(rv==""){
				alert("验证码错误！") 
				$("#"+orgid).focus();
			}else{
				$("#"+captcha_id).val(rv);
				submitfnuc(rv);
			}
		},"json");
	}

	var _captcha_ = function(){
		this.hasDiv = false;
		this.init=function(){
			this.createCaptchaDiv();
			var thisobj = this;
			$("[captcha='true']").each(function(){
				thisobj.addCaptchaEvent(this); 
				$(this).attr("autocomplete","off");
			})
			
		};
		this.createCaptchaDiv = function(){
			var div = '<div id="__captcha_div__" style="border:1px solid #888;background-color: #FFF;width: 120px;height:50px; text-align: center;position: absolute; display: none"></div>';
			if(!this.hasDiv){
				this.hasDiv = true;
				$("body").append(div);
			}
		};
		this.addCaptchaError = function (id,msg){
			var tid = id+"_error";
			if($("#"+tid).size()==0){
				$("#"+id).after("<span id='"+tid+"'></span>");
			}
			$("#"+tid).append(msg);
		};
		this.addCaptchaEvent = function (obj){
			$(obj).focus(this.showCaptchaDiv);
			$(obj).blur(this.hideCaptchaDiv);
		};
		this.hideCaptchaDiv = function (){
			$("#__captcha_div__").hide();
		};
		this.showCaptchaDiv = function (){
			$(this).val("")
			var offset = $(this).offset();
			var height = $(this).height();
			$("#__captcha_div__").empty();
			$("#__captcha_div__").append("<img onclick=\"this.src='/captcha.svl?d='+new Date()*1\"/>");
			$("#__captcha_div__").show();
			$("#__captcha_div__").css({"top":offset.top/1+height/1+5,"left":offset.left});
			$("#__captcha_div__ img").click();
		}
	}
	var _captcha_obj = new _captcha_();
}