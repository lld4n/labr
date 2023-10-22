<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: lldan
  Date: 21.10.2023
  Time: 23:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link href="index.css" rel="stylesheet">
    <title>Лабр</title>
</head>
<body>
<header class="header">
    <a href="/Labr_war" class="header__account">Лабр</a>
    <c:if test="${auth}">
        <a href="/Labr_war/addpost" class="header__account">Добавить пост</a>
    </c:if>
    <a href="/Labr_war/chat" class="header__account">Чат</a>
    <a href="/Labr_war/reactions" class="header__account">Реакции</a>
</header>
<div class="posts">
    <c:forEach items="${list}" var="post">
        <div class="posts__item">
            <div class="posts__title">${post.title}</div>
            <div class="posts__date">${post.created}</div>
            <form method="post">
                <input type="text" value="${post.post_id}" name="post" class="posts__cheat">
                <input type="submit" value="Посмотреть" class="posts__btn">
            </form>
        </div>

    </c:forEach></div>
</body>
</html>
