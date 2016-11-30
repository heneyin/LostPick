
$(function(){
	//register form validate

	 var success = false;
	 const passWord = '通过';
	 
	 function JudgeUserId()
	 {
		 //alert('JudgeUserId');
		 var userId = $('input[name=userId]').val();
		 //不能为空
		 if(userId == ""){
			 return;
		 }
		 //控制长度
		 if(userId.length < 4 || userId.length > 12) {
			 return;
		 }
	     //alert('userId:' +userId);
		 $.ajax({
	     type:"GET",
	     //本地
	     url:"http://localhost:8080/lost-pick-spring/user/checkRepeat/"+ userId ,
	     //Ubuntu
	     //url:"http://121.42.12.16/lost-pick-spring/user/checkRepeat/"+ userId ,
	     dataType:"html",
	     data:"",
	     beforeSend:function(XMLHttpRequest)
	         {
	             $("#userIdCheckResult").text("正在查询..");
	             //alert('lala1111');
	             //Pause(this,100000);
	         },
	     success:function(msg)
	         {   
	     		var result;
	     		//alert('lala');
	     		if(msg == 'true'){
	     			result = "通过";
	     			$("#userIdCheckResult").html(result);
		             $("#userIdCheckResult").addClass('pass-message').removeClass('error-message');
	     		}else {
	     			result = "该用户名已被占用！";
	     			$("#userIdCheckResult").html(result);
		             $("#userIdCheckResult").removeClass('pass-message').addClass('error-message');
	     		}
	         },
	    complete:function(XMLHttpRequest,textStatus)
	         {
	             //隐藏正在查询图片
	         },
	   error:function()
	        {
	             //错误处理
	        }
	     });
	 }

	 
	 $('input[name=password0]').blur(function(){
		 
		 var password = $('input[name=password]').val();
		 var password0 = $('input[name=password0]').val();
		 if(password != password0) {
			 $('#password0CheckResult').html('密码两次输入不一致！').removeClass('pass-message').addClass('error-message');;
			 success = false;
		 }else {
			 $('#password0CheckResult').html(passWord).removeClass('error-message').addClass('pass-message');;
			 success = true;
		 }
	 })
	 
	 $('input[name=password0]').keyup(function(){
		 
		 var password = $('input[name=password]').val();
		 var password0 = $('input[name=password0]').val();
		 if(password != password0) {
			 $('#password0CheckResult').html('密码两次输入不一致！').removeClass('pass-message').addClass('error-message');;
			 success = false;
		 }else {
			 $('#password0CheckResult').html(passWord).removeClass('error-message').addClass('pass-message');;
			 success = true;
		 }
	 })
	 
	 $('input[name=password]').blur(function(){

		 var password = $('input[name=password]').val();
		 var password0 = $('input[name=password0]').val();
		 if(password != password0) {
			 $('#password0CheckResult').html('密码两次输入不一致！').removeClass('pass-message').addClass('error-message');;
			 success = false;
		 }else {
			 $('#password0CheckResult').html(password).removeClass('error-message').addClass('pass-message');;
			 success = true;
		 }
	 });
	 
	 $('input[name=password]').keyup(function(){

		 var password = $('input[name=password]').val();
		 var password0 = $('input[name=password0]').val();
		 if(password != password0) {
			 $('#password0CheckResult').html('密码两次输入不一致！').removeClass('pass-message').addClass('error-message');;
			 success = false;
		 }else {
			 $('#password0CheckResult').html(password).removeClass('error-message').addClass('pass-message');;
			 success = true;
		 }
	 });

	function checkInput(inputname,regex,checkResultId,errorMessage){
		$('input[name='+inputname+']').blur(function () {
			var value = $(this).val();
			var result =  regex.test(value);
			if(!result){
				success = false;
				$('#'+checkResultId).html(errorMessage).removeClass('pass-message').addClass('error-message');
			}else{
				success = true;
				$('#'+checkResultId).html(passWord).removeClass('error-message').addClass('pass-message');
			}
			
			if(inputname == 'userId'){   //ajax检查用户是否可用
				JudgeUserId();
			}
		});
		
		$('input[name='+inputname+']').keyup (function () {
			var value = $(this).val();
			var result =  regex.test(value);
			if(!result){
				success = false;
				$('#'+checkResultId).html(errorMessage).removeClass('pass-message').addClass('error-message');
			}else{
				success = true;
				$('#'+checkResultId).html(passWord).removeClass('error-message').addClass('pass-message');
			}
		});
		
		$('input[name='+inputname+']').click (function () {
			var value = $(this).val();
			var result =  regex.test(value);
			if(!result){
				success = false;
				$('#'+checkResultId).html(errorMessage).removeClass('pass-message').addClass('error-message');
			}else{
				success = true;
				$('#'+checkResultId).html(passWord).removeClass('error-message').addClass('pass-message');
			}
		});
	};
	
	function checkSelect(selectName,regex,checkResultId,errorMessage){
		$('select[name='+selectName+']').blur(function () {
			var value = $(this).val();
			var result =  regex.test(value);
			if(!result){
				success = false;
				$('#'+checkResultId).html(errorMessage).removeClass('pass-message').addClass('error-message');
			}else{
				success = true;
				$('#'+checkResultId).html(passWord).removeClass('error-message').addClass('pass-message');
			}
		});
		
		$('select[name='+selectName+']').keyup (function () {
			var value = $(this).val();
			var result =  regex.test(value);
			if(!result){
				success = false;
				$('#'+checkResultId).html(errorMessage).removeClass('pass-message').addClass('error-message');
			}else{
				success = true;
				$('#'+checkResultId).html(passWord).removeClass('error-message').addClass('pass-message');
			}
		});
		
		$('select[name='+selectName+']').click (function () {
			var value = $(this).val();
			var result =  regex.test(value);
			if(!result){
				success = false;
				$('#'+checkResultId).html(errorMessage).removeClass('pass-message').addClass('error-message');
			}else{
				success = true;
				$('#'+checkResultId).html(passWord).removeClass('error-message').addClass('pass-message');
			}
		});
	}
	
	function checkTextArea(inputname,regex,checkResultId,errorMessage){
		$('textarea[name='+inputname+']').blur(function () {
			var value = $(this).val();
			var result =  regex.test(value);
			if(!result){
				success = false;
				$('#'+checkResultId).html(errorMessage).removeClass('pass-message').addClass('error-message');
			}else{
				success = true;
				$('#'+checkResultId).html(passWord).removeClass('error-message').addClass('pass-message');
			}
		});
		
		$('textarea[name='+inputname+']').keyup (function () {
			var value = $(this).val();
			var result =  regex.test(value);
			if(!result){
				success = false;
				$('#'+checkResultId).html(errorMessage).removeClass('pass-message').addClass('error-message');
			}else{
				success = true;
				$('#'+checkResultId).html(passWord).removeClass('error-message').addClass('pass-message');
			}
		});
		
		$('textarea[name='+inputname+']').click (function () {
			var value = $(this).val();
			var result =  regex.test(value);
			if(!result){
				success = false;
				$('#'+checkResultId).html(errorMessage).removeClass('pass-message').addClass('error-message');
			}else{
				success = true;
				$('#'+checkResultId).html(passWord).removeClass('error-message').addClass('pass-message');
			}
		});
	};
	
	function checkTextAreaResult(inputname,regex,checkResultId,errorMessage){
		var value = $('textarea[name='+inputname+']').val();
		var result =  regex.test(value);
		if(!result){
			$('#'+checkResultId).html(errorMessage).removeClass('pass-message').addClass('error-message');
			//return false;
		}else{
			$('#'+checkResultId).html(passWord).removeClass('error-message').addClass('pass-message');
			//return true;
		}
	}
	
	function checkInputResult(inputname,regex,checkResultId,errorMessage){
		var value = $('input[name='+inputname+']').val();
		var result =  regex.test(value);
		if(!result){
			$('#'+checkResultId).html(errorMessage).removeClass('pass-message').addClass('error-message');
			//return false;
		}else{
			$('#'+checkResultId).html(passWord).removeClass('error-message').addClass('pass-message');
			//return true;
		}
	}
	
	function checkSelectResult(inputname,regex,checkResultId,errorMessage){
		var value = $('select[name='+inputname+']').val();
		var result =  regex.test(value);
		if(!result){
			$('#'+checkResultId).html(errorMessage).removeClass('pass-message').addClass('error-message');
			//return false;
		}else{
			$('#'+checkResultId).html(passWord).removeClass('error-message').addClass('pass-message');
			//return true;
		}
	}
	
	
	
	//*****注册
	const checkUserIdReg = /^[A-Za-z0-9]{4,12}$/;   //只能为数字与字母
	const checkPasswordReg = /^[a-zA-Z0-9_!@^%$&*~#+]{6,16}$/; //密码长度为6到16,允许字符!@^%$&*~#_+
	const checkuserNameReg = /^[A-Za-z\u4E00-\u9FA50-9]{4,15}$/;
	const checkSelectReg = /^(((?!ps).)*)$/;
	const checkPhonecodeReg = /^[0-9]{11,11}$/;
	const checklopInfoReg = /^\S{1,50}$/;
	const checkDscriptionReg = /^\S{1,300}$/

	checkInput('userId',checkUserIdReg,'userIdCheckResult','只能是长度为4到12的数字与字母的组合！');
	checkInput('password',checkPasswordReg,'passwordCheckResult','密码长度为6到16,允许字符!@^%$&*~#_+');
	checkInput('userName',checkuserNameReg,'userNameCheckResult','用户名只能长度为4到12的数字，字母，汉字的组合！');
	checkSelect('academy',checkSelectReg,"academyCheckResult","还未选择！");
	checkInput('phonecode',checkPhonecodeReg,"phonecodeCheckResult","请输入11位手机号码！");


	//************添加启事
	checkSelect('goodsType',checkSelectReg,'goodsTypeCheckResult','请选择');
	checkInput('exactTime',checklopInfoReg,'exactTimeCheckResult','字数限制为1到50');
	checkInput('lopPlace',checklopInfoReg,'lopPlaceCheckResult','字数限制为1到50');
	checkTextArea('description',checkDscriptionReg,'descriptionCheckResult','字数限制为1到300');

	$('#registerForm').submit(function(){
		
		//checkInputResult('userId',checkUserIdReg,'userIdCheckResult','只能是长度为4到12的数字与字母的组合！');
		checkInputResult('password',checkPasswordReg,'passwordCheckResult','密码长度为6到16,允许字符!@^%$&*~#_+');
		checkInputResult('userName',checkuserNameReg,'userNameCheckResult','用户名只能长度为2到12的数字，字母，汉字的组合！');
		checkSelectResult('academy',checkSelectReg,"academyCheckResult","还未选择！");
		checkInputResult('phonecode',checkPhonecodeReg,"phonecodeCheckResult","请输入11位手机号码！");
		//JudgeUserId();
		if($('#userIdCheckResult').html() != passWord || $('#passwordCheckResult').html() != passWord 
				|| $('#userNameCheckResult').html() != passWord || $('#academyCheckResult').html() != passWord
				|| $('#phonecodeCheckResult').html() != passWord || $('#password0CheckResult').html() != passWord 
				){
			$('.submit-message').html('请认真填写').addClass('error-message');
			return false;
		}
		return true;
	});
	
	$('#updateUserInfo').submit(function(){
		
		//checkInputResult('userId',checkUserIdReg,'userIdCheckResult','只能是长度为4到12的数字与字母的组合！');
		//checkInputResult('password',checkPasswordReg,'passwordCheckResult','密码长度为6到16,允许字符!@^%$&*~#_+');
		checkInputResult('userName',checkuserNameReg,'userNameCheckResult','用户名只能长度为2到12的数字，字母，汉字的组合！');
		checkSelectResult('academy',checkSelectReg,"academyCheckResult","还未选择！");
		checkInputResult('phonecode',checkPhonecodeReg,"phonecodeCheckResult","请输入11位手机号码！");
		//JudgeUserId();
		if( $('#userNameCheckResult').html() != passWord || $('#academyCheckResult').html() != passWord
				|| $('#phonecodeCheckResult').html() != passWord ){
			$('.submit-message').html('请认真填写').addClass('error-message');
			return false;
		}
		return true;
	});
	
	$('#addNoticeForm').submit(function(){
		
		checkSelectResult('goodsType',checkSelectReg,'goodsTypeCheckResult','请选择');
		checkInputResult('exactTime',checklopInfoReg,'exactTimeCheckResult','字数限制为1到50');
		checkInputResult('lopPlace',checklopInfoReg,'lopPlaceCheckResult','字数限制为1到50');
		checkTextAreaResult('description',checkDscriptionReg,'descriptionCheckResult','字数限制为1到300');
		
		
		if($('#goodsTypeCheckResult').html() != passWord || $('#exactTimeCheckResult').html() != passWord
			|| $('#lopPlaceCheckResult').html() != passWord || $('#descriptionCheckResult').html() != passWord){
			 	
			$('.submit-message').html('请认真填写').addClass('error-message');
			return false;
		}
		return true;
	})
	
})

function reloadCode(){  
	document.getElementById("codePicture").src = 
		document.getElementById("codePicture").src 
			+ "?nocache="+new Date().getTime();  
}  
function eloadCode() {
        var imgSrc = $("#codePicture");
        var src = imgSrc.attr("src");
        imgSrc.attr("src", chgUrl(src));
}
    //时间戳   
    //为了使每次生成图片不一致，即不让浏览器读缓存，所以需要加上时间戳   
function chgUrl(url) {
	var timestamp = (new Date()).valueOf();
	url = url.substring(0, 17);
    if ((url.indexOf("&") >= 0)) {
        url = url + "×tamp=" + timestamp;
    } else {
        url = url + "?timestamp=" + timestamp;
    }
    return url;
}


