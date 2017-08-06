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

$("#nextPage").click(function (e) {
    e.preventDefault(); // a href='#' without scroll
    if (curPage == numOfPages-1) return;
    a.start = ++curPage * curInterval;
    getCoursesPage();
});

$("#prevPage").click(function (e) {
    e.preventDefault(); // a href='#' without scroll
    if (curPage == 0) return;
    a.start = --curPage * curInterval;
    getCoursesPage();
});

$(".pagination .page").click(function (e) {
    e.preventDefault();   // a href='#' without scroll
    a.start = ($(this).attr('id') - 1) * 10;
    curPage = $(this).attr('id') - 1;
    getCoursesPage();
});

function getCoursesPageDefault(url) {
    $.ajax({
        contentType: 'application/json',
        dataType: 'json',
        type: 'POST',
        data: JSON.stringify(a),
        url: contextPath + url,
        success: function (response) {
            $("#coursesList").html("");
            $.each(response.data, function (index, value) {
                console.log(index, value);
                $("#coursesList").append("<tr>" +
                    "<td>" + value.name + "</td>" +
                    "<td><a href='" + contextPath + "/teacher?id=" + value.teacher.id + "'>" + value.teacher.firstname + " " + value.teacher.lastname + "</a></td>" +
                    "<td>" + value.startDate + "</td>" +
                    "<td>" + value.endDate + "</td>" +
                    "<td>" +
                    "<a class='myMediumBtn' href='"+contextPath+"/courseinfo?id=" +
                    value.id + "'>Подробнее</a>" +
                    "</td>" +
                    "</tr>")

            });
        }
    });
}
