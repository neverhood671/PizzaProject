/**
 * Created by Настя on 11.04.2017.
 */

function pay() {
    sendRequest("post", "/pay/pay", "", payProcess);
}

function payProcess(request) {
    try {
        var result = eval("(" + request.responseText + ")");
        if (result.status == "SUCCESS" && result.orderId != null) {
            alert("Ваш заказ номер: " + result.orderId);
            location.href = '/startPage';
        } else {
            alert("Ошибка соединения с сервером!");
        }
    } catch (ex) {
        alert("Ошибка соединения с сервером!");
    }
}