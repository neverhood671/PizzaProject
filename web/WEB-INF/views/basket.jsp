<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: Настя
  Date: 11.04.2017
  Time: 21:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <spring:url value="/resources/css/basket.css" var="basketCss"/>
    <spring:url value="/resources/css/startPageBreadcrumb.css" var="startPageBreadcrumbCss"/>
    <spring:url value="/resources/js/jquery-3.2.0.js" var="jqueryJs"/>
    <spring:url value="/resources/js/util.js" var="utilJs"/>
    <spring:url value="/resources/js/basket.js" var="basketJs"/>
    <title>Пиццы</title>
    <link href="${basketCss}" rel="stylesheet"/>
    <link href="${startPageBreadcrumbCss}" rel="stylesheet"/>
    <script type="text/javascript" src="${jqueryJs}"></script>
    <script type="text/javascript" src="${utilJs}"></script>
    <script type="text/javascript" src="${basketJs}"></script>
</head>
<body onload="init();">
<div class="center">
    <div class="head_block">
        <span class="breadcrumb-item">Выбери пиццу</span>
        <span class="breadcrumb-item breadcrumb-item_current">Ваша корзина</span>
        <span class="breadcrumb-item">Оплата</span>
    </div>
    <div id="basketContent">
    </div>
    <div style="margin: 10px 0;">
        Итого: <span id="resultPrice"></span> ₽
    </div>
    <button onclick="goToPay()">Оплатить</button>
</div>
</body>
</html>
