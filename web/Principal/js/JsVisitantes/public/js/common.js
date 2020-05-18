//为公共的js
//全局设置jquery Ajax请求全局设置
$.ajaxSetup( {
	async : false,
	dataType : 'json',
	data : 
	{
		un : new Date().getTime()  + "_" +  Math.round(Math.random() * 10000),
		systemCode : function() {
			return document.getElementById("systemCode") ? $("#systemCode").val() : window.top.location.pathname.substring(1).split("_")[0];
		}
	},
	error : function(XMLHttpRequest, textStatus, errorThrown) 
	{
		var status = XMLHttpRequest.status;
		if(status != 200)
		{
			dealAjaxError(status);
		}
	}
});

var openLoginAlert = true;//是可以弹出登录超时窗口
function dealAjaxError(status)
{
	var result = true;
	var type;//login/other
	var text = "Failed";
	if(status != null && typeof(status) != "undefined")
	{
		switch (status.toString())
		{
			case "${REQUEST_lOGIN_TIME_OUT!201}"://登录超时201
				type = "login";
				text = "${base_login_loginTimeOut}";
				break;
			case "${REQUEST_lOGIN_REPEAT}"://同时登录202
				type = "login";
				text = "${auth_user_loginRepeat}";
				break;
			case "${REQUEST_NO_PERMISSION!101}"://权限不足101
				type = "other";
				text = "${common_prompt_serverFailed}[" + status + "]";
				break;
			case "${REQUEST_SERVER_FAILED!0}"://路径错误或服务已停止0
				type = "other";
				text = "${common_prompt_serverFailed}";
				break;
			default://程序内部异常
				type = "other";
				text = "${common_prompt_serverError}:[" + status + "]";
				break;
		}
		if(type == "login")
		{
			if(openLoginAlert)
			{
				//视频ocx控件隐藏，避免其覆盖弹出框
				$("iframe[src*='.action']:eq(0)").contents().find(".current").css("visibility", "hidden");
				$(".current").css("visibility", "hidden");
				messageBox({messageType:"alert", text: text,
					callback: function(){
						window.top.location.reload();
		 			}
				});
				openLoginAlert = false;
			}
			result = false;
		}
		else if(!sysCfg && sysCfg.devMode)
		{
			openMessage(msgType.error, text);
			result = false;
		}
		else if(status < 200)
		{
			//openMessage(msgType.error, text);
			result = false;
		}
		else if(status != 200)
		{
			//openMessage(msgType.error, text);
			result = false;
			closeMessage();
		}
	}
	
	return result;
}

/**
 * @Description: jquery特殊字符转义方法
 * @Author: lynn.chen  陈立  
 * @Modified By:
 * @Date: 2013-05-08
 * @param: str 要进行转义的字符串
 */
function jq(str)
{
	return str.replace(/(:|\.)/g, '\\$1');
}

//操作加载提示
function requestMessage()
{
	$.jBox.tip("", 'loading');
}

/**
 * 将url参数字串转换成json对象
 * @author chenpf
 * @create 2015-03-27 11:27:31 am
 * @param url 需要转换的url，可以是完整url也可以是由“&”号拼接的参数字符串，它自动截取
 * @param overwrite 参数重复是否覆盖，取值true/false,true覆盖,false拼接成数组
 * 如： 输入：http://localhost:8080/accAlarmMonitorAction!ackAlarm.action?key=1,2&systemCode=acc
 *    输出：{
 *			key:"1,2",
 *			systemCode:"acc"
 *        }
 */
function parseURL2JSON(url,overwrite)
{
	var str=url;
	if(url.indexOf("?")!=-1)//截取url参数
		str=url.substr(url.indexOf("?")+1);
	var obj = {};
	var pairs = str.split('&');
	var d = decodeURIComponent;//对url参数进行解码
	var name;
	var value;
	$.each(pairs, function(i, pair)
	{
		pair = pair.split('=');
		name = d(pair[0]);
		value = d(pair[1]);
		obj[name] = overwrite || !obj[name] ? value : [].concat(obj[name]).concat(value);
	});
	return obj;
}; 

/**
 * 对特殊字符进行转义(+、&、%)
 * @author wenxin
 * @create 2013-08-05 17:20:31 pm
 * @param obj 需要转义的字符
 */
function transferredMeaning(src)
{
	src = src.replace(/\+/g, "%2B");
	src = src.replace(/&/g, "%26");
	src = src.replace(/\%/g, "%25");
	src = src.replace(/\//g, "%2F");
	src = src.replace(/\?/g, "%3F");
	src = src.replace(/\#/g, "%23");
	src = src.replace(/\=/g, "%3D");
	src = src.replace(/\ /g, "%20");
	return src;
}

/**
 * @Description:全角判断，并将字符串中的全角字符去掉
 * @Author: helang  何朗  
 * @modify by wenxin
 * @Date: 2013-05-10
 * @return 返回不是全角字符的字符值数组 
 */
function checkDBCAndSBC(focusObj)
{
	var returnArr =	checkCharacters(focusObj.value);					
	if(0 != returnArr.length && "" !=focusObj.value)
	{
		focusObj.blur();
		messageBox({messageType:"alert",title:"${common_prompt_warning}",text:"${common_prompt_warningSBCCase}",callback:function(){
			var positionArr = new Array();
			for(var i=0; i<returnArr.length; i++)
			{
				focusObj.value = focusObj.value.replace(returnArr[i],"")
			}
			focusObj.focus();
		}});
	}
}
/**
 * @Description:验证字符是否为全角字符方法，是的情况下返回true,不是返回false
 * @Author: helang  何朗  
 * @modify by wenxin
 * @Date: 2013-05-10
 * @return 返回不是全角字符的字符值数组 
 */
function checkCharacters(str){
	//alert(/^[\x00-\xff]*$/.test(tansformCharacters(str)));
	var result = new Array();
	for(var i=0; i<str.length; i++) 
	{
		var code = str.charCodeAt(i);		//将字符转化为unicode编码
		if(code >= 65281 && code <= 65373)  //字母或者数字情况
		{
			result.push(String.fromCharCode(str.charCodeAt(i)));
		} 
		else if (code == 12288 || code == 12289 || code == 12290)	//空格、顿号、句号情况
		{
			result.push(String.fromCharCode(str.charCodeAt(i)));
		}
	}
	return result;
}

/**
 * 文本框内提示信息并清空
 * @author wenxin
 * @create 2013-06-21 14:57:11 pm
 */
function checkText(elementId, value)
{
	var textObj = document.getElementById(elementId);
	//清空文本框
	function clear()
	{
		if (textObj.className.indexOf("showGray") >= 0)
		{
			textObj.value = "";
			$(textObj).removeClass("showGray");
		}
	}
	//重置默认文字
	function reset()
	{
		if (this.value == '')
		{
			textObj.value = value;
			$(textObj).addClass("showGray");
		}
	}
	textObj.onfocus = clear;
	textObj.onblur = reset;
}

//通过请求来设置某对象的文本
function setIdHtmlByPath(path, id)
{
	$.ajaxSetup({
		async : true
	});
	$.get(path, function(result)
	{
		$("#" + id).html(result);
	}, "html");
}

//当点击添加时，根据path来异步获取编辑或添加页面信息
function getAddTemplate(path)
{
	$.ajaxSetup({
		async : false
	});
	path += (path.indexOf("?") > 0 ? "&" : "?") + "un=" + new Date().getTime();
	$.get(path, function(result)
	{
		$("#addBox").html(result);
		$("#listBox").hide();
		$("#addBox").show();
	}, "html");
}

/**
 * 在中文下隐藏姓氏(人员新增和编辑)
 * @author wenxin
 * @create 2013-08-22 14:20:31 pm
 */
function hiddenLastNameInZH()
{
	if (getCookie("language") == "zh_CN")
	{
		$("#lastNameText").hide();
		$("#lastName").hide();
	}
}

/**
 * 将object对象转化为字符串(包括数组、json、function)
 * from network
 * @author wenxin
 * @create 2013-05-20 17:05:31 pm
 * @param obj 需要转化的object对象
 */
function objToStr(obj)
{
	switch (typeof (obj))
	{
	case 'object':
		var ret = [];
		if (obj instanceof Array)
		{
			for ( var i = 0, len = obj.length; i < len; i++)
			{
				ret.push(objToStr(obj[i]));
			}
			return '[' + ret.join(',') + ']';
		}
		else if (obj instanceof RegExp)
		{
			return obj.toString();
		}
		else
		{
			for ( var a in obj)
			{
				ret.push(a + ':' + objToStr(obj[a]));
			}
			return '{' + ret.join(',') + '}';
		}
	case 'function':
		return 'function() {}';
	case 'number':
		return obj.toString();
	case 'string':
		return "\""
				+ obj.replace(/(\\|\")/g, "\\$1").replace(
						/\n|\r|\t/g,
						function(a)
						{
							return ("\n" == a) ? "\\n" : ("\r" == a) ? "\\r"
									: ("\t" == a) ? "\\t" : "";
						}) + "\"";
	case 'boolean':
		return obj.toString();
	default:
		return obj.toString();
	}
}

//复选框全选触发的函数，obName:要进行同步全选的复选框的name,headName : 头选复选框的name,numId : 显示条数的对象id
function checkedAll(obName, headId, numId)
{
	obName = obName != undefined ? obName : "ids";
	headId = headId != undefined ? headId : "headCheckBox";
	var ob = document.getElementsByName(obName);

	var isChecked = document.getElementById(headId).checked ? true : false;

	for ( var i = 0; i < ob.length; i++)
	{
		ob[i].checked = isChecked;

	}
	var num = isChecked ? ob.length : 0;
	if (numId != undefined && document.getElementById(numId))
	{
		document.getElementById(numId).innerHTML = num;
	}
}

//复选框点击时触发的函数，判断当前是否全选和改变显示选中的条数
function isCheckedAll(obName, headId, numId, selectedFunc, noSelectedFunc)
{
	obName = obName != undefined ? obName : "ids";
	headId = headId != undefined ? headId : "headCheckBox";
	var ob = document.getElementsByName(obName);
	var isAllChecked = true;
	var num = 0;
	for ( var i = 0; i < ob.length; i++)
	{
		if (!ob[i].checked)
		{
			var isAllChecked = false;
		}
		else
		{
			num++;
		}
	}
	if (ob.length == 0)
	{
		isAllChecked = false
	}

	if (isAllChecked == true)
	{
		//document.getElementById(headId).checked = true;
		$("#" + headId).attr("checked", true);
	}
	else
	{
		//document.getElementById(headId).checked = false;
		$("#" + headId).attr("checked", false);
	}

	if (num > 0 && selectedFunc)
	{
		selectedFunc();
	}
	else if (noSelectedFunc)
	{
		noSelectedFunc();
	}

	if (numId != undefined && document.getElementById(numId))
	{
		document.getElementById(numId).innerHTML = num;
	}

}

function moreScreeningCondition(oOpenTip, oShutTip, gridName)
{
	var linkId = "moreConditionLink" + gridName;
	if ($("#" + linkId).attr("value") == "show")//表示前面是显示，将会隐藏
	{
		var height = $("#" + gridName).height()
				+ $("#belowSearchBox" + gridName).height();
		$("#belowSearchBox" + gridName).slideUp(200);
		$("#moreConditionText" + gridName).html(oShutTip);
		document.getElementById("moreConditionIcon" + gridName).setAttribute(
				"src", sysCfg.rootPath + "/public/images/minlingDown.gif");
		$("#" + linkId).attr("value", "hide");
	}
	else
	{
		var height = $("#" + gridName).height()
				- $("#belowSearchBox" + gridName).height();
		$("#belowSearchBox" + gridName).slideDown(200);
		$("#moreConditionText" + gridName).html(oOpenTip);
		document.getElementById("moreConditionIcon" + gridName).setAttribute(
				"src", sysCfg.rootPath + "/public/images/minlingTop.gif");
		$("#" + linkId).attr("value", "show");
	}
	//删除搜索提示
	if(null != $('#tips').html())
	{
		$('#tips').remove();
	}
}

//清空查询条件
function clearCondition(obId, gridName)
{
	$("#" + obId + " input").each(function(){
		//如果是有默认值的日期组件，清空时， 值回到回到默认值  modified by: zhangc  2014-04-30
		if(typeof($(this).attr("defaultVal")) != "undefined")
		{
			$(this).val($(this).attr("defaultVal"));
			return;
		}
		//清空查询条件时，如果是隐藏域的元素则不清空   modified by: ob.huang  2013-05-15
		if ($(this).attr("isInit") != "true")//&& $(this).val() != ""
		{
			$(this).val("");
			$(this).change();
		}
	});
	
	$("#" + obId + " select").each(function(){
		this[0].selected = "selected";
	});
	
	//保留默认的搜索条件提示
	loadSearchText(gridName);
	
	//焦点
	$("#" + obId + " input[type=text][readonly!=readonly][disabled!='disabled']:first").focus();
}

//判断某属性是否存在
function isExist(id, path, beforeValue)
{
	var value = $("#" + id).val();
	var name = document.getElementById(id).name;
	if (value != beforeValue)
	{
		if ($.trim(value) != "")
		{
			path = path + "?" + name + "=" + value;
			$.post(path, function(data)
			{
				if (!data)
				{
					$('#span').html("");
					//isExist = false;
				}
				else
				{
					$('#span').removeClass('redCL');
					$('#span').html("<font color='red'>已存在</font>");
					isExite = true;
				}
			}, "json");
		}
	}
}

//加载当前Select的选中项
function loadSelected(ob, value, type)
{
	var obs;
	//判断是以name形式获取还id形式，默认id形式（如：复选框是name，单选框是name，下拉框是id）
	if (type == "name")
	{
		obs = document.getElementsByName(ob);
	}
	else
	{
		obs = document.getElementById(ob);
	}
	//循环判断加载
	for ( var i = 0; i < obs.length; i++)
	{
		if (obs[i].value == value)
		{
			if (type == "name")
			{
				obs[i].checked = "checked";
			}
			else
			{
				obs[i].selected = "selected";
			}
		}
	}
}

//根据cookie名获取cookie的值
function getCookie(cookieName)
{
	var cookieString = document.cookie;

	var start = cookieString.indexOf(cookieName + '=');

	if (start == -1)
	{
		return null;
	}
	start += cookieName.length + 1;

	var end = cookieString.indexOf(';', start);
	if (end == -1)
	{
		return unescape(cookieString.substring(start));
	}
	return unescape(cookieString.substring(start, end));
}

//创建Cookie
function createCookie(name,value,days)
{
	if (days) 
	{
		var date = new Date();
		date.setTime(date.getTime()+(days*24*60*60*1000));
		var expires = "; expires="+date.toGMTString();
	}
	else
	{
		expires = ""; 
	}
	document.cookie = name+"="+value+expires+"; path=/";
}


//下拉列表处理。-darcy20130108
//entityAction 实体类对应的Action名。
//displayFields 需要展示的字段名称，多个字段名称间用：隔开。
// selectObId
// keyField 关键字段，通常为表的主键，比如为id
//selectedOpId
function loadDropDownList(entityAction, displayFields, selectObId, keyField, selectedOpId)
{
	$.ajax({
		type: "GET",
		url: entityAction,
		dataType: "json",
		async: false,
		success: function(result)
	    {
			var selectOb = document.getElementById(selectObId);
			var fields = displayFields.split(":");
			//var fieldsLen = fields.length;
			var dataArray = eval(result);
			for ( var i = 0; i < dataArray.length; i++) 
			{
				var op = document.createElement("option");//创建option元素（对象）
				op.value = dataArray[i][keyField];//
				if(op.value == selectedOpId)
				{
					op.selected="selected";
				}
				var tempHTML = "";
				for(var j = 0; j < fields.length; j++)
				{
					if(j == 0)
					{
						tempHTML += dataArray[i][fields[j]];
					}
					else
					{
						tempHTML += " "+dataArray[i][fields[j]];
					}
				}
				op.innerHTML = tempHTML;
				selectOb.appendChild(op);//给select下拉框添加option元素（对象）
			}
	    }
	});
}

//设置对象的焦点在最后面
function setFocus(obj) 
{
	$(obj).focus();
    if(document.selection) 
    {
		var sel = obj.createTextRange();
		sel.moveStart('character', obj.value.length);
		sel.collapse();
		sel.select();
	}
    else if (typeof obj.selectionStart == 'number' && typeof obj.selectionEnd == 'number') 
    {
		obj.selectionStart = obj.selectionEnd = obj.value.length;
	}
    else
	{
		obj.selectionEnd = obj.value.length;
	}
}

/**
 * @Description: 给日期对象加格式化方法
 * @Author: lynn.chen  陈立  
 * @Modified By:
 * @Date: 2013-03-22
 * @param: format 日期格式
 */ 
Date.prototype.format = function(format)
{
	var o = {
	"M+" : this.getMonth()+1, //month
	"d+" : this.getDate(), //day
	"H+" : this.getHours(), //hour
	"m+" : this.getMinutes(), //minute
	"s+" : this.getSeconds(), //second
	"q+" : Math.floor((this.getMonth()+3)/3), //quarter
	"S" : this.getMilliseconds() //millisecond
	}
	if(/(y+)/.test(format)) format=format.replace(RegExp.$1,
	(this.getFullYear()+"").substr(4 - RegExp.$1.length));
	for(var k in o)if(new RegExp("("+ k +")").test(format))
	format = format.replace(RegExp.$1,
	RegExp.$1.length==1 ? o[k] :
	("00"+ o[k]).substr((""+ o[k]).length));
	return format;
}

//字符串转日期格式，strDate要转为日期格式的字符串
function getDate(strDate) {
    var date = eval('new Date(' + strDate.replace(/\d+(?=-[^-]+$)/,
     function (a) { return parseInt(a, 10) - 1; }).match(/\d+/g) + ')');
    return date;
} 

/**
 * @Description: 格式化秒数为time
 * @Author: lynn.chen 陈立  
 * @Modified By:
 * @Date: 2014-03-19
 * @param: value 秒数
 * @example 61 = 1分1秒 
 */ 
function formatSeconds(value) 
{   
	var theTime = Number(value);   
    var theTime1 = 0;   
    var theTime2 = 0;   
    //alert(theTime);   
    if(theTime > 60) 
    {   
        theTime1 = Number(theTime/60);   
        theTime = Number(theTime%60);   
        //alert(theTime1+"-"+theTime);   
        if(theTime1 > 60) 
        {   
            theTime2 = Number(theTime1/60);   
            theTime1 = Number(theTime1%60);
        }   
    }   
    var result = ""+parseInt(theTime);//s
      
        result = ""+parseInt(theTime1)+":"+result;//m
    
        result = ""+parseInt(theTime2)+":"+result;//h
    
    return result;   
}

/**
 * @Description: 模拟java中String.format方法
 * @Author: lynn.chen  陈立  
 * @Modified By:
 * @Date: 2013-07-29
 * @param: type 消息的类型
 * @param: text 消息的文本
 * //两种调用方式
 	var template1 = "我是{0}，今年{1}了";
 	var template2 = "我是{name}，今年{age}了";
 	var result1 = template1.format("loogn",22);
 	var result2 = template2.format({name:"loogn",age:22});
 */ 
String.prototype.format = function(args) {
    var result = this;
    if (arguments.length > 0) 
    {    
        if (arguments.length == 1 && typeof (args) == "object") 
        {
            for (var key in args) 
            {
                if(args[key] != undefined)
                {
                    var reg = new RegExp("({" + key + "})", "g");
                    result = result.replace(reg, args[key]);
                }
            }
        }
        else 
        {
            for (var i = 0; i < arguments.length; i++)
            {
                if (arguments[i] != undefined)
                {
                    //var reg = new RegExp("({[" + i + "]})", "g");//这个在索引大于9时会有问题，谢谢何以笙箫的指出
					var reg= new RegExp("({)" + i + "(})", "g");
                    result = result.replace(reg, arguments[i]);
                }
            }
        }
    }
    return result;
}


/**
 * @Description: 打开消息提示
 * @Author: lynn.chen  陈立  
 * @Modified By:
 * @Date: 2013-07-29
 * @param: type 消息的类型(从msgType对象中获取)
 * @param: text 消息的文本
 */ 
function openMessage(type, text, ptimeout)
{
	text = (text == "" ? null : text);
	var timeout = 1000;
	if(type == msgType.warning || type == msgType.info)//警告
	{
		timeout = 3000;
	}
	else if(type == msgType.success)//成功 
	{
		
		text = (text && text != null ? text : "${common_op_succeed}");//${common_op_succeed}:操作成功
		var num = strlen(text)/30;
		num = num > 8 ? 8 : num;
		timeout = Math.ceil(num) * timeout;//动态判断显示字符数的长度来延长显示时间
	}
	else if(type == msgType.error)//失败
	{
		text = (text && text != null) ? text : "${common_op_failed}";//${common_op_failed}:操作失败，程序出现异常
		timeout = 3000;
	}
	else if(type == msgType.loading)//处理中
	{
		timeout = 0;//当为'loading'时，timeout值会被设置为0，表示不会自动关闭。
		text = text && text != null ? text : "${common_op_processing}";//${common_op_processing}:处理中
	}
	var width = strlen(text) * 6.1 + 45;//按字符计算宽度
	timeout = ptimeout ? ptimeout : timeout;
	$.jBox.tip(text, type,{timeout: timeout, width: (width > 400 ? 400 : "auto")});//设定最大宽度为400
}

/**
 * @Description: 关闭消息提示
 * @Author: lynn.chen  陈立  
 * @Modified By:Darcy Zhangc 给定默认关闭时最小等待时间为1s
 * @Date: 2013-07-31
 * @param: type 消息的类型
 * @param: text 消息的文本
 */
function closeMessage(timeout)
{
	timeout = timeout ? timeout : 1000;
	window.setTimeout("$.jBox.closeTip();", timeout);//设定最小等待时间
}

/**
 * @Description: 处理ajax的返回结果
 * @Author: lynn.chen  陈立  
 * @Modified By:
 * @Date: 2013-07-29
 * @param: result 返回结果的json对象，格式：{"ret":"ok"},或者{"ret":"ok", "msg":"info"}
 * @param: callback 只是成功后的回调函数，失败等其它结果将不做处理
 */ 
function dealRetResult(result, callback, gridName)
{
	//此处有bug，加个不为空的判断。modify by: ob.huang  2013-08-23
	if(result)
	{   var ptimeout = result[sysCfg.ptimeout]; //modify by larry 2015-02-15
		if(result[sysCfg.ret] == sysCfg.warning)//警告
		{
			openMessage(msgType.warning, result[sysCfg.msg],ptimeout);
		}
		else if(result[sysCfg.ret] == sysCfg.success)//成功
		{
			openMessage(msgType.success, result[sysCfg.msg],ptimeout);
			if(typeof(callback) == "undefined" || callback == null)//当回调函数不存在时则默认为重新加载grid
			{
				callback = function()
				{					
					reloadGrid(gridName);
				};
			}
			if(callback != null && callback != "")
			{
				callback();
			}
		} 
		else//失败
		{
			openMessage(msgType.error, result[sysCfg.msg],ptimeout);
		}
	}
}

/**
 * @Description: 正在处理函数
 * @Author: lynn.chen  陈立  
 * @Modified By:Darcy 2014.3.5
 * @Date: 2013-07-29
 * @param: executeFunc 需要执行的函数
 * @param: isAutoClose 是否自动关闭消息提示
 */ 
function onLoading(executeFunc, isAutoClose)
{
	openMessage(msgType.loading);
	//sleep(4000);
	//window.setTimeout(executeFunc,500);//设定最小等待时间
	executeFunc();
	if(isAutoClose)//如果执行函数中没有消息提示，则需自动关闭
	{
		closeMessage();
	}
}

function sleep(numberMillis) 
{    
	openMessage(msgType.loading);
	$.ajax({
		type: "GET",
		url: "baseAction!jsSleep.action?timeout="+ numberMillis,
		dataType: "text",
		async: false,
		success: function(result)
	    {
			closeMessage();
	    }
	});
}


/**
 * @Description: 引入动态js(解决ajax获取的文本中js文件无法执行的问题)
 * @Author: lynn.chen  陈立  
 * @Modified By:
 * @Date: 2013-04-22
 * @param: url 要包含的js路径
 * @param: callback 回调函数
 * （特别说明：因为js是异步加载的，所以直接调用js中的方法时会出现该方法没有定义的错误！
 * 如需要进行直接调用，需要使用回调函数来完成，如要引用的test.js中存在testFun方法需直接调用执行
 * 用法如下：
 * includeScript("${base}/public/js/test.js", function(){
		testFun();
	});
 * ）
 */ 
function includeScript(url, callback)
{
	var script = document.createElement("script");
    var doc = document.getElementsByTagName("script")[0];
    script.type = "text/javascript";
    script.charset="utf-8";
    script.src = url;
    doc.parentNode.insertBefore(script, doc);
    if (script.readyState) 
    { //IE
        script.onreadystatechange = function () 
        {
            if (script.readyState == "loaded" || script.readyState == "complete") 
            {
                script.onreadystatechange = null;
                callback();
            }
        };
    } 
    else 
    { //标准的DOM浏览器，这时我们仅需要在脚本加载完成时，返回一个回调函数
        script.onload = function () {
        	if(callback)
        	{
        		callback();
        	}
        };
    }
    //console.log("created succeed"); //提示加载成功
}

/**
 * @Description: 设置Checkbox进行自动对齐
 * @Author: lynn.chen  陈立  
 * @Modified By:
 * @Date: 2013-04-27
 * @param: checkboxName checkbox的name属性
 */
function setRowCheckboxAlign(checkboxName)
{
	var firstOffset = 0;
	$("input[name='" + checkboxName + "']").each(function(i){ 
		if(i == 0)
		{
			firstOffset = $(this).offset().left;
		}
		if($(this).offset().left == firstOffset)
		{
			$(this).css("margin-left","8px");
		}
	});
}

/**
 * @Description: 计算字符串长度(可同时字母和汉字，字母占一个字符，汉字占2个字符)
 * @Author: ob.huang 黄玲彬
 * @Modified By:
 * @Date: 2013-09-24
 * @param: 
 */
function strlen(str)
{  
    var len = 0;  
    for (var i=0; i<str.length; i++)
    {   
		var c = str.charCodeAt(i);
		if ((c >= 0x0001 && c <= 0x007e) || (0xff60<=c && c<=0xff9f)) 
		{
			len++;   
		}	
		else 
		{
			len+=2;   
		}
    }
    return len;
}  

/**
 * @Description: url编码
	+    URL 中+号表示空格                     %2B  
	空格 URL中的空格可以用+号或者编码           %20
	/   分隔目录和子目录                       %2F    
	?    分隔实际的URL和参数                   %3F    
	%    指定特殊字符                          %25    
	#    表示书签                              %23    
	&    URL 中指定的参数间的分隔符             %26    
	=    URL 中指定参数的值                    %3D
 * @Author: lynn.chen 陈立
 * @Modified By:
 * @Date: 2013-10-8
 * @param: str 需要进行编码的url字符串
 */
function urlEncode(str)
{
	var ret="";
	var strSpecial="!\"#$%&'()*+,/:;<=>?[]^`{|}~%";
	var tt= "";
	for(var i=0;i<str.length;i++)
	{
		var chr = str.charAt(i);
		var c=str2asc(chr);
		tt += chr+":"+c+"n";
		if(parseInt("0x"+c) > 0x7f)
		{
			ret+="%"+c.slice(0,2)+"%"+c.slice(-2);
		}
		else
		{
			if(chr==" ")
			{
				ret+="+";
			}
			else if(strSpecial.indexOf(chr)!=-1)
			{
				ret+="%"+c.toString(16);
			}
			else
			{
				ret+=chr;
			}
		}
	}
	return ret;
}

/**
 * @Description: url解码
	+    URL 中+号表示空格                     %2B  
	空格 URL中的空格可以用+号或者编码           %20
	/   分隔目录和子目录                       %2F    
	?    分隔实际的URL和参数                   %3F    
	%    指定特殊字符                          %25    
	#    表示书签                              %23    
	&    URL 中指定的参数间的分隔符             %26    
	=    URL 中指定参数的值                    %3D
 * @Author: lynn.chen 陈立
 * @Modified By:
 * @Date: 2013-10-8
 * @param: str 需要进行解码的url字符串
 */
function urlDecode(str)
{
	var ret="";
	for(var i=0;i<str.length;i++)
	{
		var chr = str.charAt(i);
		if(chr == "+")
		{
			ret+=" ";
		}
		else if(chr=="%")
		{
			var asc = str.substring(i+1,i+3);
			if(parseInt("0x"+asc)>0x7f)
			{
				ret+=asc2str(parseInt("0x"+asc+str.substring(i+4,i+6)));
				i+=5;
			}
			else
			{
				ret+=asc2str(parseInt("0x"+asc));
				i+=2;
			}
		}
		else
		{
			ret+= chr;
		}
	}
	return ret;
}

function str2asc(strstr)
{
	return ("0"+strstr.charCodeAt(0).toString(16)).slice(-2);
}

function asc2str(ascasc)
{
	return String.fromCharCode(ascasc);
} 

/**
 * @Description: 获取location的参数 var menuId=getLocationParam("menuId");
 * @Author: lynn.chen  陈立  
 * @Modified By:
 * @Date: 2013-01-23
 * @param: param 参数名
 * （如：http://localhost:8088/acc_index.action?menuId=pers_listPersDepartment.action%3Ftype=custom中的menuId）
 */ 
function getLocationParam(param, path){
	var request = {
		QueryString : function(val) {
			var url = path ? path.substring(path.indexOf("?")) : window.location.search;
			var re = new RegExp("" +val+ "=([^&?]*)", "ig");
			return ((url.match(re))?(decodeURI(url.match(re)[0].substr(val.length+1))):'');
		}
	}
	return urlDecode(request.QueryString(param));//进行解码
}

/**
 * @Description: 主要是对密码输入框屏蔽空格
 * @Author: lianghb
 * @Date： 2013-12-25
 * @param {Object} e
 */
function isNotSpace(e)
{  
	 var theEvent = window.event || e;    
	 var code = theEvent.keyCode || theEvent.which;     
	 return code == 32 ? false : true;
}

/**
 * @Description 支持Esc关闭弹出框
 * @Author: lianghb
 * @Date: 2013-12-31
 */
function esckeydown(e)
{
	var theEvent = window.event || e;    
	var code = theEvent.keyCode || theEvent.which; 
	if(code ==27)
	{
	   theEvent.returnValue = null;
	   window.returnValue = null;
	   closeWindow();
	}
}

/**
 * @Description input框中只能输入数字
 * @Author: lianghb
 * @modify by wenxin 2014-04-19
 * @Date: 2013-03-12
 */
function isNumber(e) 
{  
	var event = window.event || e;
	var code = event.keyCode || event.which||e.charCode;
    if(((code > 47) && (code < 58)) || (code == 8))
    {  
        return true;  
    } 
    else
    {  
        return false;  
    }  
}

String.prototype.endWith=function(str){
  if(str==null||str==""||this.length==0||str.length>this.length)
     return false;
  if(this.substring(this.length-str.length)==str)
     return true;
  else
     return false;
  return true;
 }

 String.prototype.startWith=function(str){
  if(str==null||str==""||this.length==0||str.length>this.length)
   return false;
  if(this.substr(0,str.length)==str)
     return true;
  else
     return false;
  return true;
 }
 
//将实体转回为HTML
 function unescape(str) {
     var elem = document.createElement('div')
     elem.innerHTML = str
     return elem.innerText || elem.textContent
 }