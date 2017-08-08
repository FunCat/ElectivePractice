$(document).ready(function(){
    $("#registrationBtn").on("click", function(){

        if($(".userLogin").val() == "" || $(".userPassword").val() == "" || $(".userPassword2").val() == "" ||
            $(".userName").val() == "" || $(".userLastname").val() == "" || $(".userBirthday").val() == ""){
            $(".result").html("");
            $(".result").html(i18nStrings["NotAllRequiredField"]);
        }
        else {
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
                    if (data == "Успешная регистрация!") {
                        window.location.href = contextPath + "/login";
                    }
                    $(".userPassword").val("");
                    $(".userPassword2").val("");
                    $(".result").html(data);
                }
            });
        }
    });
});
