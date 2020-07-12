<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!doctype html>
<html lang="en">
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link crossorigin="anonymous" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh"
          rel="stylesheet">
    <script charset="UTF-8" src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"
            integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous">
    </script>
    <title>Форум</title>
</head>
<body>
<div class="container" style="width: 1000px">
    <div class="text-center"><h5>Форум главная страница</h5></div>
    <br/>
    <c:if test="${not empty name && name ne 'anonymousUser'}">
        <div class="d-flex justify-content-end">
            <div>
                <div style="margin-right: 25px">кабинет:
                    <a href="<spring:url value="/cabinet/${name eq 'admin' ? 'admin' : 'user'}?name=${name}"/>">
                        <b>${name}</b></a></div>
            </div>
            <div>
                <form name="logout" action="<spring:url value="/logout?name=${name}"/>" method="post">
                    <label>
                        <input name="out" value="выйти ${name}" type="submit"
                               style="font-size: smaller; border-color: white; background-color: dodgerblue; color: white">
                    </label>
                </form>
            </div>
        </div>
    </c:if>
    <c:if test="${empty name || name eq 'anonymousUser'}">
        <div class="d-flex justify-content-end">
            <div style="width: 40px; font-size: smaller"><a href="<spring:url value="/login"/>">вход</a>
            </div>
            <div style="width: 100px; font-size: smaller"><a href="<spring:url value="/registration"/>">регистрация</a>
            </div>
        </div>
    </c:if>
    <div class="row">
        <table class="table" style="width: 1000px">
            <thead style="border-top-color: cadetblue;">
            <tr>
                <th scope="col">тема</th>
                <th scope="col">создан</th>
                <th scope="col">описание</th>
                <th scope="col">автор</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${posts}" var="post">
                <tr>
                    <td style="font-size: smaller">
                        <a href="<spring:url value="/post?action=shows&id=${post.id}&name=${post.author}"/>"><c:out
                                value="${post.name}"/>
                    </td>
                    <td style="font-size: smaller">
                        <c:out value="${post.created.toLocalDate()}"/>
                        <c:out value=" ${fn:split(post.created.toLocalTime(),'.')[0]}"/>
                    </td>
                    <td style="font-size: smaller"><c:out value="${post.description}"/>
                    </td>
                    <td style="font-size: smaller"><c:out value="${post.author}"/>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>
</body>
</html>
