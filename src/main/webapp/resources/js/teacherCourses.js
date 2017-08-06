function getCoursesPage() {

    $.ajax({
        contentType: 'application/json',
        dataType: 'json',
        type: 'POST',
        data: JSON.stringify(a),
        url: contextPath + "/teacher/part",
        success: function (response) {
            $("#coursesList").html("");
            $.each(response.data, function (index, value) {
                console.log(index, value);
                $("#coursesList").append("<tr>" +
                    "<td>" + value.name + "</td>" +
                    "<td>" + value.teacher.lastname + "</td>" +
                    "<td>" + value.startDate + "</td>" +
                    "<td>" + value.endDate + "</td>" +
                    "<td>" +
                    "<a class='btn btn-primary btn-sm' href='"+contextPath+"/courseinfo?id=" + value.id + "'role='button'>" +
                    i18nStrings["More"] +
                    "</a> " +
                    "<a class='btn btn-danger btn-sm' href='"+contextPath+"/courseinfo?id=" + value.id + "'role='button'>" +
                    i18nStrings["Delete"] +
                    "</a>" +
                    "</td>" +
                    "</tr>")

            });
        }
    });
}