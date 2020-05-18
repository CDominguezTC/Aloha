var destroyedObjArr = new Array(); //全局变量，存储当window关闭时应销毁的所有dhmlxcombox对象
var myTrees = new Array(); //全局变量
/**
 * @Description:    初始化comboxTree控件变量方法
 * @Author: helang  何朗  
 * @Modified Carlton.wei 魏坤建 2014-01-02
 * @Date:   2013-04-11
 * @param   zoneId  初始化comboxTree控件div的ID
 * @param   url     AJAX异步加载数据的url
 * @param onClickHandler 点击事件
 * @param onClickOkHandler 点击ok键产生的事件。
 * @param onBlurHandler 或者当前树失去焦点时产生的事件
 * @param isTree 是否是单层树
 * @param needEnableThreeState 是否需要使用包含下级
 */
function loadComboxTree(zoneId, url, onClickHandler, onClickOkHandler, onBlurHandler, comboWidth, comboHeight,isTree,needEnableThreeState,isCheckDisable,onClickClearHandler)
{
    //初始化一些参数  readOnly
    this.selectWidth = $("#" + zoneId).attr("selectWidth") ? $("#" + zoneId).attr("selectWidth") : 150;
    this.comboxTreeWidth = $("#" + zoneId).attr("comboxTreeWidth") ? $("#" + zoneId).attr("comboxTreeWidth") : 220;
    this.comboxTreeHeight = $("#" + zoneId).attr("comboxTreeHeight") ? $("#" + zoneId).attr("comboxTreeHeight") : 250;
    this.needCheckBox = $("#" + zoneId).attr("type") == "radio" ? false : true;
    this.readOnly = $("#" + zoneId).attr("readOnly") ? false : true;
    this.comboxTreeName = $("#" + zoneId).attr("objName");
    this.filter = $("#" + zoneId).attr("filter") ? $("#" + zoneId).attr("filter") : "";
    var filterId = "";
    if ("" != this.filter) 
    {
        filterIds = ("," + this.filter).split(",");
        for ( var i = 0; i < filterIds.length; i++) 
        {
            if ("" != filterIds[i]) 
            {
                filterId += filterIds[i] + ",";
            }
        }
        url = url + "?" + "filterId=" + filterId.substring(0, filterId.length - 1);
    }
    if(comboWidth != undefined && comboWidth != "")
    {
        comboxTreeWidth = comboWidth;
    }
    if(comboHeight != undefined && comboHeight != "")
    {
        comboxTreeHeight = comboHeight;
    }
    this.comboxObj = initCombox.call(this, zoneId, selectWidth, comboxTreeWidth, comboxTreeHeight, comboxTreeName, readOnly,isTree,needEnableThreeState,isCheckDisable);
    this.treeObj = new dhtmlXTreeObject(zoneId + "TreeBoxId", "100%", "100%", 0);
    this.treeObj.onClickHandler = onClickHandler;
    this.treeObj.onClickOkHandler = onClickOkHandler;
    this.treeObj.onBlurHandler = onBlurHandler;
    this.treeObj.onClickClearHandler=onClickClearHandler;
    initTree.call(this, treeObj, zoneId + "TreeBoxId", url, needCheckBox, zoneId, comboxObj, readOnly,needEnableThreeState);
    myTrees[zoneId] = this.treeObj;
    return this.treeObj;
}
/**
 * @Description:    此方法创建dhtmlxCombox对象
 * @Author: helang  何朗  
 * @Date:   2013-04-11
 * @param   zoneId      初始化comboxTree控件div的ID   
 * @param   selectWidth     下拉框的宽度  
 * @param   comboxTreeWidth 下拉树的宽度
 * @param   comboxTreeHeight下拉树的高度
 * @param   comboxTreeName  dhtmlxCombox对象的name属性
 * @param   readOnly        dhtmlxCombox对象的判断是否只读属性
 * @return  返回创建成功后的dhtmlxCombox对象 
 */
function initCombox(zoneId, selectWidth, comboxTreeWidth, comboxTreeHeight, comboxTreeName, readOnly,isTree,needEnableThreeState,isCheckDisable)
{
    //comboxTreeWidth = selectWidth;
    //comboxTreeHeight = comboxTreeHeight - 50;
    //window.dhx_globalImgPath = "/public/controls/dhtmlx/dhtmlxCombo/codebase/imgs/";
    dhtmlx.skin = sysCfg.dhxSkin;
    //创建下拉框
    var comboxObj = new dhtmlXCombo(zoneId, comboxTreeName, selectWidth);
    
    comboxObj.addOption("", "");//初始化一个默认Option用于设定改变的value和text，否则报错
    $(comboxObj.DOMlist).children(".dhxcombo_option").hide();
    $(comboxObj.DOMlist).addClass("comboxTreeAutoHeight");
    
    readOnly ? comboxObj.readonly(1) : comboxObj.readonly(0);
    //comboxObj.enableFilteringMode(true);
    comboxObj.enableFilteringMode(false);
    //comboxObj.enableOptionAutoHeight(true,200);
    //comboxObj.enableOptionAutoWidth(true);
    comboxObj.setOptionWidth(comboxTreeWidth);
    //comboxObj.setOptionHeight(comboxTreeHeight);
    var treeBox = appendMyDiv(zoneId + "TreeBoxId", comboxObj, comboxTreeWidth, comboxTreeHeight,isTree,needEnableThreeState,isCheckDisable); //传入tree容器的ID
    destroyedObjArr.push(comboxObj); //将window窗口关闭时所要销毁的对象装入数组
    //$($("input[name='" + comboxTreeName + "']").get(0).previousSibling).attr("name",comboxTreeName+".love");
    return comboxObj;
}
/**
 * @Description:  此方法创建dhtmlxTree对象，及对其实绑定一些事件处理，样式
 * @Author: helang  何朗  
 * @Date:   2013-04-11 
 * @param   tree            未初始化的空dhtmlxTree对象
 * @param   treeBoxId       初始代dhtmlxTree所绑定的div的ID
 * @param   url             AJAX异步加载数据URL
 * @param   needCheckBox    树上是否显示checkbox
 * @param   zoneId          comboxTree控件div的ID
 * @param   comboxObj       dhtmlxCombox对象
 * @param   readOnly        dhtmlxCombox对象的判断是否只读属性
 * @param needEnableThreeState 是否需要使用包含下级
 */
function initTree(tree, treeBoxId, url, needCheckBox, zoneId, comboxObj ,readOnly,needEnableThreeState) 
{
    //tree.setSkin(sysCfg.dhxSkin);
    tree.setImagePath("/public/controls/dhtmlx/skins/web/imgs/dhxtree_web/");
    tree.enableDragAndDrop(false);
    if(needEnableThreeState!=null && needEnableThreeState==true)
	{
    	tree.enableThreeStateCheckboxes(true);//包含下级
	}
    else
	{
    	tree.enableThreeStateCheckboxes(false);//默认不包含下级
	}
    if(!needCheckBox)
    {
        tree.enableRadioButtons(true);
        // 加入清空和确定按钮---gordon.zhang
        var clearButton = $("<div class='divTopCheckbox'>&nbsp;</div>");
        if("${enableRTL!}"=='true'){
        	clearButton.append("<button class ='button' id='" + treeBoxId +  "ClearAll' style='position:absolute;right:2px;padding: 0px 1px; margin: 1px;margin-right: 2px;'>${common_search_clear}</button>");
        }
        else{
           	clearButton.append("<button class ='button' id='" + treeBoxId +  "ClearAll' style='padding: 0px 1px; margin: 1px;margin-left: 2px;'>${common_search_clear}</button>");
        }
        
        //clearButton.append("<button class='button' id='" + treeBoxId +  "OK' style='padding: 0px 1px; margin: 1px;margin-right: 2px; float:right;'>${common_edit_ok}</button>");
        $("#" + zoneId + "TreeBoxId").css("height","auto");
        $("#" + zoneId + "TreeBoxId").css("overflow","none");
        $("#" + zoneId + "TreeBoxId").append(clearButton);
        // 清空和确定按钮在第一位---gordon.zhang
        var firstObj = $("#" + zoneId + "TreeBoxId .containerTableStyle");
        firstObj.attr("style","height: 190px;");
        clearButton.insertBefore(firstObj);
    }
    else
    {
        tree.enableCheckBoxes(1);
    }
    tree.setXMLAutoLoading(url);
    tree.setDataMode("json");
    tree.zoneId = zoneId;
    tree.treeBoxId = treeBoxId;
    tree.comboxObj = comboxObj;
    tree.needCheckBox = needCheckBox;
    tree.readOnly = readOnly;
    //加快页面加载的速度，增加一个事件
    //整体测试发现---懒汉式加载影响到了修改功能的下拉树回填，所以在div上加了个属性 --- 14-04-03 by：张超
    var operator = function(){
        tree.loadJSON(url, function(data){
            var obj = jQuery.parseJSON(data.xmlDoc.responseText);
            if(obj && obj.hasOwnProperty('length') && obj.length == 0)
            {
                tree.enableRadioButtons(false);
                tree.enableCheckBoxes(0);
                $("img[src$='csh_bluebooks/radio_off.gif']").hide();
            }
            else
            {
                initSelectTree(tree);
            }
        });
    }
    //搜索栏如果需要lazyLoad,在对应的xml中配置该属性 lazyLoad=true   其余情况不用管 
    if('true' == $.trim($("#" + zoneId).attr('lazyLoad')))
    {   
        $("#" + zoneId).one('mouseover', operator);
    }
    else
    {
        operator();
    }
    
    //下面代码给comboxtree组件加一些样式
    var treeBoxObj = document.getElementById(treeBoxId);
    var childs = treeBoxObj.childNodes;
    var divHeight = $(treeBoxObj).css("height");
    $(treeBoxObj.firstChild).addClass("clearContainerTableStyle");
    $(childs[1]).css("width", "100%");
    //这边提交的时候要把下面134-142行的注释打开
    $(childs[1]).css("height", parseInt(divHeight) - 20);
    $(childs[1]).css("padding", "0px");
    tree.needObj = $(childs[1]); //将此对象绑定到tree上，供下面使用
    $(treeBoxObj.parentNode).addClass("clearDhx_combo_list");
    var comboxOb = document.getElementById(zoneId);
    if (comboxOb.childNodes[1]) 
    {
        $(comboxOb.childNodes[1].firstChild).addClass("clearDhx_combo_input");
    } 
    else 
    {
        $(comboxOb.firstChild.firstChild).addClass("clearDhx_combo_input");
    }
    //此处兼容前台表单验控件，移除干扰样式
    var removeClassObjs = $(".dhx_combo_box");
    for ( var i = 0; i < removeClassObjs.length; i++) 
    {
        if ($(removeClassObjs[i]).parent().attr("id") == zoneId) 
        {
            $(removeClassObjs[i]).css("overflow", "visible");
        }
    }
    //判断是否需要"包含下级"的DIV
    if(!needCheckBox)
    {
        $("#"+treeBoxId).children().eq(0).remove();
        tree.needObj.css("height", "100%");
    }
    else
    {
        /*加入全选复选框---lynn.chen*/
        var checkAllBox = $("<div  class='divBottomCheckbox clearContainerTableStyle'>&nbsp;</div>");
        
        checkAllBox.append("<input type='checkbox' id='" + treeBoxId + "checkAllBox' style='vertical-align:middle; height:20px;'/>");
        checkAllBox.append("&nbsp&nbsp;&nbsp;<a href='javascript:void(0)' id='" + treeBoxId + "checkAllLink' style='text-decoration:none;'>${common_tree_selectAll}</a>");
        
        $("#" + zoneId + "TreeBoxId").css("height","auto"); 
        $("#" + zoneId + "TreeBoxId").css("overflow","none");
        $("#" + zoneId + "TreeBoxId").append(checkAllBox);
        //$(comboxObj.DOMlist).
    }
    //清空时，将下拉树选中的取消掉
    $("#clearSearch").click(function(){
        clearAndUncheck(tree);
        tree.comboxObj.closeAll();
    });
    //清空时，将选人控件下拉树选中的取消掉
    $("#clearSearchEmpSelect").click(function(){
        clearAndUncheck(tree);
    });
    
    //给下拉选框下面的树状组件绑定事件
    $("#" + treeBoxId).parent().hover(function() {
        comboxObj.openSelect();
    }, function() {
        //comboxObj.closeAll();
        if(tree.onBlurHandler)
        {
            tree.onBlurHandler();
        }
    });
    
    
}
/**
 * @Description:  此方法给dhtmlxTree绑定事件，回选
 * @Author: helang  何朗  
 * @Date:   2013-04-11  
 * @param   tree   dhtmlxTree对象，绑定相应的初始化参数
 */
function initSelectTree(tree) 
{
    var flag = false;
    var callBackDataArray = new Array();    //创建数组，存储前台传过来的所有已选对象
    var objName = $("#" + tree.zoneId).attr("objName")
    
    //给tree添加onCheck事件，实现文本框动态显示选定内容
    var removeIds = new Array();
    //以下两个变量是用来存储值，处理点开一下拉树，再关掉的时候，会导致hidden域的value由ID值变成文本值bug
    var comboHideIds = "";
    var comboCheckedText = "";
    //给结绑定"onOpenStart"事件
    tree.attachEvent("onOpenStart", function(id,state)
    {
        return true;
    });
    //给结绑定"onOpenEnd"事件
    tree.attachEvent("onOpenEnd", function(id,state)
    {
        //给文本框及隐藏域赋值
        var hideIds = "";
        var checkedText = "";
        var value = "";
        var checkIds = ","+this.getAllChecked();
        var checkedIds = checkIds.split(",");
        for(var i = 0;i < checkedIds.length; i++)
        {   
            if(("" != checkedIds[i] && "0" != checkedIds[i] && -1 != checkedIds[i]))
            {
                checkedText += this.getItemText(checkedIds[i])+",";
                hideIds += checkedIds[i] + ",";
            }
        }
        //当装有回选数据的数组不为null的时候，每次给下拉树赋值必须将回选数据数组中的值添加上
        if(null != callBackDataArray)
        {
            for(var i=0; i<callBackDataArray.length; i++)
            {
                checkedText += callBackDataArray[i].text+",";
                hideIds += callBackDataArray[i].id+",";
            }
        }
        checkedText = checkedText.substring(0,checkedText.length-1);
        hideIds = hideIds.substring(0,hideIds.length-1);
        
        if("" != checkedText)
        {
            if(tree.readOnly)
            {
                value = hideIds;
            }
            else
            {
                value = checkedText;
            }
            comboHideIds = hideIds;
            comboCheckedText = checkedText;
        }
        else
        {
            value = "";
            checkedText = "";
            comboHideIds = "";
            comboCheckedText = "";
        }
        updateComboxValue(tree, value, unescape(checkedText), true);
        var removeDataArray = new Array();
        for(var i=0; i<callBackDataArray.length; i++)
        {
            //当本次点开的父节点ＩＤ等于回选数组中某个对象的父节点ＩＤ时，在下拉树上选中此子节点，并将此对象从回选数组中移除
            if(callBackDataArray[i].parentId == id)
            {
                //当树状态为级联下级时，先取消级联下级状态，再给下拉树赋值
                if(!document.getElementById(tree.treeBoxId + "IncludeLowerBox").checked)
                {
                    tree.setCheck(callBackDataArray[i].id,true);
                    removeDataArray.push(callBackDataArray[i]);
                }
                else
                {
                    tree.enableThreeStateCheckboxes(false);
                    tree.setCheck(callBackDataArray[i].id,true);
                    removeDataArray.push(callBackDataArray[i]);
                    tree.enableThreeStateCheckboxes(true);
                }
            }
        }
        //执行删除操作
        for(var i=0; i<removeDataArray.length; i++)
        {
            callBackDataArray.splice($.inArray(removeDataArray[i],callBackDataArray),1);
        }
        return true;
    });
    
    attachClickToCheck(tree);
    
    tree.attachEvent("onCheck", function(id,state){
        var ids = tree.getAllChecked();
        var allIds = tree.getAllSubItems(0);
        var checkBox = document.getElementById(this.treeBoxId + "checkAllBox");
        if(ids == allIds && checkBox)
        {
            checkBox.checked=true;
        }
        else if(checkBox)
        {
            checkBox.checked=false;
        }
    });
    
    //绑定加载完成后事件,包含下级时递归加载下级节点
    tree.attachEvent("onXLE",function()
    {
    	if(this.checkId != null &&  ($("#" + this.treeBoxId + "IncludeLowerBox").attr("checked") != null || $("#" + tree.treeBoxId + "checkAllBox").attr("checked") != null))
    	{
    		this.callEvent("onOpenEnd",[this.checkId,"1"]);//触发打开节点结束事件，更新下拉框文本值
    		if($("#" + tree.treeBoxId + "checkAllBox").attr("checked") != null)
    		{
    			this.openAllItems(0);//展开动态加载节点数据
    			tree.setSubChecked(0,"1");
    			tree._setCheck(0,0);
    		}
    		if(this.checkId != null)
    		{
    			this.openAllItems(this.checkId);//展开动态加载节点数据
    		}
    	}
    	if($("#" + tree.treeBoxId + "checkAllBox").attr("checked") != null)
    	{
    		this.callEvent("onOpenEnd",[0,"1"]);//触发打开节点结束事件，更新下拉框文本值
    	}
    });
    
    
    //给结绑定"onCheck"事件
    tree.attachEvent("onCheck", function(id,state){
    	//处理未展开时复选包含下级数据不准确的问题
	   	if( $("#" + tree.treeBoxId + "IncludeLowerBox").attr("checked") != null && state == "1")
	    {
	     	tree.openAllItems(id);//展开动态加载节点数据
	     	tree.checkId=id;//保留展开节点ID，动态加载结束后触发此节点的openend事件
	    }
        //下面代码用于实现带radioButton的时候，只能单选
        if(!this.needCheckBox)
        {
            if(typeof(this.getAllChecked()) != "number" && this.getAllChecked() != "")
            {
                var checkedIds = (this.getAllChecked()).split(",");
                for(var i=0; i<checkedIds.length; i++)
                {
                    if(removeIds[0] != checkedIds[i])
                    {
                        this.setCheck(removeIds[0],0);
                        removeIds.length =  0;
                        removeIds.push(parseInt(checkedIds[i]));
                        this.setCheck(removeIds[0],1);
                        break;
                    }
                }
            }
            else if(this.getAllChecked() != "" && removeIds[0] != this.getAllChecked())
            {
                removeIds.push(this.getAllChecked());
                if(typeof this.getAllChecked() == "number" && this.getAllChecked() != removeIds[0])
                {
                    removeIds.length = 0;
                    removeIds.push(parseInt(this.getAllChecked()));
                }
            }
        }
        
        var hideIds = "";
        var checkedText = "";
        var checkIds = ","+this.getAllChecked();
        var checkedIds = checkIds.split(",");
        var value = "";
        for(var i = 0;i < checkedIds.length; i++)
        {   
            if(("" != checkedIds[i] && -1 != checkedIds[i]) && "0" != checkedIds[i])
            {
                checkedText += this.getItemText(checkedIds[i])+",";
                hideIds += checkedIds[i] + ",";
            }
        }
        //当装有回选数据的数组不为null的时候，每次给下拉树赋值必须将回选数据数组中的值添加上
        if(null != callBackDataArray)
        {
            for(var i=0; i<callBackDataArray.length; i++)
            {
                checkedText += callBackDataArray[i].text+",";
                
                hideIds += callBackDataArray[i].id+",";
            }
        }
        checkedText = checkedText.substring(0,checkedText.length-1);
        hideIds = hideIds.substring(0,hideIds.length-1);
        //if(("" != checkedText) && (0　!=　checkedText))
        if("" != checkedText)
        {
            if(tree.readOnly)
            {
                value = hideIds;
            }
            else
            {
                value = checkedText;
            }
            comboHideIds = hideIds;
            comboCheckedText = checkedText;
        }
        else
        {
            value = "";
            checkedText = "";
            comboHideIds = "";
            comboCheckedText = "";
        }
        
        updateComboxValue(tree, value, unescape(checkedText));
        
        //添加表单验证(dhtmlxTree组件)
        if($("#"+tree.zoneId).parents("form").size() > 0)
        {
            $(tree.comboxObj.base).children("input[type='hidden']:eq(0)").valid();
        }
        //执行回调函数(目前搜索时会用到)
        if(tree.onClickHandler)
        {
            tree.onClickHandler();
        }
        
      //添加单项选择树,选中值后树消失
        if(tree.comboxObj.getComboText() != "" && !tree.needCheckBox)
        {
            tree.comboxObj.closeAll();
        }
        else
        {
            tree.comboxObj.openSelect();
        }
    });
    
    //添加失去焦点事件，处理Tab键移动时下拉框值变成文本的问题
    tree.comboxObj.attachEvent("onBlur", function()
	{
    	//可编辑时以文本为主，不做值处理
    	if(tree.readOnly==false)
    		return;
		//获取下拉框的值
		var val = tree.comboxObj.getActualValue();
		//获取树控件数据项集合
		var items = tree._idpull;
		//声明树控件数据项
		var item = null;
		//按逗号分割下拉框值，处理多选项的问题
		var varray = val.split(",");
		//保存文本转换成值后的变量
		var realVal = "";
		//遍历下拉框值分割数组
		for (var i = 0; i < varray.length; i++)
		{
			//寻找树控件中该文本对应的值
			for ( var key in items)
			{
				item = items[key];
				if (item.label == varray[i])
				{
					//拼接值
					realVal = realVal + item.id + ",";
					break;
				}
			}
		}
		//重设下拉框的值
		if (realVal != "" && realVal.substring(0, realVal.length - 1) != "0")
		{
			tree.comboxObj.setComboValue(realVal.substring(0, realVal.length - 1));
		}
	});
    
    //添加表单验证(dhtmlxCombo组件)
    tree.comboxObj.attachEvent("onChange", function() {
        //处理dhtmlxCombo存在的bug
        //alert(tree.comboxObj.getComboText());
//        if(tree.readOnly)
//        {
//          updateComboxValue(tree, comboHideIds, comboCheckedText);
//        }
//        else
//        {
//            //将下拉树选中的取消
//            //uncheckSelect(tree);
//        }
//        
//        if ($("#" + tree.zoneId).parents("form").size() > 0)
//        {
//          $(tree.comboxObj.base).children("input[type='hidden']:eq(0)").valid();
//        }
//        if(tree.onClickHandler)
//        {
//            tree.onClickHandler();
//        }
    });
    tree.comboxObj.DOMelem.tree = tree;
    /*//已经有全选功能就不要加这个了————lynn.chen
    tree.comboxObj.DOMelem.onkeyup = function() {
        //将下拉树选中的取消
        //uncheckSelect(this.tree);
    };*/
    //添加回选功能
    var backSelectData = $("#"+tree.zoneId).attr("value");
   
    //记录包含下级标志,之后还原
    var _tsCheckTemp = tree.tscheck;
    tree.enableThreeStateCheckboxes(false);
    
    //此if条件用于区别单列树与多列树不同的回选处理方式（因为树单列无动态加载）
    if("" != backSelectData && backSelectData != undefined && -1 == backSelectData.indexOf("^"))
    {
        var arrIds=backSelectData.split(",");
        var checkedText = "";
        for(var i=0;i<arrIds.length;i++)
        {
            if("" != arrIds[i])
            {
                tree.setCheck(arrIds[i],true);
                if(tree.getItemText(arrIds[i]) != "")
                {
                    checkedText += tree.getItemText(arrIds[i])+",";
                }
            }
        }
        comboHideIds = backSelectData;
        comboCheckedText = checkedText.substring(0,checkedText.length-1);
        removeIds.push(parseInt(backSelectData));
    }
    else
    {
        if("" != backSelectData && backSelectData != undefined)
        {
            var datasArray = ("," + backSelectData).split(",");
            var dataArr = new Array();
            var checkedText = "";
            var checkedIds= "";
            for(var i=0; i<datasArray.length; i++)
            {
                if("" != datasArray[i])
                {
                    dataArr = datasArray[i].split("^"); 
                    checkedIds += dataArr[0]+",";
                    tree.setCheck(dataArr[0],true);
                    checkedText += dataArr[1]+",";
                    //创建对象，存储后台传过来的数据，再将对象存入数组
                    //此处if用来过滤掉第一次加载就已显示出来的数据
                    if(tree.getItemParsingState(dataArr[0]) != 1){
                        var dataObj = 
                        {
                            "id" : dataArr[0],
                            "text":dataArr[1],
                            "parentId":dataArr[2]
                        };
                        callBackDataArray.push(dataObj);
                    }
                }
            }
            comboHideIds = checkedIds.substring(0,checkedIds.length-1);
            comboCheckedText = checkedText.substring(0,checkedText.length-1);
            removeIds.push(parseInt(checkedIds.substring(0,checkedIds.length-1)));
        }
    }
    
    updateComboxValue(tree, comboHideIds, unescape(comboCheckedText));
   
    //还原包含下级标志
    tree.enableThreeStateCheckboxes(_tsCheckTemp);
    
    //添加单列树默认情况下，点全部选项选取所有子节点
    if(tree.getItemText(-1) != 0)
    {
        $(checkBox).attr("checked","checked");
        tree.enableThreeStateCheckboxes(true);
        if(!tree.needCheckBox)
        {
            tree.showItemCheckbox(-1,0)
        }
    }
    
    //给checkbox绑定onchange事件
    var treeBoxObj = document.getElementById(tree.treeBoxId);
    //alert($(checkBox));
    
    //给包含下级绑定事件---lynn.chen
    $("#"+tree.treeBoxId + "IncludeLowerBox").bind("change", {
        treeObj : tree
    }, stateChange);
    $("#"+tree.treeBoxId + "IncludeLowerLink").bind("click", {
        checkBox : document.getElementById(tree.treeBoxId + "IncludeLowerBox")
    }, clickCheckBox);
    
    
    //给全选绑定事件---lynn.chen
    $("#"+tree.treeBoxId + "checkAllBox").bind("change", {
        treeObj : tree
    }, stateOnChange);
    
    $("#"+tree.treeBoxId + "checkAllLink").bind("click", {
        checkBox : document.getElementById(tree.treeBoxId + "checkAllBox")
    }, clickCheckBox);
    
    
    //给"确定"按钮添加样式切换效果；点确定选中内容事件
    $("#"+tree.treeBoxId + "OK").click(function(){
        tree.comboxObj.closeAll();
        if(tree.onClickOkHandler)
        {
            tree.onClickOkHandler();
        }
    });
    
    //清空所有选中的节点
    $("#"+tree.treeBoxId + "ClearAll").click(function(){
        clearAndUncheck(tree);
        if(tree.onClickClearHandler)
    	{
        	tree.onClickClearHandler();
    	}
    });
    
    $("#" + tree.treeBoxId + ".button-tree").each(function(b){
        $(this).hover(function(){
            $(this).css("background","url(public/images/btn_bg_hover.jpg) repeat scroll 0 0 transparent");
        },
        function(){
            $(this).css("background","url(public/images/btn_bg.jpg) repeat scroll 0 0 transparent");
        }); 
    });
    
    $(tree.comboxObj.base).siblings(".clearDhx_combo_input").focus(
        function()
        {
            flag = true;
        }
    );
    $(tree.comboxObj.base).siblings(".clearDhx_combo_input").blur(
        function()
        {
            flag = false;
        }
    );
}

function updateComboxValue(tree,value, text, isOpen)
{
    var oldValue = tree.comboxObj.getOptionByIndex(0).value;
    tree.comboxObj.updateOption(oldValue, unescape(value), text);
    tree.comboxObj.setComboText(unescape(text));
    tree.comboxObj.setComboValue(value);
    if(isOpen)
    {
        tree.comboxObj.openSelect();
    }
    else
    {
        tree.comboxObj.closeAll();
    }
}

function attachClickToCheck(tree)
{
	tree.attachEvent("onClick", function(id,state){
        this.setCheck(id,!this.isItemChecked(id));
        this.callEvent("onCheck",[id,state]);
    });
}

function swapDirection(leftObj,rightObj){
	if(leftObj==null)
		leftObj="left";
	if(rightObj==null)
		rightObj="right";
	if("${enableRTL!}"=="true")
		return rightObj;
	else
		return leftObj;
}

/**
 * @Description:  此方法是用JS动态创建comboxtree下拉div中所需要的document对象
 * @Author: helang  何朗  
 * @Date:   2013-04-11   
 * @param   treeBoxId   comboxTree控件div的ID
 * @param   comboxObj   dhtmlxCombox对象
 * @param   treeWidth   下拉树的宽度
 * @param   treeHeight  下拉树的高度
 * @param   isCheckDisable  是否禁用复选框
 */
function appendMyDiv(treeBoxId, comboxObj, treeWidth, treeHeight,isTree,needEnableThreeState,isCheckDisable) 
{
    
    var treeBox = document.createElement("div");
    var checkBox = $("<div class='divTopCheckbox'>&nbsp;</div>");
    if(isTree)
    {
        checkBox.append("<button class ='button' id='" + treeBoxId +  "ClearAll' style='padding: 0px 1px; margin: 1px;margin-"+swapDirection()+": 2px;'>${common_search_clear}</button>");
    }
    else if(needEnableThreeState != null && needEnableThreeState == true)
    {
        checkBox.append("<input type='checkbox' id='" + treeBoxId + "IncludeLowerBox' style='vertical-align:middle; height:20px;' "+(isCheckDisable?"":"disabled='disabled'")+" checked='checked'/>");
        checkBox.append("&nbsp;&nbsp;${common_tree_containsSubordinate}");
    }
    else if (needEnableThreeState != null && needEnableThreeState == false)
    {
    }
    else
    {
        checkBox.append("<input type='checkbox' id='" + treeBoxId + "IncludeLowerBox' style='vertical-align:middle; height:20px;'/>");
        checkBox.append("&nbsp;&nbsp;<a href='javascript:void(0)' id='" + treeBoxId + "IncludeLowerLink' style='text-decoration:none;'>${common_tree_containsSubordinate}</a>");
    }
    checkBox.append("<button class='button' id='" + treeBoxId +  "OK' style='padding: 0px 1px; margin: 1px;margin-"+swapDirection("right","left")+": 2px; float:"+swapDirection("right","left")+";'>${common_edit_ok}</button>");
    
    //checkBox.css("height","20px");
    checkBox.css("width","100%");
    //checkBox.css("overflow","hidden");
    treeBox.id = treeBoxId;
    treeBox.className = "createTreeBox";
    $(treeBox).css("width",treeWidth);
    $(treeBox).css("height",treeHeight);
    treeBox.appendChild(checkBox.get(0));
    comboxObj.DOMlist.appendChild(treeBox);
    $(comboxObj.DOMlist).css("height","auto"); 
    $(comboxObj.DOMlist).css("overflow","none");
    
};
/**
 * @Description:  给“包含下级”的checkbox绑定事件
 * @Author: helang  何朗  
 * @Date:   2013-04-11  
 * @param   Event   事件对象
 */
function stateChange(Event)
{
    var tree= Event.data.treeObj;
    if(this.checked)
    {
        tree.enableThreeStateCheckboxes(true);
    }
    else
    {
        tree.enableThreeStateCheckboxes(false);
    }
}
/**
 * @Description:  给“全选”的checkbox绑定事件
 * @Author: Carlton.wei  魏坤建  
 * @Date:   2014-01-02  
 * @param   Event   事件对象
 */

function stateOnChange(Event)
{
    var tree= Event.data.treeObj;
    if(this.checked)
    {
        comboHideIds="";
        comboCheckedText="";
        treeCheckedAll(tree,0,1);
        tree.openAllItems(0);//展开动态加载节点数据
        updateComboxValue(tree,comboHideIds.substring(0,comboHideIds.length-1),unescape(comboCheckedText.substring(0,comboCheckedText.length-1)),true);
  
    }
    else
    {
        treeCheckedAll(tree,0,0);
        updateComboxValue(tree,"","",true);
    }
}

/**
 * @Description:  给“全选”的超链接绑定事件
 * @Author: Carlton.wei  魏坤建 ，lynn.chen 
 * @Date:   2014-01-02  
 * @param   Event   事件对象
 */
function clickCheckBox(Event)
{
    var checkBox= Event.data.checkBox;
    checkBox.checked = !checkBox.checked;
    $(checkBox).change();
    //$(checkBox).click();
}

/**
 * @Description:  遍历tree的节点，勾选每个节点
 * @Author: Carlton.wei  魏坤建  
 * @Date:   2014-01-02  
 * @param   tree   dhtmlxTree对象
 * @param   id  节点的id
 * @param   state  节点状态
 */
function treeCheckedAll(tree,id,state)
{
    if(tree.hasChildren(id) > 0)
    {
    	tree.openAllItems(id);
    	tree.checkId=id;
        var chirlds = tree.getSubItems(id)+"";
        var chirldIds = chirlds.split(",");
        for(var i = 0; i < chirldIds.length; i++)
        {
            var childId = chirldIds[i];
            if(childId=="" || childId=="0")
                continue;
            comboHideIds+=childId+",";
            comboCheckedText+=tree.getItemText(childId)+",";
            treeCheckedAll(tree,childId,state);
            //tree.callEvent("onCheck",[childId,true]);
            tree.setSubChecked(childId,state);
        }
    }
    else
    {
        tree.setSubChecked(id,state);
       // tree.callEvent("onCheck",[id,true]);
    }
    tree._setCheck(0,0);
}
/**
 * @Description:  绑定window窗关闭时候事件
 * @Author: helang  何朗  
 * @Date:   2013-04-11  
 */
function destroyComboxTree()
{
    $(".dhxwin_active [objname]").each(function(){
        myTrees[this.id].comboxObj.unload();
        myTrees[this.id].destructor();
    });
        
    /*for(var i=0;i<destroyedObjArr.length;i++)
    {
        destroyedObjArr[i].closeAll();
    }*/
}
/**
 * @Description:清空下拉树的值和取消选中
 * @Author: wenxin  
 * @Modified Demon 赵崚峰 2013-08-29
 * @Date:   2013-08-24  
 */
function clearAndUncheck(tree)
{
    //treeCheckedAll(tree,0,0)
    var tempChearIds = tree.getAllChecked();
    if(tempChearIds != "")
    {
        var tempChearId = (tempChearIds.toString()).split(",");
        for (i = 0;i < tempChearId.length ;i++ )
        {
            tree.setSubChecked(tempChearId[i],false);
        }
        
        var oldValue = tree.comboxObj.getOptionByIndex(0).value;
        tree.comboxObj.updateOption(oldValue, "", "");
        tree.comboxObj.setComboValue("");
        tree.comboxObj.setComboText("");
    }
    else
    {
    	tree.comboxObj.setComboValue("");
        tree.comboxObj.setComboText("");
    }
}

/**
 * @Description:取消选中
 * @Author: wenxin  
 * @Date:   2013-08-24  
 */
function uncheckSelect(tree)
{
    var tempChearIds = tree.getAllChecked();
    if(tempChearIds != "")
    {
        var tempChearId = (tempChearIds.toString()).split(",");
        for (i = 0;i < tempChearId.length ;i++ )
        {
            tree.setSubChecked(tempChearId[i],false);
        }
    }
}
