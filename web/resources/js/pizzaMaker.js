/**
 * Created by Настя on 04.04.2017.
 */

var login = '';
var userName = '';
var isLogin = false;
var loadingBox;
var loginComponents = {};
var workComponents = {};
var timer;
var requestPizzaTimerId = null;
var processRequestPizzaTimer = false;
var currentPizza = null;

function Timer(timerBox) {
    this.INVERSE_STYLE = "inverse";
    this.DEFAULT_STYLE = "default";
    this.START_TIMER_VAL = {
        hours: 0,
        minutes: 10,
        seconds: 0,
        backTimer: true,
        styleName: this.DEFAULT_STYLE
    };

    this.timerBox = timerBox;
    this.seconds = 0;
    this.minutes = 0;
    this.hours = 0;
    this.timerId = null;
    this.backTimer = false;
    this.showMinus = false;

    this.startTimer = function () {
        this.reset();
        if (this.timerId == null) {
            var self = this;
            this.timerId = setInterval(function () {
                self.tick.call(self)
            }, 1000);
        }
    };

    this.stopTimer = function () {
        if (this.timerId != null) {
            clearInterval(this.timerId);
            this.timerId = null;
        }
        this.clear();
    };

    this.reset = function () {
        this.seconds = this.START_TIMER_VAL.seconds;
        this.minutes = this.START_TIMER_VAL.minutes;
        this.hours = this.START_TIMER_VAL.hours;
        this.backTimer = this.START_TIMER_VAL.backTimer;
        this.timerBox.className = this.START_TIMER_VAL.styleName;
        this.timerBox.innerHTML = this.toString();
    };

    this.clear = function () {
        this.seconds = 0;
        this.minutes = 0;
        this.hours = 0;
        this.backTimer = false;
        this.timerBox.className = this.DEFAULT_STYLE;
        this.timerBox.innerHTML = this.toString();
    };

    this.tick = function () {
        if (this.backTimer) {
            this.minusTick();
        } else {
            this.plusTick();
        }
    };

    this.plusTick = function () {
        this.seconds += 1;
        if (this.seconds > 59) {
            this.minutes++;
            this.seconds = 0;
        }
        if (this.minutes > 59) {
            this.hours++;
            this.minutes = 0;
        }
        this.timerBox.innerHTML = this.toString();
    };

    this.minusTick = function () {
        this.seconds--;
        if (this.seconds < 0) {
            this.minutes--;
            this.seconds = 59;
        }
        if (this.minutes < 0) {
            this.hours--;
            this.minutes = 59;
        }
        if (this.hours < 0) {
            this.applyNormalTime();
        }
        this.timerBox.innerHTML = this.toString();
    };

    this.applyNormalTime = function () {
        this.clear();
        this.seconds = 1;
        this.timerBox.className = this.INVERSE_STYLE;
        this.showMinus = true;
    };

    this.toString = function () {
        return (this.showMinus ? '- ' : '') +
            (this.hours > 0 ? this.hours + ' : ' : '') +
            this._numberToString(this.minutes) + ' : ' +
            this._numberToString(this.seconds);
    };

    this._numberToString = function (number) {
        return number < 10 ? "0" + number : number;
    };

    this.clear();
}


function init() {
    $(document).ready(function () {
        loadAllComponents();
        sendRequest("get", "/pizzaMaker/getLoginStatus", "", applyStatus);
    });
}

function doLogin() {
    loadingBox.style.visibility = "visible";
    login = loginComponents.loginInput.value;
    var params = "login=" + login + "&password=" + loginComponents.passwordInput.value;
    sendRequest("post", "/pizzaMaker/doLogin", params, applyStatus);
}

function logout() {
    loadingBox.style.visibility = "visible";
    sendRequest("post", "/pizzaMaker/logout", "", applyStatus);
}

function loadAllComponents() {
    loadingBox = document.getElementById("loadingBox");

    loginComponents.loginBox = document.getElementById("loginBox");
    loginComponents.loginInput = document.getElementById("loginInput");
    loginComponents.passwordInput = document.getElementById("passwordInput");
    loginComponents.loginButton = document.getElementById("loginButton");

    workComponents.workBox = document.getElementById("workBox");
    workComponents.usernameSpan = document.getElementById("usernameSpan");
    workComponents.logoutButton = document.getElementById("logoutButton");
    workComponents.pizzaImage = document.getElementById("pizzaImage");
    workComponents.timerBox = document.getElementById("timerBox");
    workComponents.pizzaNameSpan = document.getElementById("pizzaNameSpan");
    workComponents.pizzaSizeSpan = document.getElementById("pizzaSizeSpan");
    workComponents.finishPizza = document.getElementById("finishPizza");

    timer = new Timer(workComponents.timerBox);
    userName = '';
    login = '';
    isLogin = false;
}

function applyStatus(request) {
    try {
        var loginStatus = eval("(" + request.responseText + ")");

        if (loginStatus) {
            if (loginStatus.status == "LOGIN") {
                loadingBox.style.visibility = "hidden";
                showLoginBox();

                currentPizza = null;
                timer.stopTimer();
                processRequestPizzaTimer = false;
                clearPizzaComponents();
                if (requestPizzaTimerId != null) {
                    clearInterval(requestPizzaTimerId);
                    requestPizzaTimerId = null;
                }
            } else if (loginStatus.status == "SUCCESS") {
                loadingBox.style.visibility = "hidden";
                showWorkBox();

                workComponents.usernameSpan.innerHTML = loginStatus.username;
                processRequestPizzaTimer = true;
                if (requestPizzaTimerId == null) {
                    requestPizzaTimerId = setInterval(getPizza, 1000);
                }
            } else {
                loadingBox.style.visibility = "hidden";
                showLoginBox();
                alert("Ошибка авторизации");
            }
        } else {
            loadingBox.style.visibility = "hidden";
            showLoginBox();
            alert("Ошибка соединение с сервером");
        }
    } catch (ex) {
        loadingBox.style.visibility = "hidden";
        showLoginBox();
        alert("Ошибка соединение с сервером");
    }
}

function showWorkBox() {
    loginComponents.loginBox.style.visibility = "hidden";
    workComponents.workBox.style.visibility = "visible";
}

function showLoginBox() {
    workComponents.workBox.style.visibility = "hidden";
    loginComponents.loginBox.style.visibility = "visible";
}

function getPizza() {
    if (processRequestPizzaTimer) {
        processRequestPizzaTimer = false;
        sendRequest("get", "/pizzaMaker/getPizza", "", applyPizza);
    }
}

function applyPizza(request) {
    try {
        var pizzaStatus = eval("(" + request.responseText + ")");
        if (pizzaStatus && pizzaStatus.status == "NEW") {
            processRequestPizzaTimer = false;

            currentPizza = {};
            currentPizza.pizzaName = pizzaStatus.pizzaName;
            currentPizza.pizzaNameLocale = pizzaStatus.pizzaNameLocale;
            currentPizza.pizzaSize = pizzaStatus.pizzaSize;

            workComponents.pizzaNameSpan.innerHTML = currentPizza.pizzaNameLocale;
            workComponents.pizzaSizeSpan.innerHTML = currentPizza.pizzaSize;
            workComponents.pizzaImage.className = currentPizza.pizzaName;

            timer.startTimer();
        } else {
            processRequestPizzaTimer = true;
        }
    } catch (exception) {
        currentPizza = {};
        processRequestPizzaTimer = true;
    }
}

function finishPizza() {
    if (currentPizza != null) {
        sendRequest("get", "/pizzaMaker/finishPizza", "", finishedPizza);
    }
}

function finishedPizza(request) {
    var finishStatus = eval("(" + request.responseText + ")");
    if (finishStatus && finishStatus.status == "READY") {
        currentPizza = null;

        timer.stopTimer();

        clearPizzaComponents();

        processRequestPizzaTimer = true;
    } else {
        alert("Ошибка соединение с сервером");
    }
}

function clearPizzaComponents() {
    workComponents.pizzaNameSpan.innerHTML = "";
    workComponents.pizzaSizeSpan.innerHTML = "";
    workComponents.pizzaImage.className = "emptyPizza";
}