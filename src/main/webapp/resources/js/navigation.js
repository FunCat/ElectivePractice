/**
 * Created by rusamaha on 7/22/17.
 */


var curPage = 0;
var curInterval = 2;

var a = {
    start: curPage,
    length: curInterval
};
//
$(document).bind('DOMSubtreeModified', function () {
    console.log("changed: " + curPage);
    if (curPage == 0) {
        $(".pagination li:first").addClass('disabled')
    } else {
        $(".pagination li:first").removeClass('disabled')
    }
    if (curPage == numOfPages-1) {
        $(".pagination li:last").addClass('disabled')
    } else {
        $(".pagination li:last").removeClass('disabled')
    }

    // $(".pagination #page_"+curPage).addClass("active");
    $(".pagination .page").removeClass("active");
    $(".pagination #"+(curPage+1)).addClass("active");
    console.log(".pagination #"+curPage);
});


$("#nextPage").click(function () {
    if (curPage == numOfPages-1) return;
    a.start = ++curPage * curInterval;
    // console.log(curPage);

    $.ajax({
        contentType: 'application/json',
        dataType: 'json',
        type: 'POST',
        data: JSON.stringify(a),
        url: contextPath + "/part",
        success: function (response) {
            // console.log(response);

            if (curPage > 0) {
                $("#coursesList").html("");
                $.each(response.data, function (index, value) {
                    console.log(index, value);
                    $("#coursesList").append("<li>" + value.id + " " + value.name + "</li>");
                });
            }
        }
    });

});

$("#prevPage").click(function () {
    if (curPage == 0) return;
    a.start = --curPage * curInterval;
    // console.log(curPage);

    $.ajax({
        contentType: 'application/json',
        dataType: 'json',
        type: 'POST',
        data: JSON.stringify(a),
        url: contextPath + "/part",
        success: function (response) {
            // console.log(response);
            $("#coursesList").html("");
            $.each(response.data, function (index, value) {
                console.log(index, value);
                $("#coursesList").append("<li>" + value.id + " " + value.name + "</li>")
            });
        }
    });
});

$(".pagination .page").click(function () {
    console.log("this: " + this);
    console.log("$(this): "+ $(this));
    a.start = $(this).attr('id');
    curPage = $(this).attr('id')-1;
    // console.log(curPage);

    $.ajax({
        contentType: 'application/json',
        dataType: 'json',
        type: 'POST',
        data: JSON.stringify(a),
        url: contextPath + "/part",
        success: function (response) {
            // console.log(response);
            $("#coursesList").html("");
            $.each(response.data, function (index, value) {
                console.log(index, value);
                $("#coursesList").append("<li>" + value.id + " " + value.name + "</li>")
            });
        }
    });
});

