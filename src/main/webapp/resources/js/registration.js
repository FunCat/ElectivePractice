var registrationData = {

};

$(document).ready(function(){
    $("#registrationBtn").on("click", function(){

        if($(".userLogin").val() == "" || $(".userPassword").val() == "" || $(".userPassword2").val() == "" ||
            $(".userName").val() == "" || $(".userLastname").val() == "" ||
            $(".userSurname").val() == "" ||  $(".userBirthday").val() == ""){
            $(".result").html("");
            $(".result").html("Не все поля заполнены!");
        }

        console.log("hello!");
        $.ajax({
            type: 'POST',
            data: {
                login: $(".userLogin").val(),
                password: $(".userPassword").val(),
                password2: $(".userPassword2").val(),
                firstname: $(".userName").val(),
                lastname: $(".userLastname").val(),
                surname: $(".userSurname").val(),
                birthday: $(".userBirthday").val(),
            },
            url: contextPath + "/registration_check",
            success: function (data) {
                console.log(data);
                $(".result").html("");
                if(data == "Успешная регистрация!"){
                    window.location.href = contextPath + "/login";
                }
                $(".result").html(data);
            }
        });
    });
});
