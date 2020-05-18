/**
 * @Description: 该js选人控件代码，包括了树形菜单和左右选择框
 * @Author: Jason 
 * @Modified By: lianghaibo
 * @Date: 2013-01-20
 */

var actionName = "accDoor";//模块下的人员action名称，如accDoor，默认为人事下的accDoor
var gtSupportRadio = "false";//是否单选
var gtOKId = "";
var filterLeftGridGTObj = "-1";//左边列表需要过滤门的id
var isGTFilterChange = false;   //页面初始化传进来的过滤数据是否可以左右移动来改变  -- 参考门的选择和路线定义的读头选择

/**
 * 选非人的控件调用入口
 * 使用freemark在不同的文件中获取不到值如 ${(tempCard.person.name)!}，需要把值传入进来，用于编辑时使用，参考pers下的editCard.html
 * @param param 传入人员对象，生成id、text文本框，如果只是新增不需要编辑，只需要传入 personObj 参数即可
 * 示例：{"personObj": "card.person","personPin": "${(card.person.pin)!}","personName": "${(card.person.name)!}","personId": "${(card.person.id)!}"}
 */
function loadSelectWidget(param)
{
	var include = param.include != undefined ? param.include : "";
	gtOKId = param.okId != undefined ? param.okId : "confirmButton";
    //初始化参数
	initGTSelectDom(param);
	//调用的地方可能还需要做其他的验证处理， 所以submit之前需要调用 getGTSelectValue()取值
   if(include=="")
   {
	   	var	assignmentInput =  param.assignmentInput!= undefined ? param.assignmentInput : "";
   		createWindow('public_selectWidget.action^0^0^920^490^' + param.title);
			//json判断选人控件是否有人
		jsSleep();
		$("#"+gtOKId).attr("disabled", true);
		
		//确定按钮
		window.setTimeout(function(){
			$("#"+gtOKId).click(function(){
				var isGrid = param.isGrid != undefined ? param.isGrid : "";
				//alert($(isGrid.entBox).attr("id"));
				if(isGrid != "")
				{
					//isGird.addRow(1, ["<input type=checkbox name=readerIds class=chcekClass onClick=isCheckedAll('readerIds','routeHeadCheckBox','count') value="+1+" style=margin-left:7px; />",12,34]);
					mygrids["rightGTSelectGridbox"].forEachRow(function(id)
		    		{
						//movedId=
						mygrids["rightGTSelectGridbox"].moveRowTo(id,"","move","sibling",mygrids["rightGTSelectGridbox"],isGrid);
		    		});
				}
				else
				{
					
					$("#"+assignmentInput).attr("ids", getGTSelectValue()[0]);
					$("#"+assignmentInput).val(getGTSelectValue()[1].split(" ")[0]);
				}
				
				
				$("#"+$(isGrid.entBox).attr("id")+" input[name='rightGTSelectIds']").each(function(i){ 
					if($(isGrid.entBox).attr("id").indexOf("left") >= 0)
					{
						$(this).attr("name", "leftSelectIds");
						$(this).attr("onclick", "isCheckedAll('leftSelectIds', 'leftHeadCheckBox', 'count')");
					}
					else
					{
						$(this).attr("name", "rightSelectIds");
						$(this).attr("onclick", "isCheckedAll('rightSelectIds', 'rightHeadCheckBox', 'count')");
					}
				});
				closeWindow();
			});
		}, 200);
   }
   else
   {
	   var url = "public_selectWidgetContent.action";
	   $("#" + include).load(url);
	   param.findEmpIds != undefined ? param.findEmpIds: "-1";
	   $("#"+gtOKId).attr("disabled", true); 
	   if(param.formId != undefined)
	   {
		   //表单提交前事件
	       $("#"+param.formId).submit(function(){
		       $(".searchTable input").attr("disabled", true);//表单提交时将选门控件中搜索的input禁用，防止搜索的input表单提交，导致ognl报错--wenxin 20140804
	  	   });
	   }
   }
}

//单选时选人控件样式布局调整
function selectGTWidgetStyle(radio)
{
	if(radio == "true")
	{
		$("#gridAddAll").hide();
		$("#gridRemoveAll").hide();
		$("#leftGTSelectGridbox").attr("height", "270px");
		$("#rightGTSelectGridbox").attr("height", "297px");
		$("#idButtonDiv").attr("style", "padding-top: 145px;");
	}
	else
	{
		$("#leftGTSelectGridbox").attr("height", "280px");
		$("#rightGTSelectGridbox").attr("height", "305px");
	}
}

//初始化参数
function initGTSelectDom(param)
{
	var actionName = param.actionName != undefined ? param.actionName : "";
	var packageName = param.packageName != undefined ? param.packageName : "";
	var xmlFileName = param.xmlFileName != undefined ? param.xmlFileName : "";
	var selectDiv = param.selectDiv != undefined ? param.selectDiv: "";
	var filterIds = param.filterIds != undefined ? param.filterIds: "-1";
	filterLeftGridGTObj = filterIds;
	gtSupportRadio = param.radio != undefined ? param.radio : "";
	var findEmpIds = param.findEmpIds != undefined ? param.findEmpIds: "-1";
	var customData = param.customData != undefined ? param.customData: "";
	if(filterIds == "")
	{
		filterIds = "-1"
	}
	if(findEmpIds == "")
	{
		findEmpIds = "-1"
	}
	if(customData != "")
	{
		isGTFilterChange = true;
	}
	var html = '<input type="hidden" name="actionName" value="'+actionName+'" id="actionName" />'
			  +'<input type="hidden" name="xmlFileName" value="'+packageName+'" id="packageName" />'
			  +'<input type="hidden" name="xmlFileName" value="'+xmlFileName+'" id="xmlFileName" />'
			  +'<input type="hidden" name="submitGroup" value="'+filterIds+'" id="submitGroup" />'
			  +'<input type="hidden" name="findEmpIds" value="'+findEmpIds+'" id="findEmpIds" />'
			  +'<input type="hidden" name="customData" value="'+customData+'" id="customData" />';
	document.getElementById(selectDiv).innerHTML = html;
}

/**
 * 前端页面submit之前，调用这个方法返回结果
 * @param {Object} inputId 接收返回结果的文本框
 * @return 人员id组合，以逗号隔开
 */
function getGTSelectValue(inputId)
{
	$("#"+inputId).val(getGTSelectValue()[0]);
}



//获取批量选择的人员的id值
function getGTSelectValue()
{
	var myRightGrid = mygrids["rightGTSelectGridbox"];
	var rightAllRowIdsArr = myRightGrid.getAllRowIds(",").split(","); //值     id
	var retSelectedEmp = [];//文本    pin+name
	if(rightAllRowIdsArr != "")
	{
		for(var i= 0; i < rightAllRowIdsArr.length; i++)
		{
			var pin = myRightGrid.cells(rightAllRowIdsArr[i], 1).cell.innerHTML;
			pin = pin != "&nbsp;" ? pin : "";
			var name = myRightGrid.cells(rightAllRowIdsArr[i], 2).cell.innerHTML ;
			name = name != "&nbsp;" ? name : "";
			retSelectedEmp.push(pin + " " + name);
		}
	}
	return [rightAllRowIdsArr.toString(), retSelectedEmp.toString()];
}

//四个按钮功能
function changeOtherGridContact()
{
	$(".otherGrid div[class^='radioSelectDiv'], div[class^='multiSelectDiv']").each(function(){
		$(this).click(function(){
			var id = $(this).attr("id");
			var block = id == "gridAdd" || id == "gridAddAll";
			var isSelectAll = id == "gridRemove" || id == "gridRemoveAll";
			changeOtherGridContactState(block, id, isSelectAll);
		})
	});
}

var leftGTCurrPageNum;
//每次点击查询后，左边的Grid会重新加载一次，因此在点击查询后，执行下面的方法(delGTLeftRowsExistsRight);去除右边Grid中已经有的记录条数
var delGTLeftRowsExistsRight = function()
{
	mygrids["leftGTSelectGridbox"].pagingToolbar.getInput("pageNumber").value = leftGTCurrPageNum;
	mygrids["leftGTSelectGridbox"].changePage(leftGTCurrPageNum);
}
//grid排序
function orderGTGrid(grid)
{
	//考虑多选框情况  --- zhangc chenli
	if(grid.getAllRowIds().length > 0)
	{
		if(!isNaN(grid.cells(grid.getRowId(0),0).getValue()))
		{
			grid.forEachRow(function(id){
				var index = this.getRowIndex(id);
				grid.cells(id,0).setValue(index+1);
			});
		}
	}
}

//左表格单击行改变复选状态
function clickGTChangeCheckState(preGridName,rid,cind){
	if(cind!=0){
		var selRowObj=$("input[name="+preGridName+"Ids][value="+ rid +"]");
		if(selRowObj.attr("checked"))
		{
			selRowObj.attr("checked",false);
			$("#"+preGridName+"HeadCheckBox").attr("checked", false);
		}
		else
		{
			selRowObj.attr("checked",true);
			var ind=0;
			$("input[name='"+preGridName+"Ids']:checkbox").each(function(){ 
	             if($(this).attr("checked"))
	             {
	                ind++;
	             }
	    		});
			if(ind != 0 && ind==mygrids[preGridName+"Gridbox"].getRowsNum()){
				$("#"+preGridName+"HeadCheckBox").attr("checked", true);
			}
		}
	}
}

//左表格双击移动到有表格
function dblTGTClickLeftToRight(rid,cind){
	//清空
    $("#filterIds").val("-1");
	var myLeftGrid=mygrids["leftGTSelectGridbox"];
	var myRightGrid=mygrids["rightGTSelectGridbox"];
	var dblRow=$("input[name=leftGTSelectIds][value="+ rid +"]");//双击选中的行对象
	dblRow.attr("checked",false);
	var idsStr="";
    if(eval(gtSupportRadio))
    {
    	dblRow.attr("name", "rightGTSelectIds");
    	if(myRightGrid.getAllRowIds(",") != "")
	    {
    		myRightGrid.deleteRow(myRightGrid.getAllRowIds(","));
	    }   
    	myLeftGrid.moveRowTo(rid, "", "copy", "child", myLeftGrid,myRightGrid);
    	$("#rightGTSelectGridbox input[name='rightGTSelectIds']").each(function(i){ 
			$(this).css("margin-left","8px");
		});
    }
    else
    {
    	//获取右边Grid勾选的Ids
    	$("input[name='leftGTSelectIds']:checkbox").each(function(){ 
             if($(this).attr("checked"))
             {
                idsStr += $(this).val() + ",";
             }
    		});
		if(eval(1+myRightGrid.getAllRowIds(",").split(",").length) > 500)
		{
			messageBox({messageType:"alert",type:"alert",text:"${pers_widget_limitNum}"});
			return;
		}
		//将左边Grid选中的Row隐藏，同时移动到右边的Grid
		if(!myRightGrid.doesRowExist(rid) && gtSupportRadio != "true")
		{
			myLeftGrid.moveRowTo(rid, "", "copy", "child", myLeftGrid, myRightGrid);
		}
		else
		{
			var radioRightRowId = myRightGrid.getAllRowIds(",");
			if(radioRightRowId != null && radioRightRowId != "")
			{
				myRightGrid.deleteRow(radioRightRowId);
			}
			myLeftGrid.moveRowTo(rid, "", "copy", "child", myLeftGrid, myRightGrid);
		}
	
		//修改右边的checkbox的name 和onclick事件里面的参数
		$("#rightGTSelectGridbox input[type='checkbox']:first").attr('id',"rightGTSelectHeadCheckBox");
		$("#rightGTSelectGridbox input[type='checkbox']:first").click(function(){
			checkedAll('rightGTSelectIds', "rightGTSelectHeadCheckBox", "count");
		});
		var firstOffsetLeft = 0;
		$("#rightGTSelectGridbox input[name='leftGTSelectIds']").each(function(i){ 
			if(i == 0)
			{
				firstOffsetLeft = $(this).offset().left;
			}
			$(this).attr("name", "rightGTSelectIds");
			$(this).attr("onclick", "isCheckedAll('rightGTSelectIds', 'rightGTSelectHeadCheckBox', 'count')");
			if($(this).offset().left == firstOffsetLeft)
			{
				$(this).css("margin-left","8px");
			}
		});
	}
	//将左边leftGTSelectHeadCheckBox置空
	$("#leftGTSelectHeadCheckBox").attr("checked",false);
	$("#rightGTSelectHeadCheckBox").attr("checked",false);
	leftGTGridFilter(myRightGrid);
	
	if(customData != null && customData != "")
	{
		orderGTGrid(myRightGrid);
	}
	
	//重新reload Grid
	leftGTCurrPageNum = myLeftGrid.pagingToolbar.getInput("pageNumber").value;//获取当前页号
	function recoverLeftCheckRow(){
		mygrids["leftGTSelectGridbox"].pagingToolbar.getInput("pageNumber").value = leftGTCurrPageNum;
		mygrids["leftGTSelectGridbox"].changePage(leftGTCurrPageNum);
		if(idsStr!=""){
			setTimeout(function(){
			var checkArr = idsStr.split(",");
			for(var i=0; i<checkArr.length-1; i++){
				$("input[name=leftGTSelectIds][value="+checkArr[i]+"]").attr("checked",true);
			}
			if(checkArr.length-1==mygrids["leftGTSelectGridbox"].getRowsNum())
			{
				$("#leftGTSelectHeadCheckBox").attr("checked",true);
			}
			},200);
		}
	}
	reloadGrid('leftGTSelectGridbox', recoverLeftCheckRow);
	$("#selectedEmp").text(myRightGrid.getRowsNum());
	var rightObj = document.getElementsByName("rightGTSelectIds");
	if(rightObj.length > 0)
	{
		$("#"+gtOKId).attr("disabled", false);
	}
	else
	{
		$("#"+gtOKId).attr("disabled", true);
	}
}

//列表数据的左右选择操作
function changeOtherGridContactState(block, id, isSelectAll) 
{
	var size = 0;
	var idsStr = "";
	var myLeftGrid = mygrids["leftGTSelectGridbox"];
	var myRightGrid = mygrids["rightGTSelectGridbox"];
	var selectedLength =  myRightGrid.getAllRowIds(",").split(",").length;
	leftGTCurrPageNum = myLeftGrid.pagingToolbar.getInput("pageNumber").value;//获取当前页号
	
	if(id == "gridAdd")
	{
		//清空
	    $("#filterIds").val("-1");
	    if(eval(gtSupportRadio))
	    {
	    	idsStr=$('input:radio[name="leftGTSelectIds"]:checked').val();
	    	if(idsStr == "" || idsStr == undefined)
	    	{
	    		messageBox({messageType:"alert", text: "${pers_widget_noPerson}"});
				return false;
	    	}
	    	else
	    	{
	    		$('input:radio[name="leftGTSelectIds"]:checked').attr("name", "rightGTSelectIds");
	    		if(myRightGrid.getAllRowIds(",") != "")
		    	{
		    		myRightGrid.deleteRow(myRightGrid.getAllRowIds(","));
		    	}   
	    	}
	    	myLeftGrid.moveRowTo(idsStr, "", "copy", "child", myLeftGrid, myRightGrid);
	    	$("#rightGTSelectGridbox input[name='rightGTSelectIds']").each(function(i){ 
					$(this).css("margin-left","8px");
			});
	    }
	    else
	    {
			$("input[name='leftGTSelectIds']:checkbox").each(function(){ 
		        if($(this).attr("checked"))
		        {
		        	$(this).attr("checked",false);
		            idsStr += $(this).val() + ",";
		            size ++;
		         }
	   		});
			if(eval(size+selectedLength) > 500)
			{
				messageBox({messageType:"alert",type:"alert",text:"${pers_widget_limitNum}"});
				return;
			}
			//将左边Grid选中的Row隐藏，同时移动到右边的Grid
			var idsArray = idsStr.split(",");
			if(idsArray == "" || idsArray == undefined)
			{
				messageBox({messageType: "alert", title: "${common_prompt_title}", text: "${common_widget_noItems}"});
				return false;
			}
			if(idsArray != null && idsArray.length != 0)
			{
				for(var i = 0; i < idsArray.length - 1; i++)
				{
					if(!myRightGrid.doesRowExist(idsArray[i]) && gtSupportRadio != "true")
					{
						myLeftGrid.moveRowTo(idsArray[i], "", "copy", "child", myLeftGrid, myRightGrid);
					}
					else
					{
						var radioRightRowId = myRightGrid.getAllRowIds(",");
						if(radioRightRowId != null && radioRightRowId != "")
						{
							myRightGrid.deleteRow(radioRightRowId);
						}
						myLeftGrid.moveRowTo(idsArray[i], "", "copy", "child", myLeftGrid, myRightGrid);
						break;
					}
				}
//				if(actionName.indexOf("Reader") >= 0)
//				{
//					mygrids["rightGTSelectGridbox"].forEachRow(function(id){
//						var index = this.getRowIndex(id);
//						mygrids["rightGTSelectGridbox"].cells(id,0).setValue(index+1);
//					});
//				}
			}
			//修改右边的checkbox的name 和onclick事件里面的参数
			$("#rightGTSelectGridbox input[type='checkbox']:first").attr('id',"rightGTSelectHeadCheckBox");
			$("#rightGTSelectGridbox input[type='checkbox']:first").click(function(){
				checkedAll('rightGTSelectIds', "rightGTSelectHeadCheckBox", "count");
			});
			var firstOffsetLeft = 0;
			$("#rightGTSelectGridbox input[name='leftGTSelectIds']").each(function(i){ 
				if(i == 0)
				{
					firstOffsetLeft = $(this).offset().left;
				}
				$(this).attr("name", "rightGTSelectIds");
				$(this).attr("onclick", "isCheckedAll('rightGTSelectIds', 'rightGTSelectHeadCheckBox', 'count')");
				if($(this).offset().left == firstOffsetLeft)
				{
					$(this).css("margin-left","8px");
				}
			});
		}
		//将左边leftGTSelectHeadCheckBox置空
		$("#leftGTSelectHeadCheckBox").attr("checked",false);
		leftGTGridFilter(myRightGrid);
		
		if(customData != null && customData != "")
		{
			orderGTGrid(myRightGrid);
		}
		//重新reload Grid
		if(idsStr != null && idsStr != "")
		{
			reloadGrid('leftGTSelectGridbox', delGTLeftRowsExistsRight);
		}
		
		$("#selectedEmp").text(myRightGrid.getRowsNum());
	}
	else if(id == "gridAddAll")
	{
		//清空
	    $("#filterIds").val("-1");
		//获取左边Grid所有Ids
		var leftAllRowIdsArr = getCurPageRowIds("leftGTSelectGridbox",",").split(",");
		size =leftAllRowIdsArr.length;
		if(eval(size+selectedLength) > 500)
		 {
			 messageBox({messageType:"alert",type:"alert",text:"${pers_widget_limitNum}"});
			 return;
		 }
		if(leftAllRowIdsArr == "")//左边列表没有数据，返回
		{
			return;	
		}
		for(var i = 0; i < leftAllRowIdsArr.length; i++)
		{
			if(!myRightGrid.doesRowExist(leftAllRowIdsArr[i]))
			{
				myLeftGrid.moveRowTo(leftAllRowIdsArr[i], "", "copy", "child", myLeftGrid, myRightGrid);
			}
		}
//		if(actionName.indexOf("Reader") >= 0)
//		{
//			mygrids["rightGTSelectGridbox"].forEachRow(function(id){
//					var index = this.getRowIndex(id);
//					mygrids["rightGTSelectGridbox"].cells(id,0).setValue(index+1);
//			});
//		}
		//修改右边的checkbox的name 和onclick事件里面的参数
		$("#rightGTSelectGridbox input[type='checkbox']:first").attr('id', "rightGTSelectHeadCheckBox");
		$("#rightGTSelectGridbox input[type='checkbox']:first").click(function(){
				checkedAll('rightGTSelectIds', "rightGTSelectHeadCheckBox", "count");
		});
		var firstOffsetLeft = 0;
		$("#rightGTSelectGridbox input[name='leftGTSelectIds']").each(function(i){ 
			if(i == 0)
			{
				firstOffsetLeft = $(this).offset().left;
			}
			$(this).attr("name", "rightGTSelectIds");
			$(this).attr("onclick", "isCheckedAll('rightGTSelectIds', 'rightGTSelectHeadCheckBox', 'count')");
			if($(this).offset().left == firstOffsetLeft)
			{
				$(this).css("margin-left", "8px");
			}
		});
		$("#leftGTSelectHeadCheckBox").attr("checked",false);	
		leftGTGridFilter(myRightGrid);
		
		//重新reload Grid
		reloadGrid('leftGTSelectGridbox');
		setTimeout(function(){
			$("#selectedEmp").text(myRightGrid.getRowsNum());
		}, 200);
		//自动跳转到下一页
		//myLeftGrid.pagingToolbar.callEvent("onClick",["nextPage"]);
		if(customData != null && customData != "")
		{
			orderGTGrid(myRightGrid);
		}
	}
	else if(id == "gridRemove")
	{
		//清空
	    $("#filterIds").val("-1");
	    if(eval(gtSupportRadio))
	    {
	    	idsStr=$('input:radio[name="rightGTSelectIds"]:checked').val()
	    	if(idsStr == "" || idsStr == undefined)
	    	{
	    		messageBox({messageType:"alert", title: "${common_prompt_title}", text: "${pers_widget_noPerson}"});
				return false;
	    	}
	    	else
	    	{
	    		myRightGrid.deleteRow(idsStr)
	    	}
	    }
	    else
	    {
			//获取右边Grid勾选的Ids
			$("input[name='rightGTSelectIds']:checkbox").each(function(){ 
		         if($(this).attr("checked"))
		         {
		            idsStr += $(this).val() + ",";
		         }
	   		});
			var idsArray = idsStr.split(",");
			if(idsArray == "" || idsArray == undefined)
			{
				messageBox({messageType: "alert", title: "${common_prompt_title}", text: "${common_widget_noItems}"});
				return false;
			}
			for(var i = 0; i < idsArray.length-1; i++)
			{
				myRightGrid.deleteRow(idsArray[i]);
			}
		}
		
		//将左边Gird中含有rightGTSelectIds（即右边Grid移动过来的），先放在这里 
		//$("#leftGTSelectGridbox input[name='rightGTSelectIds']").each(function(){ 
		//	$(this).attr("name","leftGTSelectIds");
		//	$(this).attr("onclick","isCheckedAll('leftGTSelectIds','leftGTSelectHeadCheckBox','count')");
		//	$(this).attr("style","margin-left: 8px;");
		//});
		
	    $("#rightGTSelectHeadCheckBox").attr("checked", false);
	    leftGTGridFilter(myRightGrid);
	    
		//重新reload Grid
	    if(idsStr != null && idsStr != "")
		{
			reloadGrid('leftGTSelectGridbox', delGTLeftRowsExistsRight);
		}
	    //再由n变为0时，由于数据先删了，再来split会报错 -- zhangc 2014-06-18
//	    $("#selectedEmp").text(myRightGrid.getAllRowIds(",").split(",").length);
	    $("#selectedEmp").text(myRightGrid.getRowsNum());
		if(customData != null && customData != "")
		{
			orderGTGrid(myRightGrid);
		}
	}
	else if(id == "gridRemoveAll")
	{
		//清空
	    $("#filterIds").val("-1");
	    //alert($("#filterIds").val());
		var idsArray = myRightGrid.getAllRowIds(",").split(",");
		for(var i = 0; i < idsArray.length; i++)
		{
			myRightGrid.deleteRow(idsArray[i]);
		}
		
		leftGTGridFilter(myRightGrid);
		//重新reload Grid
		if(idsArray != null && idsArray != "")
		{
			reloadGrid('leftGTSelectGridbox');
		}
		$("#rightGTSelectHeadCheckBox").attr("checked",false);
		$("#selectedEmp").text("0");
	}
	var rightObj = document.getElementsByName("rightGTSelectIds");
	if(rightObj.length > 0)
	{
		$("#"+gtOKId).attr("disabled", false);
	}
	else
	{
		$("#"+gtOKId).attr("disabled", true);
	}
	myLeftGrid.setSizes(false);
	myRightGrid.setSizes(false);
}

//左边列表需要过滤的*
function leftGTGridFilter(myRightGrid)
{
	var rightCountArr = myRightGrid.getAllRowIds(",").split(",");//右边列表已经选择的人员
	$("#submitGroup").val(rightCountArr);
	if(rightCountArr != "")//左边的过滤初始化、查询按钮已经过滤，右边选择了人员后才需要重新过滤
    {
		if(isGTFilterChange)
		{
			$("#filterIds").val(rightCountArr);
		}
		else
		{
			$("#filterIds").val(filterLeftGridGTObj ? filterLeftGridGTObj + "," + rightCountArr : rightCountArr);
		}
    }
    else 
    {
    	if(isGTFilterChange)
    	{
    		$("#filterIds").val("");
    	}
    	else
    	{
    		$("#filterIds").val(filterLeftGridGTObj);
    	}
    }
}
