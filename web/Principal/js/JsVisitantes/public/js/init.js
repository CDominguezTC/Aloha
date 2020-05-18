/**
 * @Description: 此js为页面的初始化和一些主要的js应用
 * @Author: lynn.chen  陈立  
 * @Modified By:
 * @Date: 2013-01-08
 */ 
var operateMessage = null;
var system = {};
var btn = null;
var flag =  false;
var limitCalendars=[];
var myLayouts = new Array();
system.start_text = "<img src='/public/images/work.jpg' width='100%' height='100%'/>";
system.rightId = "right";//右边div框架的id
system.isFormByWinOpen = true;//表单页是否以弹出窗口来进行添加界面显示

var layout_a="a";
var layout_b="b";

//点击左边树时，右边窗口的tab创建
system.createTab = function(id, full_id, text, extra)
{
	var iframeTag = "iframe:";
	//$("#" + system.rightId).html("");
	//iframe内存释放
	$("iframe[src*='.action']").removeAttr("src");
	$("iframe[src*='.action']").remove();//移除之前的iframe
	$("iframe[src*='.html']").removeAttr("src");//移除之前的iframe
	$("iframe[src*='.html']").remove();//移除之前的iframe
	//$("iframe[src*='.action']")[0].contentWindow.
	$.browser.msie && CollectGarbage();
	system.contentLayout.cells(layout_b).detachObject();
	if(id.indexOf(iframeTag) == 0)
	{
		id = id.substring(iframeTag.length);
		system.contentLayout.cells(layout_b).attachURL(id);
	}
	else
	{
		setIdHtmlByPath(id,system.rightId);
		system.contentLayout.cells(layout_b).attachObject(system.rightId);
	}
	system.contentLayout.cells(layout_b).progressOff();

};


/**读取搜索配置文件，进行数据加载*/
function loadSearchConfig(searchXMLPath,gridName)
{
	searchXMLPath += (searchXMLPath.indexOf("?") > 0 ? "&" : "?") + "rootType=searchs" + "&etc=" + new Date().getTime();
	initSearchConfig(searchXMLPath, gridName);
}

/**加载搜索控件，必须结合上面的loadSearchConfig一起使用*/
function loadSearchControl(xml,gridName)
{
	loadSearchData(xml, gridName);
} 

/*function enterSearch(obj, gridName)
{
	$(obj).bind("keypress", function(e){
		var key = e.which;
		var reg = new RegExp("\\d");
		if(key == 13 )//回车键
		{
			$("#searchButton" + gridName).click();
		}
	});
}*/


function operatePrompt()
{
	var dhxWins = new dhtmlXWindows();
    dhxWins.enableAutoViewport(false);
    dhxWins.attachViewportTo(document.body);
    dhxWins.setImagePath("/public/controls/dhtmlx/dhtmlxWindows/codebase/imgs/");
    w1 = dhxWins.createWindow("w1", 0, 0, 320, 240);
    w1.setText("友情提示");
    w1.center();
	w1.attachHTMLString("<div style='background-color: #e6e5f0'>目前该功能正在努力开发，请耐心等待....</div><img src='/pers/images/build.jpg' width='300' height='200'>");
}

/**
 * TODO 设置二级菜单项鼠标经过图标
 * @author chenpf
 * @since 2015年4月29日 下午5:55:32
 * @param obj 该二级菜单项的Jquery选择器对象，可以是id,DOM元素对象等
 * @returns true:设置成功,false:设置失败
 */
function setAccMouseOverIcon(obj)
{
	var imgObj=$(obj).find("img.dhx_cell_hdr_icon");
	var oldSrc=imgObj.attr("src");
	//根据路径名判断是否是鼠标经过的图片
	if(oldSrc.indexOf("_over.")!=-1)
		return false;
	//拼接新路径
	var newSrc=oldSrc.substr(0,oldSrc.lastIndexOf("."))+"_over"+oldSrc.substr(oldSrc.lastIndexOf("."));
	imgObj.attr("src",newSrc);
	return true;
}

/**
 * TODO 设置二级菜单项鼠标离开图标
 * @author chenpf
 * @since 2015年4月29日 下午5:55:32
 * @param obj 该二级菜单项的Jquery选择器对象，可以是id,DOM元素对象等
 * @returns true:设置成功,false:设置失败
 */
function setAccMouseOutIcon(obj)
{
	var imgObj=$(obj).find("img.dhx_cell_hdr_icon");
	//根据路径名判断是否是鼠标经过的图片
	if(imgObj.attr("src").indexOf("_over.")==-1)
		return false;
	var newSrc=imgObj.attr("src").replace("_over.",".");
	imgObj.attr("src",newSrc);
	return true;
}

//界面初始化方法，一切从这里开始
function init()
{
	//session超时，地址回绑，增加一个cookie。 update by: ob.huang  2013-05-07
	//var backUrl = window.location.href;
	//var loc = backUrl.substring(backUrl.lastIndexOf("/")+1, backUrl.length)
	createCookie("backUrl",window.location.pathname);
	if("${enableRTL!}"=='true')
	{
		layout_a="b";
		layout_b="a";
	}
	
	var leftMenuXML="";
	if(document.getElementById("leftMenuXML"))
	{
		leftMenuXML = document.getElementById("leftMenuXML").value;//获取左边xml菜单
	}
	//创建主体部分，形成左右格局
	system.contentLayout = new dhtmlXLayoutObject(document.body, "2U", "dhx_subMenu");
	system.contentLayout.setOffsets({
	    top: 0,
	    right: 0,
	    bottom: 0,
	    left: 0
	});
	//绑定layout事件-darcy20130111 leftMenuXML
	setLayoutResize(system.contentLayout);
	
	//system.contentLayout = new dhtmlXLayoutObject(document.body, "3U", sysCfg.dhxSkin);
	//system.contentLayout = new dhtmlXLayoutObject(document.body, "3L", sysCfg.dhxSkin);
	 
	if(document.getElementById("top"))
	{
		if(document.getElementById("ieBrowserPrompt"))
		{
			$("#top").height($("#top").height() + $("#ieBrowserPrompt").height());
		}
		system.contentLayout.attachHeader("top");
	}
	system.contentLayout.cells(layout_a).setWidth(240);
	system.contentLayout.cells(layout_a).setText("${common_leftMenu_function}");
	system.contentLayout.cells(layout_a).attachObject("left");
	system.contentLayout.cells(layout_b).setText("");
		//$(document.body).hide();
	//system.contentLayout.cells("c").setText("系统监控");
	//system.contentLayout.setCollapsedText("c", "系统监控");
	//system.contentLayout.cells("c").attachObject("bottom");
	//system.contentLayout.cells("c").setHeight("200");
	//system.contentLayout.cells("c").collapse();//默认折叠。有报警等异常时自动展开-darcy20130409
	//监控的下半部分-事件记录
	//var statusBar = system.contentLayout.attachStatusBar();
    //statusBar.setText("Active Alarm：<label id='actAlarm'>0</label>    Ack. Alarm：<label id='ackAlarm'>3</label>   Off Normal：<label id='offNormal'>33</label>  Fresh Time:<label id='curTime'>2013/4/8 21:51</label>");
	//$("div[title='Collapse']").hide();
	//system.contentLayout.cells("b").attachURL(indexPage);
	system.contentLayout.cells(layout_a).hideHeader();
	system.contentLayout.cells(layout_b).hideHeader();
	//判断是否需要隐藏左边窗口
	if($("#isCollapse").val() == "true")
	{
		system.contentLayout.cells(layout_a).collapse();
	}
	system.contentLayout.cells(layout_a).fixSize(true, true);
	//$(system.contentLayout.cells("a").cell).css("background-color","#474b4f");
	
	//system.contentLayout.cells("b").expand();
	//system.contentLayout.cells("b").style.overflow="auto";
	//var b = system.contentLayout.cells("b");
	//b.vs[b.av].dhxcont.mainCont[b.av].style.overflow = "visible";

	if(leftMenuXML!="")
	{
		system.myAcc = system.contentLayout.cells(layout_a).attachAccordion({icons_path: "/public/controls/dhtmlx/skins/web/imgs/dhxtree_web/"});
		system.myAcc.setOffset(0);//设置间隔
		//system.myAcc.enableMultiMode("auto", 600)//启用多选模式
		system.myAcc.initSelectAccId = null;
		system.myAcc.initSelectMenuId = null;
		system.myAcc.openedSelf = true;

		system.myAcc.attachEvent("onBeforeActive", function(id, state) {//用于展开时点击自己close
			system.myAcc.openedSelf = false;
			//切换图标
			system.myAcc.forEachItem(function(cell){
			   if(cell.isOpened())
			   {
				   setAccMouseOutIcon(cell.cell);
			   }
			});
		    return true; // allow opening
		});
		
		$.ajax({   
	        url:leftMenuXML,   
	        type: 'GET',   
	        dataType: 'xml',   
	        async : false, 
	        success: function(xml){
	        	var childrenSize = $(xml).find("tree").children().size();
	        	
	        	var initSelectMenuId= getLocationParam("menuId");//通过获取路径中的menuId来指定选中菜单
	    		if((initSelectMenuId == null || initSelectMenuId == "") && sysCfg.devMode)//开发模式开启
	    		{
	    			initSelectMenuId = getCookie("leftMenuId" + window.location.pathname);//模块菜单选中记忆功能
	    		}
	        	
	    		/**初始化选中节点---begin**/
	    		var defaultSelectMenuId = null;
	    		var defaultSelectAccId = null;
	    		$(xml).find("tree").children().each(function(i) {
					$(this).find("item").each(function(j) {
						
	        			if($(this).attr("id") == initSelectMenuId)
	        			{
	        				system.myAcc.initSelectMenuId = initSelectMenuId;
	        				return false;
	        			}
	        			else if($(this).attr("select"))
	        			{
	        				defaultSelectMenuId = $(this).attr('id');
	        			}
	        		});
					
					if(system.myAcc.initSelectMenuId != null)//存在初始化id
					{
						system.myAcc.initSelectAccId = $(this).attr('id');
						return false;
					}
					else if(defaultSelectAccId == null && defaultSelectMenuId != null)//存在xml中的默认的初始化id
					{
						defaultSelectAccId = $(this).attr('id');
					}
					
	    		});
	    		
	    		if(system.myAcc.initSelectAccId == null)
	    		{
	    			system.myAcc.initSelectAccId = defaultSelectAccId;
	    			system.myAcc.initSelectMenuId = defaultSelectMenuId;
	    		}
	    		/**初始化选中节点---end**/
	    			    		
	        	$(xml).find("tree").children().each(function(i) {
	        		var accId = $(this).attr('id');
	        		var text = $(this).attr('text');
	        		var icon = $(this).attr('im0');
	        		//getItem($(this));
	        		
	        		//console.log($(this).find("item"));
	        		var isOpen = false;//是否打开该Acc节点
	        		if(accId == system.myAcc.initSelectAccId)
	        		{
	        			isOpen = true;
	        		}
	        		system.myAcc.addItem(accId, text, isOpen, null, icon);
	        		
	        		var subMenuXMLString = "";
	        		$(this).find("item").each(function(j) {
	        			
	        			var $item = $(this)[0];
	        			var attrStr = "";
	        			$($(this)[0].attributes).each(function(i) {
	        				
	        				attrStr += " " + $(this)[0].name + "='" + $(this)[0].value + "'";
	        		    });
	        			subMenuXMLString += "<" + $item.nodeName + "" + attrStr +"></" + $item.nodeName + ">";
	        		});
	        		
	        		var isLast = (childrenSize == (i+1) ? true : false);
	        		if(isLast)
	        		{
	        			system.myAcc.lastAccId = accId;
	        		}
	        		loadTree(accId, subMenuXMLString, isLast);
	        		
	        		$(system.myAcc.cells(accId).cell.firstChild).attr("accid", accId);
	    			$(system.myAcc.cells(accId).cell.firstChild).bind("click",function(){
	    				
	    				if(system.myAcc.openedSelf)
	    				{
	    					system.myAcc.cells($(this).attr("accid")).close();
	    					//system.myAcc.cells(system.myAcc.lastAccId).open();//展开最后一个
	    					system.myAcc.openedSelf = false;
	    				}
	    				else
	    				{
	    					system.myAcc.openedSelf = true;
	    				}
	    			});
	        	});
	        	
    			$(".dhx_cell_cont_acc").mCustomScrollbar({//自定义滚动条dhx_cell_cont_acc
    				scrollButtons:{
						enable:true //是否使用上下滚动按钮
					},
    				scrollInertia:150
    			});
    			
    			//console.log(system.myAcc.initSelectAccId);
	        }
		});
		
	}
}

function loadTree(accId, subMenuXMLString, isLast)
{
	var tree = system.myAcc.cells(accId).attachTree();
	tree.setSkin("dhx_subMenu");
	tree.setImagePath(sysCfg.treeImgPath);
	tree.enableTreeImages("false");
    tree.enableTreeLines("false");
    tree.enableTextSigns(false);
    tree.enableLineNowrap= false;
	tree.setOnLoadingEnd(function (){
		
		if(system.myAcc.initSelectAccId == accId)
		{
			//system.myAcc.cells(accId).open();
			this.selectItem(system.myAcc.initSelectMenuId,true,true);
			system.submenuTree = this;
			//设置选中图标
			setAccMouseOverIcon(system.myAcc.cells(system.myAcc.initSelectAccId).cell);
			$("div[class='dhx_cell_acc'] .dhx_cell_cont_acc").mCustomScrollbar("scrollTo",".selectedTreeRow");//使其到达选中节点位置
		}
		if(isLast)
		{
			//$(".dhxtree_dhx_subMenu td.standartTreeRow").removeAttr("nowrap");//使其不强制不换行,已改源码加入tree.enableLineNowrap解决
			
			$('div[class="dhx_cell_hdr_text dhx_cell_hdr_icon"]').each(function(i, obj){
				
				var height = (50-14)/2;
				if($(obj).height() > 50)
				{
					$(obj).css({"line-height": "14px"});
					height = (50-(14*2))/2;
					$(obj).prepend("<div style='height:"+ height +"px'></div>")
					$(obj).show();
				}
				
				//$(obj).parent().css({"padding-top": padding, "padding-bottom": padding});
			});
		}
		
		$(".dhx_cell_acc .dhx_cell_hdr").each(function(i,obj)
		{
			//是否已添加错误事件,避免重复绑定错误事件
			var hasErrorEvent=true;
			//添加鼠标经过事件
			$(obj).bind("mouseover",function()
			{
				if(!setAccMouseOverIcon(obj))
					return;
				//错误时还原路径
				var imgObj=$(obj).find("img.dhx_cell_hdr_icon");
				if(hasErrorEvent){
					imgObj.bind("error",function()
					{
						imgObj.attr("src",oldSrc);
					});
					hasErrorEvent=false;
				}
			});
				
			//添加鼠标离开事件
			$(obj).bind("mouseout",function()
			{
				if(system.myAcc.cells($(obj).attr("accid")).isOpened())
					return;
				setAccMouseOutIcon(obj);
			});
		});		
   	});
    
   	
   	//tree.enableDragAndDrop(true);
   	tree.attachEvent("onClick", function(id) {
   		system.submenuTree = this;
   		/*$.jBox.closeTip();
   		$.jBox.closeMessager();*/
		//tree.saveSelectedItem("selectedMenu","expires="+0+";path="+ "/;");
   		var menuId = id;
		//主菜单
   		id = (id.indexOf("^") == -1 ? id : id.split("^")[1]);//截取id
   		
   		//当节点不存在，默认选中第一个子节点(权限)
   		if(tree.getIndexById(id)==null)
   		{
   			id = tree.getItemIdByIndex(menuId, 0);
   		}
		//通过当前选中的菜单来设置右边的标题文本
		var model = tree.getItemText(id);
		var text = "<img src='"+sysCfg.rootPath+"/public/images/title-icon.gif'/>" + model;
		system.contentLayout.cells(layout_b).progressOn();
		system.createTab(id);
		system.contentLayout.cells(layout_b).setText(text);
		
		createCookie("leftMenuId" + window.location.pathname,menuId);
		
		$(".dhx_cell_closed .selectedTreeRow").addClass("standartTreeRow").removeClass("selectedTreeRow");
		
		//document.cookie = 'leftMenuId='+ id +';expires='+0+";path="+ "/;";
		//当前选中
		//tree.selectItem(id);
		//当前焦点
		//tree.focusItem(id);
		return true;
	});
   	var xmlString = '<?xml version="1.0" encoding="UTF-8"?><tree id="0">' + subMenuXMLString + '</tree>';
   	//console.log(xmlString);
   	tree.loadXMLString(xmlString);
}

//加载窗体变化时宽度自动适应的事件
if (window.attachEvent)
{
	window.attachEvent("onresize",resizeWindow);
}
else
{
	window.addEventListener("resize",resizeWindow, false);
}

//重置窗口大小
function resizeWindow()
{
	var t;
	var cautionHeight = null;
	var doorStateHeight = null;
	
    window.clearTimeout(t);
    window.clearTimeout(gt);
    var gt;
    t = window.setTimeout(function()
	{
    	function resizeLayout()
		{
			if(system.contentLayout)
			{
				system.contentLayout.setSizes(false);
			}
			if(typeof(myLayouts) != "undefined")
			{
				for(var key in myLayouts)
			    {
					if(document.getElementById(key) && "setSizes" in myLayouts[key])//判断该对象是否存在setSizes方法
					{
						$("#" + key).height("100%");
						var height = $("#" + key).height() - $("#" + key).position().top;
						$("#" + key).height(height);
						myLayouts[key].setSizes(false);
					}
			    }
			}
		}
    	resizeLayout();
    	resizeLayout();
    	
    	setSizesGrid();
    	if(document.getElementById("deptTreeDiv"))
    	{
    		var height = $(document.body).height()- 145 - 30 - 45;
			$("#deptTreeDiv").height(height);
			$("#layoutBox").height(height + 32);
    	}
        
        if(document.getElementById("mainDiv"))
        {
			$("#mainDiv").height("100%");
        }
        //set和编辑模板页面自适应
        $(".dhx_cell_cont_layout .content_div").height(0);
        $(".dhx_cell_cont_layout .content_div").height($(".dhx_cell_cont_layout .content_td").height());
    },50);
}

function setSizesGrid()
{
	if(typeof(mygrids) != "undefined")
	{
		for(var key in mygrids)
		{
		    if("setSizes" in mygrids[key])//判断该对象是否存在setSizes方法
		    {
			    if(mygrids[key].enableResetHeight)
			    {
			    	resizeGird(key);
			    }
		    	mygrids[key].setSizes(false);
		    }
		}
	}
	
}

//enter键事件
function enterOnKeyUp(e)
{
	var focusObj = document.activeElement;	
	//绑定回车确定事件代码
	var theEvent = window.event || e;
	var code = theEvent.keyCode || theEvent.which||e.charCode;
	if (code == 13)
	{
		//绑定全局回车提交表单事件	
		if($("#OK").length > 0)
		{
			$("#OK").click();    
		}
		//每次进来初始化
		btn = null;
		flag =  false;
		
		if(focusObj.nodeName == "INPUT" && focusObj.nodeType == 1)
		{
			var firstParentNodes = $(document.activeElement).parentsUntil('#searchBoxrightGridbox');
			var secondParentNodes = $(document.activeElement).parentsUntil('#searchBoxleftGridbox');
			if(firstParentNodes.length < secondParentNodes.length)
			{
				serchButtonObj($("#searchBoxrightGridbox"),focusObj);
				serchFoucusObj($("#searchBoxrightGridbox"),focusObj);
				if(flag)
				{
					$(btn).click();
				}
			}
			else if(firstParentNodes.length == secondParentNodes.length)
			{
				serchButtonObj($("#searchBoxgridbox"),focusObj);
				serchFoucusObj($("#searchBoxgridbox"),focusObj);
				if(flag)
				{
					$(btn).click();
				}
			}
			else if(firstParentNodes.length > secondParentNodes.length)
			{
				serchButtonObj($("#searchBoxleftGridbox"),focusObj);
				serchFoucusObj($("#searchBoxleftGridbox"),focusObj);
				if(flag)
				{
					$(btn).click();
				}
			}
		}
	}
}

//根据节点id，来判断在path后面添加参数 --add by wenxin
function addParamByMenuId(path)
{
	if (system && system.submenuTree)
	{
		var leftMenuId = system.submenuTree.getSelectedItemId();
		if(new RegExp("searchHide=").test(leftMenuId))
		{
			var pathParam = leftMenuId.split("searchHide=")[1];//[alarmEvent:'alarm']
			pathParam = pathParam.substring(1,pathParam.length-1);
			var paramKey = pathParam.split(":")[0];	
			var paramValue = pathParam.split(":")[1];	
			if(path.indexOf("?") > 0)
			{
				path += "&" + paramKey + "=" + paramValue;
			}
			else
			{
				path += "?" + paramKey + "=" + paramValue;
			}
		}
	}
	return path;
}
