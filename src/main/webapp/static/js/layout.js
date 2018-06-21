function reloadResult(url, target, query, size, page){
    let searchResultTarget = $(target);
    let searchResultTargetContainer = $(`.search-result-container ${target}`).parent("div");
    searchResultTargetContainer.show();
    //searchResultTarget.text("Loading result, please wait...");

    $.get(url, {"q":query, "size": size, "page": page},  function (data) {
        searchResultTarget.html(data);
        searchResultTarget.find(".page-item").click(function(event){
            reloadResult(url, target, query, size, $(event.target).parent().data("page"));
        });
    }).fail(function(xhr){
        searchResultTarget.html(`<span class="alert alert-danger"> Error ${xhr.status}, please contact support.</span>`);
    });
}


function search() {
    $(".search-result-container").hide();

    $.each($("#selectpicker option:selected"), function (index, object) {
        let option = $(object);
        reloadResult(option.data("url"), option.data("target"), $("#search-term").val(), $("#max-results").val(), 0);
        /*let searchResultTargetSelector = option.data("target");
        let searchResultTarget = $(searchResultTargetSelector);
        let searchResultTargetContainer = $(`.search-result-container ${searchResultTargetSelector}`).parent("div");
        searchResultTargetContainer.show();
        searchResultTarget.text("Loading result, please wait...");

        $.get(option.data("url"), {"q":$("#search-term").val(), "size": $("#max-results").val()},  function (data) {
            searchResultTarget.html(data);
        }).fail(function(xhr){
            searchResultTarget.html(`<span class="alert alert-danger"> Error ${xhr.status}, please contact support.</span>`);
        });*/
    });
}
$(function () {
    $("#search").click(search);
    $(".search-result-container").hide();
});