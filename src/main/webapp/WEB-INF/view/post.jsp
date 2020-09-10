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
    <script src="<spring:url value="js/message.js"/>"></script>
    <script charset="UTF-8" src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"
            integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous">
    </script>
    <title>пост</title>
</head>
<body>
<div class="container" style="width: 1000px">
    <div class="text-center"><h5>Форум пост</h5></div>
    <c:if test="${not empty name && name ne 'anonymousUser'}">
        <div class="d-flex justify-content-between">
            <div><a href="<spring:url value="/forum"/>">Главная </a></div>
            <div>
                <div style="display: inline-block; margin-right: 25px">кабинет:
                    <a href="<spring:url value="/cabinet/${name eq 'admin'? 'admin': 'user'}?name=${name}"/>">
                        <b>${name}</b></a>
                </div>
                <div style="display: inline-block">
                    <form name="logout" action="<spring:url value="/logout?name=${name}"/>" method="post">
                        <label>
                            <input name="out" value="выйти ${name}" type="submit"
                                   style="font-size: smaller; border-color: white; background-color: dodgerblue; color: white">
                        </label>
                    </form>
                </div>
            </div>
        </div>
    </c:if> <c:if test="${empty name || name eq 'anonymousUser'}">
    <div class="d-flex justify-content-start">
        <div><a href="<spring:url value="/forum"/>">Главная </a></div>
    </div>
</c:if>
    <div class="row">
        <table class="table" style="width: 1000px">
            <thead style="border-top-color: cadetblue;">
            <tr style="font-size: smaller">
                <th style="text-align: center;">тема: ${post.name}</th>
                <th style="text-align: center;">создан:
                    <c:out value="${post.created.toLocalDate()}"/>
                    <c:out value=" ${fn:split(post.created.toLocalTime(),'.')[0]}"/>
                </th>
                <th style="text-align: center;">автор: ${post.author}</th>
                <th><label>
                    <textarea disabled style="width: 300px; height: 100px; font-size: smaller"
                              maxlength="300">${post.description}
                    </textarea>
                </label>
                </th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${msg}" var="message">
                <tr>
                    <td colspan="3" style="font-size: smaller; border-color: dodgerblue;">
                        <div style="display: inline-block"><b>${message.author}</b> |
                            <c:out value="${message.created.toLocalDate()}"/>
                            <c:out value=" ${fn:split(message.created.toLocalTime(),'.')[0]}"/>
                        </div>
                        <c:if test="${message.author eq name || name eq 'admin'}">
                            <div style="display: inline-block; margin-left: 3px;">
                                <form name="delete" action="<spring:url value="/post/message/delete"/>"
                                      method="post">
                                    <input type="hidden" name="idPostD" value="${post.id}">
                                    <input type="hidden" name="authorPostD" value="${post.author}">
                                    <input type="hidden" name="idMsgD" value="${message.id}">
                                    <input type="submit" value="удалить"
                                           style="font-size: smaller; border-color: white; background-color: cadetblue;
                                       color: black">
                                </form>
                            </div>
                            <div style="display: inline-block; margin-left: 3px;">
                                <form name="update"
                                      action="<spring:url value="/post/message/update"/>"
                                      method="post">
                                    <input type="hidden" name="DescU"
                                           id="DescU_${post.id}_${message.id}_${name}_${post.author}"
                                           value="${message.description}">
                                    <input type="button" id="${post.id}_${message.id}_${name}_${post.author}"
                                           onclick="prepareUpdate(this)"
                                           value="обновить"
                                           style="font-size: smaller; border-color: white; background-color: cadetblue;
                                       color: black">
                                </form>
                            </div>
                        </c:if>
                    </td>
                </tr>
                <tr style="font-size: smaller; column-span: all">
                    <td colspan="3" style="font-size: smaller; border-bottom-color: dodgerblue;">
                        <div style="width: 300px"></div>
                        <c:out value="${message.description}"/></td>
                </tr>
            </c:forEach>
            <tr>
                <td colspan="4">
                    <div id="comment">
                        <form name='message' id="msg" action="<spring:url  value="/post/message/create"/>"
                              method='POST'>
                            <input type="hidden" name="id" value="${post.id}">
                            <input type="hidden" name="authorPost" value="${post.author}">
                            <c:if test="${not empty name && name ne 'anonymousUser'}">
                                <div class="form-group">
                                    <label class="sr-only" for="message">message</label>
                                    <textarea id="message" name="message"
                                              style="width: 300px; height: 200px" maxlength="300"></textarea>
                                </div>
                                <div class="form-group">
                                    <input class="btn" id="addMsg"
                                           style="background-color:cadetblue; border-radius: 0; font-weight: bold; width: 300px;"
                                           type="submit" form="msg" onclick="preventDoubleMsg(this)"
                                           value="добавить коммент"/>
                                </div>
                            </c:if>
                        </form>
                    </div>
                </td>
            </tr>
            </tbody>
        </table>
    </div>
</div>
</body>
</html>
