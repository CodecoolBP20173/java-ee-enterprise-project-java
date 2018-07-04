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
    $("#change-password").click(onChangePassword);

    var button = $("#add-row div#button");
    button.click(function () {
        var formData = getFormData($("#add-row"));
        let headers = {};
        addCsrf(formData, headers);
        $.ajax(button.data("url"), {
            headers: headers,
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
            let header = {};
            addCsrf(rowData, header);
            if (button.data("method").toUpperCase() === "GET") {
                $.ajax(button.data("url"), {
                    success: function () {
                        // loadTable();
                    }
                });
            } else {
                $.ajax(button.data("url"), {
                    method: button.data("method"),
                    headers: header,
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

function addCsrf(targetObj, header) {
    let csrfName = $("meta[name='csrfName']").attr("content");
    let csrfToken = $("meta[name='csrfToken']").attr("content");
    let csrfHeader = $("meta[name='csrfHeader']").attr("content");
    targetObj[csrfName] = csrfToken;

    header[csrfHeader] = csrfToken;
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

function sidebarListeners() {
    $.each($(".model-button"), function (index, object) {
        var button = $(object);
        button.click(function () {
            loadTable(button);
        });
    });
}

function addEventListeners() {
    addButtonListener();
    rowButtonListeners();
}

function onChangePassword(event){

    event.preventDefault();
    event.stopPropagation();
   let formData = getFormData($("#change-password-form"));
   let headers = {};
   addCsrf(formData, headers);
   $.ajax("/admin/change-password", {
       data: JSON.stringify(formData),
       headers: headers,
       method: "POST",
       dataType: "json",
       contentType: "application/json",
       success: function() {
       alert("Password successfully changed!");
   }
   }).fail(function(xhr) {
       alert(xhr.responseText);
   });
}

$(document).ready(function () {
        sidebarListeners();

        addEventListeners();
    }
);
