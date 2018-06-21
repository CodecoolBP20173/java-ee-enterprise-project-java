function search() {
    $(".search-result-container").hide();

    $.each($("#selectpicker option:selected"), function (index, object) {
        let button = $(object);
        let searchResultTargetSelector = button.data("target");
        let searchResultTarget = $(searchResultTargetSelector);
        let searchResultTargetContainer = $(`.search-result-container ${searchResultTargetSelector}`).parent("div");
        searchResultTargetContainer.show();
        searchResultTarget.text("Loading result, please wait...");

        $.get(button.data("url"), {"q":$("#search-term").val()},  function (data) {
            searchResultTarget.html(data);
        }).fail(function(xhr){
            searchResultTarget.html(`<span class="alert alert-danger"> Error ${xhr.status}, please contact support.</span>`);
        });
    });
}
$(function () {
    $("#search").click(search);
    $(".search-result-container").hide();
});