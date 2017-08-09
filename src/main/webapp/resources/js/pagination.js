var curPage = 0;
var curInterval = 10;

var a = {
    start: curPage,
    length: curInterval
};

$(document).bind('DOMSubtreeModified', function () {
    changePaginationClasses();
});

$("#nextPage").click(function () {
    nextPage();
});

$("#prevPage").click(function () {
    prevPage();
});

$(".pagination .page").click(function () {
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
                    "<a class='btn btn-primary btn-sm' href='"+contextPath+"/courseinfo?id=" +
                    value.id + "'>" + i18nStrings["More"] + "</a>" +
                    "</td>" +
                    "</tr>")

            });
        }
    });
}

function getCoursesPageDefaultPagination(url) {
    $.ajax({
        contentType: 'application/json',
        dataType: 'json',
        type: 'POST',
        data: JSON.stringify(a),
        url: contextPath + url,
        success: function (response) {
            var $pagination = $(".pagination");

            $("#coursesList").html("");
            $pagination.html("");
            $.each(response.data, function (index, value) {
                console.log(index, value);
                $("#coursesList").append("<tr><td>" +
                    ((value.status == 'ACTIVE') ? "<div class='active_status'></div>" : "<div class='arhive_status'></div>") + "</td>" +
                    "<td>" + value.name + "</td>" +
                    "<td><a href='" + contextPath + "/teacher?id=" + value.teacher.id + "'>" + value.teacher.firstname + " " + value.teacher.lastname + "</a></td>" +
                    "<td>" + value.startDate + "</td>" +
                    "<td>" + value.endDate + "</td>" +
                    "<td>" +
                    "<a class='btn btn-primary btn-sm' href='" + contextPath + "/courseinfo?id=" +
                    value.id + "'>" + i18nStrings["More"] + "</a>" +
                    "</td>" +
                    "</tr>")
            });
            var totalRecords = response.recordsTotal;
            numOfPages = (totalRecords % 10 === 0) ? totalRecords / 10 : Math.floor(totalRecords / 10) + 1;

            $pagination.append("<li class = '' id='prevPage' onclick='prevPage()'>«</li>");
            for(var i = 1; i <= numOfPages; i++){
                $pagination.append("<li class='page' id='" + i + "'  onclick='numPage(this)'>" + i + "</li>");
            }
            $pagination.append("<li class='' id='nextPage' onclick='nextPage()'>»</li>");

            changePaginationClasses();
        }
    });
}

function nextPage(){
    if (curPage === numOfPages - 1) return;
    a.start = ++curPage * curInterval;
    getCoursesPage();
}

function prevPage(){
    if (curPage === 0) return;
    a.start = --curPage * curInterval;
    getCoursesPage();
}

function numPage(el){
    a.start = ($(el).attr('id') - 1) * 10;
    curPage = $(el).attr('id') - 1;
    getCoursesPage();
}

function changePaginationClasses() {
    if (curPage === 0) {
        $(".pagination #prevPage").addClass('disabled')
    } else {
        $(".pagination #prevPage").removeClass('disabled')
    }
    if (curPage === numOfPages - 1) {
        $(".pagination #nextPage").addClass('disabled')
    } else {
        $(".pagination #nextPage").removeClass('disabled')
    }
    $(".pagination .page").removeClass("active");
    $(".pagination #" + (curPage + 1)).addClass("active");
}