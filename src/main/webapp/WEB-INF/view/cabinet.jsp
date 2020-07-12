<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
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
    <script crossorigin="anonymous" src="https://kit.fontawesome.com/3e4faed5c9.js"></script>
    <title>кабинет</title>
</head>
<body>
<div class="container" style="width: 1000px">
    <div class="text-center">
        <h5 style="color: black">Форум - кабинет:
            <c:if test="${not empty name && name ne 'anonymousUser'}"> ${name}</c:if>
            <c:if test="${empty name || name eq 'anonymousUser'}"> взлом </c:if>
        </h5>
    </div>
    <div class="d-flex justify-content-start">
        <div style="margin-left: 25px"><a href="<spring:url value="/forum"/>">Главная</a></div>
    </div>
    <br/>
    <c:if test="${not empty name && name ne 'anonymousUser'}">
    <div class="d-flex justify-content-between">
        <div style="margin-left: 25px">
            <a href="<spring:url value="/post?action=create&name=${name}"/>">создать пост</a>
        </div>
        <div>
            <form name="logout" action="<spring:url value="/logout"/>" method="post">
                <label>
                    <input name="out" value="выйти ${name}" type="submit"
                           style="font-size: smaller; border-color: white; background-color: dodgerblue; color: white">
                </label>
            </form>
        </div>
    </div>
    </c:if>
    <div class="container" style="width: 1000px">
        <table class="table" style="width: 1000px">
            <thead>
            <tr style="text-align: center">
                <th>тема</th>
                <th>создание</th>
                <th>автор</th>
                <th>название</th>
                <th>описание</th>
                <th>
                    <div class="fa fa-cloud"></div>
                </th>
                <th>
                    <div class="fa fa-trash"></div>
                </th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${posts}" var="post">
                <tr style="text-align: center">
                    <td style="font-size: smaller">
                        <a href="<spring:url
                        value="/post?action=${name eq 'admin'?'shows':'show'}&id=${post.id}&name=${post.author}"/>">
                                <c:out value="${post.name}"/>
                    </td>
                    <td style="font-size: smaller">
                        <c:out value="${post.created.toLocalDate()}"/>
                        <c:out value=" ${fn:split(post.created.toLocalTime(),'.')[0]}"/>
                    </td>
                    <td style="font-size: smaller">
                        <c:out value="${post.author}"/>
                    </td>
                    <td style="font-size: smaller">
                        <div style="width: 100px"><c:out value="${post.name}"/></div>
                    </td>
                    <td style="font-size: smaller">
                        <div style="width: 200px; height: 50px;">
                            <label>
                                <textarea maxlength="500"><c:out value="${post.description}"/></textarea>
                            </label>
                        </div>
                    </td>
                    <td>
                        <form action="<spring:url value="/post/update"/>" method="POST" title="обновить">
                            <input name="action" type="hidden" value="update"/>
                            <input name="idPost" type="hidden" value="${post.id}"/>
                            <input name="authorPostUpdate" type="hidden" value="${post.author}"/>
                            <input class="btn btn-info btn-sm"
                                   style="background-color:dodgerblue; border-color:white; color: white;
                                   border-radius: 0;"
                                   type="submit" value="обновить"/>
                        </form>
                    </td>
                    <td>
                        <form action="<spring:url value="/remove"/>" method="POST" title="удалить">
                            <input name="action" type="hidden" value="remove"/>
                            <input name="id" type="hidden" value="${post.id}"/>
                            <input name="authorPostUpdate" type="hidden" value="${post.author}"/>
                            <input class="btn btn-info btn-sm"
                                   style="background-color:dodgerblue; border-color:white; color: white;
                                   border-radius: 0;"
                                   type="submit" value="удалить"/>
                        </form>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</body>
</html>
