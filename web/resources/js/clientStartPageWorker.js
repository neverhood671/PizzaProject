var pizzaTypeMap = [];
var basket = {
    price: 0,
    allTakenPizza: []
};

function PizzaType(pizzaName, pizzaSize) {
    this.pizzaName = pizzaName;
    this.pizzaSize = pizzaSize;
    this.pizzaCount = 1;

    this.getHashName = function () {
        return this.pizzaName + this.pizzaSize;
    }
}

function Pizza(name, priceS, priceM, priceL) {
    this.name = name;
    this.priceS = priceS;
    this.priceM = priceM;
    this.priceL = priceL;
    this.selectedSize = 'L';

    this.getPizzaType = function () {
        return new PizzaType(this.name, this.selectedSize);
    };

    this.getSelectedPrice = function () {
        switch (this.selectedSize) {
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
    loadPrices();
    $(document).ready(function () {
        $('input[type=radio]').click(function (eventObject) {
            updatePrice(eventObject);
        });
    });
}

function updatePrice(eventObject) {
    if (eventObject && eventObject.target && pizzaTypeMap) {
        var pizza = pizzaTypeMap[eventObject.target.name];
        if (pizza) {
            pizza.selectedSize = eventObject.target.defaultValue;
            document.getElementById(pizza.name + 'CurrentPrice').innerHTML = String(pizza.getSelectedPrice());
        }
    }
}

function loadPrices() {
    sendRequest("get", "/startPage/jsonPizzaTypeList", "", showPrice);
}

function initPrice() {
    if (pizzaTypeMap) {
        for (var i in pizzaTypeMap) {
            pizzaTypeMap[i].selectedSize = $("input[name=" + pizzaTypeMap[i].name + "]:checked").val();
            document.getElementById(pizzaTypeMap[i].name + 'CurrentPrice').innerHTML =
                String(pizzaTypeMap[i].getSelectedPrice());
        }
    }
}

function initPizzaMap(pizzaTypeData) {
    if (pizzaTypeData && pizzaTypeData.length > 0) {
        for (var i = 0; i < pizzaTypeData.length; i++) {
            pizzaTypeMap[pizzaTypeData[i].name] = new Pizza(
                pizzaTypeData[i].name,
                pizzaTypeData[i].priceS,
                pizzaTypeData[i].priceM,
                pizzaTypeData[i].priceL,
                'L');
        }
    }
}

function showPrice(request) {
    try {
        initPizzaMap(eval("(" + request.responseText + ")"));
        initPrice();
    } catch (ex) {
        alert("Ошибка соединения с сервером!")
    }
}

function showOrderPrice(pizzaName) {
    var selectedPizza = pizzaTypeMap[pizzaName];
    if (selectedPizza) {
        var pizzaType = selectedPizza.getPizzaType();
        if (basket.allTakenPizza[pizzaType.getHashName()]) {
            basket.allTakenPizza[pizzaType.getHashName()].pizzaCount += 1;
        } else {
            basket.allTakenPizza[pizzaType.getHashName()] = pizzaType;
        }
        basket.price += selectedPizza.getSelectedPrice();
        document.getElementById("basketCost").innerHTML = String(basket.price);
    } else {
        alert("Ошибка. Попробуйте перезагрузить страницу.");
    }
}

function goToBasket() {
    if (basket.allTakenPizza) {
        var pizzaList = [];
        var i = 0;
        for (var obj in basket.allTakenPizza) {
            pizzaList[i] = basket.allTakenPizza[obj];
            i++;
        }
        if (pizzaList.length > 0) {
            var param = "pizzaList=" + JSON.stringify(pizzaList);
            sendRequest("post", "/startPage/createBasket", param, goToBasketProcess);
        } else {
            alert("Выберите пиццу, а лучше две))");
        }
    } else {
        alert("Ошибка. Попробуйте перезагрузить страницу.");
    }
}

function goToBasketProcess(request) {
    try {
        var result = eval("(" + request.responseText + ")");
        if (result.status == "SUCCESS") {
            location.href = '/basket';
        } else {
            alert("Ошибка соединения с сервером!");
        }
    } catch (ex) {
        alert("Ошибка соединения с сервером!");
    }
}





