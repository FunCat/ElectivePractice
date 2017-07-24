/**
 * Created by rusamaha on 7/22/17.
 */

var curPage = 0;

$("#nextPage").click(function () {
    curPage++;
    console.log(curPage);
});

$("#prevPage").click(function () {
    curPage--;
    console.log(curPage);

    var a = {
        start: curPage * 10,
        length: 10
    };

    $.ajax({
        contentType:'application/json',
        dataType: 'json',
        type: 'POST',
        data: JSON.stringify(a),
        url: contextPath + "/news.all",
        success: function (response) {
            console.log(response);
            // $("#coursesList").html("");
            $.each(response.data, function( index, value ) {
                console.log(index, value);
                $("#coursesList").append("<li>" + value.name + "</li>")
            });
        }
    });

});