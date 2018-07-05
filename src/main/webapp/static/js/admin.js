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
    $("#btn-register-new-admin").click(onRegisterNewAdmin);

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
    $.each($("td .button"), function (index, object) {
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

let lastLoaded;

function loadTable(button) {
    let content = $("#content");
    if (content.attr("hidden") === "hidden") {
        content.removeAttr("hidden");
    }
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
    $.each($(".basemodel-button"), function (index, object) {
        var button = $(object);
        button.click(function () {
            loadTable(button);
        });
    });
}

function addEventListeners() {
    addModalButtonListeners();
    addButtonListener();
    rowButtonListeners();
}


function submitAjaxForm(form, resultAlert, url) {
    event.preventDefault();
    event.stopPropagation();
    let formData = getFormData(form);
    let headers = {};
    addCsrf(formData, headers);

    $.ajax(url, {
        data: JSON.stringify(formData),
        headers: headers,
        method: "POST",
        dataType: "text",
        contentType: "application/json"
    }).done( function(data) {
        resultAlert.text(data).removeClass("alert-danger").addClass("alert-success");
    }).fail(function(xhr) {
        resultAlert.text(xhr.responseText).removeClass("alert-success").addClass("alert-danger");
    }).always(function() {
        resultAlert.show();
        form[0].reset();
    });
}

function onChangePassword(event){
    event.preventDefault();
    event.stopPropagation();
    submitAjaxForm( $("#change-password-form"), $("#password-change-result"), "/admin/change-password");
}

function onRegisterNewAdmin(event){
    event.preventDefault();
    event.stopPropagation();
    submitAjaxForm($("#register-new-admin"), $("#admin-register-result"), "/admin/register-new-admin");
}

$(document).ready(function () {
        sidebarListeners();

        addEventListeners();
    }
);

function addModalButtonListeners() {
    let button = $(".modal-button");
    $.each(button, function () {
        $(button).click(
            $.ajax(button.data("url"), {
                dataType: "json",
                success: function (data) {
                    let jsonData = JSON.stringify(data._embedded[button.data("name-in-json")]);
                    // TODO format the data nicely, not just plain json
                    $(button.data("target") + " .modal-body").text(jsonData);
                }
            })
        );
    });
}