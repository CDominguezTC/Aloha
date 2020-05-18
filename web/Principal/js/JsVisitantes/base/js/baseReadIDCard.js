/**
 * ----------------------------------------------------------读取身份证信息--------------------------------------------------------------
*/
/**
 * 读卡器接口返回值
 */
var ERR_IDCARD_OK = 0;	//成功
var ERR_IDCARD_OPENCOMFAIL = 1;	//端口打开失败
var ERR_IDCARD_TRANDATATIMEOUT = 2;	//数据传输超时
var ERR_IDCARD_NOFINDCARD = 10;	//没有找到卡
var ERR_IDCARD_READCARDFAIL = 11;	//读卡操作失败
var ERR_IDCARD_SELFTESTFAIL = 20;	//自检失败
var ERR_IDCARD_OTHER = 30;	//其他错误
var ERR_IDCARD_PHOTODECODEFAIL = 40;	//相片解码失败
var ERR_IDCARD_TIMEOUT = 100;	//timeout
var ERR_IDCARD_GET_BASE64 = 200;	//GetBase64PhotoData()
var idCardReaderStatus = 0;
/**
 * 读取身份证信息
 * @param {Object} iPort 端口号
 * @param {Object} browserFlag
 */
function readIdCardInfo(browserFlag)
{
	if(idCardReaderStatus != 0)
	{
		readIDCardFailed();
		return;
	}
	idCardReaderStatus = 1;
	if(browserFlag == "html5")
	{
		$.ajax( {
			type : "GET",
			url : issOnlineUrl+"/ScanReadIdCardInfo?OP-DEV=1&CMD-URL=4",
			dataType : "text",
			async: true,
			//timeout:1000,
			success : function(data) 
			{
				try{
					idCardReaderStatus = 0;
					data = data.replace(/\\/g,"/");
					var result = JSON.parse(data);
					if(result != null)
					{
						//接口调用成功返回时
						if(result.ret == ERR_IDCARD_OK)
						{
							setIDCardData(result.Certificate,result.Certificate.Base64Photo);
							return;
						}
						else if(result.ret == ERR_IDCARD_TIMEOUT)
						{
							IDCardNotFound();
							return;
						}
						else if(result.ret == ERR_IDCARD_OPENCOMFAIL)
						{
							IDCardReadOpenFailed();
							return;
						}
					}
					readIDCardFailed();
				}
				catch(e) 
				{
					readIDCardFailed();
				}
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) 
			{
				idCardReaderStatus = 0;
				readIDCardFailed();
		    }
		});
	}
	else if(browserFlag == "simple")
	{
		//创建XDomainRequest实例，用于ie8和ie9跨域访问
		var xDomainRequest = new XDomainRequest();
		//如果xDomainRequest存在,则可以使用xDomainRequest函数，否则，说明不是ie浏览器
		if (xDomainRequest) 
		{  
			xDomainRequest.open('GET', issOnlineUrl+"/ScanReadIdCardInfo?OP-DEV=1&CMD-URL=4");  
			xDomainRequest.onload = function()
			{
				try
				{
					idCardReaderStatus = 0;
					//获取接口返回值
					var resultData = xDomainRequest.responseText;
					//转化为json对象
					resultData = resultData.replace(/\\/g,"/");
					var obj = JSON.parse(resultData);
					//返回码
					var ret = null;
					var certificate = null;
					if(obj.ret != undefined)
					{
						ret = obj.ret;
					}
					if(obj.Certificate != undefined)
					{
						certificate = obj.Certificate;
					}
					//接口调用成功返回时
					if(ret == ERR_IDCARD_OK)
					{
						setIDCardData(certificate,certificate.Base64Photo);
						return;
					}
					else if(ret == ERR_IDCARD_TIMEOUT)
					{
						IDCardNotFound();
						return;
					}
					else if(ret == ERR_IDCARD_OPENCOMFAIL)
					{
						IDCardReadOpenFailed();
						return;
					}
					readIDCardFailed();
				}
				catch(e)
				{
					readIDCardFailed();
				}
			};  
			xDomainRequest.onerror = function()
			{
				idCardReaderStatus = 0;
				//用完后，将对象置为空
				xDomainRequest = null;
				readIDCardFailed();
			};
			xDomainRequest.send();  
		} 
		else 
		{  
			idCardReaderStatus = 0;
			readIDCardFailed();
			dhtmlx.alert("Do not create XDomainRequest");
		}
	}
	else
	{
		idCardReaderStatus = 0;
		readIDCardFailed();
	}
	
}

/**
 * ----------------------------------------------------------html5 end-------------------------------------------------------------
*/