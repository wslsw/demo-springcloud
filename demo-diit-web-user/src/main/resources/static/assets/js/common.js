/* 
	* 获得时间差,时间格式为 年-月-日 小时:分钟:秒 或者 年/月/日 小时：分钟：秒 
	* 其中，年月日为全格式，例如 ： 2010-10-12 01:00:00 
	* 返回精度为：秒，分，小时，天
	*/
	function getDateDiff(startTime, endTime, diffType) {
	    //将xxxx-xx-xx的时间格式，转换为 xxxx/xx/xx的格式 
	    startTime = startTime.replace(/\-/g, "/");
	    endTime = endTime.replace(/\-/g, "/");

	    //将计算间隔类性字符转换为小写
	    diffType = diffType.toLowerCase();
	    var sTime = new Date(startTime);      //开始时间
	    var eTime = new Date(endTime);  //结束时间
	    //作为除数的数字
	    var divNum = 1;
	    switch (diffType) {
	        case "second":
	            divNum = 1000;
	            break;
	        case "minute":
	            divNum = 1000 * 60;
	            break;
	        case "hour":
	            divNum = 1000 * 3600;
	            break;
	        case "day":
	            divNum = 1000 * 3600 * 24;
	            break;
	        default:
	            break;
	    }
	    return Math.abs(parseInt((eTime.getTime() - sTime.getTime()) / parseInt(divNum)));
	}
function datetimeFormat(longTypeDate){  
    var date = new Date();  
    date.setTime(longTypeDate);
    var datetimeType = date.getFullYear()+"-"+getMonth(date)+"-"+getDay(date)+"&nbsp;"+getHours(date)+":"+getMinutes(date)+":"+getSeconds(date);
    return datetimeType;
}
function dateFormat(longTypeDate){  
    var date = new Date();  
    date.setTime(longTypeDate);
    var datetimeType = date.getFullYear()+"-"+getMonth(date)+"-"+getDay(date);
    return datetimeType;
}
function getToday(){  
    var date = new Date();  
    var datetimeType = date.getFullYear()+"-"+getMonth(date)+"-"+getDay(date);
    return datetimeType;
}
//返回 01-12 的月份值   
function getMonth(date){  
    var month = "";  
    month = date.getMonth() + 1;
    if(month<10){  
        month = "0" + month;  
    }  
    return month;  
}  
//返回01-30的日期  
function getDay(date){  
    var day = "";  
    day = date.getDate();  
    if(day<10){  
        day = "0" + day;  
    }  
    return day;  
}
//返回小时
function getHours(date){
    var hours = "";
    hours = date.getHours();
    if(hours<10){  
        hours = "0" + hours;  
    }  
    return hours;  
}
//返回分
function getMinutes(date){
    var minute = "";
    minute = date.getMinutes();
    if(minute<10){  
        minute = "0" + minute;  
    }  
    return minute;  
}
//返回秒
function getSeconds(date){
    var second = "";
    second = date.getSeconds();
    if(second<10){  
        second = "0" + second;  
    }  
    return second;  
}
function onComboValidation(e) {
	var items = this.findItems(e.value);
	if (!items || items.length == 0) {
		e.isValid = false;
		e.errorText = "输入值不在下拉数据中";
	}
}

function getErrorTexts(errors){
	var result="";
	for(x in errors){
		var error = errors[x].requiredErrorText;
		if(!isNullOrEmpty(error)){
			result = result + error + "\n";
		}
	}
	return result;
}

function isNullOrEmpty(v){
	if(v == null || v == "" || v== undefined){
		return true;
	}else{
		return false;
	}
}

/**
 * 获取服务器IP及端口 http://localhost:8080
 * 
 * @returns
 */

function getLocalhostPath(){
	var curWwwPath = window.document.location.href;
	var pathName = window.document.location.pathname;
	var pos = curWwwPath.indexOf(pathName);
	var localhostPath = curWwwPath.substring(0, pos);
	return localhostPath;
}

function getDisplayName(){
	var pathName = window.document.location.pathname;
	var pos = pathName.indexOf('/', 1);
	var displayName = pathName.substring(0, pos);
	return displayName;
}

function getBaseUrl(){
	return getLocalhostPath() + getDisplayName();
}

function getBaseRestUrl(){
	return getLocalhostPath() + "/tddc-service/";
}

function getNowFormatDate() {
    var date = new Date();
    var seperator1 = "-";
    var seperator2 = ":";
    var month = date.getMonth() + 1;
    var strDate = date.getDate();
    if (month >= 1 && month <= 9) {
        month = "0" + month;
    }
    if (strDate >= 0 && strDate <= 9) {
        strDate = "0" + strDate;
    }
    var currentdate = date.getFullYear() + seperator1 + month + seperator1 + strDate
            + "T" + date.getHours() + seperator2 + date.getMinutes()
            + seperator2 + date.getSeconds();
    return currentdate;
}

function roundweek() {
	getRecentDaysCondition(6);
	queryMessage();
}

function getRecentDaysCondition(recentDays) {
	var date = new Date();
	var seperator1 = "-";
	date.setDate(date.getDate() - recentDays);
	strDate = date.getDate();
	month = date.getMonth() + 1;
	year = date.getFullYear();
	if (month >= 1 && month <= 9) {
		month = "0" + month;
	}
	if (strDate >= 0 && strDate <= 9) {
		strDate = "0" + strDate;
	}
	var qssj = year + seperator1 + month + seperator1 + strDate;
	return qssj;
}

function disablebyId(id){
	nui.get(id).addCls("asLabel");
	    nui.get(id).setReadOnly(true);
  	nui.get(id).disable();
}

function enablebyId(id){
	nui.get(id).removeCls("asLabel");
	    nui.get(id).setReadOnly(false);
  	nui.get(id).enable();
}

//将Tue Mar 15 00:00:00 UTC+0800 2011 转换为  2011-3-15 00:00:00 格式
function Todate(num) { 
	if(num==""||num==null||num==undefined){
		return "";
	}
    num = num + "";
    var date = "";
    var month = new Array();
    month["Jan"] = 1; month["Feb"] = 2; month["Mar"] = 3; month["Apr"] = 4; month["May"] = 5; month["Jan"] = 6;
    month["Jul"] = 7; month["Aug"] = 8; month["Sep"] = 9; month["Oct"] = 10; month["Nov"] = 11; month["Dec"] = 12;
    var week = new Array();
    week["Mon"] = "一"; week["Tue"] = "二"; week["Wed"] = "三"; week["Thu"] = "四"; week["Fri"] = "五"; week["Sat"] = "六"; week["Sun"] = "日";
    str = num.split(" ");
    date = str[3] + "-";
    date = date + month[str[1]] + "-" + str[2]+" "+str[4];
    return date;
}

//提示信息
function showTips(title,commont){
	mini.showTips({
        content: "<b>" + title + "</b> <br/>" + commont,
        state: 'success',
        x: 'center',
        y: 'center',
        timeout: 3000
	});
}

/*
 * 设置只读 form:form对象
 */
function setreadOnly(form) {
	// var form = new nui.Form("#" + formName);
	var fields = form.getFields();
	for (var i = 0, l = fields.length; i < l; i++) {
		var c = fields[i];
		if (c.setReadOnly&&c.el.className.indexOf("mini-hidden")==-1) {
			c.setReadOnly(true);// 只读
			c.addCls("asLabel");
		}
		if (c.setIsValid)
			c.setIsValid(true); // 去除错误提示
	}
}

/*
 * 功能授权，调用restful服务  add By Chen
 */
function permission(userId, code, url) {
    $.ajax({
		  method: "GET",
		  url: url,
		  dataType: "json",
		  data: { userId: userId, code:code},
		  success: function (data) {
		  	if (data != null && typeof data === 'object') {
	  			 data.forEach(function(item) {
	  				 if (item.type == 3 && item.operation) {
	  					  var arr = item.operation.split(";");
	  					  arr.forEach(function(data){
	  						  if(data == "0001") {
	  							  nui.get(item.code).hide();
	  						  }
	  						  if(data == "0002"){
	  							  disablebyId(item.code);
	  							  $('#'+item.code).removeAttr("id").addClass("disable-bg");
	  						  }
	  					  })
	  				 }
	  				 
	  			 })
	  		 }
		  	$(".cloak").css("visibility","visible");
		  }
	})
}

/*
 * 获取form下所有有效input值
 */
function getFormJson(id) {
	var arr = $("#"+id+" input[class='textbox-value']");
	var json = {};
	for(var i=0; i<arr.length; i++) {  
		var input = arr[i];
		var name = $(input).attr("name");
		if(name){
			json[name] = $(input).val();
		}
	}
	arr = $("#"+id+" textarea");
	for(var i=0; i<arr.length; i++) {  
		var input = arr[i];
		var name = $(input).attr("name");
		if(name){
			json[name] = $(input).val();
		}
	}
	return json;
}

