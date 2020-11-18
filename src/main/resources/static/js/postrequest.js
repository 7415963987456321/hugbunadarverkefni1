$( document ).ready(function() {

    var id;
    // $('[id^=commentForm]').submit(function(event) {
    $('.ajaxForm').submit(function(event) {
        var form = $(this);
        id = form.attr('id');
    event.preventDefault();
    ajaxPost();
  });


    function ajaxPost(){
      // PREPARE FORM DATA
      var formData = {
          lessonId : $("#lessonId"+id).val(),
          message : $('#message'+id).val(),
          userName :  $('#userName'+id).val()
      }
        // console.log($('#message'+id).val());
        // console.log(id);
      $.ajax({
      type : "POST",
      contentType : "application/json",
          url : window.location + "/" + formData.lessonId + "/comment/post",
      data : JSON.stringify(formData),
      dataType : 'json',
      success : function(result) {
          $('#postResult'+id).prepend("<ul class='list-group'> <div class='card bg-light mb-3' style='max-width: 28rem;'> <div class='card-header'> " + result.userName + " skrifaði " + result.date + "</div> <div class='card-body'><p class='card-text'>" + result.message + "</div> </div></ul>");
      },
      error : function(e) {
        alert("Error!")
        console.log("ERROR: ", e);
      }
    });

        resetData();

    }

    function resetData(){
      $("#message"+id).val("");
    }
})
