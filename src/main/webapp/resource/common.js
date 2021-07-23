function mobileSideBar__show(){
  $('.mobile-side-bar').addClass('active');
  $('.mobile-side-bar__content').addClass('active');
  $('html').addClass('mobile-side-bar-actived');
}

function mobileSideBar__hide(){
  $('.mobile-side-bar').removeClass('active');
  $('.mobile-side-bar__content').removeClass('active');
  $('html').removeClass('mobile-side-bar-actived');
}

function mobileSideBar__init(){
  $('.mobile-side-bar__btn').click(function(){
    mobileSideBar__show();
  });
  
  $('.mobile-side-bar__close-btn').click(function(){
    mobileSideBar__hide();
  });
}

mobileSideBar__init();