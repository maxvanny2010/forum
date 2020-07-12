<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>

<%--
  Created by IntelliJ IDEA.
  User: Maxim Vanny
  Date: 6/10/2020
  Time: 10:08 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head>
    <link crossorigin="anonymous" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh"
          rel="stylesheet">
    <script charset="UTF-8" src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"
            integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous">
    </script>
    <title>Registration</title>
</head>
<body>
<div class="container">
    <div class="text-center"><h5>Форум регистрация</h5></div>
    <div class="d-flex justify-content-end">
        <div><a href="<spring:url value="/forum"/>">Главная</a></div>
    </div>
    <hr/>
    <div class=" row">
        <form name='registration' action="<spring:url value="/registration"/>" method='POST'>
            <table>
                <tr>
                    <td><label>
                        <input type='text' name='username' required placeholder="введите имя">
                    </label></td>
                </tr>
                <tr>
                    <td><label>
                        <input type='password' name='password' required placeholder="введите пароль"/>
                    </label></td>
                </tr>
                <tr>
                    <td>
                        <input style="border-radius: 0; border-color: white; background-color: dodgerblue;
                         color: white" name="submit" type="submit" value="Регистрация"/>
                    </td>
                    <c:if test="${not empty msg}">
                        <td style="color:red; font-size: smaller; font-weight: bold;">
                                ${msg}
                        </td>
                    </c:if>
                </tr>
            </table>
            <%-- <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>--%>
        </form>
    </div>
</div>
</body>
</html>
