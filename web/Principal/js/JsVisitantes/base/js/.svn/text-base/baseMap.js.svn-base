/*
File name: electro_map.js
Description: electro-map of ZKAccess5.0 Security System
Author: Darcy
Create date: 2010.10.26
Company: ZKSoftware
*/

    //对门循环处理top和left时的公共代码
    var new_door_top = 0;
    var new_door_left = 0;
    var new_door_width = 0;
    var new_door_width_ = 0
    function loop_doors($img, scale)
    {
        var door_width = parseFloat($img.parent().parent().css("width").replace("px", ""), 10);//parseFloat否则会出现16.5/2=8
        new_door_width = parseFloat(door_width) * scale;//门宽度按比例变化
        var door_width_ = parseFloat($img.css("width").replace("px", ""), 10);//parseFloat否则会出现16.5/2=8
        new_door_width_ = parseFloat(door_width_) * scale;
        var door_top = parseFloat($img.parent().parent().css("top").replace("px", ""), 10);//门的上边距
        var door_left = parseFloat($img.parent().parent().css("left").replace("px", ""), 10);//当前门的左边距
        //alert('door_left='+door_left+'door_top='+door_top+' scale='+scale+"  door_width="+door_width)
        //中点(door_top+door_width/2, door_left + door_width/2)按比例变化即可
        //alert(new_door_width)
        new_door_top = (door_top + door_width/2) * scale - new_door_width/2;
        new_door_left = (door_left + door_width/2) * scale - new_door_width/2;
    }

    //实现地图的放大缩小--start
    var multiple = 0;  //当前倍数，只能在-10至10这个倍数范围缩放

    function zoom_img($img, scale, left, top)
    {
        var img_width = $img.width();//当前地图宽度。不带px
        var img_height = $img.height();
        var img_top = $img.offset().top;
        var img_left = $img.offset().left;
        //alert('top--='+img_top+" left="+img_left);
        var box_width = scale * img_width;//放大或缩小后的地图宽度
        var box_height = scale * img_height;
        var valid = true;//标记能够继续缩小
        //比较imgBox的长宽比与img的长宽比大小
        
        if(scale > 1 )
         {
        	if(multiple >= 10)
        	{
        		openMessage(msgType.info, "${base_map_zoomInError}");//地图宽度到达上限(倍数超过10倍)，不能再放大！
                return false;
        	}
        	else
        	{
        		multiple ++; //放大	
        	}
         }
        if(scale < 1 )
        {
        	    if(multiple <= -10)
        		{
        	    	openMessage(msgType.info, "${base_map_zoomOutError1}");//地图宽度到达上限(倍数超过10倍)，不能再缩小！
                    return false;
        		}
        	    else
        	    {
        	    	 multiple --;  //缩小
        	    }
        }

        //门图标联动--坐标和大小
        var $zoom_imgs = $img.parent().find(".can_drag");//取到当前地图上的门


        //要先检测所有的门的坐标是否有效，只要有一个无效，将无法继续，直接返回，不做缩小操作（扩大不受影响）
        $zoom_imgs.each(function(){
        	loop_doors($(this), scale);

        	if(scale < 1)
            {
                if(multiple <= -10)
                {
                    valid = false;
                    //return false;
                }
            }

            if(!valid)
            {
                return false;//中断each
            }
        });

        if(!valid)
        {
            //门图标的位置（Top或Left）到达下限，请稍作调整后再进行缩小操作！
        	openMessage(msgType.info, "${base_map_zoomOutError3}");
            return false;
        }

        $zoom_imgs.each(function(){
            loop_doors($(this), scale);
            //alert('new_door_left='+new_door_left+'new_door_top='+new_door_top)
            $(this).parent().parent().css("top", new_door_top);//上边距
            $(this).parent().parent().css("left", new_door_left);//左边距
            $(this).css("width", new_door_width_);
            $(this).parent().parent().css("width", new_door_width);//只控制width即可，height：auto，默认40px
        });
        //重新设置img的width和height
        $img.width(box_width);
        $img.height(box_height);
    }


    function saveMapPos(path)
    {
    	var id = mapTree.getSelectedItemId().toString();
		if(id != "" && id != "0" && id.indexOf("_")<0)
		{
			var posArray = new Array();//二维
	        var entityId = "";
	        var entityWidth="40";
	        var entityType = "";
	        var mapId = id;
	        var entityLeft = "";
	        var entityTop = "";
	        var mapPosId = "";
	        var mapWidth = $("#id_map_"+id+" .map").width();
	        var mapHeight = $("#id_map_"+id+" .map").height();
	        $("#id_map_"+id+" .can_drag").each(function(){
	        	mapPosId = $(this).attr("data");
	        	entityWidth = $(this).width();//40
	        	var $posItem = $("#item_" + $(this).attr("data").split("_")[0]);
	        	entityLeft = parseInt($posItem.css("left"));//非$(this)
	        	entityTop = parseInt($posItem.css("top"));
	            posArray.push(new Array(mapPosId, entityWidth, entityLeft, entityTop))
	        });
	        var stamp5 = new Date().getTime();
	        path = path + "?mapId=" + mapId + "&mapWidth=" + mapWidth + "&mapHeight=" + mapHeight + "&stamp=" + stamp5;
			$.post(path, {"posArray": posArray.toString()}, function(result){
				dealRetResult(result);
			}, "json");
		}
		else
		{
			messageBox({messageType:"alert",text: "${base_map_plSelMap}"});
			//messageBox({messageType:"alert",text: "${base_map_plSelMap}"});//${base_map_plSelMap}://请选择地图
		}
    }
   
    function zoomIn(path)
    {
    	var id = mapTree.getSelectedItemId().toString();
		if(id != "" && id != "0" && id.indexOf("_")<0)
		{
	        var $zoom_img = $("#id_map_"+id+" img:last");
	        if($("#reloadFlag").val() == 0)
	        {
	        	multiple = 0;
	        }
	        zoom_img($zoom_img, 1.25, 0, 0);	
	        $("#reloadFlag").val(1);
		}
		else
		{
			messageBox({messageType:"alert",text: "${base_map_plSelMap}"});//${base_map_plSelMap}://请选择地图
		}
    }


    function zoomOut(path)
    {
    	var id = mapTree.getSelectedItemId().toString();
    	
		if(id != "" && id != "0" && id.indexOf("_")<0)
		{
	        var $zoom_img = $("#id_map_"+id+" img:last");
	        if($("#reloadFlag").val() == 0)
	        {
	        	multiple = 0;
	        }
	        zoom_img($zoom_img, 0.8, 0, 0);
	        $("#reloadFlag").val(1);
		}
		else
		{
			messageBox({messageType:"alert",text: "${base_map_plSelMap}"});//${base_map_plSelMap}://请选择地图
		}
    }


    var isFullScreen = false;
    function fullScreen(path)
    {
    	if(!isFullScreen)
    	{
    		operateToolbars["operate"].setItemImage(path, "base_map_exitFullScreen.png");
    		operateToolbars["operate"].setItemText(path, "${common_op_exitFullScreen}");
    		myLayouts[layoutName].cells(layout_a).collapse();
    		if(window.top.system.contentLayout)
    		{
    			window.top.system.contentLayout.cells(layout_a).collapse();
    			//leo 门禁地图，最大化后，出现多余标题栏
        		//window.top.system.contentLayout.cells("b").hideHeader();
    		}
    		isFullScreen = true;
    	}
    	else
    	{
    		operateToolbars["operate"].setItemImage(path, "base_map_fullScreen.png");
    		operateToolbars["operate"].setItemText(path, "${common_op_fullScreen}");
    		myLayouts[layoutName].cells(layout_a).expand();
    		if(window.top.system.contentLayout)
    		{
    			window.top.system.contentLayout.cells(layout_a).expand();
    			//leo 门禁地图，最大化后，出现多余标题栏
        		//window.top.system.contentLayout.cells("b").showHeader();
    		}
    		isFullScreen = false;
    	}
    }


    function editMap(path)
	{
		var id = mapTree.getSelectedItemId().toString();
		if(id != "" && id != "0" && id.indexOf("_")<0)
		{
			path=path.replace("(id)",id);
			openWindow(path);
		}
		else
		{
			messageBox({messageType:"alert",text: "${base_map_plSelMap}"});//${base_map_plSelMap}://请选择地图
		}
	}

	function delMap(delPath)
	{
		var id = mapTree.getSelectedItemId().toString();
		if(id != "" && id != "0" && id.indexOf("_")<0)
		{
			delPath = delPath + "?id="+id;
			var submit = function (result) {
			    if(result)
			    {
					$.get(delPath, function(result){
						dealRetResult(result, function (){
							reloadTree();
						});
					}, "json");
				}
			    else
			    {
			        // 取消
			    }
			    return true; //close
			};
			messageBox({messageType:"confirm",text:"${base_map_sureDelMap}",callback:submit});//${base_map_sureDelMap}://你确定要删除当前地图吗?
		}
		else
		{
			messageBox({messageType:"alert",text: "${base_map_plSelMap}"});//${base_map_plSelMap}://请选择地图
		}
	}


	function addEntityToMap(path)
	{
		var id = mapTree.getSelectedItemId().toString();
		if(id != "" && id != "0" && id.indexOf("_")<0)
		{
			path = path.replace("(id)",mapTree.getSelectedItemId());
			openWindow(path);
		}
		else
		{
			
			messageBox({messageType:"alert",text: "${base_map_plSelMap}"});//${base_map_plSelMap}://请选择地图
		}
	}


	/**
	 * 检测上图图片大小
	 * @author liangm
	 * @date 20140419
	 * @param fileId
	 * @return
	 */
	function checkImgSize(fileId)
	{
		var file = document.getElementById(fileId);
		var fileSize = 0;
		//if($.browser.msie)
		//{
			//var fso = new ActiveXObject("Scripting.FileSystemObject");
			//fileSize = fso.GetFile(file.value).size;
		//}
		if(!$.browser.msie)
		{
			fileSize = file.files[0].size;//火狐等标准取值办法
		}
		if(fileSize > 1024*1024*2)//2M
		{
			openMessage(msgType.info, "${base_map_imgSizeError}");//${base_map_plSelMap}://请选择地图
			return false;
		}
		else
		{
			return true;
		}
	}