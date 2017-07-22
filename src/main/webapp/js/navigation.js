/**
 * Created by rusamaha on 7/22/17.
 */

var curPage = 0;

$("#nextPage").click(function () {
    curPage++;
    console.log(curPage);
});

$("#prevPage").click(function () {
    curPage--;
    console.log(curPage);

    var a = {
        start: curPage * 10,
        length: 10
    };

    $.ajax({
        dataType: 'json',
        type: 'POST',
        data:a,
        url: contextPath + "/news.all",
        success: function (response) {
            console.log(response);
        }
    });

});