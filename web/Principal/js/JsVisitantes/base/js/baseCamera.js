function openCameraAloha()
{
	//createWindow('base_baseFPRegister.html?random=' + getRandomNum() + '^0^0^465^480^' + title);//public/html/applet.html
	createWindow('opCaptureContent.html?random=' + getRandomNum() + '^0^0^465^300^Capturar');//public/html/applet.html
	//createWindow('CapturarFotoHuella.html?random=1000^0^0^600^350^Capturar');	

}

function openCameraAlohaConsecutivo()
{
	//createWindow('base_baseFPRegister.html?random=' + getRandomNum() + '^0^0^465^480^' + title);//public/html/applet.html
	//createWindow('opCaptureContent.html?random=' + getRandomNum() + '^0^0^465^300^Capturar');//public/html/applet.html
	createWindow('CamaraConsecutivo.html?random=' + getRandomNum() + '^0^0^465^300^Capturar');//public/html/applet.html
	
	//pers_opCaptureContent.action^0^0^465^282^Capturar'
}

function SendImageToServerAloha(Data)
{
	$.ajax( {
			url : "ScriptData",
			data: JSON.stringify(Data),
			type: 'POST',
			dataType: 'json',
			contentType: 'application/json',
			mimeType: 'application/json',
			success : function(result) 
			{
				var x = result;
				return x;
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) 
			{
				messageBox({messageType: "alert", title: "Error", text: "Error al Guardar la Imagen"});
			}
		});
	
}

function SendImageToServerAloha1(Data)
{
	var x = "";
	$.ajax( {
	
			//type : "POST",
			//url : "ScriptData",
			//contentType : "application/x-www-form-urlencoded;charset=UTF-8",
			
			//dataType : "json",
			//async: false,
			
			type : "post",
			url : "ScriptData",
			data: JSON.stringify(Data),
			dataType : "json",			
			async: false,
			
			success : function(result) 
			{
				x = result.Identificacion + "," + result.Nombres + "," + result.Imagen;
				//return x;
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) 
			{
				messageBox({messageType: "alert", title: "Error", text: "Error al Guardar la Imagen"});
			}
		});
	return x;
}

function createHtml(type,containerWidth,containerHeight)
{
	var html = null;
	if(type === "simple")
	{
		var swf = "capture_cert_01.swf";
		if(window.module === "pers")
		{
			swf = "capture.swf";
			var style = $("#id_cap_div").attr("style");
			style += ";background:url()"
			$("#id_cap_div").attr("style",style);
		}
		html = "<object classid='clsid:d27cdb6e-ae6d-11cf-96b8-444553540000' " + "id='myFlash_cert" + 
		"'" + "width='" + containerWidth + "px'" + "height='" + containerHeight + "px'" + 
		"codebase='http://fpdownload.macromedia.com/pub/shockwave/cabs/flash/swflash.cab#version=8,0,0,0' id='video' align='middle'>" +
		"<param name='allowScriptAccess' value='sameDomain' />" + "<param name='movie' value='${base}/public/media/swf/" +
		swf + "' />" + "<param name='quality' value='best' />" + "<param name='bgcolor' value='#ffffff' />" + 
		"<param name='wmode' value='transparent' />" + "<param name='allowScriptAccess' value='always' />" + 
		"<param name='swliveconnect' value='true'/>" + "<embed wmode='transparent' src='${base}/public/media/swf/" +
		swf + "'" + "width='" + containerWidth + "px'" + "height='" + containerHeight + "px'" +
		"quality='best' bgcolor='#ffffff' align='middle' allowScriptAccess='sameDomain' type='application/x-shockwave-flash' " +
		"name='myFlash_cert'" + "swLiveConnect='true'  pluginspage='http://www.macromedia.com/go/getflashplayer' class='"+
		"certFlash' />" + "</object>";
	}else
	{
		if(containerWidth == 220)
		{
			containerWidth = 187;
		}
//		html = "<div id='initTip'><span class='warningImage'></span><a class='example-image-link warningColor' href='"+createGif('Simple')+"' data-lightbox='example-2' style='padding-top:1px;text-decoration:underline;color:#E57A14'>"+"${common_camera_authorizedCamera}"+"</a></div>";
		html = "<div id='initTip'><span class='warningImage'></span><p class='example-image-link warningColor' data-lightbox='example-2' style='padding-top:1px;color:#E57A14'>"+"${common_camera_authorizedCamera}"+"</p></div>";
		html += '<video id="video" height="'+containerHeight+'px" width="'+containerWidth+'px" autoplay></video> ';
	}

	return html;
};

function saveCapture(type,previewWidth,previewHeight)
{
	if(type === "html5")
	{
		var video = document.getElementById('video');
		var canvas = document.createElement('canvas');
		canvas.width = 360;
		canvas.height = 480;
		context = canvas.getContext("2d");

		var sheight = video.videoHeight - 10;
		var swidth = sheight * previewWidth/previewHeight;
		var sx = (video.videoWidth - swidth)/2;
		var sy = 5;

		if(window.module == "vis")
		{
			context.drawImage(video,0,0,previewWidth,previewHeight);
		}
		else
		{
			context.drawImage(video,sx,sy,swidth,sheight,0,0,360,480);
		}
		$("#preview").text("");
		var imgData = canvas.toDataURL("image/jpg");
        var img = document.createElement('img');//创建新的img标签保存图片
        img.width = previewWidth;
        img.height = previewHeight;
        img.src = imgData;
        $("#preview").append(img);
		var base64String = imgData.substr(22); //取得base64字串
		$("#capturePhoto").val(base64String);
	}
	else
	{
		var paramArray = new Array();
		window.captureParams = paramArray;
		var capture = getCapture("myFlash_cert");
		if (capture == null)
		{
			openMessage(msgType.warning, "${common_camera_noCamera}".replace("<br/>",""));
		}
		else if (capture.saveCamera != undefined)
		{
			capture.saveCamera();
		}else
		{
			openMessage(msgType.warning, "${common_camera_noCamera}".replace("<br/>",""));
		}
	}
}

//修改IE浏览器抓拍头像失败问题  modified by 陈彩云  20160531
function myFlash_DoFSCommand(command, args)
{
	if (command == "send_pic")//发送的是base64的图片
	{
		var captureParam = window.captureParams;
		var img = '<img id="id_capture_photo" width="120'+'px" height="140'+'px" src=""/>';
		$("#preview").text("");
		$("#preview").append(img);
		var photo = "data:image/jpg;base64," + args;
		$("#id_capture_photo")[0].src = photo;
		$("#capturePhoto").val(args);
	}
	else if(command=="send_no_cam"){
		if(args==-1)
		{
			//异步避免其他脚本影响，位置出现在上方
			setTimeout(function(){
				openMessage(msgType.warning, "${common_camera_cameraError}".replace("<br/>",""));
			}, 10);
		}
		else
		{
			openMessage(msgType.warning, "${common_camera_noCamera}".replace("<br/>",""));
		}
	}
	else
	{
		openMessage(msgType.warning, "${common_camera_authorizedCamera}".replace("<br/>",""));
	}
}

//获取Object标签对象
function getCapture(id)
{
	return document.embeds[id] || window[id] || window.document[id] || document.getElementById(id);
}

function startWebcam(previewWidth,previewHeight,captureButton,browseType,e){

//	debugger;
//	console.info(MediaStreamTrack.getSources());
	navigator.getUserMedia({
		video: true,
		audio: false
	}, onSuccess, onError);

	function onSuccess(stream) {
		var style = $("#id_cap_div").attr("style"); 
		style += ";background:url()"
			$("#id_cap_div").attr("style",style);
		$("#initTip").remove();
		$(document).off("click",captureButton);
		$(document).on("click",captureButton,function(e){
			saveCapture(browseType,previewWidth,previewHeight);
		});
		$("#camera_message").text("");
		var video = document.getElementById('video');
		//高版本火狐、谷歌不兼容URL.createObjectURL(MediaStream)
		if(typeof video.srcObject == "object")
		{
			video.srcObject = stream;
		}
		else
		{
			//低版本火狐、谷歌可以使用URL.createObjectURL(MediaStream)
			video.src = window.URL.createObjectURL(stream) || stream;
		}
		video.onloadedmetadata = function(e) {
			if(stream.getVideoTracks()[0].readyState === "ended")
			{
				message = "${common_camera_inUse}";
				$("#id_cap_div").html("<span class='warningImage'></span><p class='warningColor' style='padding-top:1px'>"+message+"</p>");
				var style = $("#id_cap_div").attr("style");
				style += ";background:url(/vis/images/PC/noCamera.png) center no-repeat"
					$("#id_cap_div").attr("style",style);
			}
		};
		video.autoplay = true;
		setTimeout(function(){
			captureBorder(previewWidth,previewHeight);
		}, 2500);
	}
	function onError() {
		$("#initTip").remove();
		var errorType = arguments[0].name;
		var message = "";
		var hyperlink = false;
		switch (errorType)
		{
			case "PermissionDeniedError":
			case "PermissionDismissedError":
				hyperlink = true;
				message = "${common_camera_authorizedCamera}";
				break;
			case "SourceUnavailableError":
			case "MediaDeviceFailedDueToShutdown":
				message = "${common_camera_inUse}";
				break;
			case "DevicesNotFoundError":
				message = "${common_camera_noCamera}";
				break;
			default:
				if(window.console)
				{
					console.error("exception not define:" + errorType);
				}
			message = "${common_camera_noCamera}";
			break;
		}
		var img = '<img id="noCamera" width="'+previewWidth+'px" height="'+previewHeight+'px" src="${base}/vis/images/PC/noCamera.png"/>';
		$(document).off("click",captureButton);
		$(document).on("click",captureButton,function(e){
			openMessage(msgType.warning, message.replace("<br/>",""));
		});
		if(hyperlink)
		{
			showPemisson(message);
		}
		else
		{
			$("#id_cap_div").html("<span class='warningImage'></span><p class='warningColor' style='padding-top:1px'>"+message+"</p>");
		}
	}
}

function showPemisson(message)
{
//	var gif = createGif();
	$("#id_cap_div").html("<span class='warningImage'></span><p class='example-image-link warningColor' data-lightbox='example-2' style='padding-top:1px;color:#E57A14'>"+message+"</p>");
}

//function createGif(simple)
//{
//	var simple = simple || "";
//	var gif = "${base}/base/images/camera/";
//	gif += window.module;
//	if(navigator.userAgent.indexOf("Firefox")!=-1)
//	{
//		gif += "Ff";
//	}
//	else
//	{
//		gif += "Chrome";
//	}
//
//	if("${application['system.language']}" === "zh_CN")
//	{
//		gif += "Zh";
//	}
//	else
//	{
//		gif += "En";
//	}
//	gif += simple;
//
//	gif += ".gif";
//
//	return gif;
//}

function createCamera(container,containerWidth,containerHeight,captureButton,previewWidth,previewHeight,module)
{
	window.containerWidth = containerWidth;
	window.containerHeight = containerHeight;
	window.module = module || "vis";
	var type = "html5";
	navigator.getUserMedia || (navigator.getUserMedia = navigator.mozGetUserMedia || navigator.webkitGetUserMedia || navigator.msGetUserMedia);
	if (!navigator.getUserMedia) {
		type = "simple";
	}


	var videoId = "video_" + container.substring(1,container.length);
	$(container).html(createHtml(type,containerWidth,containerHeight));
	$(document).off("click",captureButton);
	$(document).on("click",captureButton,function(e){
		if(type === "simple")
		{
			saveCapture(type,previewWidth,previewHeight);
		}
		else
		{
			openMessage(msgType.warning, "${common_camera_authorizedCamera}".replace("<br/>",""));
		}
	});
	if(type==="html5")
	{
		if(previewWidth == 220)
		{
			previewWidth = 187;
		}
		startWebcam(previewWidth,previewHeight,captureButton,type);
	}
	else
	{
		window.setTimeout(function(){
			var capture = getCapture("myFlash_cert");
			if (capture == null || (capture != null && undefined == capture.saveCamera))
			{
				$("#id_cap_div").css("background","").html("<span class='warningImage'></span><span class='warningColor' style='padding-top:1px'>${common_camera_noCamera}</span>");
			}
		}, 100);
	}
}

//IPC拍照
function saveIPCapture(channelId,width,height)
{
	$.ajax({ 
		type: "post", 
		contentType: "application/json", 
		url: "/rs/vid_channel/capture", 
		data: "{channelId:"+channelId+"}",
		dataType: "json", 
		success: function (result){
			if(result.data == undefined || result.data == null)
			{
				openMessage(msgType.error);
				return;
			}
			if(result.data == "")
			{
				openMessage(msgType.error, "${common_camera_disabled}");
				return;
			}
			var img = '<img id="id_capture_photo" width="'+width+'px" height="'+height+'px" src=""/>';
			$("#preview").text("");
			$("#preview").append(img);
			$("#preview").removeClass("user");
			var photo = "data:image/gif;base64";
			//系统操作类型（登记或签离）
			$("#id_capture_photo")[0].src = photo;
			$("#id_capture_photo").show();
			$("#capturePhoto").val(result.data);
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) 
		{
			openMessage(msgType.error);
		}

	});
}

/**
 * HTML5拍照截取图片的虚框
 * 
 * @author <a href=mailto:oujunxiao.ou@zkteco.com>欧骏骁</a>
 * @since 2015年4月30日 下午2:16:50
 */
function captureBorder(previewWidth,previewHeight)
{
	var video = document.getElementById('video');
	var position = $("#video").position();
	$("#border").css("position","absolute");
	var realWidth = video.videoWidth/video.videoHeight*window.containerHeight;
//	$("#id_cap_div").css("width",realWidth+"px");
	var width = (realWidth-previewWidth)/2;
	var height = (window.containerHeight-previewHeight)/2;
	$("#border").css("left",position.left+width);
	$("#border").css("top",position.top+height+5);
	$("#border").css("width",previewWidth);
	$("#border").css("height",previewHeight-10);
	$("#border").css("border","1px dashed rgb(38, 219, 74)");
}
