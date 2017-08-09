function subscribe(){
        $.ajax({
            contentType: 'application/json',
            dataType: 'json',
            type: 'GET',
            data: {courseid: id},
            url: contextPath + "/subscribe",
            success: function (response) {
                $("#subscribeResult").html("");
                console.log(response);
                if (response) {
                    $("#subscribeResult").html("Успешно");
                    $("#subscribe").remove();
                    $("#subdiv").append(
                        "<div id = 'unsubscribe' class='myMediumBtn' onclick='unsubscribe()'>" +
                        i18nStrings["Unsubscribe"] +
                        "</div>"
                    );
                }
                else $("#subscribeResult").html("Действие невозможно");
            }
        });
}
function unsubscribe() {
        $.ajax({
            contentType: 'application/json',
            dataType: 'json',
            type: 'GET',
            data: {courseid: id},
            url: contextPath + "/unsubscribe",
            success: function (response) {
                $("#subscribeResult").html("");
                console.log(response);
                if (response) {
                    $("#subscribeResult").html("Успешно");
                    $("#unsubscribe").remove();
                    $("#subdiv").append(
                        "<div id = 'subscribe' class='myMediumBtn' onclick='subscribe()'>" +
                        i18nStrings["Subscribe"] +
                        "</div>"
                    );
                }
                else $("#subscribeResult").html("Действие невозможно");
            }
        });
}