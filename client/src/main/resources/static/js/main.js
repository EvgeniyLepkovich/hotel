$(document).ready(function(){
	$('.main__slider').slick({
  	slidesToShow: 3,
  	slidesToScroll: 1,
  	autoplaySpeed: 2000,
  	prevArrow:'<span class="main__slider__arrow main__slider__arrow-left"></span>',
    nextArrow:'<span class="main__slider__arrow main__slider__arrow-right"></span>',
    responsive: [{
      breakpoint: 1180,
      settings: {
        slidesToShow: 2,
      }
    }]
  });
})