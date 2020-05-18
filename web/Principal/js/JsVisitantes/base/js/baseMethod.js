var installLanguage;//安装语言
/**
 * 根据安装语言，改变样式
 * @author wenxin
 * @create 2013-09-09 14:57:11 pm
 */
function changeNameStyle(name)
{
	var nameInput = "";
	$("#empNameDiv").html("");
	
	installLanguage = "${application['system.language']}";
	//安装语言为中文
	if(installLanguage == "zh_CN" || installLanguage == "zh_TW")//简体中文、繁体中文
	{
		nameInput = "<input name='empName' id='empName' type='text' maxlength='8' value='" + name + "'>";
		$("#empNameDiv").html(nameInput);
	}
	else
	{
		var empName = "";
		if(name != undefined && name != null)
		{
			empName = name;
		}
		nameInput = "<input name='empName' id='empName' type='text' maxlength='23'";
		if($.trim(empName) == "")
		{
			nameInput += " value='"+ "${pers_person_fullName!}" + "' class='showGray'";
		}
		else
		{
			nameInput += " value='" + empName + "'";
		}
		nameInput += "'>";
		$("#empNameDiv").html(nameInput);
		checkText("empName", "${pers_person_fullName!}");
	}
}
/**
 * @Description: 将全角转成半角
 * @Author: 
 * @Modified By:wenxin
 * @Date: 2013-11-05
 * @param: str 要进行转换的字符串
 */
function DBC2SBC(str)
{
	var result = "";
	for ( var i = 0; i < str.length; i++)
	{
		code = str.charCodeAt(i);//获取当前字符的unicode编码 
		if (code >= 65281 && code <= 65373)//在这个unicode编码范围中的是所有的英文字母已经各种字符 
		{
			var d = str.charCodeAt(i) - 65248;
			result += String.fromCharCode(d);//把全角字符的unicode编码转换为对应半角字符的unicode码 
		}
		else if (code == 12288)//空格 
		{
			var d = str.charCodeAt(i) - 12288 + 32;
			result += String.fromCharCode(d);
		}
		else
		{
			result += str.charAt(i);
		}
	}
	return result;
}

/**
 * @Description 生成随机数
 * @Author: wenxin
 * @Date: 2014-04-15
 */
function getRandomNum() 
{
    var random = parseInt(Math.random() * 10000);
    return random;
}