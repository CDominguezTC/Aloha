/**
 * 音频文件管理js
 * @author qingj.qiu
 * @create 2015-02-04 08:52 am
 */
/**
 * 加载音频
 * @param path 音频文件的路径
 * @param idPosition 播放位置div的id
 */
function loadAudio(path,idPosition)
{
	if(isFirefox()||isChrome())
	{
		$("#"+idPosition+"").empty();
		$("#"+idPosition+"").html("<audio id='myaudio' controls='controls' hidden='true' loop='true'><source id='mp3source2' name = 'soundname' src='"+path+"' type='audio/mp3' preload='auto' /><source id='mp3source1' name = 'soundname' src='"+path+"' type='audio/x-wav' preload='auto'/><embed id='mp3source3' name = 'soundname' height='100' width='100' src='"+path+"' preload='auto'/></audio>");
		var myaudio = $("#myaudio")[0];
		myaudio.pause();
	}
	else
	{
		$("#"+idPosition+"").html("<bgsound id='myaudio' src='' loop='-1'/>");
	}
}

/**
 * 播放或停止音频文件
 * @param path 音频文件的路径
 * @param buttonName 播放按钮的名称
 * @param idPosition 播放位置div的id
 * @param state 当前播放状态
 */
function playOrStopAudio(path,buttonName,idPosition,state)
{
	if(isFirefox()||isChrome())
    {
	    var myaudio = $("#myaudio")[0];
	   	if(myaudio.paused)
		{
			myaudio.play();
			$("input[name$='"+buttonName+"']").attr("value","${base_file_stop}");
			return
		}
		myaudio.pause();
		myaudio.currentTime = 0.0;
		$("input[name$='"+buttonName+"']").attr("value","${base_file_play}");
    }
    else
    {
    	if(state==1)
		{//播放
			$("#"+idPosition+"").html("<bgsound id='myaudio' src='"+path+"' loop='-1'/>");
			$("input[name$='"+buttonName+"']").attr("value","${base_file_stop}");
		}
		else
		{//停止
			$("#"+idPosition+"").html("<bgsound id='myaudio' src='' loop='-1'/>");
			$("input[name$='"+buttonName+"']").attr("value","${base_file_play}");
		}
    }
}

/**
 * 播放音频
 * @param path 音频文件的路径
 * @param buttonName 播放按钮的名称
 * @param idPosition 播放位置div的id
 */
function playAudio(path,buttonName,idPosition)
{
	if(isFirefox()||isChrome())
	{
		var myaudio = $("#myaudio")[0];
	   	if(myaudio.paused)
		{
			myaudio.play();
			$("input[name$='"+buttonName+"']").attr("value","${base_file_stop}");
		}
	}
	else
	{
		$("#"+idPosition+"").html("<bgsound id='myaudio' src='"+path+"' loop='-1'/>");
		$("input[name$='"+buttonName+"']").attr("value","${base_file_stop}");
	}
}
/**
 * 关闭音频
 * @param buttonName 播放按钮的名称
 * @param idPosition 播放位置div的id
 */
function stopAudio(buttonName,idPosition)
{
	if($("#myaudio")[0]){
		if(isFirefox()||isChrome())
		{
			var myaudio = $("#myaudio")[0];
			myaudio.pause();
			//myaudio.currentTime = 0.0;
			$("input[name$='"+buttonName+"']").attr("value","${base_file_play}");
		}
		else
		{
			$("#"+idPosition+"").html("<bgsound id='myaudio' src='' loop='-1'/>");
			$("input[name$='"+buttonName+"']").attr("value","${base_file_play}");
		}
	}
}

/**
 * 根据文件id获取文件url
 * @param id
 * @returns
 */
function getFilePath(id)
{
	$.ajax({
        type: "post",
        url: "baseMediaFileAction!getFileWebUrl.action",
        data: "fileId=" + id,
        success: function (result) {
    		return result;
        },
	    error:function (XMLHttpRequest, textStatus, errorThrown)
		{
		    messageBox({messageType: "alert", title: "${common_prompt_title}", text: "${common_prompt_serverError}" + "-628"});
		}
      });
}

//判断浏览器类型，火狐
function isFirefox()
{
    return (navigator.userAgent.indexOf("Firefox")!=-1);
}
//判断浏览器类型，谷歌
function isChrome()
{
    return (navigator.userAgent.indexOf("Chrome")!=-1);
}


