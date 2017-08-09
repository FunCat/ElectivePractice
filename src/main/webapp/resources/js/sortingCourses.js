$(".columSort").click(function(){
    if($(this).hasClass("columDesc")) {
        desc = false;
        $(this).removeClass("columDesc").addClass("columAsc");
    }
    else if($(this).hasClass("columAsc")) {
        desc = true;
        $(this).removeClass("columAsc").addClass("columDesc");
    }
    else {
        $(".columSort").removeClass("columDesc columAsc");
        columSorting = $(this).data("columsorting");
        desc = true;
        $(this).addClass("columDesc");
    }
    getCoursesPageDefaultPagination("/coursestag?columSorting=" + columSorting + "&desc=" + desc + "&term=" + $("#tags").val());
});