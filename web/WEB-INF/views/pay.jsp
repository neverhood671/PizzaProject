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
    <spring:url value="/resources/css/pay.css" var="payCss"/>
    <spring:url value="/resources/css/startPageBreadcrumb.css" var="startPageBreadcrumbCss"/>
    <spring:url value="/resources/js/jquery-3.2.0.js" var="jqueryJs"/>
    <spring:url value="/resources/js/util.js" var="utilJs"/>
    <spring:url value="/resources/js/pay.js" var="payJs"/>
    <title>Пиццы</title>
    <link href="${payCss}" rel="stylesheet"/>
    <link href="${startPageBreadcrumbCss}" rel="stylesheet"/>
    <script type="text/javascript" src="${jqueryJs}"></script>
    <script type="text/javascript" src="${utilJs}"></script>
    <script type="text/javascript" src="${payJs}"></script>
</head>
<body onload="init();">
<div class="center">
    <div class="head_block">
        <span class="breadcrumb-item">Выбери пиццу</span>
        <span class="breadcrumb-item">Ваша корзина</span>
        <span class="breadcrumb-item breadcrumb-item_current">Оплата</span>
    </div>
    <button class="pay" onclick="pay()">Оплатить</button>
</div>
</body>
</html>
