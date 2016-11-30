$(function(){
	var success = false;
	var passWord =""
	function checkInput(inputname,regex,checkResultId,errorMessage){
			$('input[name='+inputname+']').blur(function () {
				var value = $(this).val();
				var result =  regex.test(value);
				if(!result){
					$('#'+checkResultId).html(errorMessage).removeClass('pass-message').addClass('error-message');
				}else{
					$('#'+checkResultId).html(passWord).removeClass('error-message').addClass('pass-message');
				}
			});
			
			$('input[name='+inputname+']').keyup (function () {
				var value = $(this).val();
				var result =  regex.test(value);
				if(!result){
					$('#'+checkResultId).html(errorMessage).removeClass('pass-message').addClass('error-message');
					success = false;
				}else{
					$('#'+checkResultId).html(passWord).removeClass('error-message').addClass('pass-message');
					success = true;
				}
			});
			
			$('input[name='+inputname+']').click (function () {
				var value = $(this).val();
				var result =  regex.test(value);
				if(!result){
					$('#'+checkResultId).html(errorMessage).removeClass('pass-message').addClass('error-message');
					success = false;
				}else{
					$('#'+checkResultId).html(passWord).removeClass('error-message').addClass('pass-message');
					success = true;
				}
			});
		};
	
		const checkUserIdReg = /^[A-Za-z0-9]{4,12}$/;   //只能为数字与字母
		checkInput('userId',checkUserIdReg,'userIdCheckResult','用户名是长度为4到12的数字与字母的组合！');
	
	/*		$('input:submit').click(function(){
			 //alert(success);
			 if( success == false ){
				 $("form").onSubmit(false);
			 }else{
				 $("form").onSubmit(true);
			 }
		 })*/
		$('form').submit(function(){
			return success;
		});
		
	
})





