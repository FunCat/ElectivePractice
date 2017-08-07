function getCoursesPage() {

    $.ajax({
        contentType: 'application/json',
        dataType: 'json',
        type: 'POST',
        data: JSON.stringify(a),
        url: contextPath + "/course/students?id="+id,
        success: function (response) {
            $("#studentList").html("");
            $.each(response.data, function (index, value) {
                console.log(index, value);
                $("#studentList").append("<tr>" +
                    "<td>" + value.groupId.student.firstname + "</td>" +
                    "<td>" + value.groupId.student.lastname + "</td>" +
                    "<td><input type='text' id='grade_"+index +"' disabled value="+value.grade+" /></td>" +
                    "<td><input type='text' id='review_"+index+"' disabled value="+value.review+" /></td>" +
                    "<td>" +
                    "<a href='#' class='btn btn-warning btn-sm edit' attr_id="+index+" role='button'>"+
                    i18nStrings["Edit"] +
                    "</a>"+
                    "<a href='#' class='btn btn-danger btn-sm hidden save' student_id=" + value.groupId.student.id + "  attr_id="+index+" role='button'>"+
                    i18nStrings["Save"] +
                    "</a>" +
                    "</td>" +
                    "</tr>"
                )
            });
        }
    });
}
$(document).ready(function () {
    $(document).on("click",".edit",function(e) {
    // $(".edit").click(function (e) {
        e.preventDefault();
        $(this).addClass('hidden');
        $(this).next('.save').removeClass('hidden');

        $('#grade_'+$(this).attr('attr_id')).removeAttr('disabled');
        $('#review_'+$(this).attr('attr_id')).removeAttr('disabled');

    });
    $(document).on("click",".save",function(e) {
        e.preventDefault();
        $(this).addClass('hidden');
        $(this).prev('.edit').removeClass('hidden');

        $('#grade_'+$(this).attr('attr_id')).attr('disabled', true);
        $('#review_'+$(this).attr('attr_id')).attr('disabled', true);

        var group = {
            groupId: {
                student: {id:$(this).attr('student_id')},
                course: {id:id}
            },
            grade: $('#grade_'+$(this).attr('attr_id')).val(),
            review: $('#review_'+$(this).attr('attr_id')).val()
        };

        console.log(group);
        $.ajax({
            contentType: 'application/json',
            // dataType: 'json',
            type: 'POST',
            data: JSON.stringify(group),
            url: contextPath + "/editgroup",
            success: function (data) {
                console.log(data);
                $(".result").html("");

                $(".result").html(data);
            },
            error:function (data) {
                alert("Error");
            }
        })
    })
});

