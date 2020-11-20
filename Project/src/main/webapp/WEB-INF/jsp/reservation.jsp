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
    <title>Hall reservation</title>
    <style><jsp:include page="/WEB-INF/css/popup.css"/></style>
    <script type="text/javascript" src="http://code.jquery.com/jquery-2.0.2.min.js"></script>
    <script><jsp:include page="/WEB-INF/js/popupprocessor.js"/></script>
</head>
<body>
<jsp:include page="/WEB-INF/jsp/header.jsp"/>
<div class="background-shadow"></div>

<div class="container">
    <div class="row">
        <div class="col-sm-3 col-sm-push-3"  id="form-wrap">
            <form action="${pageContext.request.contextPath}/reserve_hall?key=${hall.id}" method="post">
                <div class="row form-group">
                    <div class="col-md-12">
                        <label for="fieldDate"><fmt:message key="reservation.date"/></label>
                            <input  type="date" id="fieldDate" class="form-control" min="${minDate}" max="${maxDate}" required name="reservationDate">
                    </div>
                </div>
                <div class="row form-group">
                    <div class="col-md-12">
                        <label for="fieldContactTime"><fmt:message key="reservation.contactTime"/></label>
                        <input type="time" id="fieldContactTime" class="form-control" min="${minTime}" max="${maxTime}" required name="contactTime">
                    </div>
                </div>
                <div class="row form-group">
                    <div class="col-md-12">
                        <label for="fieldContactPhone"><fmt:message key="reservation.contactPhone"/></label>
                        <input type="text" id="fieldContactPhone" class="form-control" value="${actor.user.phone}" placeholder="80" required name="contactPhone">
                    </div>
                </div>
                <div class="row form-group">
                    <div class="col-md-12">
                        <label for="fieldSubmit"></label>
                        <p>
                            <input type="submit" id="fieldSubmit" class="btn btn-primary btn-outline btn-lg" value="<fmt:message key="reservation.reserve"/> ${hall.name}">
                        </p>
                    </div>
                </div>
            </form>
        </div>
    </div>
    <div class="popup-window-small red-wrap p-w-dateError">
        <p class="close">x</p>
        <br>
        <hr style="width: 100%;">
        <div class="popup-inner centered">
            <p class="intro-text error-msg"><fmt:message key="reservation.dateError"/></p>
        </div>
    </div>
    <div class="popup-window-small red-wrap p-w-timeError">
        <p class="close">x</p>
        <br>
        <hr style="width: 100%;">
        <div class="popup-inner centered">
            <p class="intro-text error-msg"><fmt:message key="reservation.timeError"/> (${minTime} - ${maxTime})</p>
        </div>
    </div>
</div>

<jsp:include page="/WEB-INF/jsp/footer.jsp"/>
</body>
</html>