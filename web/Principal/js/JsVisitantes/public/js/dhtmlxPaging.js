/**
 * @Description: 此js为dhtmlxgrid分页  主要在listTemplate中进行了加载
 * @Author: lynn.chen  陈立  
 * @Modified By:
 * @Date: 2013-01-08
 */

var i18n =
{
	//分页
	common_paging_results:"共{0}条记录",
	common_paging_records:"从{0}到{1}",//
	common_paging_page:"页 ",//
	common_paging_perpage:"每页{0}行",//
	common_paging_first:"第一页",//
	common_paging_previous:"上一页",//
	common_paging_next:"下一页",//
	common_paging_last:"最后一页",//
	common_paging_notfound:"没有记录", //
	common_paging_jumpto:"跳转至",//
	common_paging_jumpError:"请输入正确的跳转页数",//
	common_paging_jumpPages:"跳转的页数",//
	common_paging_loading:"加载中"
};

var defaultGridName = "gridbox";
var mygrids = new Array();
var pagerSizeArray = Array(10, 15, 20, 25, 50, 75, 100, 200, 300);//每页显示条数数组
var pagerSizeIndex = 4;//默认显示条数数组下标
var pagesInGrp = pagerSizeArray.length;//可见的页面选择器个数

var path = window.location.href;
var expires = new Date();//cookie保存的有效时间
expires.setTime(expires.getTime()+ 1*12* 30 * 24 * 60 * 60 * 1000);

/**
 * 
 * TODO 获取grid当前页所有行id
 * <p>
 * TODO 用于替换getAllRowIds,
 *      getAllRowIds获取的数据是从rowsBuffer数组里获取的，该数组可能存在脏数据，即可能存在未渲染成dom元素的Object对象
 * <pre>
 * getCurPageRowIds("leftPersonSelectGridbox",',')
 * </pre>
 * 
 * @author chenpf
 * @see 相关类或方法，不需要请删除此行
 * @since 2015年4月2日 下午5:30:15
 * @param gridName grid的id
 * @param a 分隔符
 * @returns
 */
function getCurPageRowIds(gridName,a) {
	var grid=mygrids[gridName];
    for (var b = [], c = 0; c < grid.rowsBuffer.length; c++) {
    	if(grid.rowsBuffer[c] && grid.rowsBuffer[c].tagName){
    		b.push(grid.rowsBuffer[c].idd);
    	}
    }
    return b.join(a || grid.delim);
}

//清除行数据缓存
function clearRowsBuffer(gridName){
	var firstRow = mygrids[gridName].getStateOfView()[1];//get the index of the first row on the leaving page
	var lastRow = mygrids[gridName].getStateOfView()[2];//get the index of the last row on the leaving page
	for(var i= firstRow; i<lastRow; i++)
	{
		if(lastRow == 0)
		{
			break;
		}
		mygrids[gridName].rowsBuffer[i] = 0; //clear the cache of the row from the leaving page
	}
}

//path=actionName(0, 1).toLowerCase() + actionName.substring(1)+"Action!getAll.action";//进行头字母小写转换
/*headData: headData.columnList, headData.minWidthBuffer,headData.headerMenuBuffer,headData.fixedColumn*/
function doOnGridLoad(rootPath, headData, user, actionPath, parentObj, callback, param, xmlFileName)
{
	var gridName = "gridbox";
	if(actionPath)
	{
    	path = actionPath + (actionPath.indexOf("?") == -1 ? "?" : "&") + $.param($.ajaxSetup().data);
    }
	
	if(typeof(parentObj) != "undefined" &&  parentObj != null && typeof(parentObj) == "object")
    {
    	gridName = (parentObj.id ? parentObj.id : gridName);
    	mygrids[gridName] = parentObj;
    }
    else
    {
    	gridName = (parentObj ? parentObj : gridName);
    	mygrids[gridName] = new dhtmlXGridObject({
		    parent: gridName,
		    image_path: rootPath+"/public/controls/dhtmlx/skins/web/imgs/",
		    skin: sysCfg.dhxSkin,
		    columns: eval(headData.columnList),
		    multiselect: false
		});
    }
	var searchCondition = getSearchTerm(gridName);
	param += searchCondition;
	mygrids[gridName].searchCondition = searchCondition;
	param = param ? param : "";
	
	//$("#" + gridName).addClass("dhxGridBox");
    if($("#" + gridName).parent().parent().attr("class") == "dhx_cell_cont_layout")
    {
    	$("#" + gridName).addClass("cellDhxGridBox");
    }
	
	mygrids[gridName].setColumnMinWidth(headData.minWidthBuffer);//设置列的最小宽度("50,70,70,70,70,70,70,70")
	mygrids[gridName].enableHeaderMenu(headData.headerMenuBuffer);//设置可进行字段可选的列
	mygrids[gridName].enableTooltips(headData.tooltipBuffer);//设置可进行字段可选的列
	
	
	mygrids[gridName].id = gridName;
	mygrids[gridName].enableSorting = true;
	mygrids[gridName].enableResetHeight = true;
	
    //清除缓存信息
	if(!$("#"+gridName).attr("useCache"))
	{
		mygrids[gridName].attachEvent("onBeforePageChanged", function(ind, count){
			clearRowsBuffer(gridName);
			return true;
		});
	}
  
    var loadMode = "paging"; //加载模式paging/smart（分页/智能）
	if($("#"+gridName).attr("loadMode"))
	{
	    loadMode = $("#"+gridName).attr("loadMode").split(",")[0];
	}
	
	if(loadMode == "smart")
	{
		var loadCount = 50;
		var loadParams =  $("#"+gridName).attr("loadMode").split(",");
		if(loadParams.length>0)
		{
			loadCount = loadParams[1];
			
			mygrids[gridName].enableSmartRendering(true);
			if(loadCount != "*")
			{
				path += (path.indexOf("?")>0 ? "&" : "?") + "pager.posStart=0&pager.pageSize=" + loadCount;
			}
		}
	}
	else
	{
		var pagingId = gridName + "_paging";
	    if(document.getElementById(pagingId))
	    {
	    	$("#" + pagingId).addClass("dhxGridBoxPaging");
	    	if($("#" + pagingId).position().left != 0)
	    	{
	    		//$("#" + pagingId).css("border-left", "1px solid #A4BED4");
	    		//$("#" + pagingId).css("border-right", "1px solid #A4BED4");
	    	}
			//the sorting direction (“asc”,”des”)
		    //mygrids[gridName].setPagingWTMode(true, true, true, true);自带的分页模式
			//mygrids[gridName].enablePaging(true, 16, 10, gridName ? gridName + "_pagingArea" : "pagingArea", true, gridName ? gridName + "_infoArea" : "infoArea");
		    
	    	//使用分页，设置默认条数和可见的页面选择器个数
	    	var pagingObj = document.getElementById(pagingId);
		    mygrids[gridName].enablePaging(true, pagerSizeArray[pagerSizeIndex], pagesInGrp, true);
			mygrids[gridName].pagingToolbar = new dhtmlXToolbarObject(pagingId);
			mygrids[gridName].pagingToolbar.setSkin(sysCfg.dhxSkin);
			mygrids[gridName].pagingToolbar.setIconsPath("public/controls/dhtmlx/skins/web/imgs/dhxgrid_web/");
			$(pagingObj).addClass("in_layoutcell");
			var pagingWTModes = [true,true,true,true];//默认分页模式为全部出现（上下页，选择分页数，可跳转分页和总页数，总记录数）
		    if($("#"+pagingId).attr("setMode"))
		    {
		    	pagingWTModes = eval("[" + ($(pagingObj).attr("setMode")).split(",") + "]");//转换成数组
		    }
			
		    //上下页
	    	mygrids[gridName].pagingToolbar.addButton("firstPage", 0, "", "ar_left_abs.gif", "ar_left_abs_dis.gif");
	    	mygrids[gridName].pagingToolbar.addButton("previousPage", 1, "", "ar_left.gif", "ar_left_dis.gif");
	    	
	    	mygrids[gridName].pagingToolbar.addText("info", 2, "${common_paging_loading}");//加载中//${common_paging_recordsInfo}:从{0}到{1}
	    	
	    	mygrids[gridName].pagingToolbar.addButton("nextPage", 3, "", "ar_right.gif", "ar_right_dis.gif");
	   	 	mygrids[gridName].pagingToolbar.addButton("lastPage", 4, "", "ar_right_abs.gif", "ar_right_abs_dis.gif");
	   	 	mygrids[gridName].pagingToolbar.addSeparator("separator1", 5);
	   	 	
		    //每页多少条
	    	var pagingOpts = new Array();
		    for(var i = 1; i<=pagesInGrp; i++)
		    {
		    	//建立页数选择的数组{0}为要替换的页数
		    	pagingOpts[i-1] = Array(i, 'obj', "${common_paging_perpage}".replace("{0}", pagerSizeArray[i-1]));//${common_paging_perpage}:每页{0}行
		    }
		    mygrids[gridName].pagingToolbar.addButtonSelect("pageSize", 6, pagingOpts[pagerSizeIndex][2] + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;", pagingOpts, null, null, true, true);
		    //mygrids[gridName].pagingToolbar.setWidth("pageSize", 100);
		   	mygrids[gridName].pagingToolbar.addSeparator("separator1", 7);
		   	
		   	//跳转到第几页
	    	mygrids[gridName].pagingToolbar.addText("pageNumberText", 8, "${common_paging_jumpto}");//${common_paging_jumpto}:跳转至
		    mygrids[gridName].pagingToolbar.addInput("pageNumber", 9, 0, 40);
		    
		    mygrids[gridName].pagingToolbar.addText("pageCount", 10, "/"+0 + " " + "${common_paging_page}");//${common_paging_page}:页
			mygrids[gridName].pagingToolbar.addSeparator("separator3", 11);
			//总记录条数
		    mygrids[gridName].pagingToolbar.addText("records", 12, "${common_paging_results}".replace("{0}", 0));//${common_paging_results}:共{0}条记录
			
		   	if($("#"+pagingId).attr("showInfo") && $("#"+pagingId).attr("showInfo") == "false")
	    	{
	    		mygrids[gridName].pagingToolbar.hideItem("info");
	    	}
		    
		   	$("#"+pagingId+" div.dhx_toolbar_btn").css("padding","2px 0px 1px");
		   	//$("#"+pagingId+" div.dhx_toolbar_btn").css("margin-right","-7px");
		   	$(".dhx_toolbar_poly_dhx_web td.td_btn_img").remove();
		   	
		    if(pagingWTModes[0] == false)
			{
				mygrids[gridName].pagingToolbar.hideItem("firstPage");
				mygrids[gridName].pagingToolbar.hideItem("previousPage");
				mygrids[gridName].pagingToolbar.hideItem("info");
				mygrids[gridName].pagingToolbar.hideItem("nextPage");
				mygrids[gridName].pagingToolbar.hideItem("lastPage");
				mygrids[gridName].pagingToolbar.hideItem("separator1");
			}
		   	
		    if(pagingWTModes[1] == false)
			{
				mygrids[gridName].pagingToolbar.hideItem("pageSize");
				mygrids[gridName].pagingToolbar.hideItem("separator1");
			}
		    
		    var obj = mygrids[gridName].pagingToolbar.getInput("pageNumber");
		    $(obj).css("padding","0px");
		    $(obj).css("height","16px");
		    //此方法主要使跳转页数文本框获得焦点时，移动光标到文本框最后
			obj.onfocus = function()
			{
				setFocus(obj);
				obj.select();
			}
			//当跳转页数文本框按下回车时执行跳转操作，其中包含验证（判断页数的合法性）
			$(obj).keypress(function(e){
				var key = e.which;
				var reg = new RegExp("\\d");
				if(key == 13 )//回车键
				{
					if(isNaN(this.value) || this.value == "")//判断是否是数字和是否为空
					{
//						alert("${common_paging_jumpError}");//${common_paging_jumpError}:请输入正确的跳转页数
						messageBox({messageType: "alert", title: "${common_prompt_title}", text: "${common_paging_jumpError}"});
						
					}
					else if(this.value > mygrids[gridName].pageCount)
					{
//						alert("${common_paging_jumpError}")
						messageBox({messageType: "alert", title: "${common_prompt_title}", text: "${common_paging_jumpError}"});
					}
					else
					{
						mygrids[gridName].changePage(this.value);
					}
				}
				else if(key == 8 || key == 0)//8为删除键，0为F5
				{
					isValid = true;
				}
				else//正常输入的页数
				{
					var keychar = String.fromCharCode(key);
			    	isValid = reg.test(keychar);//判断输入是否为数字，不是则将禁用
				}
				return isValid;
			});
			
			if(pagingWTModes[2] == false)
			{
				mygrids[gridName].pagingToolbar.hideItem("pageNumberText");
				mygrids[gridName].pagingToolbar.hideItem("pageNumber");
				mygrids[gridName].pagingToolbar.hideItem("pageCount");
				mygrids[gridName].pagingToolbar.hideItem("separator3");
			}
			
		    if(pagingWTModes[3] == false)
			{
				mygrids[gridName].pagingToolbar.hideItem("records");
			}
		    
		    mygrids[gridName].pagingToolbar.setItemToolTip("firstPage", "${common_paging_first}");//${common_paging_first}:第一页
		   	mygrids[gridName].pagingToolbar.setItemToolTip("previousPage", "${common_paging_previous}");//${common_paging_previous}:上一页
		    mygrids[gridName].pagingToolbar.setItemToolTip("nextPage", "${common_paging_next}");//${common_paging_next}:下一页
		    mygrids[gridName].pagingToolbar.setItemToolTip("lastPage", "${common_paging_last}");//${common_paging_last}:最后一页
		    mygrids[gridName].pagingToolbar.setItemToolTip("pageNumber", "${common_paging_jumpPages}");//跳转至
			
			mygrids[gridName].pagingToolbar.disableItem("firstPage");
    		mygrids[gridName].pagingToolbar.disableItem("previousPage");
    		mygrids[gridName].pagingToolbar.disableItem("nextPage");
    		mygrids[gridName].pagingToolbar.disableItem("lastPage");
    		mygrids[gridName].pagingToolbar.disableItem("pageNumber");
			
    		mygrids[gridName].pagingToolbar.name = gridName;
		    mygrids[gridName].pagingToolbar.attachEvent("onClick", function(id){
		    	gridName = this.name;
		    	switch (id) 
		    	{
					case "firstPage":
						mygrids[gridName].changePage(1); 
						break;
					case "previousPage":
						mygrids[gridName].changePageRelative(-1);
						break;
					case "nextPage":
						mygrids[gridName].changePageRelative(1);
						break;
					case "lastPage":
						mygrids[gridName].changePage(mygrids[gridName].pageCount);
						break;
					default:
						id= id-1;//因为数组下标是0开始，而id循环是从1开始的
						pagerSizeIndex = id;
						/*mygrids[gridName].forEachRow(function(id){
							
							this.setRowHidden(id,true);
						});*/
					
						clearRowsBuffer(gridName);
						mygrids[gridName].enablePaging(true, pagerSizeArray[pagerSizeIndex], pagesInGrp,null,true);
						mygrids[gridName].pagingToolbar.setItemText("pageSize", pagingOpts[pagerSizeIndex][2] + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;");
						mygrids[gridName].setPagingSkin("custom");
						//使用正则替换加载路径中的分页显示条数
						mygrids[gridName].path = mygrids[gridName].path.replace(/\bpager.pageSize=\d+\b/, "pager.pageSize="+pagerSizeArray[pagerSizeIndex]);
						mygrids[gridName].changePage(1);
						//reloadGrid(gridName);
						break;
				}
		    });
		    
			mygrids[gridName]._pgn_custom = function(page, start, end, records){
				gridName = this.id;				
		    	//var info =  "${common_paging_recordsInfo}".format((start+1), end);
		    	this.pagingToolbar.setItemText("info", (start+1)+" - "+end);
		    	
		    	this.pagingToolbar.setValue("pageNumber", page);
		    	this.pageCount = Math.ceil(records/pagerSizeArray[pagerSizeIndex]);//设定总页数
		    	this.pagingToolbar.setItemText("pageCount", "/" + this.pageCount + " " + "${common_paging_page}");//页
		    	
		    	this.pagingToolbar.setItemText("records","${common_paging_results}".replace("{0}", records));//${common_paging_results}:共{0}条记录
		    	//记录条数，供获取条数时使用---by Jason20130523
		    	if(this.pagingToolbar.objPull[this.pagingToolbar.idPrefix+"recordsCount"] == null)
		    	{
		    		this.pagingToolbar.addText("recordsCount", 13, records);
		    	}
		    	else
		    	{
		    		this.pagingToolbar.setItemText("recordsCount",records);
		    	}
		    	this.pagingToolbar.hideItem("recordsCount");
		    	
		    	var relWidth = 0;
				var relInd = 0;
				$("#" + gridName + "_paging .dhxtoolbar_float_${((enableRTL!'false')=='true')?string('right','left')}").children().each(function()
				{
					if (this.style.display != "none" && relInd < 12)
					{
						relWidth += $(this).width();
						relInd++;
					}
				});
				if (relWidth + 52 >= $("#" + gridName + "_paging .dhxtoolbar_float_${((enableRTL!'false')=='true')?string('right','left')}").width())
				{
					this.pagingToolbar.setItemText("records", "<span title='"+"${common_paging_results}".replace("{0}", records)+"'>"+records+"</span>");//${common_paging_results}:共{0}条记录
		    	}
		    	//判断当前是否是第一页，是则禁用第一页和上一页
			    if(page == 1)
			    {
		    		this.pagingToolbar.disableItem("firstPage");
		    		this.pagingToolbar.disableItem("previousPage");
		    	}
			    else
			    {
			    	this.pagingToolbar.enableItem("firstPage");
		    		this.pagingToolbar.enableItem("previousPage");
			    }
			    
			    //判断当前是否是最后一页，是则禁用下一页和最后一页
		    	if(page == this.pageCount)
		    	{
		    		this.pagingToolbar.disableItem("nextPage");
		    		this.pagingToolbar.disableItem("lastPage");
		    	}
		    	else
		    	{
		    		this.pagingToolbar.enableItem("nextPage");
		    		this.pagingToolbar.enableItem("lastPage");
		    		
		    	}
		    	
		    	if(this.pageCount == 1)
		    	{
		    		this.pagingToolbar.disableItem("pageNumber");
		    	}
		    	else
		    	{
		    		this.pagingToolbar.enableItem("pageNumber");
		    	}
		    	
		    	if(records == 0)
		    	{
		    		this.pagingToolbar.disableItem("firstPage");
		    		this.pagingToolbar.disableItem("previousPage");
		    		this.pagingToolbar.disableItem("nextPage");
		    		this.pagingToolbar.disableItem("lastPage");
		    		this.pagingToolbar.disableItem("pageNumber");
		    		this.pagingToolbar.setItemText("info","<font color='#E57A14'> 0 </font/>");//${common_paging_notfound}:没有记录
		    	}
		    	else
		    	{
		    		var linkId = "moreConditionLink" + gridName;
		    		//查询完，如果有结果，则收起。
					if($("#"+linkId).attr("value") == "show")//表示前面是显示，将会隐藏
					{
						moreScreeningCondition('${common_search_retract}', '${common_more}', gridName);
					}
		    	}
		    	
		    	//this.pagingToolbar.attachEvent("onPaging", function(count){alert(count)});
		    }		

		    mygrids[gridName].setPagingSkin("custom");
		    path += (path.indexOf("?")>0 ? "&" : "?") + "pager.posStart=0&pager.pageSize=" + pagerSizeArray[pagerSizeIndex];
		    if(path.indexOf("xmlFileName") > 0 && xmlFileName)
		    {
			    path += "&xmlFileName=" + xmlFileName;
		    }
		}
	    else
	    {
	    	$("#" + gridName).addClass("noDhxGridBoxPaging");
	    }
	    
	}
    if(xmlFileName)
    {
    	 path += "&xmlFileName=" + xmlFileName;
    }
    //mygrids[gridName].setPagingSkin("toolbar", "dhx_blue");
	//mygrids[gridName].setPagingSkin("bricks");//设置分页皮肤bricks,toolbar,("toolbar","dhx_black")
    //mygrids[gridName].setPagingTemplates("Pages - [current:0] [current:+1] [current:+2] , from [total] rows","Pages <b>[from]-[to]</b> of <b>[total]</b>");
    mygrids[gridName].enableColumnMove(true);//是否可移动列
    mygrids[gridName].enableAutoHeight(false);//是否可自动适应高度
	mygrids[gridName].enableAutoWidth(false);//是否可自动适应宽度
	//mygrids[gridName].forceLabelSelection(true);
	//mygrids[gridName].enableBlockSelection(true);
	//mygrid.setAwaitedRowHeight(25);
	//可复制列信息（同时去掉了dhtmlxgrid.css 下div.gridbox table.obj td 的 -moz-user-select）
	mygrids[gridName].entBox.onselectstart=function(e){(e||event),cancelBubble=true;return true;};

	mygrids[gridName].sortField_old = mygrids[gridName].sortField;
	var nowGridName = gridName;
	mygrids[gridName].sortField = function() {
		if(this.enableSorting)//启用排序功能
		{
			var columnArray = eval(headData.columnList);
	        if(customColumnSort(arguments[0], columnArray[arguments[0]].id,columnArray[arguments[0]].sort, nowGridName))//arguments[0]获得当前要排序的列的下标
	        {
	        	mygrids[gridName].sortField_old.apply(this, arguments);
	        }
		}
    }
	//加载数据时进行提示
	mygrids[gridName].attachEvent("onXLS", function(){
        showLoading(true, this.id);
        this.setSizes(false);
    });
	mygrids[gridName].attachEvent("onXLE", function(){
		showLoading(false, this.id);
		if(typeof(this._fake) != "undefined")
  		{
			$(this.obj).find("tr td:hidden:lt(" + this._fake._cCount +")").text("");//去除表数据重复的td内容
		}
		this.setSizes(false);
	});
    
	$("#" + gridName + " .objbox").bind("click",function(){
		$(".dhx_header_cmenu").hide();
		if($(".dhx_textarea").size() > 0)
		{
			mygrids[gridName].selectRowById(mygrids[gridName].getSelectedRowId(),true,true,true);
		}
		
	});
	
     //加载和保存cookie信息load为读取，Saving为保存
	mygrids[gridName].loadOrderFromCookie(user+"_"+actionPath);//字段显示顺序
	mygrids[gridName].loadSortingFromCookie(user+"_"+actionPath);//排序
	//mygrids[gridName].loadSizeFromCookie(user+"_"+actionPath);//列宽
	mygrids[gridName].loadHiddenColumnsFromCookie(user+"_"+actionPath);//显示字段
	//mygrids[gridName].enableAutoSizeSaving(user+"_"+actionPath);
	mygrids[gridName].enableSortingSaving(user+"_"+actionPath);
	mygrids[gridName].enableOrderSaving(user+"_"+actionPath);
	mygrids[gridName].enableAutoHiddenColumnsSaving(user+"_"+actionPath);
	
	//mygrids[gridName].enableAutoSaving(user+"_"+actionPath,"expires="+expires.toGMTString()+";path="+ "/;");
	mygrids[gridName].path = path;
	mygrids[gridName].actionName = path.substring(0,path.lastIndexOf("Action!"));
	
	//判断是否存在自定义的回调函数，不存在则使用默认的
  	if(!callback)
  	{
  		callback = function(){};
  	}
    //grid固定列 0 -- 默认是后台传过来  undefined -- 是其他调用处没传这个参数
  	if(undefined != headData.fixedColumn && "0" != headData.fixedColumn)
  	{
  		mygrids[gridName].splitAt(headData.fixedColumn);
  		if(typeof(mygrids[gridName]._fake) != "undefined")
  		{
  			$(mygrids[gridName].hdr).find("tr td:hidden:lt(" + mygrids[gridName]._fake._cCount +")").text("");//去除表头重复的td内容
  		}
  		//mygrids[gridName].enableResetHeight = false;
  	}
	mygrids[gridName].load(path + param, eval(callback), "json");//读取数据

    
	//下面是对subgrid的支持
	mygrids[gridName].attachEvent("onSubGridCreated", function(subgrid, id, ind, rowData){
		var packageName = rowData.split("^")[0];
		var actionName = rowData.split("^")[1];
		var xmlFileName = rowData.split("^")[2];
		var idName = rowData.split("^")[3];
		$.ajax({
			type: "get",
			async: false,
			dataType: "json",
			url: "baseAction!getGridHeader.action",
			data: {
				"packageName": packageName,
				"actionName": actionName,
				"xmlFileName": xmlFileName
			},
			success: function(data, textStatus) {
				var headers = new Array();
				var colTypes = new Array();
				var initWidths = new Array();
				var colAligns = new Array();
				var columnArray=eval(data.columnList);
				
				for(var i = 0; i<columnArray.length; i++){
					headers.push(columnArray[i].label);
					colTypes.push(columnArray[i].type);
					initWidths.push(columnArray[i].width);
					colAligns.push(columnArray[i].align);
				}
				//加单引号是使之成为subgrid与之能够识别的数据
				subgrid.setHeader('' + headers + '');
				subgrid.setColTypes('' + colTypes + '');
      			subgrid.setInitWidths('' + initWidths + '');
      			subgrid.setColAlign('' + colAligns + '');
      			
      			subgrid.enableAutoHeight(true);//是否可自动适应高度
				subgrid.enableAutoWidth(true);//是否可自动适应宽度
      			
      			subgrid.setColumnMinWidth(data.minWidthBuffer);
      			
      			//可复制列信息（同时去掉了dhtmlxgrid.css 下div.gridbox table.obj td 的 -moz-user-select）
				subgrid.entBox.onselectstart=function(e){(e||event),cancelBubble=true;return true;};
      			
      			subgrid.init();
      			subgrid.setSkin(dhxSkin);
      			var actionPath = actionName + "Action!getAll.action?pager.posStart=0&pager.pageSize=" + pagerSizeArray[pagerSizeIndex];
      			actionPath += "&" + idName + "=" + id;
      			actionPath += "&packageName=" + packageName;
      			subgrid.actionPath = actionPath;
      			mygrids[gridName].enableAutoWidth(true);//是否可自动适应宽度
			    subgrid.load(actionPath, function(){
					subgrid.callEvent("ongridReconstructed", []);//加回调函数，重置gird网格加载方法
					mygrids[gridName].enableAutoWidth(false);//是否可自动适应宽度
					window.setTimeout(function(){
						subgrid.setSizes(false);
						mygrids[gridName].setSizes(false);
					}, 1000);
				},"json");
     			return false; // prevent default behavior
			}
		});
	})
	window.gridUser = user;
}


/**注：此方法使用了大量grid源码
 * @Description: 获取显示列的name和label，其顺序是一对一的关系
 * 				另外还有actionName（主要是结合导出时用到）
 * @Author: lynn.chen  陈立
 * @Modified By:
 * @Date: 2013-08-20
 * @param: gridName 指定要获取那个grid，为null是则使用默认的defaultGridName（gridBox）
 * @return var retData = {
				fields : columnIds,
				labels : hdrLabels,
				gridName : thisGrid.actionName
			};
			columnIds和hdrLabels为数组，2个顺序和长度一致
 */
function getDisplayField(gridName)
{
	gridName = (mygrids[gridName] == null ? defaultGridName : gridName);
	var thisGrid = mygrids[gridName];
	//var columnIds = [];//存放列的id，也就是配置文件中的属性值
	//var hdrLabels = [];//存放列的文本内容
	var hrrar = [].concat(thisGrid._hrrar || []);//复选框选择后需要隐藏的列
	if (thisGrid._fake && thisGrid._fake._hrrar)
	{
		for ( var d = 0; d < thisGrid._fake._cCount; d++)
		{
			hrrar[d] = thisGrid._fake._hrrar[d] ? "1" : "";
		}
	}
	hrrar = hrrar.join(",").replace(/display:none;/g, "1");
	hrrar = hrrar.split(",");
	
	var fieldData = "{";//存放列信息，json格式的字符串{name:chenli}
	var c = 0;
	if (thisGrid._fake)//存在splitAt，前半部分列
	{
		c = thisGrid._fake._cCount;
		//初始化时不提供选择列（复选框中不会出现的列），结合复选框中的列进行获取
		for ( var g = 0,i =0; g < thisGrid._fake._cCount; g++)
		{
			var e = thisGrid._fake.hdr.rows[1].cells[g];
			if ((!thisGrid._hm_config || thisGrid._hm_config[g] && 
					thisGrid._hm_config[g] != "false") && hrrar[g] != 1)
			{
				//{"title":"编号","name":"id"}
				fieldData += "\""+thisGrid.columnIds[g]+"\":\""+thisGrid.hdrLabels[g]+"\",";
				//fieldData += (thisGrid.columnIds[c] + ":" + thisGrid.hdrLabels[c]);
			}
		}
	}
	
	//初始化时不提供选择列（复选框中不会出现的列），结合复选框中的列进行获取
	for ( var g = c; c < thisGrid.hdr.rows[1].cells.length; c++) 
	{
		var e = thisGrid.hdr.rows[1].cells[c];
		if ((!thisGrid._hm_config || thisGrid._hm_config[c] && 
				thisGrid._hm_config[c] != "false") && hrrar[c] != 1)
		{
			//{"title":"编号","name":"id"}
			fieldData += "\""+thisGrid.columnIds[c]+"\":\""+thisGrid.hdrLabels[c]+"\",";
			//fieldData += (thisGrid.columnIds[c] + ":" + thisGrid.hdrLabels[c]);
		}
		g += e.colSpan || 1;
	}
	fieldData = fieldData.substr(0,fieldData.length-1);
	fieldData += "}";
	
	//加上查询条件的数据
	if(mygrids[gridName].searchCondition != null)
	{
		fieldData += "@"+ mygrids[gridName].searchCondition;
	}
	else
	{
		fieldData += "@ ";
	}
	//alert(system.submenuTree.getSelectedItemText());
	//var retData = {
	//	fieldData : fieldData,
	//	gridName : thisGrid.actionName
	//};
	//alert("http://localhost:8088/empImport.action?fields=" + columnIds + "&labels="+hdrLabels);
	/*
	$.ajax({
		type: "post",
		url: "/persPersonAction!getById.action",
		data : {
			fieldData : fieldData
		},
		success: function(result)
	    {
		 	dealRetResult(result);
		} 
    });
	*/
	//console.log(fieldData);
	return fieldData;
}


//grid加载中的处理
function showLoading(isShow, gridName) 
{
	//alert(gridName);
    if(isShow == true && !document.getElementById(gridName +"PolyProgress"))
    {
    	$("#" + gridName +" .objbox").append("<div class='dhtmlxLayoutPolyProgress' id='" + gridName +"PolyProgress' style='background-color: #93C0E7;opacity: 0.15;width: 100%;z-index: 1;height: 100%;position: absolute;left: 0;top: 0;'></div>" +
    										"<div class='dhtmlxLayoutPolyProgressBGIMG' style=\"background-image: url('" + sysCfg.rootPath + "/public/controls/dhtmlx/dhtmlxLayout/codebase/imgs/dhxlayout_dhx_skyblue/dhxlayout_progress.gif');background-position: center center;background-repeat: no-repeat;height: 100%;left: 0;position: absolute;top: 0;width: 100%;z-index: 1;\"></div>");//加入转圈处理
    	$("#" + gridName + " .checkClass").attr("checked", false);//清除checkbox为不选中
    }
    else
    {
    	$("#"+ gridName +" .objbox div").remove();//清除转圈
    }
}

function exportTrans(id)
{
	var str = id.split("^");
	var st = "";
	for(var i=0; i < str.length; i++)
	{
		if(i > 0)
		{
			st = st + "^" +str[i];
		}
	}
	gridName = id && id.split("#")[1] ? id.split("#")[1] : (id ? id : defaultGridName);
	gridName = gridName.split("^")[0] ? gridName.split("^")[0] : gridName;
	gridName = mygrids[gridName] == null ? defaultGridName : gridName;
	var searchPath = id.split("^")[0];
	var searchDivs = new Array("topSearchBox"+gridName, "belowSearchBox" + gridName);
	//----------------add by Liang.Haibo----------
	//----------------checkbox勾选问题
	var head ;
	var child ;
  	if(gridName.indexOf("right") >= 0 || gridName.indexOf("left") >= 0)
  	{
  		if(gridName.indexOf("EmpSelect") >= 0)
  		{
  			if(gridName.charAt(0) == "r")
	  		{
	  			head = gridName.substring(0,5) + "EmpSelectHeadCheckBox";
	  			child = 'rightEmpSelectIds';
	  		}
	  		else
	  		{
	  			head = gridName.substring(0,4) + "EmpSelectHeadCheckBox";
	  			child = 'leftEmpSelectIds';
	  		}
  		}
  		else
  		{
  			if(gridName.charAt(0) == "r")
	  		{
	  			head = gridName.substring(0,5) + "HeadCheckBox";
	  			child = 'empIds';
	  		}
	  		else
	  		{
	  			head = gridName.substring(0,4) + "HeadCheckBox";
	  			child = 'empIds';
	  		}
	  	}
  	}
  	//----------------end -------------------------
  	var searchCondition = "";
	for(var i = 0; i < searchDivs.length; i++)
	{
		if(document.getElementById(searchDivs[i]))
		{
			var selects = document.getElementById(searchDivs[i]).getElementsByTagName('select');
			for(var j = 0; j < selects.length; j++) 
			{
				if(selects[j].name != "")
				{
					searchCondition += "&"+selects[j].name + "=" + selects[j].value.trim();
				}
			}
			var inputs=document.getElementById(searchDivs[i]).getElementsByTagName('input');
			for( var j = 0; j < inputs.length; j++) 
			{
				if(inputs[j].name != "" && inputs[j].disabled == false)
				{
					if(inputs[j].value != "")
					{
						var value = inputs[j].value.trim();
						//将特殊字符转义,后台需要将转义的特殊字符解析
						value = transferredMeaning(value);
						searchCondition += "&" + inputs[j].name + "=" + value;
					}
				}
			}
		}
	}
	mygrids[gridName].searchCondition = searchCondition + (mygrids[gridName].col_name ? "&sortName=" + mygrids[gridName].col_name : "") 
										+ (mygrids[gridName].sort_order ? "&sortOrder=" + mygrids[gridName].sort_order : "");
	searchPath += searchCondition;
  	//showLoading(true);
  	if(mygrids[gridName].col_name&&mygrids[gridName].a_direction)
  	{
  		searchPath = searchPath + "&sortName=" + mygrids[gridName].col_name + "&sortOrder=" + mygrids[gridName].sort_order;
  	}
    if (mygrids[gridName].a_direction)
    {
    	mygrids[gridName].setSortImgState(true, mygrids[gridName].s_col, mygrids[gridName].a_direction);
    	mygrids[gridName].saveSortingToCookie(window.gridUser + "_" + actionName);//因为自动保存不能生效，这里进手动保存
    }
    mygrids[gridName].setSizes(false);
    
    searchPath += st+"^"+operateToolbars[operateObjId].getItemText(id);
    openWindow(searchPath);
}
function reloadGrid(id, callback) 
{
	//由于可能是操作栏进行的刷新操作，id会已#来进行拆分
	// 查询默认id为 **gridBox^selectedFirstRow，如果不是左侧列表，需要去掉后面的selectedFirstRow，避免点击了第一行
	id = id ? id.split("^")[0] == "leftGridbox" ? id : id.split("^")[0] : defaultGridName;
	var gridName = id && id.split("#")[1] ? id.split("#")[1] : (id ? id : defaultGridName);
	gridName = gridName.split("^")[0] ? gridName.split("^")[0] : gridName;
	gridName = mygrids[gridName] == null ? defaultGridName : gridName;
	
	//此处不做判断的话，非常规页面，会有bug，modify by: ob.huang  2013-08-30,lynn.chen 2013-11-27
	if(!(mygrids[gridName] || document.getElementById(gridName)))
	{
		return;
	}
	
	//绑定转圈事件 ,直接调用不起作用 -- zhangc 
	
	//showLoading(true,gridName);
	var searchPath = mygrids[gridName].path;
	var searchCondition = getSearchTerm(gridName);
	
	mygrids[gridName].searchCondition = searchCondition + (mygrids[gridName].col_name ? "&sortName=" + mygrids[gridName].col_name : "") 
										+ (mygrids[gridName].sort_order ? "&sortOrder=" + mygrids[gridName].sort_order : "");
	searchPath += searchCondition;
	  
  	if(mygrids[gridName].col_name&&mygrids[gridName].a_direction)
  	{
  		searchPath = searchPath + "&sortName=" + mygrids[gridName].col_name + "&sortOrder=" + mygrids[gridName].sort_order;
  	}
  	//判断是否存在自定义的回调函数，不存在则使用默认的
  	if(!callback)
  	{
  		callback = function(){
  			if(id.indexOf("selectedFirstRow") > 0)
  			{
	    		if(mygrids[gridName].getRowId(0) != undefined)
	    		{
					mygrids[gridName].selectRow(0,true,true,true);
				}
	    		else
	  			{
	  				mygrids[gridName].callEvent("onRowSelect", [-1])
	  			}
	    	}
  		}
  	}
    mygrids[gridName].clearAndLoad(searchPath, eval(callback), "json");
    
    if (mygrids[gridName].a_direction)
    {
    	mygrids[gridName].setSortImgState(true, mygrids[gridName].s_col, mygrids[gridName].a_direction);
    	mygrids[gridName].saveSortingToCookie(window.gridUser + "_" + actionName);//因为自动保存不能生效，这里进手动保存
    }
    mygrids[gridName].setSizes(false);
    
}

function customColumnSort(ind,sortName,sortType,gName) 
{
    if(sortType == "na") 
    {
        //alert("Table can't be sorted by this column.");
        if(mygrids[gName].s_col)
        {
        	//mygrids[gridName].setSortImgState(true, window.s_col, window.a_direction);
        }
        return false;
    }
    var a_state = mygrids[gName].getSortingState();
    mygrids[gName].s_col = ind;
    mygrids[gName].col_name = sortName;//定义要排序的列
    mygrids[gName].sort_order = ((a_state[1] == "des") ? "asc": "desc");
    mygrids[gName].a_direction = ((a_state[1] == "des") ? "asc": "des");
    reloadGrid(gName);
    return true;
}

function delById(delPath, callbackFun) 
{
	commonOp(delPath,"${common_prompt_sureToDel}",1200,0,callbackFun);
}
//------------------add start by 赵崚峰
//------------------弹出确认窗口，提示信息operateText，确定后执行operatePath
function confirmOp(opPath,opText,callbackFun) 
{
	commonOp(opPath,"${common_prompt_sureToDel}",1200,0,callbackFun);
}

function commonOp(opPath,opText,expireValue,opNum,callbackFun,gridName) 
{
	var submit = function (result) {
	    if(result) 
	    {
	    	onLoading(function(){
	    		$.ajax({
					type: "GET",
					url: opPath,
					async : true,
					success: function(result)
				    {
					 	dealRetResult(result,function(){
					 		
							if(callbackFun)
							{
								//多笔操作
								if(opNum > 0)
								{
									callbackFun(opNum);
								}
								else//单笔操作
								{
									callbackFun();
								}
							}
							else
							{
								if(gridName)
								{
									if (gridName.indexOf("^") >= 0) //刷新多个grid  add by pokiz.xu 徐秀滨
									{
										var firstGridName = gridName.substring(0,gridName.indexOf("^"));
										var secondGridName = gridName.substring(gridName.indexOf("^") + 1);
										reloadGrid(firstGridName);
										reloadGrid(secondGridName);
									}
									else
									{
										reloadGrid(gridName);     //刷新列表
									}
								}
								else
								{
									reloadGrid();
								}
							}
					 	});
					}
		        });
	    	});
	    }
	    else
	    {
	        // 取消
	    }
	    return true; //close
	};
	messageBox({messageType:"confirm",text:opText,callback:submit});
}

function manyDel(delPath, callbackFun) 
{
	//判断路径中是否存在"#",存在则取#号后面是数据为路径（在list页面中操作xml中存在）
	gridName = delPath.split("#")[1] ? delPath.split("#")[1] : defaultGridName;
	delPath = delPath.indexOf("#")>0 ? delPath.split("#")[0] : delPath;
	var idsName = delPath.split("^")[1] ? delPath.split("^")[1] : "ids";
	var ids = getGridCheckboxValue(idsName);
	if(ids != "") //判断是否至少选中一个
	{
		if(delPath.indexOf("$(") > 0)
		{
			var objId  = delPath.substring(delPath.indexOf("$(") + "$(".length, delPath.lastIndexOf(")"));
			delPath = delPath.replace("$(" + objId +")", $("#" + objId).val());//替换原来的id标识为当前值
		}
		delPath = delPath.split("^")[0] + (delPath.indexOf("?") > 0 ? "&" : "?") + "un=" + Date.parse(new Date())+"&"+idsName+"="+ids;
		var num = ids.split(",").length;
		
		commonOp(delPath,"${common_prompt_sureToDelThese}",2000,num,callbackFun,gridName);
		//$.jBox.confirm('你确定要删除此'+num+'条信息吗?', '提示', submit,{top:'30%'});
	} 
	else 
	{
		messageBox({messageType:"alert",text:"${common_prompt_selectObj}"});
	}
}

/**
 * @Description: 获取grid中Checkbox选中的值
 * @Author: lynn.chen  陈立  
 * @Modified By:
 * @Date: 2013-01-23
 * @param: checkboxName Checkbox的name值,默认为ids，这里是
 * @param: split 获取value的分隔符，默认为逗号","
 */ 
function getGridCheckboxValue(checkboxName,split) 
{
	split = split? split : ",";
	//判断路径中是否存在"#",存在则取#号后面是数据为路径（在list页面中操作xml中存在）
	var ids = "";//拼接所获取的id值
	
	var obName = checkboxName ? checkboxName : "ids";
	var selected = false;//判断是否至少选中一个
	$("[name='"+ obName +"']:checked:not(:hidden)").each(function(){
		ids += $(this).val() + split;
		selected = true;
    });
	
	return selected ? ids.substring(0,ids.length-1) : "";
}

//重新加载grid
function loadMygrid(path, gName) 
{
	var gridName = gName ? gName : defaultGridName;
	mygrids[gridName].clearAndLoad(path, "json");//读取数据
}

function loadGrid(packageName, actionName, parentName, headId, obName, numId, callback, param, xmlFileName)
{
	resizeGird(parentName);
	//前面是获取表头信息
	$.ajax({
		type: "get",
		async: false,
		dataType: "json",
		url: "baseAction!getGridHeader.action",
		data: 
		{
			"packageName": packageName,
			"actionName": actionName,
			"xmlFileName": xmlFileName
		},
		success : function(headData, textStatus) 
		{
			//这里是真正的加载数据
			param = param ? param : "";
			
			doOnGridLoad(sysCfg.rootPath , headData, 'admin', actionName+"Action!getAll.action?packageName="+packageName, parentName, callback, param, xmlFileName);
			//这里是全选CheckBox事件
			
			headId = headId ? headId : "headCheckBox";
			obName = obName ? obName : "ids";
			numId = numId ? numId : "count";
			$("#"+headId).click(function(){
				checkedAll(obName, headId, numId);
			});
		}    
	});
}

/**
 * @Description: 重置grid大小
 * @Author: lynn.chen  陈立  
 * @Modified By:
 * @Date: 201312-27
 * @param: girdName
 */ 
function resizeGird(gridName)
{
	if(document.getElementById(gridName))
	{
		$("#" + gridName).height("100%");
		$("#" + gridName).width("100%");
		if($("#" + gridName).position().left == 0)
    	{
    		$("#" + gridName).width($("#" + gridName).width());
    	}
		var height = $("#" + gridName).outerHeight() - $("#" + gridName).position().top;
		if(document.getElementById(gridName + "_paging"))
		{
			height -= $("#" + gridName + "_paging").height();
			if(dhtmlx.skin && dhtmlx.skin.indexOf("web") != -1)
			{
				height += 0;
			}
			if(dhtmlx.skin && dhtmlx.skin.indexOf("terrace") != -1)
			{
				height -= 6;
			}
			else
			{
				height -= 2;
			}
		}
		height -= ($("#" + gridName).outerHeight() - $("#" + gridName).height());
		$("#" + gridName).height(height);
		if($("#" + gridName).find(".gridbox_dhx_web").size() > 0)
		{
			$("#" + gridName).find(".gridbox_dhx_web").height(height);
			$("#" + gridName).find(".gridbox_dhx_web").next().height(height);
		}
		$("#" + gridName).width("100%");
		
		//自适应宽度调整
		var topW=$("#rightSearchBox" + gridName).width();//搜索栏宽度
		var cellW=$("[id$=LayoutBox" + gridName + "]").parent(".dhx_cell_cont_layout").width();//grid内容宽度
		if(topW != null && cellW != null)
		{
			//grid内容宽度大于搜索栏宽度，还原grid宽度100%,火狐浏览器会有滚动条所以减1
			if(topW < cellW)
			{
				$("#searchBox" + gridName).width("100%");
				$("[id$=LayoutBox" + gridName + "]").width(cellW - 1); 
			}
			//grid内容宽度小于搜索栏宽度，放大grid宽度
			else
			{
				$("#searchBox" + gridName).width(topW);
				$("[id$=LayoutBox" + gridName + "]").width(topW);
			}
			$("#belowSearchBox" + gridName).width($("#searchBox" + gridName).width() - 10);
		}
	}
}
/**
 * @Description: 设置路径参数并对grid进行重新加载
 * @Author: lynn.chen  陈立  
 * @Modified By:
 * @Date: 201312-27
 * @param: girdName
 * @param: param 参数名
 * @param: value 参数值
 * @param: isReload 是否重载，默认为true
 */ 
function pathSetAndReloadGrid(gridName, param, value, isReload)
{
	isReload = (isReload && isReload.toString() == "false" ? false : true);
	if(mygrids[gridName].path.indexOf(param) == -1)//不存在直接拼接
	{
		mygrids[gridName].path += "&" + param + "=" + value
	}
	else//存在则替换值
	{
		mygrids[gridName].path = mygrids[gridName].path.replace(eval("/\\b" + param + "=(\\d+||\\w+)?\\b/"), param + "=" + value);
	}
	if(isReload)
	{
		reloadGrid(gridName);
	}
}

/**
 * @Description: 取得搜索的条件value
 * @Author: zhangc 张超  
 * @Modified By:
 * @Date: 2014-04-30
 * @param: girdName  当前表格gird对象
 * @return 当前表格的搜索条件
 */
function getSearchTerm(gridName)
{
	var searchDivs = new Array("topSearchBox"+gridName, "belowSearchBox" + gridName);
	var searchCondition = "";
	for(var i = 0; i < searchDivs.length; i++)
	{
		if(document.getElementById(searchDivs[i]))
		{
			var selects = document.getElementById(searchDivs[i]).getElementsByTagName('select');
			for(var j = 0; j < selects.length; j++) 
			{
				if(selects[j].name != "")
				{
					searchCondition += "&"+selects[j].name + "=" + selects[j].value.trim();
				}
			}
			var inputs=document.getElementById(searchDivs[i]).getElementsByTagName('input');
			for( var j = 0; j < inputs.length; j++) 
			{
				if(inputs[j].name != "" && inputs[j].disabled == false)
				{
					if(inputs[j].value != "")
					{
						var value = inputs[j].value.trim();
						//编码格式转换
						//value=escape(encodeURIComponent(value)); 
						value = transferredMeaning(value);
						searchCondition += "&" + inputs[j].name + "=" + value;
					}
				}
			}
		}
	}
	return searchCondition;
}
