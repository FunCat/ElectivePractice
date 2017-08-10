var curPage = 0;
var curInterval = 10;

var a = {
    start: curPage,
    length: curInterval
};

$(document).bind('DOMSubtreeModified', function () {
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
    $(".pagination .page").removeClass("active");
    $(".pagination #"+(curPage+1)).addClass("active");
});

$(document).ready(function(){
    getCoursesPage();
    console.log(numOfPages);
});

$("#nextPage").click(function () {
    if (curPage == numOfPages-1) return;
    a.start = ++curPage * curInterval;
    getCoursesPage();
});

$("#prevPage").click(function () {
    if (curPage == 0) return;
    a.start = --curPage * curInterval;
    getCoursesPage();
});

function getCoursesPage() {
    $.ajax({
        contentType: 'application/json',
        dataType: 'json',
        type: 'POST',
        data: JSON.stringify(a),
        url: contextPath + "/partuser",
        success: function (response) {
            $("#coursesList").html("");
            $.each(response.data, function (index, value) {
                console.log(index, value);
                $("#coursesList").append("<tr>" +
                    "<td>" + value.name + "</td>" +
                    "<td>" + value.teacher.lastname + "</td>" +
                    "<td>" + new Date(value.startDate).toDateString().slice(0,10) + "</td>" +
                    "<td>" + new Date(value.endDate).toDateString().slice(0,10) + "</td>" +
                    "<td>" +
                    "<a class='myMediumBtn' href='"+contextPath+"/courseinfo?id=" +
                    value.id + "' role='button'>Подробнее</a>" +
                    "</td>" +
                    "</tr>")

            });
        },
        error: function(response){
            alert("Error in user getCoursesPage()");
            console.log(response)
        }
    });
}

$(".pagination .page").click(function () {
    a.start = ($(this).attr('id') - 1) * 10;
    curPage = $(this).attr('id') - 1;

    $.ajax({
        contentType: 'application/json',
        dataType: 'json',
        type: 'POST',
        data: JSON.stringify(a),
        url: contextPath + "/partuser",
        success: function (response) {
            // console.log(response);
            $("#coursesList").html("");
            $.each(response.data, function (index, value) {
                console.log(index, value);
                $("#coursesList").append("<tr>" +
                    "<td>" + value.name + "</td>" +
                    "<td>" + value.teacher.lastname + "</td>" +
                    "<td>" + new Date(value.startDate).toDateString().slice(0,10) + "</td>" +
                    "<td>" + new Date(value.endDate).toDateString().slice(0,10) + "</td>" +
                    "<td>" +
                    "<a class='myMediumBtn' href='"+contextPath+"/courseinfo?id=" +
                    value.id + "' role='button'>Подробнее</a>" +
                    "</td>" +
                    "</tr>")
            });
        },
        error: function (response) {
            alert("Error in user courses pagination");
            console.log(response);
        }
    });
});
