$(document).ready(function(){



	var mySwiper = new Swiper('.index-banner',{

		pagination: '.index-pagination',

		loop:true,

		grabCursor: true,

		paginationClickable: true,

		onSlideChangeEnd : function() {

			var bannerTitle = $(".index-banner ul li.swiper-slide-active img").attr("title");

			$(".banner-title p").text(bannerTitle);

		}

	})

	var bannerFstTitle = $(".index-banner ul li.swiper-slide-active img").attr("title");

	$(".banner-title p").text(bannerFstTitle);

	fjcHeight();

	fontSizeHack();

	$(".index-banner ul li img").show();

	

})
