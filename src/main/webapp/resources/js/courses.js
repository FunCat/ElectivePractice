var countpage = 1;
var totalCounts = 0;

$(document).ready(function(){
    getCoursesPage();

    $("#prevPageBtn").on("click", function (){
        if(countpage > 1)
            countpage--;
        getCoursesPage();
    });

    $("#nextPageBtn").on("click", function () {
        if(countpage < totalCounts / 10)
            countpage++;
        getCoursesPage();
    });
});

function getCoursesPage() {
    $.post('/ElectiveEPAM/coursesPart',
        {page: countpage},
        function (data) {
            console.log(data);
            $(".removableRows").remove();
            totalCounts = data.recordsTotal;
            $.each(data.data, function () {
                $(".appendAfterRow").after("<tr class='removableRows'><td class='col-md-5'>" + this.name +
                    "</td><td class='col-md-2'>" + this.id +
                    "</td><td class='col-md-3'>" + new Date(this.createDate).toDateString() +
                    "</td><td class='col-md-2'><a href='/editcourse?id=" + this.id +
                    "'><input type='button' value='Edit' class='editbtn'/></a></td></tr>");
            });
        }
    );
}