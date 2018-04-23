layui.use('form', function(){
	var form = layui.form,layer = layui.layer;
	
    //监听提交
    form.on('submit(save)', function(data){
    	var json = JSON.stringify(data.field);
    	//提交
    	$.ajax({
			url:getBaseUrl()+'/role/insertRole',
			type:'POST',
  	  		dataType:"json",
	  	  	contentType:"application/json",               
	        data:json, 
			success:function(data){
				if(data.success){
					parent.layer.closeAll();
					parent.layer.msg("保存成功!", { time : 4000 });
					parent.location.reload();
				}else{
					layer.msg("保存失败!"+data.message, { time : 2000 });
				}
			},
			error:function(){
				layer.msg("保存失败,请检查网络情况!", { time : 2000 });
			}
		});
    	return false;
    });
});