$("#saveCourse").click(function (){
    $.ajax({
        type: 'POST',
        data: {
            idcourse: $(".idCourse").val(),
            namecourse: $(".nameCourse").val(),
            startdatecourse: $(".startDateCourse").val(),
            enddatecourse: $(".endDateCourse").val(),
            descriptionCourse: $(".descriptionCourse").val(),
        },
        url: contextPath + "/save_course",
        success: function (data) {
            console.log(data);
            $("#resultRequest").html("");
            $("#resultRequest").html(data);
        }
    });
});

$("#addCourse").click(function (){
    $.ajax({
        type: 'POST',
        data: {
            namecourse: $(".nameCourse").val(),
            startdatecourse: $(".startDateCourse").val(),
            enddatecourse: $(".endDateCourse").val(),
            descriptionCourse: $(".descriptionCourse").val(),
        },
        url: contextPath + "/add_new_course",
        success: function (data) {
            console.log(data);
            $("#resultRequest").html("");
            $("#resultRequest").html(data);
        }
    });
});