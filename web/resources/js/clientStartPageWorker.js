var pizzaTypeData = null;
var allTakenPizza = null;
function init() {
    loadPrices();
    $(document).ready(function () {
        $('input[type=radio]').click(function () {
            updatePrice();
        });
    });
}

function loadPrices() {
    sendRequest("get", "/data/jsonPizzaTypeList", "", showPrice);
}

function updatePrice() {
    if (pizzaTypeData && pizzaTypeData.length > 0) {
        for (var i = 0; i < pizzaTypeData.length; i++) {
            var currentSize = pizzaTypeData[i].name + "Size";
            var size = $("input[name=" + currentSize + "]:checked").val();
            var price = "";
            if (size == "S")
                price = pizzaTypeData[i].priceS;
            else if (size == "M")
                price = pizzaTypeData[i].priceM;
            else if (size == "L")
                price = pizzaTypeData[i].priceL;
            document.getElementById(pizzaTypeData[i].name + 'CurrentPrice').innerHTML = price;
        }
    }
}
function showPrice(request) {
    pizzaTypeData = eval("(" + request.responseText + ")");
    updatePrice();
}

function showOrderPrice(pizzaName) {
    var currentPrice = document.getElementById(pizzaName + "CurrentPrice").innerHTML;
    var v = Number(document.getElementById("basketCost").innerHTML) + Number(currentPrice);
    document.getElementById("basketCost").innerHTML = String(v);

}





