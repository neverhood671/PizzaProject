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
    <title>Пиццы</title>
    <spring:url value="/resources/css/components.css" var="componentsCss"/>
    <spring:url value="/resources/css/startPage.css" var="mainCss"/>
    <spring:url value="/resources/css/startPageBreadcrumb.css" var="startPageBreadcrumbCss"/>
    <spring:url value="/resources/Pizza_sea.png" var="seaImg"/>
    <spring:url value="/resources/Pizza_red_sea.png" var="redSeaImg"/>
    <spring:url value="/resources/Pizza_margarita.png" var="margaritaImg"/>
    <spring:url value="/resources/Pizza_mushrooms.png" var="mashroomsImg"/>
    <spring:url value="/resources/Pizza_pepperoni.png" var="pepImg"/>
    <spring:url value="/resources/Pizza_pepperoni_mushrooms.png" var="pepMashImg"/>
    <spring:url value="/resources/js/jquery-3.2.0.js" var="jqueryJs"/>
    <spring:url value="/resources/js/util.js" var="utilJs"/>
    <spring:url value="/resources/js/clientStartPageWorker.js" var="clientStartPageWorkerJs"/>
    <link href="${componentsCss}" rel="stylesheet"/>
    <link href="${mainCss}" rel="stylesheet"/>
    <link href="${startPageBreadcrumbCss}" rel="stylesheet"/>
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
    <button class=btn-primary onclick="goToBasket()">Перейти в корзину</button>
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
                    <input name="Margarita" type="radio" value="S"/>
                    маленькая
                </p>
                <p>
                    <input name="Margarita" type="radio" value="M"/>
                    средняя
                </p>
                <p>
                    <input name="Margarita" type="radio" value="L" checked="checked"/>
                    большая
                </p>


                <div class="price_value">
                    <span class="value" id="MargaritaCurrentPrice">375</span> ₽
                </div>
                <button class=btn-primary onclick="showOrderPrice('Margarita')">Добавить в корзину</button>

            </div>

            <div class="element">
                <div class="pizzaImg">
                    <img src="${seaImg}">
                </div>
                <div class="text">Морская</div>
                <p>
                    <input name="Sea" type="radio" value="S"/>
                    маленькая
                </p>
                <p>
                    <input name="Sea" type="radio" value="M"/>
                    средняя
                </p>
                <p>
                    <input name="Sea" type="radio" value="L" checked="checked"/>
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
                    <input name="Mashrooms" type="radio" value="S"/>
                    маленькая
                </p>
                <p>
                    <input name="Mashrooms" type="radio" value="M"/>
                    средняя
                </p>
                <p>
                    <input name="Mashrooms" type="radio" value="L" checked="checked"/>
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
                    <input name="Pepperoni" type="radio" value="S"/>
                    маленькая
                </p>
                <p>
                    <input name="Pepperoni" type="radio" value="M"/>
                    средняя
                </p>
                <p>
                    <input name="Pepperoni" type="radio" value="L" checked="checked"/>
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
                    <input name="ReadSea" type="radio" value="S"/>
                    маленькая
                </p>
                <p>
                    <input name="ReadSea" type="radio" value="M"/>
                    средняя
                </p>
                <p>
                    <input name="ReadSea" type="radio" value="L" checked="checked"/>
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
                    <input name="PepMash" type="radio" value="S"/>
                    маленькая
                </p>
                <p>
                    <input name="PepMash" type="radio" value="M"/>
                    средняя
                </p>
                <p>
                    <input name="PepMash" type="radio" value="L" checked="checked"/>
                    большая
                </p>

                <div class="price_value">
                    <span class="value" id="PepMashCurrentPrice">375</span> ₽
                </div>
                <button class=btn-primary onclick="showOrderPrice('PepMash')">Добавить в корзину</button>
            </div>
        </div>
    </div>
</div>
</body>
