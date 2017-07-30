$(document).ready(function(){
    $(".editBtn").on("click", function (){
        $.post('/ElectiveEPAM/user/edit_profile',
            {
                firstname: $(".userFirstname").val(),
                lastname: $(".userLastname").val(),
                middlename: $(".userMiddlename").val(),
                userlogin: $(".userLogin").val(),
                birthday: $(".userBirthday").val()
            },
            function (data) {
                $(".result").empty();
                $(".result").html(data);
            }
        );
    });
});