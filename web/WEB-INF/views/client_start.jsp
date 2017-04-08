<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: Настя
  Date: 01.04.2017
  Time: 19:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<head>
    <spring:url value="/resources/css/style.css" var="mainCss"/>
    <spring:url value="/resources/Pizza_sea.png" var="seaImg"/>
    <spring:url value="/resources/Pizza_red_sea.png" var="redSeaImg"/>
    <spring:url value="/resources/Pizza_margarita.png" var="margaritaImg"/>
    <spring:url value="/resources/Pizza_mushrooms.png" var="mashroomsImg"/>
    <spring:url value="/resources/Pizza_pepperoni.png" var="pepImg"/>
    <spring:url value="/resources/Pizza_pepperoni_mushrooms.png" var="pepMashImg"/>
    <spring:url value="/resources/js/jquery-3.2.0.js" var="jqueryJs"/>
    <spring:url value="/resources/js/util.js" var="utilJs"/>
    <spring:url value="/resources/js/clientStartPageWorker.js" var="clientStartPageWorkerJs"/>
    <link href="${mainCss}" rel="stylesheet"/>
    <script type="text/javascript" src="${jqueryJs}"></script>
    <script type="text/javascript" src="${utilJs}"></script>
    <script type="text/javascript" src="${clientStartPageWorkerJs}"></script>
</head>
<body onload="init();">
<div class="center">
    <div class="head_block">
        <span class="breadcrumb-item breadcrumb-item_current">Выбери пиццу</span>
        <span class="breadcrumb-item">Ваша корзина</span>
        <span class="breadcrumb-item">Оплата</span>
    </div>
</div>
<div class="basketBox">
    <div class="basketImg"></div>
    <div>
        <span id="basketCost">0</span> ₽
    </div>
</div>
<div class="center">
    <div class="main_block">
        <div class="pizza_line">
            <div class="element">
                <div class="pizzaImg">
                    <img src="${margaritaImg}">
                </div>
                <div class="text">Маргарита</div>
                <p>
                    <input id="margSizeS" name="MargaritaSize" type="radio" value="S"/>
                    маленькая
                </p>
                <p>
                    <input id="margSizeM" name="MargaritaSize" type="radio" value="M"/>
                    средняя
                </p>
                <p>
                    <input checked="checked" id="margSizeL" name="MargaritaSize" type="radio" value="L"/>
                    большая
                </p>


                <div class="price_value">
                    <span class="value" id="MargaritaCurrentPrice"></span> ₽
                </div>
                <button class=btn-primary onclick="showOrderPrice('Margarita')">Добавить в корзину</button>

            </div>

            <div class="element">
                <div class="pizzaImg">
                    <img src="${seaImg}">
                </div>
                <div class="text">Морская</div>
                <p>
                    <input id="seaSizeS" name="SeaSize" type="radio" value="S"/>
                    маленькая
                </p>
                <p>
                    <input id="seaSizeM" name="SeaSize" type="radio" value="M"/>
                    средняя
                </p>
                <p>
                    <input checked="checked" id="seaSizeL" name="SeaSize" type="radio" value="L"/>
                    большая
                </p>

                <div class="price_value">
                    <span class="value" id="SeaCurrentPrice">375</span> ₽
                </div>
                <button class=btn-primary onclick="showOrderPrice('Sea')">Добавить в корзину</button>
            </div>

            <div class="element">
                <div class="pizzaImg">
                    <img src="${mashroomsImg}">
                </div>
                <div class="text">Грибная</div>
                <p>
                    <input id="mashroomsSizeS" name="MashroomsSize" type="radio" value="S"/>
                    маленькая
                </p>
                <p>
                    <input id="mashroomsSizeM" name="MashroomsSize" type="radio" value="M"/>
                    средняя
                </p>
                <p>
                    <input checked="checked" id="mashroomsSizeL" name="MashroomsSize" type="radio" value="L"/>
                    большая
                </p>

                <div class="price_value">
                    <span class="value" id="MashroomsCurrentPrice">375</span> ₽
                </div>
                <button class=btn-primary onclick="showOrderPrice('Mashrooms')">Добавить в корзину</button>
            </div>
        </div>

        <div class="pizza_line">
            <div class="element">
                <div class="pizzaImg">
                    <img src="${pepImg}">
                </div>
                <div class="text">Пепперони</div>
                <p>
                    <input id="pepSizeS" name="PepperoniSize" type="radio" value="S"/>
                    маленькая
                </p>
                <p>
                    <input id="pepSizeM" name="PepperoniSize" type="radio" value="M"/>
                    средняя
                </p>
                <p>
                    <input checked="checked" id="pepSizeL" name="PepperoniSize" type="radio" value="L"/>
                    большая
                </p>

                <div class="price_value">
                    <span class="value" id="PepperoniCurrentPrice">375</span> ₽
                </div>
                <button class=btn-primary onclick="showOrderPrice('Pepperoni')">Добавить в корзину</button>
            </div>


            <div class="element">
                <div class="pizzaImg">
                    <img src="${redSeaImg}">
                </div>
                <div class="text">Морская Острая</div>
                <p>
                    <input id="redSeaSizeS" name="ReadSeaSize" type="radio" value="S"/>
                    маленькая
                </p>
                <p>
                    <input id="redSeaSizeM" name="ReadSeaSize" type="radio" value="M"/>
                    средняя
                </p>
                <p>
                    <input checked="checked" id="redSeaSizeL" name="ReadSeaSize" type="radio" value="L"/>
                    большая
                </p>

                <div class="price_value">
                    <span class="value" id="ReadSeaCurrentPrice">375</span> ₽
                </div>
                <button class=btn-primary onclick="showOrderPrice('ReadSea')">Добавить в корзину</button>
            </div>

            <div class="element">
                <div class="pizzaImg">
                    <img src="${pepMashImg}">
                </div>
                <div class="text">Пепперони с грибами</div>
                <p>
                    <input id="pepMashSizeS" name="PepMashSize" type="radio" value="S"/>
                    маленькая
                </p>
                <p>
                    <input id="pepMashSizeM" name="PepMashSize" type="radio" value="M"/>
                    средняя
                </p>
                <p>
                    <input checked="checked" id="pepMashSizeL" name="PepMashSize" type="radio" value="L"/>
                    большая
                </p>

                <div class="price_value">
                    <span class="value" id="PepMashCurrentPrice">375</span> ₽
                </div>
                <button class=btn-primary onclick="showOrderPrice('PepMash')">Добавить в корзину</button>
            </div>
        </div>
    </div>
    <div>
        <button class=btn-primary>Перейти в корзину</button>
    </div>
</div>
</body>
