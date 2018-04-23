$(function () {
	$("dd a").click(function (e) {
		var txt = $(this).html();
		if(txt == "用户管理"){
			$("#mainFrame").attr("src","http://localhost:8080/api-user/index");
		}else if(txt == "角色管理"){
			$("#mainFrame").attr("src","http://localhost:8080/api-role/index");
		}else if(txt == "服务注册"){
			$("#mainFrame").attr("src","http://localhost:8501");
		}else if(txt == "服务链路"){
			$("#mainFrame").attr("src","http://localhost:8504");
		}else if(txt == "服务监控"){
			$("#mainFrame").attr("src","http://localhost:8506/hystrix/monitor?stream=http%3A%2F%2Flocalhost%3A8506%2Fturbine.stream&title=portal");
		}else if(txt == "服务管理"){
			$("#mainFrame").attr("src","http://localhost:8505");
		}
	});
});
layui.use('element', function(){
  var element = layui.element;
});