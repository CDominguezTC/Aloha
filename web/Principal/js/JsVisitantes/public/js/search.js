/**
 * 搜索关键字提示内容，鼠标和键盘可以实现互动选取内容
 * 必须保证调用页面不能有【id=tips】【id=li_1..9】的dom组件
 * @param searchId 搜索框的id或name
 * @param url 服务器地址
 * @param paramName 发送给服务器的param参数名
 * @memberOf {TypeName} 
 */

function searchTips(searchDom,paramName)
{

	return;
	
    //选取提示的序列号
    var index = 0;
    //<li>标签的id编号
    var tipsNum = 1;
    //保存用户输入的搜索字
    var userVal = '';
    //键盘选中的<li>标签id
    var keyboardNum = 0;
    //鼠标滑过选中的<li>标签id
    var mouseNum = 0;
    //键盘事件发生标记
    var keyboardClick = false;
    if(($(searchDom).attr("name")))
    {
        searchDom = "input[name='" + $(searchDom).attr("name") + "']:first";
    }
    else if($(searchDom).attr("id"))
    {
        searchDom = '#'+$(searchDom).attr("id");
    }
    else if($("#"+searchDom).val())
    {
        searchDom = '#'+$(searchDom).attr("id");
    }
    else if($("input[name='" + searchDom + "']:first").val())
    {
        searchDom = "input[name='" + name + "']:first";
    }
    $(searchDom).bind('keyup',function(e)
    { 
        //上下左右键
        if(e.keyCode != 38 && e.keyCode != 40 && e.keyCode != 37 && e.keyCode != 39)
        {
            if(null != $('#tips').html())
            {
                $('#tips').remove();
            }
            var tipsWidth = $(this).width();
            var tipsLeft = $(this).position().left + 7 ;   //左边距离与input相同
            var tipsTop = $(this).position().top + $(this).height() + 2;        //上边距离 = input的top + input的heigth()
            var tips = '<div id="tips" style="background-color: #F3F5F0; height: auto;visibility: hidden;border: 1px solid #c3c2c2;padding: 0 3px;margin: 0px 0px; position: absolute; display: none; z-index:99999; overflow-x: auto;" ><ul style="margin: 0px; padding: 0px; overflow-x: auto; "></ul></div>';

            $(this.parentNode).append(tips);

            $('#tips').css({"top": tipsTop + "px", "left": tipsLeft + "px", "width": tipsWidth+ "px"});

            var value = $(searchDom).val();
            if("" != value && value.length > 0)
            {
                userVal = $(searchDom).val();
                var path = 'searchAction!getSearchTips.action?' + paramName + '=' + value;
                $.ajax({
                    url: path,
                    type: 'get',
                    dataType : "json", 
                    async :true,
                    success: function (data) 
                    {
                    	if(data && data.length >= 0)
                    	{
                    		$('#tips').show();
                    	}
                    	else
                    	{
                    		return;
                    	}
                    	
                        var ul = $('#tips ul');
                        $('#tips').css('visibility','visible');
                        $('#tips ul li').remove();
                        if(data.length == 0)
                        {
                            $('#tips').css('visibility','hidden');
                        }
                        else if(data.length > 0)
                        {
                            for(var i = 0; i < data.length; i++)
                            {
                                if(i == 0) 
                                {
                                    tipsNum = 1;  //重置序列号
                                }
                                else
                                {
                                    tipsNum += 1;
                                }
                                ul.append("<li id='li_"+ tipsNum +"' style='list-style-type:none; margin-bottom:1px; padding:0px; text-align: left;'>" + data[i].tips +"</li>");
                            }
                        }
                        //单击鼠标选择提示内容
                        $('#tips ul li').bind('mousedown',function(){
                            //取得当前单击的<li>
                            var name = $(this).text();
                            var input = $(searchDom);
                            input.val('');
                            input.val(name);
                            //所有标记位清零
                            keyboardNum = 0;
                            mouseNum = 0;
                            index = 0;
                            $('#tips ul li').remove();
                            $('#tips').css('visibility','hidden');
                        });
                        //如果键盘事件响应了，则关闭鼠标滑过事件
                        if(keyboardClick == false)
                        {
                            $('#tips ul li').bind('mouseover',function(){
                                $('#li_'+keyboardNum).css('background','');
                                $(this).css('background','#C0C0C0').css('cursor','pointer');
                                for(var i = 1; i < tipsNum; i++)
                                {
                                    if($(this).text() == $('#li_'+i).text())
                                    {
                                        mouseNum = i;  //修改标记位
                                        index = i;  //键盘的位置也跟着改变
                                    }
                                }
                            });
                        }
                        $('#tips ul li').bind('mouseout',function(){

                            $(this).css('background','');

                        })
                    }   
                });
            }
            else
            {
                //隐藏提示
                $('#tips ul li').remove();
                $('#tips').css('visibility','hidden');
            }
        }
        //当前焦点在输入框时
        if($('#tips ul li').length > 0)
        {
            if(e.keyCode == 38 || e.keyCode == 40)
            {
                //关闭鼠标事件
                keyboardClick = true;
                //清除鼠标滑过选择的标记位样式
                $('#tips ul li').css('background','');
                switch(e.keyCode)
                {
                    case 38:
                        if(index == 0)
                        {
                            index = tipsNum;
                            //记录键盘选择的标记位
                            keyboardNum = tipsNum;
                            $(searchDom).val($('#li_'+index).css('background','#C0C0C0').text());
                        }
                        else if(index == 1)

                        {  
                            $('#li_'+index).css('background','');

                            index = 0;

                            keyboardNum = 0;

                            $(searchDom).val(userVal); //内容回滚

                        }
                        else if(0 < index <= tipsNum)
                        {

                            $('#li_'+index).css('background','');

                            index --;

                            keyboardNum = index;

                            $(searchDom).val($('#li_'+index).css('background','#C0C0C0').text());

                        }
                        break;
                    case 40:
                        if(index == tipsNum)
                        {  
                            //回到输入框
                            $('#li_'+index).css('background','');
                            index = 0;
                            keyboardNum = 0;
                            $(searchDom).val(userVal);
                        }
                        else if(index == 0)
                        {
                            index ++;
                            keyboardNum = index;
                            $(searchDom).val($('#li_'+index).css('background','#C0C0C0').text());
                        }
                        else if( 0 < index <= tipsNum)
                        {
                            $('#li_'+index).css('background','');
                            index ++;
                            keyboardNum = index;
                            $(searchDom).val($('#li_'+index).css('background','#C0C0C0').text());
                        }
                        break;

                }
                keyboardClick = false;  //控制权交给鼠标
            }
        }

    });

}

/**
 * @Description:根据配置文件初始化搜索
 * @Author: helang  何朗 
 * @Modified by wenxin
 * @Date: 2013-04-08
 * @param: searchXMLPath 后台解析读取配置文件的路径
 * @param: gridName 当前操作的grid名称
 * @Remark：colin 2014-4-21 15:40:16 修改内容：添加验证表单功能
 */
function initSearchConfig(searchXMLPath, gridName)
{
    $.ajax({   
        url:searchXMLPath,   
        type: 'GET',   
        dataType: 'xml',   
        async : false, 
        success: function(xml){
            var type="";
            var name="";
            var id="";
            var label="";
            var value="";
            var lazyLoad = false;   //下拉树加载的方式，默认为恶汉式加载
            var searchUrlParam = ""; //搜索提示的参数名（表名^字段）
            var hiddenInZh="";//在中文下隐藏
            var maxlength="";//输入框最大长度
            var itemsType="";//搜索的items的type值
            var belowSearchStyle="";//搜索中"更多"时，下面的搜索排列样式.table：按表格排列；没有则是默认排列
            var globalWidth="";
            var inputWidth="";
            var topSearchHtml="";
            var belowSearchHtml="";
            var html="";
            var itemsLength = $(xml).find("items").size();
            var isTableLayout = false;
            var onlyNum = false;//只能输入数字
//          var onlyNumOrLetters = false;//只能输入数字或者字母
            //这里的循环是先创建html对象
            $(xml).find("items").each(function(i){ 
                if($(this).attr("type"))
                {
                    itemsType = $(this).attr("type");
                }
                if($(this).attr("belowSearchStyle"))
                {
                    belowSearchStyle = $(this).attr("belowSearchStyle");
                }
                if(itemsType == "topSearch")
                {
                    html += "<table cellpadding='0' cellspacing='0' class='searchTable'>";
                }
                else
                {
                    if(isTableLayout)
                    {
                        if(i == 1)
                        {
                            html += "<table cellpadding='0' cellspacing='0' class='searchTable2'>";
                        }
                    }
                    else
                    {
                        html += "<table cellpadding='0' cellspacing='0' class='searchTable'>";
                    }
                }
                
                html += "<tr>";
                $(this).find("item").each(function(i){
                    var cutZeroFront = false;
                    if($(this).attr("type"))
                    {
                        type = $(this).attr("type");
                    }
                    if($(this).attr("name"))
                    {
                        name = $(this).attr("name");
                    }
                    if($(this).attr("label"))
                    {
                        label = $(this).attr("label");
                    }
                    if($(this).attr("value"))
                    {
                        value = $(this).attr("value");
                    }
                    if($(this).attr("id"))
                    {
                        id = $(this).attr("id");
                    }
                    else
                    {
                        id = name;
                    }
                    if($(this).attr("hiddenInZh"))
                    {
                        hiddenInZh = $(this).attr("hiddenInZh");
                    }
                    if($(this).attr("maxlength"))
                    {
                        maxlength = $(this).attr("maxlength");
                    }
                    if($(this).attr("cutZeroFront"))
                    {
                        cutZeroFront = $(this).attr("cutZeroFront");
                    }
                    if($(this).attr("searchUrlParam"))
                    {
                        searchUrlParam = $(this).attr("searchUrlParam");
                    }
                    if($(this).attr("lazyLoad"))
                    {
                        lazyLoad = $(this).attr("lazyLoad");
                    }
                    if($(this).attr("onlyNum"))
                    {
                        onlyNum = $(this).attr("onlyNum");
                    }
//                  if($(this).attr("onlyNumOrLetters"))
//                  {
//                      onlyNumOrLetters = $(this).attr("onlyNumOrLetters");
//                  }
                    if(type=="settings")
                    {
                        globalWidth = $(this).attr("inputWidth");
                    }
                    else
                    {
                        if($(this).attr("inputWidth"))//如果该对象没有自己配置了宽度，那么就使用全局属性
                        {
                            inputWidth = $(this).attr("inputWidth");
                        }
                        else
                        {
                            inputWidth = globalWidth;
                        }
                    }
                    
                    if(type == "input")
                    {
                        //点击更多时，下面搜索需要按table排列
                        if(isTableLayout)
                        {
                            html += "<th><label>" + label + "</label></th>";
                            html += "<td valign='middle'>";
                            /*if(hiddenInZh == "true" && getCookie("language") == "zh_CN")
                            {
                                html+="<td valign='middle' style='display:none;'>";
                            }
                            else
                            {
                                html+="<td valign='middle'>";
                            }*/
                        }
                        //默认排列
                        else
                        {
                            html += "<td valign='middle'>";
                            html += label;
                        }
                        var style = " style='width:" + inputWidth;
                        if("${enableRTL!}"=='true')
                        	style+="px;margin-right:7px;";
                        else
                        	style+="px;margin-left:7px;";
                        html+="<input name='" + name + "' id='" + id + "' type='text'";
                        if(maxlength != "")
                        {
                            html += " maxlength='" + maxlength + "'";
                        }
                        if(cutZeroFront)
                        {
                            html += " cutZeroFront='" + cutZeroFront + "'";
                        }
                        if(searchUrlParam)
                        {
                            html += " searchUrlParam='" + searchUrlParam + "'";
                        }
                        if(onlyNum)
                        {
                            html += " onkeypress = 'return isNumber(event);'";
                            style += "ime-mode:disabled;"
                        }
//                      else if(onlyNumOrLetters)
//                      {
//                          html += " onkeypress = 'return isString(event);'";
//                          style += "ime-mode:disabled;"
//                      }
                        html += style + "'";
                        html += " title='" + label + "'/>";
                        html += "</td>";
                    }
                    else if(type == "hidden")
                    {
                        html += "<td valign='middle' style='display:none;'>"
                        html += "<input type='hidden' isInit='true' name='" + name + "' id='" + id + "' value='" + value + "'";
                        html += " /></td>"
                    }
                    else if(type == "calendar")
                    {
                        //点击更多时，下面搜索需要按table排列
                        if(isTableLayout)
                        {
                            html += "<th><label>" + label + "</label></th>";
                            html += "<td valign='middle'>";
                            html += "<input name='" + name + "' id='" + id + "' type='text' style='width:" + inputWidth + "px' title='" + label + "'";
                            if(maxlength != "")
                            {
                                html += " maxlength='" + maxlength + "'";
                            }
                            html += " readonly/>";
                        }
                        //默认排列
                        else
                        {
                            html += "<td valign='middle'>";
                            html += "<nobr>" + label + "<input name='" + name + "' id='" + id + "' type='text' style='width:" + inputWidth;
                            if("${enableRTL!}"=='true')
                            	html += "px; margin-right:7px;' title='"; 
                            else
                            	html += "px; margin-left:7px;' title='"; 
                            html +=  label + "'";
                            if(maxlength != "")
                            {
                                html += " maxlength='" + maxlength + "'";
                            }
                            html += " readonly/></nobr>";
                        }
                        html += "</td>"
                    }
                    else if(type == "date")
                    {
                        var limit = $(this).attr("limit"); //max\min
                        var clickId = $(this).attr("clickId"); //endTime\startTime 反向取值
                        html += "<td><label>" + label + "</label></td>";
                        html += "<th valign='middle'>";
                        html += "<input name='" + name + "' id='" + id + "' type='text' style='width:" + inputWidth + "px;' onclick=setSens('" + clickId + "','" + limit + "','" + id+"') title='" + label + "'" + "value='" + value + "'";
                        if(maxlength != "")
                        {
                            html += " maxlength='" + maxlength + "'";
                        }
                        html += " readonly/></th>"
                    }
                    else if(type == "comboTree" || type == "combo")
                    {
                        //点击更多时，下面搜索需要按table排列
                        if(isTableLayout)
                        {
                            html += "<th><label>" + label + "</label></th>";
                            html += "<td valign='middle'>";
                            if("${enableRTL!}"=='true')
                            	html += "<div style='float: right;padding-top: 5px;padding-left:7px;'>";
                            else
                            	html += "<div style='float: left;padding-top: 5px;padding-right:7px;'>";
                        }
                        //默认排列
                        else
                        {
                            html += "<td valign='middle'>";
                            if("${enableRTL!}"=='true')
                            	html += "<div style='float: right;padding-top: 5px;padding-left:7px;'>";
                            else
                            	html += "<div style='float: left;padding-top: 5px;padding-right:7px;'>";
                            html += label;
                        }
                        html += "</div><div id='" + id + "' objName='" + name + "' selectWidth='" + inputWidth + 
                            "' type='radio' value='' title='" + label+"'";
                        if($(this).attr("readonly")==null || $(this).attr("readonly")!="true")
                        {
                        	html +=" readOnly='false' ";
                        }
                        if("${enableRTL!}"=='true')
                        	 html +="  style='float: right;width:";
                        else
                        	 html +="  style='float: left;width:";
                        html += inputWidth + "px' " + "lazyLoad='"+lazyLoad+"'></div>";
                        html += "</td>"
                    }
                    else if(type == "label")
                    {
                        html += "<td valign='middle'>"
                        html += label;
                        html += "</td>"
                    }
                });
                html += "</tr>";
                if(itemsType == "topSearch")
                {
                    html += "</table>";
                }
                else
                {
                    if(isTableLayout)
                    {
                        if((itemsLength-1) == i)
                        {
                            html += "</table>";
                        }
                    }
                    else
                    {
                        html += "</table>";
                    }
                }
                if(itemsType == "topSearch" && belowSearchStyle == "table")
                {
                    isTableLayout = true;
                }
                if($(this).attr("type")=="topSearch")
                {
                    topSearchHtml = html;
                }
                else
                {
                    belowSearchHtml += html;
                }
                html = "";
            });
            $("#topSearchBox"+gridName).html(topSearchHtml);
            if(belowSearchHtml == "")
            {
                $("#moreConditionButton"+gridName).hide();
                
            }
            if(topSearchHtml!="")//如果有值就显示，没值直接移除该div
            {
                $("#searchBox"+gridName).show();
            }
            else
            {
                $("#searchBox"+gridName).remove();//如果配置文件中找不到搜索配置，则直接移除搜索模块
            }
            $("#belowSearchBox" + gridName).html(belowSearchHtml);
            //这里的循环是给对象赋值dhtmlx的控件，如：时间，下拉框
            loadSearchControl(xml,gridName);
            //绑定全局的keyup事件
            /*
            document.onkeyup = function(e) 
            {
                enterOnKeyUp(e);
            }*/
        }   
    });  
}

/**
 * @Description:加载搜索初始化数据
 * @Author: helang  何朗 
 * @Modified by wenxin
 * @Date: 2013-04-08
 * @param: xml 配置文件对象
 * @param: gridName 当前操作的grid名称
 */
var combos = new Array();//下拉树框控件存放数组
var timeScope = "${application['date.timeScope']!3}";
timeScope = eval(timeScope < 0 ?  -timeScope : timeScope);
function loadSearchData(xml, gridName)
{
	var myCalendars = new Array(); //日期控件存放数组
	limitCalendars=new Array();
    var count=1;
    var globalWidth="";
    $(xml).find("items").each(function(i){ 
    	var type="";//该搜索对象类型
	    var name="";//该搜索对象名称
	    var id="";//该搜索对象id
	    var searchUrlParam = "";
	    /**-------------add by wenxin start--------------*/
	    var comboWidth = "";//该搜索对象comboWidth
	    var comboHeight = "";//该搜索对象comboHeight
	    var label = "";//该搜索对象label
	    var maxlength = "";//该搜索对象maxlength
	    /**-------------add by wenxin end----------------*/
	    var inputWidth="";
	    
        $(this).find("item").each(function(i){ 
            
            //读取xml文件，设置属性
            if($(this).attr("type"))
            {
                type = $(this).attr("type");
            }
            if($(this).attr("name"))
            {
                name = $(this).attr("name");
            }
            if($(this).attr("id"))
            {
                id = $(this).attr("id");
            }
            else
            {
                id = name;
            }
            if($(this).attr("comboWidth"))
            {
                comboWidth = $(this).attr("comboWidth");
            }
            if($(this).attr("comboHeight"))
            {
                comboHeight = $(this).attr("comboHeight");
            }
            if($(this).attr("label"))
            {
                label = $(this).attr("label");
            }
            if($(this).attr("maxlength"))
            {
                maxlength = $(this).attr("maxlength");
            }
            if($(this).attr("searchUrlParam"))
            {
                searchUrlParam = $(this).attr("searchUrlParam");
            }
            //判断组件类型
            if(type=="settings")
            {
                globalWidth = $(this).attr("inputWidth");
            }
            else
            {
                if($(this).attr("inputWidth"))//如果该对象没有自己配置了宽度，那么就使用全局属性
                {
                    inputWidth = $(this).attr("inputWidth");
                }
                else
                {
                    inputWidth = globalWidth;
                }
            }
            if(type == "calendar")
            {
                if($(this).attr("dateFormat"))
                {
                    if($(this).attr("dateFormat") == "dhtmlxShortDate")
                    {
                        myCalendars[id] = createShortDhxCalendar(id);
                    }
                    else if($(this).attr("dateFormat") == "dhtmlxLongDate")
                    {
                        myCalendars[id] = createLongDhxCalendar(id);
                    }
                }
                /*给所有时间控件注册onClick事件来加载搜索区域所搜文本内容*/
                myCalendars[id].attachEvent("onClick", function(){
                    loadSearchText(gridName);
                });
            }
            else if(type == "date")
            {
                //人为设定时分秒，主要是解决工具栏刷新时数据的实时性
                var nowTime = new Date();
                nowTime.setHours(23);
                nowTime.setMinutes(59);
                nowTime.setSeconds(59);
                var endTime = nowTime.format(sysCfg.sysLongDateFmt);
                var beginTime = new Date(nowTime.setMonth((nowTime.getMonth() - timeScope)));
                beginTime.setHours(00);
                beginTime.setMinutes(00);
                beginTime.setSeconds(00);
                beginTime = beginTime.format(sysCfg.sysLongDateFmt);
                
                //获取开始时间和结束时间id
                var beginTimeId="beginTime";
                var endTimeId="endTime";
                var limitVal=$(this).attr("limit");
                if(limitVal=="max")
                {
                	beginTimeId=$(this).attr("id");
                	endTimeId=$(this).attr("clickId");
                }
                else if(limitVal=="min")
                {
                	beginTimeId=$(this).attr("clickId");
                	endTimeId=$(this).attr("id");
                }
                
                
                if(limitCalendars[beginTimeId]==null)
                {
					if ($(this).attr("dateFormat"))
					{
						if ($(this).attr("dateFormat") == "dhtmlxShortDate")
						{
							limitCalendars[beginTimeId] = createShortDhxCalendar([beginTimeId, endTimeId]);
						}
						else if ($(this).attr("dateFormat") == "dhtmlxLongDate")
						{
							limitCalendars[beginTimeId] = createLongDhxCalendar([beginTimeId, endTimeId]);
						}
					}
					else
					{
						limitCalendars[beginTimeId] = createLongDhxCalendar([beginTimeId, endTimeId]);
					}
                	
                    /*----add by 徐秀滨 以下代码为了使日期控件日期选中后会在当前查询条件显示*/
					limitCalendars[beginTimeId].attachEvent("onClick", function(){
                        loadSearchText(gridName);
                    });
                    $("#"+beginTimeId).attr("timeScope", $(this).attr("timeScope"));
                    $("#"+endTimeId).attr("timeScope", $(this).attr("timeScope"));
                    
                }
                
                if($("#"+beginTimeId).val() == '{defTime}' && $("#"+endTimeId).val() == '{defTime}')
                {
                    //赋予三个月范围的默认值
                    var value = $("#"+beginTimeId).val(beginTime);
                    var valu = $("#"+endTimeId).val(endTime);
                    //保存起来，用来clear重新绑定时间
                    $("#"+beginTimeId).attr("defaultVal", beginTime);
                    $("#"+endTimeId).attr("defaultVal", endTime);
                    
                    //添加默认的搜索条件提示
                    loadSearchText(gridName);
                    //时间范围提示
                    $("#"+beginTimeId+","+"#"+endTimeId).bind('click',function(){
                        $(this).css('border-color','');                 
                    })
                }
                else
                {
                    var value = $("#"+beginTimeId).val();
                    var valu = $("#"+endTimeId).val();
                }
                count ++;
            }
            else if(type == "comboTree")
            {
                if($(this).attr("path"))//必须存在path
                {
                    var idtemp=id;
                    var onClickHandler = function(){
                        loadSearchText(this.gridName);
                        //解决选中后，搜索内容没有实时变化的问题
                        $("#"+idtemp).find("input").keyup();
                    }
                    var path = $(this).attr("path");
                    //根据节点id，来判断在path后面添加参数
                    path = addParamByMenuId(path);
                    //加载下拉树
                    var comboxTree = loadComboxTree(id, path, onClickHandler, null, null, comboWidth, comboHeight);
                    comboxTree.comboxObj.gridName = gridName;
                    comboxTree.enableTreeImages(false);
                    /**--------add by wenxin start---------*/
                    //此处给combox的input添加title属性，因为enter键查询时，会根据title进行判断
                    comboxTree.comboxObj.DOMelem.getElementsByTagName("input")[0].title = label;
                    if(maxlength != "")
                    {
                        //此处给combox的input添加maxlength属性
                        comboxTree.comboxObj.DOMelem.getElementsByTagName("input")[0].setAttribute("maxlength", maxlength);
                    }
                    
                  //清空条件时联动用 --- lynn.chen 2014-05-16
                    $(comboxTree.comboxObj.base.childNodes[1]).attr("comboId", id);
                    $(comboxTree.comboxObj.base.childNodes[1]).change(function(){
                    	var treeId = $(this).attr("comboId");
                    	uncheckSelect(myTrees[treeId]);
                    	var oldValue = myTrees[treeId].comboxObj.getOptionByIndex(0).value;
                    	myTrees[treeId].comboxObj.updateOption(oldValue, "", "");
                    });
                    
                    /**--------add by wenxin end-----------*/
                    comboxTree.comboxObj.attachEvent("onKeyPressed", function(keyCode){
                    	
                    	enterSearch(keyCode, this.gridName);
                    });
                    //绑定回车搜索事件
                    //enterSearch(comboxTree.comboxObj.DOMelem, gridName);
                }
            }
            else if(type == "combo")
            {
                combos[id] = new dhtmlXCombo(id, name, inputWidth);
                //combos[id].enableOptionAutoHeight(true,300);
                //combos[id].enableOptionAutoWidth(true);
                combos[id].readonly(true);
                combos[id].id = id;
                if($(this).attr("path"))
                {
                    combos[id].path = $(this).attr("path");
                    combos[id].required = ($(this).attr("required") || $(this).attr("required") == "true");
                    combos[id].loadData = function(path){
                        var id = this.id;
                        if(!this.required)
                        {
                            var text = "---------";
                            combos[id].addOption([["", text, "selected:true"]]);//添加默认值
                            combos[id].selectOption(0);//默认值
                        }
                        $.ajax({
                            type: "GET",
                            async: false,
                            url: path,
                            success: function(result)
                            {
                                combos[id].addOption(eval(result));//添加下拉对象
                            }
                        });
                    };
                    combos[id].loadData(combos[id].path);
                    if($(this).attr("childCombo"))
                    {
                        //递归获取当前Combo的所有父Combo的条件参数
                        function getParentPathParm(comboObj)
                        {
                            if(comboObj.parentId && typeof(comboObj.parentId) != "undefined")
                            {
                                return "&" + comboObj.parentId + "=" + combos[comboObj.parentId].getActualValue() + getParentPathParm(combos[comboObj.parentId]);
                            }
                            else
                            {
                                return "";
                            }
                        }
                        combos[id].childComboId = $(this).attr("childCombo");
                        
                        //清空条件时联动用 --- lynn.chen 2014-05-16
                        $(combos[id].base.childNodes[1]).attr("comboId", id);
                        $(combos[id].base.childNodes[1]).change(function(){
                            combos[$(this).attr("comboId")].selectOption(0);
                        });
                        combos[id].attachEvent("onChange", function(){
                            combos[this.childComboId].clearAll(true);
                            
                            combos[this.childComboId].parentId = this.id;//赋值父id
                            var path = combos[this.childComboId].path;
                            path += path.indexOf("?") == -1 ? "?" : "&";
                            path += this.id + "=" + this.getActualValue();
                            
                            path += getParentPathParm(this);
                            combos[this.childComboId].loadData(path);
                        });
                    }
                }
                else
                {
                    var options = new Array();
                    var option;
                    var selOptionNum = null;//默认选中的编号值
                    $(this).find("option").each(function(i){
                        option = new Array();
                        option.push($(this).attr("value"));
                        option.push($(this).attr("text"));
                        if($(this).attr("selected") == "true")
                        {
                            selOptionNum = i;
                        }
                        options.push(option);
                    });
                    combos[id].addOption(options);//添加下拉对象
                    //如果有默认值且大于0，则设置默认值--add by wenxin
                    if(selOptionNum != null && selOptionNum >= 0)
                    {
                        combos[id].selectOption(selOptionNum);//默认值
                    }
                    /*else
                    {
                        combos[id].selectOption(0);//默认值第一个
                    }*/
                }
                /*给所有下拉框注册onChange事件来加载搜索区域所搜文本内容*/
                combos[id].gridName = gridName;
                
                $(combos[id].base.childNodes[1]).attr("comboId", id);
                $(combos[id].base.childNodes[1]).change(function(){
                    combos[$(this).attr("comboId")].selectOption(0);
                });
                
                combos[id].attachEvent("onChange", function(){
                    loadSearchText(this.gridName);
                });
                //绑定回车搜索事件
                combos[id].attachEvent("onKeyPressed", function(keyCode){
                	enterSearch(keyCode, this.gridName);
                });
                //enterSearch(combos[id].DOMelem, gridName);
            }
            else if(type = "input")
            {
                if("" != $(this).attr("searchUrlParam") && undefined != $(this).attr("searchUrlParam"))
                {
                    searchTips(this, $(this).attr("searchUrlParam"));
                }
            }
        });
    });

    /*给#searchBox"+gridName下的所有文本框注册keyup事件来加载搜索区域所搜文本内容*/
    $("#searchBox"+gridName).find("input").bind("keyup", function(){
        if(this.getAttribute("cutZeroFront") == "false")
        {
            if($.trim(this.value) != "")
            {
                this.value = this.value.replace(/[^\d+\.+\/]/g,'');
                if (this.value!='')
                {
                    $(this).val(parseInt( this.value));
                }
            }
        }
        loadSearchText(gridName);
    }).bind("blur", function(event, ui) {
        loadSearchText(gridName);
    });
    
    //给input注册回车搜索事件
    
    $("#searchBox"+gridName).find("input").bind("keypress", function(e){
    	if(e.keyCode == 13) 
		{
    		enterSearch(e.keyCode, gridName);
    		
			return false;
		}
    });
}

function enterSearch(keyCode, gridName)
{
	if(keyCode == 13 )//回车键
	{
		$("#searchButton" + gridName).click();
	}
}

var myEvent;

var myEvents=[];

/**
 * 处理日期组件的时间范围
 * @param clickId
 * @param k
 * @param id
 */
function setSens(clickId, k, id)  
{
	var date = $("#"+clickId).val();
	var limitCalendar=limitCalendars[k=="min"?clickId:id];
 	if(date != "")
 	{
 		var year = date.substring(0,4);
    	var mouth = date.substring(5,7);
	    var day = date.substring(8);
	    if (k == "min")
	    {
	    	var endDate = new Date;
	    	//alert($("#" + id).attr("timeScope") == 0);
	    	if($("#" + id).attr("timeScope") == 0)
	    	{
	    		endDate = null;
	    	}
	    	else
	    	{
	    		if(myEvent != undefined)
		        {
	    			limitCalendar.detachEvent(myEvent);
		        }
		        myEvents[clickId] = limitCalendar.attachEvent("onClick", function(endDate){
		        	var tempEndDate = endDate;
		        	tempEndDate.setSeconds(59);
		        	
		        	$('#' + id).val(tempEndDate.format(sysCfg.sysLongDateFmt));
		        	//年换算成月份
		        	startMouth = eval(year * 12) + eval(mouth);
		        	endMouth = eval(endDate.getFullYear()*12) + eval(endDate.getMonth()+1);
		        	mouthMinus = Math.abs(endMouth - startMouth); //分月差
		        	//大于指定的时间范围才联动变化
		        	if(mouthMinus > timeScope)
		        	{
		        		endDate.setMonth(endDate.getMonth() - timeScope);
		        		$('#' + clickId).val(endDate.format(sysCfg.sysLongDateFmt));
		        	}
				});
		        myEvent= myEvents[clickId];
	    	}
	    	limitCalendar.setSensitiveRange(date, endDate); //结束日期不能大于当前日期new Date
	        
	    } 
	    else
	    {
	    	if($("#" + id).attr("timeScope") != 0)
	    	{
	    		if(myEvent != undefined)
		        {
	    			limitCalendar.detachEvent(myEvent);
		        }
	    		myEvents[id] = limitCalendar.attachEvent("onClick", function(startDate){
	    			startMouth = eval(startDate.getFullYear()*12) + eval(startDate.getMonth()+1);
		        	endMouth = eval(year * 12) + eval(mouth);
		        	mouthMinus = Math.abs(endMouth - startMouth); 
		        	if(mouthMinus > timeScope)
		        	{
		        		startDate.setMonth(startDate.getMonth() + timeScope);
		        		startDate.setHours(23);
		        		startDate.setMinutes(59);
		        		startDate.setSeconds(59);
		        		//结束时间联动变化不能超过单前的时间 -- 保留这部分代码，放开后不会影响功能
		        		/*endTimeStamp = startDate.getTime();
		        		nowTimeStamp = new Date().getTime();

		        		if(endTimeStamp > nowTimeStamp)
		        		{
		        			$('#' + clickId).val(nowTimeStamp.format('yyyy-MM-dd HH:mm:ss')); //最大为当前时间
		        		}
		        		else
		        		{*/
		        			$('#' + clickId).val(startDate.format(sysCfg.sysLongDateFmt));
//		        		}
		        	}
				});
	    		myEvent= myEvents[id];
	    	}
	    	
	    	limitCalendar.setSensitiveRange(null, date); //开始日期不能大于结束日期
	    }
 	}
 	else
 	{
 		if($("#" + id).attr("timeScope") != 0)
 			limitCalendar.setSensitiveRange(null, new Date()); //开始日期不能大于结束日期
 		else
 			limitCalendar.clearSensitiveRange();
 	}
}

/**
 * @Description: 加载搜索区域所搜文本内容
 * @Author: lynn.chen  陈立  
 * @Modified By:
 * @Date: 2013-03-09
 * @param: gridName 当前操作的grid名称
 */ 
function loadSearchText(gridName)
{
    $("#searchText"+gridName).html("");
    $("#searchBox"+gridName).find(":text").each(function () {
        
        if($(this).val() != "")
        {
            var value = $(this).val();
            value = value.length > 20 ? value.substring(0,20) + "..." : value;
            $("#searchTextDiv"+gridName).show();
            var label = "";
            //如果当前下拉框控件选择的值为""，则直接忽略
            if($(this).attr("class") && $(this).attr("class").indexOf("dhxcombo_input") >= 0 && $(this).next().val() == "")
            {
                return true;
            }
            if($(this).attr("class") && $(this).attr("class").indexOf("dhxcombo_input") >= 0)
            {
                label= ($(this).parent("div").parent("div").attr("title"));
            }
            else
            {
                label = $(this).attr("title");
            }
            //label = label.trim();//ie不支持该方法
            $("#searchText"+gridName).append(label+":("+value+")&nbsp;&nbsp;");
            var title = $("#searchText"+gridName).html().replace(/&nbsp;/g,"");
            $("#searchText"+gridName).attr("title", title);
        }
    });
    if($("#searchText"+gridName).html() == "")
    {
        $("#searchText"+gridName).html($("#noSearchText"+gridName).html());
        //$("#searchTextDiv"+gridName).hide();
    }
}

/**
 * @Description:搜索框回车确定功能：递归查找搜索按扭方法
 * @Author: helang  何朗  
 * @Date: 2013-04-08
 * @param: fatherObj 递归遍历的父结点对象
 * @param: focusObj  当前页面焦点所在的结点对象
 */
function serchButtonObj(fatherObj,focusObj)
{
    var children = fatherObj.children();
    for(var i=0;i<children.length;i++)
    {
        if($(children[i]).children(".button").length != 0)
        {
            btn = ($(children[i]).children(".button"))[0];
        }
        else
        {
            arguments.callee($(children[i]),focusObj);
        }
    }   
}

/**
 * @Description:搜索框回车确定功能：递归判断当前焦点是否在搜索框控件上
 * @Author: helang  何朗  
 * @Date: 2013-04-08
 * @param: fatherObj 递归遍历的父结点对象
 * @param: focusObj  当前页面焦点所在的结点对象
 */
function serchFoucusObj(fatherObj,focusObj)
{
    var children = fatherObj.children();
    for(var i=0;i<children.length;i++)
    {
        if($.trim($(focusObj).attr("title")) == $.trim($(children[i]).attr("title"))&&($.trim($(children[i]).attr("title"))))
        {
            flag = true;
        }
        else
        {
            arguments.callee($(children[i]),focusObj);
        }
    }
}