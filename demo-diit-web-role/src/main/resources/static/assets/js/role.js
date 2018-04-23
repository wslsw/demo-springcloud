layui.use('table', function(){
	var table = layui.table,laytpl = layui.laytpl;
	var $ = layui.$, active = {
	    add: function(){ //获取选中数据
	    	layer.open({
				type : 2,
				title : '添加角色',
				shade : 0.1,
				maxmin : false,
				area : [ '720px', '350px' ],
				content : getBaseUrl()+'/role_add'
			});
		}
	    ,edit: function(){
		  var checkStatus = table.checkStatus('tbRole'),data = checkStatus.data;
		  if(data.length == 0){
			  layer.msg('请选择要修改的角色!');
		  }else if(data.length > 1){
			  layer.msg('请选择一个角色!');
		  }else{
			  layer.open({
					type : 2,
					title : '修改角色',
					shade : 0.1,
					maxmin : false,
					area : [ '720px', '350px' ],
					content : getBaseUrl()+'/role_edit?id='+data[0].id
				});
		  }
		}
		,del: function(){
		  var checkStatus = table.checkStatus('tbRole'),data = checkStatus.data;
		  if(data.length == 0){
			  layer.msg('请选择要删除的角色!');
		  }else{
			  layer.confirm('确认删除已选择的角色吗?', {
					btn : [ '确定', '取消' ]
			  // 按钮
			  }, function(index) {
					var json=[];
				    for(var i=0;i<data.length;i++){
				    	json.push(data[i].id);
				    }
				    //提交
				    $.ajax({
							url:getBaseUrl()+'/role/deleteRole',
							type:'POST',
				  	  		dataType:"json",
					        data:{ids:json.toString()}, 
							success:function(data){
								if(data.success){
									layer.msg("删除成功!", { time : 2000 });
									location.reload();
								}else{
									layer.msg("删除失败!"+data.message, { time : 2000 });
								}
							},
							error:function(){
								layer.msg("删除失败,请检查网络情况!", { time : 2000 });
							}
					  });
			      layer.close(index);
			  }, function(index) {
				  layer.close(index);
			  });
		  }
		}
		,assign: function(){
			var checkStatus = table.checkStatus('tbRole'),data = checkStatus.data;
			  if(data.length == 0){
				  layer.msg('请选择要分配用户的角色!');
			  }else if(data.length > 1){
				  layer.msg('请选择一个角色!');
			  }else{
				  layer.open({
						type : 2,
						title : '分配用户',
						shade : 0.1,
						maxmin : false,
						area : [ '720px', '350px' ],
						content : getBaseUrl()+'/user_assign?id='+data[0].id
					});
			  }
		}
	};
  
    $('.tlRole .layui-btn').on('click', function(){
	  var type = $(this).data('type');
	  active[type] ? active[type].call(this) : '';
    });
});