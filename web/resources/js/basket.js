/**
 * Created by Настя on 11.04.2017.
 */

var pizzaTypeMap = [];
var pizzaList;
var resultPrice = 0;

function Pizza(name, priceS, priceM, priceL) {
    this.name = name;
    this.priceS = priceS;
    this.priceM = priceM;
    this.priceL = priceL;

    this.getPrice = function (size) {
        switch (size) {
            case 'S':
                return this.priceS;
            case 'M':
                return this.priceM;
            case 'L':
                return this.priceL;
        }
        return null;
    };
}

function init() {
    $(document).ready(function () {
        loadPrices();
    });
}

function loadPrices() {
    sendRequest("get", "/startPage/jsonPizzaTypeList", "", onLoadPrices);
}

function onLoadPrices(request) {
    try {
        initPizzaMap(eval("(" + request.responseText + ")"));
        loadBasket();
    } catch (ex) {
        alert("Ошибка соединения с сервером!")
    }
}

function initPizzaMap(pizzaTypeData) {
    if (pizzaTypeData && pizzaTypeData.length > 0) {
        for (var i = 0; i < pizzaTypeData.length; i++) {
            pizzaTypeMap[pizzaTypeData[i].name] = new Pizza(
                pizzaTypeData[i].name,
                pizzaTypeData[i].priceS,
                pizzaTypeData[i].priceM,
                pizzaTypeData[i].priceL);
        }
    }
}

function loadBasket() {
    sendRequest("get", "/basket/getBasket", "", onLoadBasket);
}

function onLoadBasket(request) {
    try {
        pizzaList = eval("(" + request.responseText + ")");
        drawBasketContent();
    } catch (ex) {
        alert("Ошибка соединения с сервером!")
    }
}

function drawBasketContent() {
    if (pizzaList) {
        for (var i = 0; i < pizzaList.length; i++) {
            addPizzaTypeBlock(pizzaList[i]);
        }
    }
    document.getElementById("resultPrice").innerHTML = String(resultPrice);
}

function addPizzaTypeBlock(orderedPizzaType) {
    var pizza = pizzaTypeMap[orderedPizzaType.pizzaName];
    if (!pizza) {
        return;  //todo throw exception
    }

    var basketContent = document.getElementById("basketContent");

    var pizzaBox = document.createElement('div');
    pizzaBox.className = "pizzaBox";
    basketContent.appendChild(pizzaBox);

    var pizzaImage = document.createElement('div');
    pizzaImage.className = "pizzaImage " + pizza.name;
    pizzaBox.appendChild(pizzaImage);

    var pizzaDescriptionBox = document.createElement('div');
    pizzaDescriptionBox.className = "pizzaDescriptionBox";
    pizzaBox.appendChild(pizzaDescriptionBox);

    var pizzaName = document.createElement('div');
    pizzaName.className = "pizzaName";
    pizzaName.innerHTML = "Имя: " + pizza.name;
    pizzaDescriptionBox.appendChild(pizzaName);

    var pizzaCount = document.createElement('div');
    pizzaCount.className = "pizzaCount";
    pizzaCount.innerHTML = "Количество: " + orderedPizzaType.pizzaCount;
    pizzaDescriptionBox.appendChild(pizzaCount);

    var pizzaSize = document.createElement('div');
    pizzaSize.className = "pizzaSize";
    pizzaSize.innerHTML = "Размер: " + orderedPizzaType.pizzaSize;
    pizzaDescriptionBox.appendChild(pizzaSize);

    var pizzaPrice = document.createElement('div');
    pizzaPrice.className = "pizzaPrice";
    var price = orderedPizzaType.pizzaCount * pizza.getPrice(orderedPizzaType.pizzaSize);
    resultPrice += price;
    pizzaPrice.innerHTML = "Цена: " + price + " ₽";
    pizzaDescriptionBox.appendChild(pizzaPrice);
}

function goToPay() {
    sendRequest("post", "/basket/goToPay", "", goToPayProcess);
}

function goToPayProcess(request) {
    try {
        var result = eval("(" + request.responseText + ")");
        if (result.status == "SUCCESS") {
            location.href = '/pay';
        } else {
            alert("Ошибка соединения с сервером!");
        }
    } catch (ex) {
        alert("Ошибка соединения с сервером!");
    }
}