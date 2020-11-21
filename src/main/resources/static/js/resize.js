$(document).ready(function(){
         var curZoom;
         //check if cookie exists and if not set it
         if(!document.cookie.split(';').some(function(item) {
             return item.trim().indexOf('zoom=') == 0
     })) {
             curZoom = 1;
             document.cookie = "zoom="+curZoom+";path=/";
         } else {

             var curZoom = getCookie('zoom');
             // console.log("zoom is there: " + document.cookie.indexOf('zoom='));
         }
        setZoom(curZoom);
     $('#increasetext').click(function() {
         curZoom *= 1.1;
         setZoom(curZoom);
     });

     $('#decreasetext').click(function() {
         curZoom *= 0.9;
         setZoom(curZoom);

     });
         function setZoom(zoom){
             $('body').css('zoom', zoom );
             $('body').css('MozTransform','scale(' + zoom + ')');
             document.cookie = "zoom="+curZoom+";path=/";
             // console.log("zoom is now: " + curZoom);
         }
         function getCookie(name) {
             const value = `; ${document.cookie}`;
             const parts = value.split(`; ${name}=`);
             if (parts.length === 2) return parts.pop().split(';').shift();
         }
})
