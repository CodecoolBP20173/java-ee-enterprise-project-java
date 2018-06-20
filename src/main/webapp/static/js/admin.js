$.each($(".model-button"), function (index, object) {
    var button = $(object);
    object.addEventListener("click", function() {
        $.ajax(button.data("url"), {
            success: function (data) {
                $("#content").html($.param(data._embedded));
            }
        });
    });
});
// for (let i = 0; i < modelButtons.length; i++) {
//     let button = $(modelButtons[i]);
//     modelButtons[i].addEventListener("click", function() {
//         console.log(button);
//
//         $.ajax(button.data("url"), {
//             success: function (data) {
//                 console.log(data._embedded.books);
//                 $("#content").html($.param(data._embedded));
//             }
//         });
//     });
// }