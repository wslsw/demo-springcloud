if (window != top) {
	top.location.href = location.href;
}
//绑定回车事件
$(document).keyup(function(event) {
	if (event.keyCode == 13) {
		$(".login").trigger("click");
	}
});
$(function () {
	//loginFirst();

	$(".login").click(function (e) {
		var index = layer.load(1); 
        $.ajax({
            type : "POST",
            url : getBaseUrl()+"/user/login",
            data : {
                "account" : $("#username").val(),
                "password" : $("#password").val()
            },
            dataType : "text",
            success : function(result) {
            	layer.close(index);   
            	if (result) {
                    var obj = eval(eval(result));
                    if (obj.success) {
                        window.location = obj.success;
                    }else{//第一遍因为延迟失败
                    	layer.msg('登录失败！'+obj.msg);
                    }
            	}else{
            		layer.msg('登录失败！请检查网络状况!');
            	}
            },
            error:function(){
            	layer.close(index);   
            	layer.msg('登录失败！请检查网络状况!');
            }
        });
    });
	
	var hei = $(window).height();
	var wid = $(window).width();
	var kuan = $('.left').width();
	$('.main').ready(function() {
		$('.main').css({"height": hei});
		$('.main_top').css({"width": wid - kuan});
		$('.main').css({"width": wid - kuan});
		$('.main-all').css({"width": wid - kuan-15});
		$('.main-all').css({"height": hei});
		$('.list').css({"height": hei-73});
	});
	$.inputStyle();
});
(function($) {
	$.extend({
		inputStyle: function() {
			function check(el, cl) {
				$(el).each(function() {
					$(this).parent('i').removeClass(cl);
					var checked = $(this).prop('checked');
					if(checked) {
						$(this).parent('i').addClass(cl);
					}
				})
			}
			
			$('input[type="checkbox"]').on('click', function() {
				check('input[type="checkbox"]', 'checkbox_bg_check');
			})
		}
	})
})(jQuery);