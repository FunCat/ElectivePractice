$("#saveCourse").click(function (){

    var $idCourse = $(".idCourse").val();
    var $nameCourse = $(".nameCourse").val();
    var $startDateCourse = $(".startDateCourse").val();
    var $endDateCourse = $(".endDateCourse").val();
    var $descriptionCourse = $(".descriptionCourse").val();
    var $resultRequest = $("#resultRequest");


    if($idCourse === "" || $nameCourse === "" || $startDateCourse === "" ||
        $endDateCourse === "" || $descriptionCourse === ""){
        $resultRequest.html("");
        $resultRequest.html(i18nStrings["NotAllRequiredField"]);
    }
    else {
        $.ajax({
            type: 'POST',
            data: {
                idcourse: $idCourse,
                namecourse: $nameCourse,
                startdatecourse: $startDateCourse,
                enddatecourse: $endDateCourse,
                descriptionCourse: $descriptionCourse
            },
            url: contextPath + "/save_course",
            success: function (data) {
                $resultRequest.html("");

                if (data === i18nStrings["SuccessUpdateCourse"])
                    window.location.href = contextPath + "/courseinfo?id=" + $idCourse;

                $resultRequest.html(data);
            }
        });
    }
});

$("#addCourse").click(function (){

    var $nameCourse = $(".nameCourse").val();
    var $startDateCourse = $(".startDateCourse").val();
    var $endDateCourse = $(".endDateCourse").val();
    var $descriptionCourse = $(".descriptionCourse").val();
    var $resultRequest = $("#resultRequest");

    if($nameCourse === "" || $startDateCourse === "" ||
        $endDateCourse === "" || $descriptionCourse === ""){
        $resultRequest.html("");
        $resultRequest.html(i18nStrings["NotAllRequiredField"]);
    }
    else {
        $.ajax({
            type: 'POST',
            data: {
                namecourse: $nameCourse,
                startdatecourse: $startDateCourse,
                enddatecourse: $endDateCourse,
                descriptionCourse: $descriptionCourse
            },
            url: contextPath + "/add_new_course",
            success: function (data) {
                $resultRequest.html("");

                if (data === i18nStrings["SuccessUpdateCourse"])
                    window.location.href = contextPath + "/teacher/managecourses";

                $resultRequest.html(data);
            }
        });
    }
});