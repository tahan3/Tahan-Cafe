<%--
  Created by IntelliJ IDEA.
  User: St.Anislav
  Date: 5/20/2020
  Time: 4:31 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java"  contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page isELIgnored="false" %>

<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="messages"/>

<!DOCTYPE html>
<html xml:lang="${locale}">
<head>
    <title>Halls</title>
    <style><jsp:include page="/WEB-INF/css/popup.css"/></style>
    <script type="text/javascript" src="http://code.jquery.com/jquery-2.0.2.min.js"></script>
    <script><jsp:include page="/WEB-INF/js/popupprocessor.js"/></script>
</head>
<body>
<jsp:include page="/WEB-INF/jsp/header.jsp"/>
<div class="background-shadow"></div>

<div class="container">
    <div class="row">
        <table class="menu-table-1">
            <tr>
                <c:forEach var="hall" items="${halls}">
                <c:set var="count" value="${count+1}"/>
                <td class="menu-table-item centered">
                    <div class="form-group">
                        <p class="intro-text text-center"><fmt:message key="halls.hall"/> "${hall.name}" <fmt:message key="halls.for"/> ${hall.guestsNumber} <fmt:message key="halls.ppl"/></p>
                        <p align="center">${hall.description}</p>
                        <c:if test="${actor!=null}">
                                <div class="row form-group">
                                    <div class="col-md-12 centered">
                                        <label for="fieldSubmit"></label>
                                        <a href="${pageContext.request.contextPath}/reservation?key=${hall.id}" id="fieldSubmit" class="btn btn-primary btn-outline btn-lg"><fmt:message key="halls.reserve"/></a>
                                    </div>
                                </div>
                        </c:if>
                    </div>
                </td>
                <c:if test="${count%3==0}">
            </tr>
            <tr>
                </c:if>
                </c:forEach>
            </tr>
        </table>
    </div>
    <div class="row">
        <c:if test="${actor==null}">
            <p class="centered"><fmt:message key="halls.guestmsg"/></p>
        </c:if>
    </div>
    <div class="popup-window white-wrap p-w-success">
        <p class="close">x</p>
        <br>
        <hr style="width: 100%;">
        <div class="popup-inner centered">
            <p class="intro-text"><fmt:message key="halls.success"/> ${reservation.reservedHall.name}</p>
            <fmt:message key="halls.success.date"/> ${reservation.reservationDate} <br>
            <fmt:message key="halls.success.contact"/> ${reservation.contactTime}<br>
            <fmt:message key="halls.success.phone"/>: ${reservation.contactPhone}
        </div>
    </div>
</div>

<jsp:include page="/WEB-INF/jsp/footer.jsp"/>
</body>
</html>