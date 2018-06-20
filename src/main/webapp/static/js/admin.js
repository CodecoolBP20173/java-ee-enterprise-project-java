function addSidebarEventListeners() {
    $.each($(".model-button"), function (index, object) {
        var button = $(object);
        button.click(function () {
            $.ajax(button.data("url"), {
                success: function (data) {
                    $("#content").html($.param(data._embedded));
                }
            });
        });
    });
}

$(document).ready(
    addSidebarEventListeners()

);