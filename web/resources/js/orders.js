/**
 * Created by Настя on 13.04.2017.
 */

var requestOrderTimerId = null;

function init() {
    $(document).ready(function () {
        requestOrderTimerId = requestPizzaTimerId = setInterval(loadOrders, 5000);
    });
}

function loadOrders() {
    sendRequest("get", "/orders/getOrders", "", showOrders);
}

function showOrders(request) {
    try {
        var inProgressBox = document.getElementById("inProgressBox");
        var readyBox = document.getElementById("readyBox");

        inProgressBox.innerHTML = "";
        readyBox.innerHTML = "";

        var status = eval("(" + request.responseText + ")");

        if (status.status == "SUCCESS") {
            var orders = status.orders;
            for (var i = 0; i < orders.length; i++) {
                var order = orders[i];
                if (order) {
                    if (order.orderStatus == "IN PROGRESS") {
                        inProgressBox.innerHTML += order.id + "<br/>";
                    } else {
                        readyBox.innerHTML += order.id + "<br/>";
                    }
                }
            }
        } else {
            alert("Ошибка сервера!")
        }
    } catch (ex) {
        alert("Ошибка соединения с сервером!")
    }
}