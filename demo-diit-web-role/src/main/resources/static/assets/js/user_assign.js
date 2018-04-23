layui.use('table', function(){
	var table = layui.table,laytpl = layui.laytpl;
	var $ = layui.$, active = {
	    ok: function(){ //获取选中数据
	    	
		}
	    ,cancel: function(){
	    	parent.layer.closeAll();
		}
	};
  
    $('.tlUser .layui-btn').on('click', function(){
	  var type = $(this).data('type');
	  active[type] ? active[type].call(this) : '';
    });
});