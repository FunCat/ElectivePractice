$(document).ready(function(){
    $(".editBtnProfile").on("click", function (){
        $.post(contextPath + '/edit_profile',
            {
                firstname: $(".userName").val(),
                lastname: $(".userLastname").val(),
                middlename: $(".userSurname").val(),
                userlogin: $(".userLogin").val(),
                birthday: $(".userBirthday").val()
            },
            function (data) {
                $(".resultProfile").empty();
                $(".resultProfile").html(data);
            }
        );
    });

    $(".editBtnPassword").on("click", function (){
        $.post(contextPath + '/edit_password',
            {
                nowpassword: $(".nowPassword").val(),
                newpassword: $(".newPassword").val(),
                newpassword2: $(".newPassword2").val(),
            },
            function (data) {
                $(".resultPassword").empty();
                $(".nowPassword").val("");
                $(".newPassword").val("");
                $(".newPassword2").val("");
                $(".resultPassword").html(data);
            }
        );
    });
});