<%--
  Created by IntelliJ IDEA.
  User: Настя
  Date: 04.04.2017
  Time: 23:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
    <title>Пиццемейкер</title>
    <spring:url value="/resources/css/components.css" var="componentsCss"/>
    <spring:url value="/resources/css/pizzaMaker.css" var="mainCss"/>
    <spring:url value="/resources/css/loadingBox.css" var="loadingBoxCss"/>
    <spring:url value="/resources/Pizza_sea.png" var="seaImg"/>
    <spring:url value="/resources/Pizza_red_sea.png" var="redSeaImg"/>
    <spring:url value="/resources/Pizza_margarita.png" var="margaritaImg"/>
    <spring:url value="/resources/Pizza_mushrooms.png" var="mashroomsImg"/>
    <spring:url value="/resources/Pizza_pepperoni.png" var="pepImg"/>
    <spring:url value="/resources/Pizza_pepperoni_mushrooms.png" var="pepMashImg"/>
    <spring:url value="/resources/js/jquery-3.2.0.js" var="jqueryJs"/>
    <spring:url value="/resources/js/util.js" var="utilJs"/>
    <spring:url value="/resources/js/pizzaMaker.js" var="pizzaMakerJs"/>
    <title>Pizza Maker</title>
    <link href="${componentsCss}" rel="stylesheet"/>
    <link href="${mainCss}" rel="stylesheet"/>
    <link href="${loadingBoxCss}" rel="stylesheet"/>
    <script type="text/javascript" src="${jqueryJs}"></script>
    <script type="text/javascript" src="${utilJs}"></script>
    <script type="text/javascript" src="${pizzaMakerJs}"></script>
</head>
<body onload="init();">
<div id="loadingBox">
    <div id="loadingBox_1" class="fountainG"></div>
    <div id="loadingBox_2" class="fountainG"></div>
    <div id="loadingBox_3" class="fountainG"></div>
    <div id="loadingBox_4" class="fountainG"></div>
    <div id="loadingBox_5" class="fountainG"></div>
    <div id="loadingBox_6" class="fountainG"></div>
    <div id="loadingBox_7" class="fountainG"></div>
    <div id="loadingBox_8" class="fountainG"></div>
</div>
<div id="loginBox" style="visibility: hidden;">
    <div>
        <span>Login: </span><input id="loginInput" type="text"/>
    </div>
    <div>
        <span>Password: </span><input id="passwordInput" type="password"/>
        <input id="loginButton" class="btn-primary" type="button" value="OK" onclick="doLogin();">
    </div>
</div>
<div id="workBox" style="visibility: hidden;">
    <div id="headBox">
        <span id="usernameSpan"></span>
        <input id="logoutButton" class="btn-primary" type="button" onclick="logout();" value="Выйти"/>
    </div>
    <div class="pizzaWorkBox">
        <div id="pizzaImage"></div>
        <div id="timerBox"></div>
        <div id="pizzaTypeBox">
            <div>Имя пиццы: <span id="pizzaNameSpan"></span></div>
            <div>Размер пиццы: <span id="pizzaSizeSpan"></span></div>
        </div>
        <div>
            <input id="finishPizza" class="btn-primary" type="button" value="Готово" onclick="finishPizza();"/>
        </div>
    </div>
</div>
</body>
</html>
