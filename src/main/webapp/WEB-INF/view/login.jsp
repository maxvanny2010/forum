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
    <title>логин</title>
</head>
<body>
<div class="container" style="width: 1000px">
    <div class="text-center"><h5>Форум логин</h5></div>
    <div class="d-flex justify-content-end">
        <div><a href="<spring:url value="/forum"/>">Главная</a></div>
    </div>
    <hr/>
    <div class="row">
        <form name='login' action="<c:url value='/login'/>" method='POST'>
            <table>
                <tr>
                    <td style="font-size: smaller">Логин:</td>
                    <td><label><input type='text' name='username'></label></td>
                </tr>
                <tr>
                    <td style="font-size: smaller">Пароль:</td>
                    <td><label><input type='password' name='password'/></label></td>
                </tr>
                <tr>
                    <td><input name="submit"
                               style="border-radius: 0; border-color: white; background-color: dodgerblue; color: white"
                               type="submit" value="Войти"/>
                    </td>
                    <c:if test="${not empty errorMessage}">
                        <td style="color:red; font-size: smaller; font-weight: bold;">
                                ${errorMessage}
                        </td>
                    </c:if>
                </tr>
            </table>
        </form>
    </div>
    <div class="d-flex flex-column">
        <div>

        </div>
        <div>login: user pass: secret</div>
        <div>login: admin pass: secret</div>
        <div>или <a href="<spring:url value="/registration"/>">регистрация</a></div>
    </div>
</div>
</body>
</html>
