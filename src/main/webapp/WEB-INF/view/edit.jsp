<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
    <script crossorigin="anonymous" src="https://kit.fontawesome.com/3e4faed5c9.js"></script>
    <title>${action eq 'update' ? 'обновить' : 'создать'}</title>
</head>
<body>
<div class="container" style="width: 1000px">
    <div class="text-center"><h5>Форум ${action eq 'update' ? 'обновить ' : 'создать'} пост.
    </h5></div>
    <div class="d-flex justify-content-between"
         style="border-bottom-color: cadetblue; margin-bottom: 5px; height: 35px;">
        <div><a href="<spring:url value="/forum"/>">Главная </a></div>
        <div>
            <div style="display: inline-block; margin-right: 25px">кабинет:
                <a href="<spring:url value="/cabinet/${name eq 'admin'? 'admin' : 'user'}?name=${name}"/>">
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
    <hr/>
    <div class="row">
        <div class="container" style="width: 1000px">
            <div class="float-left" style="width: 250px;">
                <form name="${action eq 'create'? 'create': 'update'}"
                      id="${action eq 'create'? 'create': 'update'}"
                      action="<spring:url value="/${action eq 'create'? 'create': 'update'}"/>" method='POST'>
                    <input type="hidden" name="authorPost" value="${post.author}">
                    <input type="hidden" name="names" value="${name}">
                    <c:if test="${action eq 'update'}">
                        <input type="hidden" name="id" value="${post.id}">
                    </c:if>
                    <div class="form-group">
                        <label class="sr-only" for="date">date</label>
                        <input style="width: 250px" id="date" type="datetime-local" name="date"
                               value="${action eq 'update' ? post.created : ''}">
                    </div>
                    <div class="form-group">
                        <label class="sr-only" for="name">Name</label>
                        <input style="width: 250px" id="name" type="text" name="name" required maxlength="20"
                               value="${action eq 'update' ? post.name : ''}">
                    </div>
                    <div class="form-group">
                        <label class="sr-only" for="description">Desc</label>
                        <textarea style="width: 250px; height: 100px" id="description" required maxlength="2000"
                                  name="description">${action eq 'update' ? post.description : ''}</textarea>
                    </div>
                    <div class="form-group">
                        <input class="btn"
                               style="background-color:cadetblue; color:white; font-weight: bold; width: 250px"
                               type="submit" form="${action eq  'create' ? 'create' : 'update'}"
                               value="Сохранить"/>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
</body>
</html>
