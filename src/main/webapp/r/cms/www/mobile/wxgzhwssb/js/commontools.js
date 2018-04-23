if(__CoMmOntools__sataus__==null){
	/**
	 * 常用工具JS
	 * 现有功能：
	 * １：xml的写入
	 */
	
	/***　xml　插入*/
	var __CoMmOntools__sataus__ = true;
	String.prototype.toXmlNode = function (name){
		return createNodeItem(name,this,"node");
	}
	String.prototype.toXmlAttr = function(name){
		return createNodeItem(name,this,"attr");
	}
	function getDoc(){
		var doc;
		if($.browser.msie){
			doc = new ActiveXObject("Microsoft.XMLDOM");
		}else{
			doc = document.implementation.createDocument("","",null);
		}
		return doc;
	}
	
	function createNodeItem(name,value,type){
		var doc = getDoc();
		if(type=="node"){
			var item = doc.createElement(name);
			if(value!=""){
				item.appendChild(doc.createTextNode(value));
			}
		}else{
			var item = doc.createAttribute(name);
			item.value = value;
		}
		return item;
	}
	/**
	 * 将节点插入XML中，
	 * 第一个参数为　被插入的节点
	 * 最后一个参数为　插入的类型：　node和attr
	 * 中间可以有N个参数，都为需要插入的节点
	 */
	function insertXml(){
		if(arguments.length>=2){
			var rootItem = arguments[0];
			var type = arguments[arguments.length-1];
			for(var i=1 ;i < arguments.length-1; i++){
				if(type=="node"){
					//rootItem.appendChild();
					$(rootItem).append(arguments[i]);
				}else{
					rootItem.setAttributeNode(arguments[i]);
				}
			}
		}
	}
	
	var string2Xml = function(xmlstr){
		var xml;
		if(window.ActiveXObject){
			xml = new ActiveXObject("Microsoft.XMLDOM");
			xml.async = false;
			xml.loadXML(xmlstr);
		}else{
			xml = (new DOMParser()).parseFromString(xmlstr, "text/xml");
		}
		return xml;
	}
	var xml2String = function(xmlobj){
		var xmlStr="";
		if(window.ActiveXObject){
	      xmlStr = xmlobj.xml;
		}else{
	      xmlStr = new XMLSerializer().serializeToString(xmlobj);
		}
		return xmlStr;
	}
	
	/***　xml　插入　end*/
	var createNewId = function (pre){
		return pre+Math.floor((Math.random()+1)*10000);
	}
	 
	var json2Xml = function(data,rootCode){
		var xmlstr="";
		var _item = function(code,val,pcode){
			var rstr = "";
			var tval = "";
			var a = false;
			if((typeof val)!="function"){
				var c = true;
				if((typeof val)=="object"){
					for(var i in val){
						var tv = _item(i,val[i],code);
						if(tv["v"]!=""){
							tval += tv["v"];
						}
						if(tv["t"]){
							c = false;
						}
					}
				}else{ 
					tval = (val+"").replace(/</g,"&lt;").replace(/>/g,"&gt;");
				}
				if(!isNaN(code)){
					code = pcode;
					a = true;
				}　
				if(c){
					tval = "<"+code+">"+tval+"</"+code+">";
				}
			}
			rstr = {t:a,v:tval};
			return rstr;
		}
		for(var i in data){
			var vx =_item(i,data[i],"");
			if(vx["v"]!=""){
				xmlstr += vx["v"];
			}
		}
		if(rootCode!=null&&rootCode!=""){
			xmlstr = "<"+rootCode+">"+xmlstr+"</"+rootCode+">";
		}
		return xmlstr;
	}
} 