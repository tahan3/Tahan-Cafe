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
    <title>Reservations</title>
</head>
<body>
<jsp:include page="/WEB-INF/jsp/admin/aheader.jsp"/>

<div class="container">
    <div class="row">
        <table class="table-w-10 table-border-collapsed">
            <c:forEach var="reservation" items="${reservations}">
                <div class="popup-window white-wrap p-w-${reservation.id}">
                    <p class="close">x</p>
                    <br>
                    <hr style="width: 100%;">
                    <div class="popup-inner centered">
                        <fmt:message key="admin.reservation"/> ${reservation.id} <br>
                            ${reservation.reservationDate}
                    </div>
                    <div class="row form-group centered">
                        <div class="col-md-12">
                            <p>
                                <fmt:message key="admin.reservationContactTime"/>: ${reservation.contactTime}
                            </p>
                        </div>
                    </div>
                    <div class="row form-group centered">
                        <div class="col-md-12">
                            <p>
                                <fmt:message key="admin.reservationContactPhone"/>: ${reservation.contactPhone}
                            </p>
                        </div>
                    </div>
                    <form action="${pageContext.request.contextPath}/delete_reservation?dkey=${reservation.id}&hkey=${hallId}" method="post">
                        <div class="row form-group centered">
                            <div class="col-md-12">
                                <p>
                                    <input type="submit" class="btn btn-primary btn-outline btn-lg" value="<fmt:message key="admin.reservationClose"/> ${reservation.id}">
                                </p>
                            </div>
                        </div>
                    </form>
                </div>
                <tr class="list-element popup-open" about="${reservation.id}">
                    <td style="width: 20%; color: rgba(245, 245, 245, 0.9)">
                        <fmt:message key="admin.reservation"/> ${reservation.id}
                    </td>
                    <td style="width: 60%; text-transform: uppercase; color: rgba(245, 245, 245, 0.9)">
                            ${reservation.reservationDate}
                        <c:if test="${!reservation.expired}">
                            <a>*</a>
                        </c:if>
                    </td>
                    <td style="width: 20%; color: rgba(245, 245, 245, 0.9)">
                            ${reservation.reservedHall.name}
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>
</div>

<jsp:include page="/WEB-INF/jsp/footer.jsp"/>
</body>
</html>