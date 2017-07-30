$(document).ready(function(){
    $("#loginCheck").on("click", function (){
        $.post('/ElectiveEPAM/user/login_check',
            {login: $(".userLogin").val(),
             password: $(".userPassword").val()},
            function (data) {
                $(".result").empty();
                if(data == "Успешная авторизация!"){
                    window.location = "/ElectiveEPAM/user/main";
                }
                $(".result").html(data);
            }
        );
    });
});