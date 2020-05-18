/**常用字符串验证js**/

//jQuery自带验证方式消息提示定义
jQuery.extend(jQuery.validator.messages,{
	required: "${common_jqMsg_required}",
	remote: "${common_jqMsg_remote}",
	email: "${common_email_inputEmailError}",
	url: "${common_jqMsg_url}",
	date: "${common_jqMsg_date}",
	dateISO: "${common_jqMsg_dateISO}",
	number: "${common_jqMsg_number}",
	digits: "${common_jqMsg_digits}",
	creditcard: "${common_jqMsg_creditcard}",
	equalTo: "${common_jqMsg_equalTo}",
	accept: "${common_jqMsg_accept}",
	maxlength: $.validator.format("${common_jqMsg_maxlength}"),
	minlength: $.validator.format("${common_jqMsg_minlength}"),
	rangelength: $.validator.format("${common_jqMsg_rangelength}"),
	range: $.validator.format("${common_jqMsg_range}"),
	max: $.validator.format("${common_jqMsg_max}"),
	min: $.validator.format("${common_jqMsg_min}")
});

/**
 * 自定义jQuery验证函数提示（包含你覆盖重写的验证方式）
 * @Author: lynn.chen  陈立
 * 说明：下面一是定义消息，二是作为验证方式api便于快速锁定是否有自己需要的验证方法（通用/多处用到），对于提示可以自定义，
 * 也可以使用原有的提示，根据需求进行修改————提示后面必须注释简明该验证功能的作用
**/
jQuery.extend(jQuery.validator.messages,{
	groupRequired: $.validator.messages.required,//文本框组合为空验证
	overRemote: $.validator.messages.remote,//文本框组合为空验证
	validInputStr: "${common_prompt_strFmtMsg}",//所有编辑表单input禁止输入英文引号
	empNameValid: function(){
		if(installLanguage == "zh_CN" || installLanguage == "zh_TW")
		{
			return '${pers_person_nameValid}';//人员姓名输入校验规则
		}
		else if(installLanguage == "en_US")
		{
			return '${pers_person_nameValidForEN}';
		}
		else
		{
			return '${pers_person_noSpecialChar}';
		}
	},
});

var z_index_max=999999;
var errorFun = function(error,element){
	element.parent().children(".ts_box").remove();
	element.parents(".dhxcombo_dhx_web").siblings(".ts_box").remove();
	var type = element.attr("type");
	var text = "<div class='ts_box'>" +
					"<div class='ts_jt'></div>" +
					"<div class='ts_nr'>" +
						"<h1></h1>" +
					"</div>" +
				"</div>";
	if(type == "hidden")
	{
		element = element.prev();
		element.addClass('dhtmlxComboxError');
		//element = element.prev(":text");
		
		element.siblings("input[type=hidden]:last").after(text);
	}
	else
	{
		element.after(text);
	}
	
	z_index_max--;
	element.siblings(".ts_box").css("z-index", ""+(z_index_max));
   	error.appendTo(element.siblings(".ts_box").children(".ts_nr").first().children("h1")[0]);
   	
   	var left = element.offset().left + element.width();
   	if(element.parents("form").width() - element.width() < 90)
   	{
   		left = left - (260 - (element.parents("form").width() - element.width()));
   	}
   	
   	var top = element.offset().top + element.outerHeight() - 8;
   	
   	//当验证字段为file类型时，左移多一点
   	if(type == "file")
   	{
   		element.siblings(".ts_box").offset({ top: top, left: left - 100});
   	}
   	else if(type == "hidden")
   	{
   		element.parents(".dhxcombo_dhx_web").after(element.siblings(".ts_box"));
   		left = left - 43;
   		element.parents(".dhxcombo_dhx_web").siblings(".ts_box").offset({ top: top, left: left});
		//var width = element.parents(".dhxcombo_dhx_web").siblings(".ts_box").width();
		//element.parents(".dhxcombo_dhx_web").siblings(".ts_box").width(width);
		//element.siblings("input[type=hidden]:last").after(element.parents(".dhxcombo_dhx_web").siblings(".ts_box"));
		/*if(width > 90)
		{
			element.siblings(".ts_box").css("min-width", width);
		}*/
   		
   		//element.siblings(".ts_box").offset({ top: top, left: left});
   	}
   	else
   	{
   		var width = element.width();
   		left = width < 40 ? 45 + width : left;
   		if(width < 40)
   		{
   			left= element.offset().left + 60;
   		}
   		element.siblings(".ts_box").offset({ top: top, left: left - 60});
   	}
   	
   	if(element.siblings(".ts_box").height() > 70 && element.width() > 260)
   	{
   		left = left - (260 - (element.parents("form").width() - element.width()));
   		
		element.siblings(".ts_box").offset({ top: top, left: left - 40});
   	}
   
   	if(element.attr("type") == "hidden")
   	{
		element = element.next();
	}
}

//edit和set中的表单验证参数--lynn.chen 20140319
$.validator.setDefaults({
	debug: true
	,
	errorElement: "em"
	/*,
	wrapper: "div"*/
	,
	errorPlacement: errorFun,
	ignore: "",
	success: function(element)
	{
		element.parents(".ts_box").parent().find(".dhtmlxComboxError").removeClass("dhtmlxComboxError");
		element.parents(".ts_box").remove();
	}
	,
	onchange:true
});

/**
 * @Description: jquery组合为空验证，只支持2个文本框
 * @Author: lynn.chen  陈立  
 * @Modified By:
 * @Date: 2013-01-23
 * @param: value 当前对象值
 * @param: element 当前对象
 * @param: param 要进行组合验证的对象id
 * 用法：
 * 		"firstName":{
			groupRequired: "lastName"
		},
		"lastName":{
			groupRequired: "firstName"
		}
 */
jQuery.validator.addMethod("groupRequired", function(value, element, param){
	//console.log(element.value + "---" + element.id);
	element.validToObj = param;
	
	function isRequired(obj)//判断对象值是否为空
	{
		obj = (typeof(obj) == "string" ? document.getElementById(obj) : obj);
		return obj.value == "" || $(obj).hasClass("showGray");
	} 
	
	function clearErrorTip(obj)//清除错误提示
	{
		if(isRequired(obj) && $("#" + obj.validToObj).hasClass("error"))
		{
			$("#" + obj.validToObj).valid();
		}
		else if(isRequired(obj) && !isRequired(obj.validToObj) && !$(obj).hasClass("error"))
		{
			$(obj).valid();
		}
	}
	
	//添加keyup事件监听，主要解决当对象值为空时不能进入验证方法的问题
	$(element).bind("keyup", function(){clearErrorTip(this)});
	//添加keyup事件监听，主要解决当对象值为空时不能进入验证方法的问题
	//$(element).bind("blur", function(){clearErrorTip(this)});
	
	var validRet = true;//验证结果
	if(!isRequired(element) && isRequired(param))
	{
		window.setTimeout(function(){//为了实现异步
			if($("#" + param).hasClass("error"))
			{
				$("#" + param).valid();
			}
		},1);
		validRet = true;
	}
	else if(isRequired(element) && !isRequired(param))
	{
		validRet = false;
	}
	else if(isRequired(element) && isRequired(param))
	{
		validRet = true;
		//如果存在验证提示没有去掉的情况，则再次验证去除
		clearErrorTip(element);
	}
	return validRet;
});


jQuery.validator.addMethod("overRemote", function(value, element, remoteParam){
	//console.log(element.value + "---" + element.id);
	var url = remoteParam[0];
	var oldValue = remoteParam[1];
	
	if(value != oldValue)
	{
		var param = {
	            url : url, //后台处理程序
	            type : "post" //数据发送方式
	        };
		//下面是jquery remote源码
		if ( this.optional(element) )
				//return "dependency-mismatch";   //--modify by LiangHaibo 
				return true;

			var previous = this.previousValue(element);
			if (!this.settings.messages[element.name] )
				this.settings.messages[element.name] = {};
			previous.originalMessage = this.settings.messages[element.name].remote;
			this.settings.messages[element.name].remote = previous.message;

			param = typeof param == "string" && {url:param} || param;

			if ( this.pending[element.name] ) {
				return "pending";
			}
			if ( previous.old === value ) {
				return previous.valid;
			}

			previous.old = value;
			var validator = this;
			this.startRequest(element);
			var data = {};
			data[element.name] = value;
			$.ajax($.extend(true, {
				url: url,
				mode: "abort",
				port: "validate" + element.name,
				dataType: "json",
				data: data,
				success: function(response) {
					validator.settings.messages[element.name].remote = previous.originalMessage;
					var valid = response === true;
					if ( valid ) {
						var submitted = validator.formSubmitted;
						validator.prepareElement(element);
						validator.formSubmitted = submitted;
						validator.successList.push(element);
						validator.showErrors();
					} else {
						var errors = {};
						var message = response || validator.defaultMessage( element, "remote" );
						errors[element.name] = previous.message = $.isFunction(message) ? message(value) : message;
						validator.showErrors(errors);
					}
					previous.valid = valid;
					validator.stopRequest(element, valid);
				}
			}, param));
			return "pending";
	}
	return true;
});

/**
 * 姓名验证
 * 暂时只处理中文、英文，需要对语言进行判断
 * @author wenxin
 * @param value 
 */
jQuery.validator.addMethod("empNameValid", function(value, element, param){
	/*if(installLanguage == "zh_CN" || installLanguage == "zh_TW")
	{
		var pattenChar = /^[\w\u4E00-\u9FA5\uF900-\uFA2D[\-\,\s\·]*]*$/;
		return this.optional(element) || (pattenChar.test(value));
	}
	else if(installLanguage == "en_US")
	{
		if(!$(element).hasClass("showGray"))
		{
			var pattenChar = /^[a-zA-Z0-9\_\-\.]+$/;
			return this.optional(element) || (pattenChar.test(value));
		}
		else
		{
			return true;
		}
	}
	else
	{
		return true;
	}*/
	return true;
});

/**
 * 所有编辑表单input禁止输入英文引号-
 * @author liangm 20140117
 */
jQuery.validator.addMethod("validInputStr", function(value, element){
	if(value.indexOf('"') >= 0 || value.indexOf("'") >= 0)
	{
		return false;
	}
	else
	{
		return true;
	}
});

/**
 * 手机号码验证(暂时只支持国内的手机号码验证)
 * @author wenxin
 * @param value 传入的需要验证的值
 */
function mobilePhoneValid(value)
{
	//^1[3|4|5|8][0-9]\d{4,8}$;^\d{1,15}$;^(((1[0-9]{1}[0-9]{1}))+\d{8})$
	// /^1([\\d]{10})|((\\+[0-9]{2,4})?\\(?[0-9]+\\)?-?)?[0-9]{7,8}$/
	var language = "${application['system.language']}";
	var pattenNum = "";
	if(language == "zh_CN")
	{
		//pattenNum = /^((\+861)|(01)|(1))[3|4|5|8][0-9]{9}$/
		pattenNum = /^1[3|4|5|8][0-9]\d{4,8}$/;
	}
	else
	{
		pattenNum = /^((\+27)|(0027)|(0))[1-9]\d{8}$/;
	}
	if(value != "")
	{
		return pattenNum.test(value);
	}
	else
	{
		return true;
	}
}

/**
 * 电话号码验证(家庭电话、办公电话)
 * @author wenxin
 * @param value 传入的需要验证的值
 */
function cellPhoneValid(value)
{
	var language = "${application['system.language']}";
	var pattenNum = "";
	if(language == "zh_CN")
	{
		pattenNum = /^([\d-+]*)$/;
	}
	else
	{
		pattenNum = /^((\+27)|(0027)|(0))[1-9]\d{8}$/;
	}
	if(value != "")
	{
		return pattenNum.test(value);
	}
	else
	{
		return true;
	}
}

/**
 * 邮编验证
 * @author wenxin
 * @param value 传入的需要验证的值
 */
function postcodeValid(value)
{
	var language = "${application['system.language']}";
	var pattenNum = "";
	if(language == "zh_CN")
	{
		pattenNum = /^[1-9][0-9]{5}$/;
	}
	else
	{
		pattenNum = /^[0-9]{4}$/;
	}
	if(value != "")
	{
		return pattenNum.test(value);
	}
	else
	{
		return true;
	}
}

/**
 * 身份证验证(暂时只支持国内身份证号验证，如需扩展，可以在此添加)
 * @author wenxin
 * @param value 传入的需要验证的值
 */
function idCardValid(value)
{
	var language = "${application['system.language']}";
	var pattenNum = "";
	if(language == "zh_CN")
	{
		pattenNum = /^(\d{15}$|^\d{18}$|^\d{17}(\d|X|x))$/;
	}
	else
	{
		pattenNum = /(((\d{2}((0[13578]|1[02])(0[1-9]|[12]\d|3[01])|(0[13456789]|1[012])(0[1-9]|[12]\d|30)|02(0[1-9]|1\d|2[0-8])))|([02468][048]|[13579][26])0229))(( |-)(\d{4})( |-)(\d{3})|(\d{7}))/;
	}
	if(value != "")
	{
		return pattenNum.test(value);
	}
	else
	{
		return true;
	}
}

/**
 * 区号验证
 * @param value 传入的需要验证的值
 */
function areaCodeValid(value)
{
	var rule = /^(\+){0,1}\d{1,10}$/;
	if (!rule.test(value)) 
	{
		return false;
	}
	return true;
}

/**
 * 非中文、英文下去除特殊字符
 * @author wenxin
 * @param s 传入的需要验证的值
 */
function stripScript(s)
{
	var pattern = new RegExp("[`~!@#$^&*()=|{}':;',\\[\\].<>/?~！@#￥……&*（）——|{}【】‘；：”“'。，、？%\\\\]")
	var rs = "";
	for (var i = 0; i < s.length; i++) 
	{
		rs = rs+s.substr(i, 1).replace(pattern, '');
	}
	return rs;
}

/**
 * @Description: 登录密码验证
 * @Author: lynn.chen 陈立  
 * @Modified By:
 * @Date: 2014-03-19
 * @param: callBackFun 验证成功的回调函数
 * @param: funData 回调函数传递的数据
 * 用法：
 * 		verifyLoginPwd(function(funData){
 * 			alert(funData);
 * 		},funData)
 */
function verifyLoginPwd(callBackFun, funData)
{
	var html = "<div style='padding:30px;'>" +
    				"<form id='userVerifyForm' action='authUserAction!verifyLoginPwd.action' method='post'>" +
    					"${auth_user_userPwd}<span class='required'>*</span><input type='password' name='loginPwd' style='margin-left:20px;'/>" +
    				"</form>" +
				"</div>";
	//根据国际化语言改变宽度
	var jBoxWidth=330;
	if("${currentLanguage}"=="es")
		jBoxWidth=370;
	else if("${currentLanguage}"=="zh_CN")
		jBoxWidth=300;
	$.jBox(html, { 
		title: "${common_securityVerify}",
		width: jBoxWidth,
		submit: function (v, h, f) {
		    if (v == "ok")
		    {
		    	$("#userVerifyForm").submit();
		        return false;
		    }
		}
	});
		
	setFocus($("#userVerifyForm input[name=loginPwd]")[0]);//设置焦点
	
	//添加回车提交表单
	$("#userVerifyForm input").bind("keydown", function(e){
		if(e.keyCode==13)
		{
			$("#userVerifyForm").submit();
		}
	});
	//数据表单验证
	$("#userVerifyForm").validate({
		rules : {
			"loginPwd" : {
				required : true,
				rangelength : [4, 18]
			}
		},
	 	submitHandler: function(form)
	   	{   
	 		$(".jbox-button-focus").addClass("jbox-button-hover");
			$(form).ajaxSubmit({
				async : false,
				dataType : 'json',
				success: function(result)
				{	
					if(result == true)
					{
						$.jBox.close();
						if(callBackFun)
						{
							callBackFun(funData);
						}
					}
					else
					{
						openMessage(msgType.warning,"${auth_user_pwdIncorrect}");//密码不正确
						$(".jbox-button-focus").removeClass("jbox-button-hover");
					}
				}
			});
	   	} 
	 });
}

//空字符串判断
function isEmpty(v) {
   return v === null || v === undefined || v.trim()=='';
}
