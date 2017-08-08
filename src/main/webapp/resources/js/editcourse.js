$("#saveCourse").click(function (){
    if($(".idCourse").val() == "" || $(".nameCourse").val() == "" || $(".startDateCourse").val() == "" ||
        $(".endDateCourse").val() == "" || $(".descriptionCourse").val() == ""){
        $("#resultRequest").html("");
        $("#resultRequest").html(i18nStrings["NotAllRequiredField"]);
    }
    else {
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
    }
});

$("#addCourse").click(function (){
    if($(".nameCourse").val() == "" || $(".startDateCourse").val() == "" ||
        $(".endDateCourse").val() == "" || $(".descriptionCourse").val() == ""){
        $("#resultRequest").html("");
        $("#resultRequest").html(i18nStrings["NotAllRequiredField"]);
    }
    else {
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
    }
});