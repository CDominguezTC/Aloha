/**
 * TODO 系统全局js配置
 * 
 * @author lynn.chen
 * @version TODO 添加版本
 * @since 2014年11月25日 上午10:23:54
 */

//jBox控件按钮国际化
if(typeof(jBoxConfig) != "undefined")
{
	jBoxConfig.defaults = {
		//buttons: { '${common_edit_ok}': "ok", '${common_edit_cancel}': "cancel" }
		buttons: { 'Ok': "ok", 'Cancelar': "cancel" }
	};
	jBoxConfig.languageDefaults = {
	    //close: '${common_close}', /* 窗口右上角关闭按钮提示 */
	    //ok: '${common_edit_ok}', /* $.jBox.prompt() 系列方法的“确定”按钮文字 */
	    //yes: '${common_yes}', /* $.jBox.warning() 方法的“是”按钮文字 */
	    //no: '${common_no}', /* $.jBox.warning() 方法的“否”按钮文字 */
	    //cancel: '${common_edit_cancel}' /* $.jBox.confirm() 和 $.jBox.warning() 方法的“取消”按钮文字 */
		
		close: 'Cerrar', /* 窗口右上角关闭按钮提示 */
	    ok: 'Ok', /* $.jBox.prompt() 系列方法的“确定”按钮文字 */
	    yes: 'Si', /* $.jBox.warning() 方法的“是”按钮文字 */
	    no: 'No', /* $.jBox.warning() 方法的“否”按钮文字 */
	    cancel: 'Cancelar' /* $.jBox.confirm() 和 $.jBox.warning() 方法的“取消”按钮文字 */
	};
	
	$.jBox.setDefaults(jBoxConfig);
}

//系统信息配置类
var sysCfg = 
{
	dhxSkin : "dhx_web",
	rootPath : "${base}",
	treeImgPath: "/public/controls/dhtmlx/skins/web/imgs/dhxtree_web/",//树图片路径
	devMode : "${application['system.devMode']}" == "true",
	dhxShortDateFmt : "${dhxShortDateFmt}",
	dhxLongDateFmt : "${dhxLongDateFmt}",
	sysShortDateFmt : "${sysShortDateFmt}",
	sysLongDateFmt : "${sysLongDateFmt}",
	ret : "${RET}",
	msg : "${MSG}",
	data : "${DATA}",
	success : "${SUCCESS}",
	error : "${ERROR}",
	warning : "${WARNING}",
	ptimeout: 3000,  //add by larry 2015-02-15
	enableRTL:"${enableRTL!}"
};

//消息控件的使用类型的类
var msgType = 
{
	info : "info",
	success : "success",
	warning : "warning",
	error : "error",
	loading : "loading"
};

//dhtmlx时间控件国际化
dhtmlXCalendarObject.prototype.langData["zk"] = 
{
	dateformat: sysCfg.dhxLongDateFmt,
	monthesFNames: ["${common_full_januar}","${common_full_februar}","${common_full_march}","${common_full_april}","${common_full_may}","${common_full_june}","${common_full_jul}","${common_full_aug}","${common_full_sep}","${common_full_oct}","${common_full_nov}","${common_full_dec}"],
	monthesSNames: ["${common_jan}","${common_feb}","${common_mar}","${common_apr}","${common_may}","${common_jun}","${common_jul}","${common_aug}","${common_sep}","${common_oct}","${common_nov}","${common_dec}"],
	daysFNames: ["${common_sunday}","${common_monday}","${common_tuesday}","${common_wednesday}","${common_thursday}","${common_friday}","${common_saturday}"],
	daysSNames: ["${common_abb_sunday}","${common_abb_monday}","${common_abb_tuesday}","${common_abb_wednesday}","${common_abb_thursday}","${common_abb_friday}","${common_abb_saturday}"],
	weekstart: 1,
	weekname: "w"
};
//dhtmlx时间控件默认语言包
dhtmlXCalendarObject.prototype.lang="zk";

//防止退格键退出系统
$(document).keydown(function(e)
{
	var doPrevent;
	if (e.keyCode == 8)
	{
		var d = e.srcElement || e.target;
		if (d.tagName.toUpperCase() == 'INPUT' || d.tagName.toUpperCase() == 'TEXTAREA')
		{
			doPrevent = d.readOnly || d.disabled;
		}
		else
		{
			doPrevent = true;
		}
	}
	else
	{
		doPrevent = false;
	}
	if (doPrevent)
	{
		return false;
		//e.preventDefault();
	}
});

function doIEBrowserPrompt()
{
	if(window == window.top && "${application['ie.versionLowPrompt.enabled']!'true'}" == "true")
	{
		var ieVersion = parseInt($.browser.version.split(".")[0]);
		if ($.browser.msie && ieVersion < 11)
		{
			var ieBrowserPrompt = "${auth_login_ieBrowserPrompt}".format(ieVersion, ieVersion);
			$('body').prepend('<div id="ieBrowserPrompt" style="padding: 3px 5px 1px 5px;color: #474b4f;background-color: #f3f5f0">' +
								'<img src="${base}/public/images/favicon.png" style="margin-top: -2px"/>' +
								'<span style="padding-left: 5px">'+ ieBrowserPrompt +'<span>' +
							  '<div>');
			$("#testNG_A").css("top","-25px");
			$(".login_top").css("padding-bottom","20px");
		}
	}
}

var isDoIEBrowserPrompt = true;
$(function(){
	
	if(isDoIEBrowserPrompt)
	{
		doIEBrowserPrompt();
	}
});


