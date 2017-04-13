<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: Настя
  Date: 13.04.2017
  Time: 22:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <spring:url value="/resources/css/orders.css" var="ordersCss"/>
    <spring:url value="/resources/js/jquery-3.2.0.js" var="jqueryJs"/>
    <spring:url value="/resources/js/util.js" var="utilJs"/>
    <spring:url value="/resources/js/orders.js" var="ordersJs"/>
    <title>Заказы</title>
    <link href="${ordersCss}" rel="stylesheet"/>
    <script type="text/javascript" src="${jqueryJs}"></script>
    <script type="text/javascript" src="${utilJs}"></script>
    <script type="text/javascript" src="${ordersJs}"></script>
</head>
<body onload="init();">
<div class="orderBox">
    <div class="orderHead">Готовятся</div>
    <div id="inProgressBox" class="orderList"></div>
</div>
<div class="orderBox">
    <div class="orderHead">Готовы</div>
    <div id="readyBox" class="orderList"></div>
</div>
</body>
</html>
