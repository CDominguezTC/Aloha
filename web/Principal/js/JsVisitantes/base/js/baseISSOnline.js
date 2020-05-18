
/**
 * 创建ISSOnline设备:目前支持 所有身份证阅读器 \聚盾证件扫描仪 FS531-U \条码扫描枪 N4313 民德ES4200 激光 串口COM1
 * <p>
 * 使用时应引入JS ："${base}/base_baseISSObject.js.action"
 * <pre>
 * example code... 
	var setting = {
			Cert : {
				callBack : function(result){
					$("#id_certType").val(2);
					setCertificateData(result);
				},
				select : "#button_readID"
			},
			Barcode : {
				callBack : function(result){
					$("#id_certNumber").val(result.data);
					$("#id_certNumber").trigger("change");
				},
				select : "#button_readCodeImage"
			},
			Scanner : {
				callBack : function(result){
					setCertificateData(result);
				},
				select : "#button_scanner"
			},
			Methods : {
				showMessage : function(type,message){
					$("#cert_message").text(message);
					$("#cert_message_type").text(msgType[type]);
				},
				checkWebServer : function(){
					return $("#fpCountMessage").html() != "";
				},
				downloadDrive : function(){
					$("#ipc_preview").hide();
					messageBox({messageType: "confirm", text: "${common_issOnline_driverPrompt}", 
						callback: function(result){
							if(result)
							{
								window.location.href = "base/middleware/ISSOnline.exe";
							}
							closeMessage();
							$("#ipc_preview").show();
					}});
				}
			}
		}
		createISSonlineDevice(setting);
 * </pre>
 * 
 * @author oujunxiao.ou
 * @param setting json格式：{设备类型：不需要则不传
 * 							Cert\Barcode\Scanner : {回调函数 'callBack' 参数为设备返回值，参见ISSO接口，
 * 													访问设备jquery 选择器 'select'，click事件触发访问设备指令
 * 													}，
 * 							Methods 自定义方法，不传则执行默认方法
 * 							showMessage ：接收执行结果，参数：type：sucess\warning\error message:返回的错误信息
 * 							checkWebServer ：检测驱动是否安装  return type : boolean
 * 							downloadDrive : 下载驱动
 */
function createISSonlineDevice(setting)
{
	var ISSOnline = "ISSOnline";
	if(typeof setting.issType == "function")
	{
		switch (setting.issType())
		{
			case "Cert":
				ISSOnline = "${common_issOnline_cert}";
				break;
			case "Scanner":
				ISSOnline = "${common_issOnline_scanner}";
				break;
			case "CertScanner":
				ISSOnline = "${common_issOnline_certScanner}";
				break;
			case "highSpeed":
				ISSOnline = "${common_issOnline_highSpeed}";
				break;
			case "FPCert":
				ISSOnline = "${common_issOnline_fpCert}";
				break;
			case "FPScanner":
				ISSOnline = "${common_issOnline_fpScanner}";
				break;
			case "FPCertScanner":
				ISSOnline = "${common_issOnline_fpCertScanner}";
				break;
			case "FPhighSpeed":
				ISSOnline = "${common_issOnline_fpHighSpeed}";
				break;
			case "touch":
				ISSOnline = "${common_issOnline_touch}";
				break;
			case "FPtouch":	
				ISSOnline = "${common_issOnline_fpTouch}";
				break;
			case "FPnone":
			default:
				ISSOnline = "${common_issOnline_fp}";
				break;
		}
	}
	var old = $.jBox.getContent();
	var detect = "${common_issOnline_detect}".replace("%s",ISSOnline);
	$.jBox.setContent(old + '<div style="padding-left: 10px;"><label id="issM">'+detect+'</label></div><div style="padding-left: 10px;padding-right:10px;"><div class="progress"><div id="iss" text="'+ISSOnline+'" class="progress-bar progress-error" state="loading" style="width: 5%;"></div></div></div>');
	var browserFlag = getBrowserType() || "";
	//刷卡信息返回默认方法
	if(typeof setting.Methods == "object")
	{
		if(typeof setting.Methods.showMessage != "function")
		{
			setting.Methods.showMessage = function(type,message)
			{
				$("#cert_message").text(message);
				$("#cert_message_type").text(msgType[type]);
			}
		}
		//检查驱动安装默认方法
		if(typeof setting.Methods.checkWebServer != "function")
		{
			setting.Methods.checkWebServer = function(myDevice)
			{
				var ISSVersion = function(){};
				ZK.extend(ISSVersion, Device, {
					message : "",
					url : "/info",
					interfaceResult : 
					{
						0:{mean:"成功",message:"${common_issOnline_cardSuccess}",type:"success",notShow:true}
					},
					dealDeviceData:function(result){
						var existVersion = result.data.server_version;
						var curVersion = "${application['fpDriver.version']}";
						var existVersionArr = existVersion.split(".");
						var curVersionArr = curVersion.split(".");
						var isLast = true;
						var len = existVersionArr.length;
						for(var i=0;i<len;i++)
						{
							if(parseInt(existVersionArr[i])<parseInt(curVersionArr[i]))
							{
								isLast = false;
								break;
							}
						}
						//if(result.data.server_version >= "${application['fpDriver.version']}")
						if(isLast)
						{
							$("#iss").css("width","100%");
							$("#iss").removeClass("progress-error");
							$("#iss").addClass("progress-success");
							$("#iss").attr("state","success");
							$("#issM").text("${common_issOnline_installedProperly}".replace("%s",ISSOnline));
							if(typeof myDevice == "object")
							{
								//连接设备，处理返回信息
								setTimeout(function(){
									myDevice.accessDevice();
								},100);
							}
						}
						else
						{
							$("#iss").css("width","100%");
							$("#iss").removeClass("progress-error");
							$("#iss").addClass("progress-warning");
							$("#iss").attr("state","warning");
							$("#issM").text("${common_issOnline_needUpgrade}".replace("%s",ISSOnline));
							$("#issM").append("<a href='base/middleware/ISSOnline.exe'> ${common_issOnline_driverDownload}</a>");
							if(typeof myDevice == "object")
							{
								//连接设备，处理返回信息
								setTimeout(function(){
									myDevice.accessDevice();
								},100);
							}
							else
							{
								if(typeof setting.Methods.notInstall == "function")
								{
									setting.Methods.notInstall();
								}
							}
						}
					},
					installDrive:function(){
						$("#iss").css("width","100%");
						$("#iss").attr("state","error");
						$("#issM").text("${common_issOnline_notInstalled}".replace("%s",ISSOnline));
						$("#issM").append("<a href='base/middleware/ISSOnline.exe'> ${common_issOnline_driverDownload}</a>");
						if(typeof myDevice == "object")
						{
							closeMessage();
							//驱动未安装
							setting.Methods.downloadDrive();
						}
						else
						{
							if(typeof setting.Methods.notInstall == "function")
							{
								setting.Methods.notInstall();
							}
						}
					}
				});
				var version = new ISSVersion();
				version.accessDevice();
			}
		}
		//下载驱动默认方法
		if(typeof setting.Methods.downloadDrive != "function")
		{
			setting.Methods.downloadDrive = function()
			{
				$("#ipc_preview").hide();
				messageBox({messageType: "confirm", text: "${common_issOnline_driverPrompt}",
					callback: function(result){
						if(result)
						{
							window.location.href = "base/middleware/ISSOnline.exe";
						}
						closeMessage();
						$("#ipc_preview").show();
				}});
			}
		}
	}
	/**
	 * 设备
	 */
	var Device = function()
	{
		message : ""
	};
	var buttonNames = {"Cert":"","Scanner":"","Barcode":""};
	if(typeof setting.Cert == "object")
	{
		buttonNames.Cert = setting.Cert.select;
		//身份证阅读器
		var Cert = function(){};
		ZK.extend(Cert, Device, {
			//提示信息
			message : "${common_issOnline_cardPrompt}",
			//服务url
			url : "/ScanReadIdCardInfo?OP-DEV=1&CMD-URL=4&common=1" + "&random=" + getRandomNum(),
			//接口返回值
			interfaceResult : 
			{
				0:{mean:"成功",message:"${common_issOnline_cardSuccess}",type:"success"},
				1:{mean:"端口打开失败",message:"${common_issOnline_idcardReaderNotFound}",type:"warning"},
				2:{mean:"数据传输超时",message:"${common_issOnline_idcardReaderNotFound}",type:"error"},
				10:{mean:"没有找到卡",message:"${common_issOnline_idcardNotFound}",type:"warning"},
				11:{mean:"读卡操作失败",message:"${common_issOnline_idcardReaderNotFound}",type:"error"},
				20:{mean:"自检失败",message:"${common_issOnline_readIDCardError}",type:"error"},
				30:{mean:"其他错误",message:"${common_issOnline_readIDCardError}",type:"error"},
				40:{mean:"相片解码失败",message:"${common_issOnline_readIDCardError}",type:"error"},
				100:{mean:"超时",message:"${common_issOnline_idcardNotFound}",type:"warning"},
				200:{mean:"GetBase64PhotoData",message:"${common_issOnline_readIDCardError}",type:"error"}
			},
			dealDeviceData:setting.Cert.callBack
		});
	}
	
	if(typeof setting.Barcode == "object")
	{
		buttonNames.Barcode = setting.Barcode.select;
		//条码红外线扫描仪
		var BarcodeScanner = function(){};
		ZK.extend(BarcodeScanner, Device, {
			message : "${common_issOnline_imageCodePrompt}",
			url : "/ScanReadIdCardInfo?OP-DEV=3&CMD-URL=1&COMNAME=COM1" + "&random=" + getRandomNum(),
			interfaceResult : 
			{
				"0":{mean:"OK",message:"${common_issOnline_scanSuccess}",type:"success"},
				"-10":{mean:"OPENCOMFAIL",message:"${common_issOnline_infraredReaderNotFound}",type:"warning"},
				"-11":{mean:"COMFLUSH",message:"${common_issOnline_scanError}",type:"error"},
				"-12":{mean:"TIMEOUT",message:"${common_issOnline_infraredReadTimeOut}",type:"warning"},
				"-15":{mean:"COMWRITE",message:"${common_issOnline_scanError}",type:"error"}
			},
			dealDeviceData:setting.Barcode.callBack
		});
	}
	
	if(typeof setting.Scanner == "object")
	{
		buttonNames.Scanner = setting.Scanner.select;
		//证件扫描仪 目前只支持身体证，type为2，其它证件需要定制
		var CertificateType = 2;
		var Scanner = function(){};
		ZK.extend(Scanner, Device, {
			message : "${common_issOnline_certPrompt}",
			url : "/ScanReadIdCardInfo?OP-DEV=2&CMD-URL=1&CertificateType=" + CertificateType + "&random=" + getRandomNum(),
			interfaceResult : 
			{
				0:{mean:"识别成功",message:"${common_issOnline_scanSuccess}",type:"success"},
				1:{mean:"扫描发生错误",message:"${common_issOnline_scanError}",type:"error"},
				2:{mean:"用户输入的文件路径名不符合要求",message:"${common_issOnline_scanError}",type:"error"},
				3:{mean:"识别失败",message:"${common_issOnline_scanError}",type:"warning"},
				4:{mean:"目前扫描仪没有在线",message:"${common_issOnline_scannerNotFound}",type:"warning"},
				10:{mean:"扫描类型错误",message:"${common_issOnline_scanError}",type:"error"}
			},
			dealDeviceData:setting.Scanner.callBack
		});
	}
	
	//工厂
	var DeviceFactory = 
	{
		createDevice : function(button){
			var device;
			switch(button){
				case "Cert":
					device = new Cert();
					break;
				case "Scanner":
					device = new Scanner();
					break;
				case "Barcode":
				default:
					device = new BarcodeScanner();
					break;
			}
			return device;
		}
	}
	//设备工厂
	Device.prototype.createDevice = function(button)
	{
		return DeviceFactory.createDevice(button);
	};
	//信息显示
	Device.prototype.setMessage = function()
	{
		setting.Methods.showMessage(msgType.loading,this.message);
	};
	
	//处理硬件返回值
	Device.prototype.dealDeviceInfo = function(result)
	{
		if(result === null||result.ret===null)
		{
			setting.Methods.showMessage("error","${common_deviceConnError}");
			return;
		}
		//信息提示
		var info = this.interfaceResult[result.ret];
		if(info == undefined)
		{
			setting.Methods.showMessage("error","${common_deviceConnError}");
			return;
		}
		if(info.notShow == undefined)
		{
			setting.Methods.showMessage(info.type,info.message);
		}
		//处理数据
		if("success" === info.type)
		{
			this.dealDeviceData(result);
		}
	}
	
	//html5使用ajax方式访问服务
	Device.prototype.ajaxAccess = function(url)
	{
		var result = null;
		var parent = this;
		$.ajax({
			type : "GET",
			url : issOnlineUrl + url,
			dataType : "text",
			async:true,
			timeout:10000,
			success : function(data)
			{
				data = data.replace(/\\/g,"/");
				result = JSON.parse(data);
				parent.dealDeviceInfo(result);
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) 
			{
				if(typeof parent.installDrive == "function")
				{
					parent.installDrive();
				}
				else
				{
					setting.Methods.showMessage("error","${common_deviceConnError}");
				}
			},
			
		});
	}
	
	//IE8、IE9访问服务
	Device.prototype.xDomainAccess = function(url)
	{
		var parent = this;
		var xDomainRequest = new XDomainRequest();
		if (xDomainRequest) 
		{ 
			xDomainRequest.timeout=10000;
			xDomainRequest.open('GET', issOnlineUrl + url);
			xDomainRequest.onload = function()
			{
				var resultData = xDomainRequest.responseText;
				resultData = resultData.replace(/\\/g,"/");
				var obj = JSON.parse(resultData);
				parent.dealDeviceInfo(obj);
			};
			xDomainRequest.onerror = function()
			{
				//用完后，将对象置为空
				xDomainRequest = null;
				setting.Methods.showMessage("error","${common_deviceConnError}");
			};
			xDomainRequest.ontimeout = function()
			{
				//用完后，将对象置为空
				xDomainRequest = null;
				setting.Methods.showMessage("error","${common_deviceConnError}");
			};
			xDomainRequest.send();
		}
	}
	
	//驱动检测
	Device.prototype.installDrive = function()
	{
		
	}
	//设备通信,得到硬件返回的数据
	Device.prototype.accessDevice = function()
	{
		$("#iss").css("width","25%");
		if(browserFlag == "html5")
		{
			this.ajaxAccess(this.url);
		}
		else if(browserFlag == "simple")
		{
			this.xDomainAccess(this.url);
		}
		else
		{
			if(window.console)
			{
				console.error("browserFlag is missing");
			}
		}
	};
	
	$.each(buttonNames, function(key, value){
		$(document).off("click",value);
		$(document).on("click",value,function(e){
			$(value).blur();
			//创建设备
			var device = new Device();
			var myDevice = device.createDevice(key);
			//显示提示信息
			myDevice.setMessage();
			setting.Methods.checkWebServer(myDevice);
		});
	}); 

	setting.Methods.checkWebServer();
//	debugger;
//	var device = new Device();
//	var myDevice = device.createDevice("BarcodeScanner");
//	myDevice.dealDeviceData(55);
//	var info = myDevice.interfaceResult[-10];
//	alert(info);
}