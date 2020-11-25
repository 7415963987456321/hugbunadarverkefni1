$(document).ready(function(){
         var curZoom;
         var curFontSize = parseInt($('html').css('font-size').slice(0,-2));
         //check if cookie exists and if not set it
         if(!document.cookie.split(';').some(function(item) {
             return item.trim().indexOf('zoom=') == 0
     })) {
             curZoom = 1;
             document.cookie = "zoom="+curZoom+";path=/";
             document.cookie = "font="+curFontSize+";path=/";
         } else {
             
             curZoom = getCookie('zoom');
             curFontSize = parseInt(getCookie('font'));

             // console.log("zoom is there: " + document.cookie.indexOf('zoom='));
         }
    setZoom(curZoom, curFontSize);
     $('#increasetext').click(function() {
         curZoom *= 1.1;
         curFontSize += 2;
         setZoom(curZoom,curFontSize);

     });

     $('#decreasetext').click(function() {
         curZoom *= 0.9;
         curFontSize -= 2;
         setZoom(curZoom,curFontSize);

     });
    function setZoom(zoom,font){
             $('body').css('zoom', zoom);
             // $('body').css('MozTransform','scale(' + zoom + ')');
             document.cookie = "zoom="+curZoom+";path=/";
             if(navigator.userAgent.indexOf("Firefox") != -1 ){
                 console.log(font);
                 $('html').css('font-size',font);
             }

             document.cookie = "font="+curFontSize+";path=/";
         }
         function getCookie(name) {
             const value = `; ${document.cookie}`;
             const parts = value.split(`; ${name}=`);
             if (parts.length === 2) return parts.pop().split(';').shift();
         }
})
