/**
 * Created by rusamaha on 8/4/17.
 */

function getCoursesPage() {

    $.ajax({
        contentType: 'application/json',
        dataType: 'json',
        type: 'POST',
        data: JSON.stringify(a),
        url: contextPath + "/part",
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
                    "<a class='myMediumBtn' href='"+contextPath+"/courseinfo?id=" + value.id + "' role='button'>Подробнее</a>" +
                    "</td>" +
                    "</tr>")

            });
        }
    });
}
