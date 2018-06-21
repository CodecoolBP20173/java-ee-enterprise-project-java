function sidebarListeners() {
    $.each($(".model-button"), function (index, object) {
        var button = $(object);
        button.click(function () {
            loadTable(button);
        });
    });
}

function getFormData(jqueryObject) {
    var formData = {};
    jqueryObject.find("input[name], select").each(function (index, node) {
        if ($(node).attr("type") === "checkbox") {
            formData[node.name] = $(node).attr("checked");
        } else {
            formData[node.name] = $(node).val();
        }
    });
    return formData;
}

function addButtonListener() {
    var button = $("#add-row div#button");
    button.click(function () {
        var formData = getFormData($("#add-row"));

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
            addEventListeners();
        }
    });
}

function getRowData(button) {
    var rowData = {};
    $(button).parent().parent().find(".editable").each(function (index, object) {
        var child = $(object);
        var checkbox = child.find("input[type=checkbox]");
        if (checkbox.length > 0) {
            rowData[child.data("field")] = checkbox.is(":checked");
        } else {
            rowData[child.data("field")] = child.text();
        }
    });
    return rowData;
}

function rowButtonListeners() {
    $.each($("td div.button"), function (index, object) {
        var button = $(object);
        button.click(function () {
            var rowData = getRowData(button);
            if (button.data("method").toUpperCase() === "GET") {
                $.ajax(button.data("url"), {
                    success: function () {
                        // loadTable();
                    }
                });
            } else {
                $.ajax(button.data("url"), {
                    method: button.data("method"),
                    dataType: "json",
                    contentType: "application/json",
                    data: JSON.stringify(rowData),
                    success: function () {
                        loadTable();
                    }
                });
            }
        });
    });
}

function addEventListeners() {
    addButtonListener();
    rowButtonListeners();
}

$(document).ready( function() {
        sidebarListeners();

        addEventListeners();
    }
);
