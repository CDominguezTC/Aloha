var HH=23;
var MM=59;
var SS=59;
var mode={};
var LOOP;
var timeFormat;

function loopInit(id,sign){
	LOOP=true;				
	change(id,sign);
}
function loopBreak(){
	LOOP=false;
}
function getTag(id,tagName,str,attr){
	var tagArray=document.getElementById(id).getElementsByTagName(tagName);
	for(i=0;i<tagArray.length;i++){
		if(eval("tagArray[i]."+attr)==str){
				return tagArray[i];
		}
	}
}
function setMode(id,hms){
	var selectInputPre = getTag(id,'input',mode[id+"mode"],'name');
	var selectInput = getTag(id,'input',hms,'name');
	selectInputPre.style.backgroundColor="#FFFFFF";
	mode[id+"mode"]=hms;
	selectInput.style.backgroundColor="#FFB951";
}
function bgBlur(obj){
	obj.style.backgroundColor="#FFFFFF"
}

function change(id,sign){
	if(LOOP){
		var num=getTag(id,'input',mode[id+"mode"],'name').value-0;
		var changeNum=eval(num+sign+1)+"";
		if(changeNum>=0&changeNum<=eval(mode[id+"mode"])){	
			changeNum=complete(changeNum);
			getTag(id,'input',mode[id+"mode"],'name').value=changeNum;		
		}
		setTimeout("change('"+id+"','"+sign+"')",200);		
	}
	setHiddenValue(id);
}
function complete(num){
	while(!(num.length==2)){
		num="0"+num;	
	}
return num;		
}
/**
 * @Description: 时分秒控件返回最终时间值
 * @Author: 张法军 
 * @Modified By:
 * @Date: 2014-08-26
 * @param: tempId 显示的divid
 */ 
function returnTimer(id){
		var timeFormatArr = timeFormat.split(":");
		var stringtime='';
		for (var i=0;i<timeFormatArr.length;i++) {
			stringtime = stringtime+getTag(id,'input',timeFormatArr[i],'name').value+":";
		}
		return stringtime.substring(0,stringtime.length-1);
}
function checkNum(id,sign,value){
	if(value<10){
		value=complete(value);
		getTag(id,'input',sign,'name').value=value;
	}else{
		if(value>eval(sign)){
			getTag(id,'input',sign,'name').value=eval(sign);
		}
	}
}
function keyDown(id,key,onFocusObject){
	var nextObject;
	var timeFormatArr = timeFormat.split(":");
	if(!((key>=48 && key<=57)||(key>=96 && key<=105)||(key==8)||(key==46)||(key>=37 && key<=40))){
		event.returnValue=false;
	}
	if(key==37){
		for (var i=0;i<timeFormatArr.length;i++) {
			if (onFocusObject.name==timeFormatArr[i]) {
				if (i==0) {
					nextObject=timeFormatArr[timeFormatArr.length-1];
				}else{
					nextObject=timeFormatArr[i-1];
				}
			}
		}
		getTag(id,'input',nextObject,'name').focus();
	}
	if (key==39) {
		for (var i=0;i<timeFormatArr.length;i++) {
			if (onFocusObject.name==timeFormatArr[i]) {
				if (i+1>=timeFormatArr.length) {
					nextObject=timeFormatArr[0];
				}else{
					nextObject=timeFormatArr[i+1];
				}
			}
		}
		getTag(id,'input',nextObject,'name').focus();
	}
	if(key==38){
		loopInit(id,'+');
	}
	if(key==40){
		loopInit(id,'-');
	}
	setHiddenValue(id);
}
function keyUp(id,key){
	if(key==38||40){
		loopBreak();
	}	
	setHiddenValue(id);
}

function setHiddenValue(id){
	document.getElementById(id+"_hidden").value = returnTimer(id);
}

/**
 * @Description: 时分秒控件
 * @Author: 张法军 
 * @Modified By:
 * @Date: 2014-08-26
 * @param: tempId 显示的divid
 * @param: hiddenName 为了form提交，增加隐藏域，赋值name属性
 * @param: isShowRemark 是否显示时间格式化,默认false 可选(true/false)
 * @param: timeStr 初始值，可选，默认00:00:00
 * @param: timeFormatParam 时间显示格式化，默认HH:MM:SS
 */ 
function showTimer(tempId,hiddenName,isShowRemark,timeStr,timeFormatParam){
	if (!timeFormatParam) {
		timeFormat = "HH:MM:SS";
	}else{
		timeFormat=timeFormatParam;
	}
	if (!hiddenName) {
		hiddenName=tempId;
	}
	var timeFormatArr = timeFormat.split(":");
	var hourstr="00";
	var minutestr="00";
	var secondstr="00";
	if (timeStr) {
		var timearray = timeStr.split(":");
		for (var i=0;i<timearray.length;i++) {
			if (timeFormatArr[i]=="HH") {
				var hourtemp = Number(timearray[i]);
				if (hourtemp&&hourtemp>=0&&hourtemp<24)  hourstr = timearray[i];
			}
			if (timeFormatArr[i]=="MM") {
				var minutetemp = Number(timearray[i]);
				if (minutetemp&&minutetemp>=0&&minutetemp<59)  minutestr = timearray[i];
			}
			if (timeFormatArr[i]=="SS") {
				var secondtemp = Number(timearray[i]);
				if (secondtemp&&secondtemp>=0&&secondtemp<59)  secondstr = timearray[i];
			}
		}
	}
	var HHcontent = '<input type="text"  maxlength="2" style="border:0;background:transparent;width:15px" name="HH" value="'+hourstr+'" onchange="checkNum('+"'"+tempId+"',"+"'HH',"+'this.value)" onkeydown="keyDown('+"'"+tempId+"'"+',event.keyCode,this)" onkeyup="keyUp('+"'"+tempId+"'"+',event.keyCode)" onfocus="setMode('+"'"+tempId+"','HH'"+')" onblur="bgBlur(this)"/>';
	var MMcontent = '<input type="text"  maxlength="2" style="border:0;background:transparent;width:15px" name="MM" value="'+minutestr+'" onchange="checkNum('+"'"+tempId+"',"+"'MM',"+'this.value)" onkeydown="keyDown('+"'"+tempId+"'"+',event.keyCode,this)" onkeyup="keyUp('+"'"+tempId+"'"+',event.keyCode)" onfocus="setMode('+"'"+tempId+"','MM'"+')" onblur="bgBlur(this)"/>';
	var SScontent = '<input type="text"  maxlength="2" style="border:0;background:transparent;width:15px;" name="SS" value="'+secondstr+'" onchange="checkNum('+"'"+tempId+"',"+"'SS',"+'this.value)" onkeydown="keyDown('+"'"+tempId+"'"+',event.keyCode,this)" onkeyup="keyUp('+"'"+tempId+"'"+',event.keyCode)" onfocus="setMode('+"'"+tempId+"','SS'"+')" onblur="bgBlur(this)"/>';
	var datejson = {"HH":HHcontent,"MM":MMcontent,"SS":SScontent};
	mode[tempId+"mode"] = "HH";
	var tdwidth =timeFormatArr.length==2?40:60;
	var trwidth = tdwidth + 25;
	var timerConent= '<table><tr><td>'
	timerConent+='<table cellpadding="0" cellspacing="0" style="border:#949495 1px solid;table-layout : fixed;display:inline-block;" >'+'<tr style="width:'+trwidth+'px;">'+'<td width="'+tdwidth+'px" style="border:0px;">'+datejson[timeFormatArr[0]];
	for (var i=1;i<timeFormatArr.length;i++) {
		timerConent = timerConent+'<input type="text"  style="border:0;background:transparent;width:6px;" readOnly=true  value=":"/>'+datejson[timeFormatArr[i]];
	}
	timerConent = timerConent + '</td><td width="20px" style="border:0px;line-height:0px"><input type="button" hidefocus="true" style=";border-width:2px;display:block;background:url(./images/up.jpg);width:20px;height:10px;" onmouseup="loopBreak()" onmousedown="loopInit('+"'"+tempId+"','+'"+')"/><input type="button" hidefocus="true" style="border-width:2px ;background:url(./images/down.jpg);width:20px;height:10px;" onmouseup="loopBreak()" onmousedown="loopInit('+"'"+tempId+"','-'"+')"/>';
	timerConent += '</td></tr></table>';
	timerConent += '</td>';
	if (isShowRemark==true) {
		timerConent=timerConent+'<td style="border:0px;"><label width="50" style="padding-left:5px;color: #666666">('+timeFormat+')</label></td>';
	} 
	
	timerConent += '</tr></table>';
	timerConent += '<input id="'+tempId+'_hidden" name="'+hiddenName+'" type="hidden"/>'
	document.getElementById(tempId).innerHTML=timerConent;
	setHiddenValue(tempId);
}