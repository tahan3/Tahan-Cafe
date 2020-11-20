<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="thandler" uri="https://github.com/Mtlcwtchr/EpamCafe" %>
<%--
  Created by IntelliJ IDEA.
  User: St.Anislav
  Date: 5/25/2020
  Time: 7:55 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html xml:lang="${locale}">
<head>
    <title>Footer</title>
    <style><%@include file="/WEB-INF/css/style.css"%></style>
</head>
<body>

<footer>
    <div class="container">
    <div class="row copyright">
        <div class="col-md-12 text-center">
            <p>
                <small class="block">
                     &copy; <thandler:timeCustomTag/> <a>TCafe</a>.AllRightsReserved.
                </small>
                <small class="block">
                    <br> Contact phone: +(29) 420-69-69
                    <br> Email: cafecontactemail@gmail.com
                </small>
            </p>
        </div>
    </div>
    </div>
</footer>

</body>
</html>
