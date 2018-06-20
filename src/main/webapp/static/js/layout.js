function search() {
    $.each($("#selectpicker option:selected"), function (index, object) {
        var button = $(object);

        $.ajax(button.data("url"), {
            success: function (data) {
                console.log(data);
            }
        });
    });
}
$(function () {
    $("#search").click(search);
});