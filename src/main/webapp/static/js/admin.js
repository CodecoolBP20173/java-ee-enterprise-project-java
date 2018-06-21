function addSidebarEventListeners() {
    $.each($(".model-button"), function (index, object) {
        var button = $(object);
        button.click(function () {
            loadTable(button);
        });
    });
}

function addObjectEventListener() {
    let button = $("#add-row div#button");
    button.click(function () {
        var formData = {};
        $("#add-row").find("input[name]").each(function (index, node) {
            if ($(node).attr("type") === "checkbox") {
                formData[node.name] = !!$(node).attr("checked");
            } else {
                formData[node.name] = node.value;
            }
        });

        $.ajax(button.data("url"), {
            method: "POST",
            dataType: "json",
            contentType: "application/json",
            data: JSON.stringify(formData),
            success: function () {
                loadTable()
            }
        })
    })
}

var lastLoaded;

function loadTable(button) {
    if (!button) {
        button = lastLoaded;
    } else {
        lastLoaded = button;
    }
    $.ajax(button.data("url"), {
        success: function (data) {
            $("#content").html(data);
            refresh();
        }
    });
}

function refresh() {

    addObjectEventListener();
}

$(document).ready(
    addSidebarEventListeners(),
    refresh()
);
