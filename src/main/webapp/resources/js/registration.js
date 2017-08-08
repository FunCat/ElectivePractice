$(document).ready(function(){
    $("#registrationBtn").click(function(){
        var userLogin = $(".userLogin").val();
        var userPassword = $(".userPassword").val();
        var userPassword2 = $(".userPassword2").val();
        var userName = $(".userName").val();
        var userLastname = $(".userLastname").val();
        var userBirthday = $(".userBirthday").val();
        var $result = $(".result");



        if(userLogin === "" || userPassword === "" || userPassword2 === "" ||
            userName === "" || userLastname === "" || userBirthday === ""){
            $result.html("");
            $result.html(i18nStrings["NotAllRequiredField"]);
        }
        else {
            $.ajax({
                type: 'POST',
                data: {
                    login: userLogin,
                    password: userPassword,
                    password2: userPassword2,
                    firstname: userName,
                    lastname: userLastname,
                    surname: $(".userSurname").val(),
                    birthday: userBirthday,
                },
                url: contextPath + "/registration_check",
                success: function (data) {
                    console.log(data);
                    $result.html("");
                    if (data === "Успешная регистрация!") {
                        window.location.href = contextPath + "/login";
                    }
                    $(".userPassword").val("");
                    $(".userPassword2").val("");
                    $result.html(data);
                }
            });
        }
    });
});
