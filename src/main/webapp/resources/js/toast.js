
		$(function(){
			
			var value = $('#toast-value').val();
			if(value != ''){
				$('.toast').html(value).fadeIn(1000);
                                $('.toast').html(value).fadeOut(1000);
			}
		})