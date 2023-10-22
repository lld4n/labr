<!doctype html>
<html lang="en">
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
    <a href="/Labr_war/posts" class="header__account">Посмотреть посты</a>
    <a href="/Labr_war/chat" class="header__account">Чат</a>
</header>
<main class="post">
    <div class="post__author">${user.username}</div>
    <div class="post__title">${post.title}</div>
    <div class="post__text">${post.post_content}</div>
    <div class="post__date">${post.created}</div>
    <#if auth>
        <form class="post__form" method="post">
            <label class="post__label">
                <input type="text" placeholder="Напишите комментарий" class="post__input" id="comment" name="comment">
                <div class="post__len" id="len">50</div>
            </label>
            <input type="submit" value="Отправить" class="post__btn" id="btn">
        </form>
    </#if>


    <div class="post__comments">
        <#list comments as item>
            <div class="post__comment">${item.comment_text}</div>
        </#list>
    </div>


</main>
<script src="post.js"></script>
</body>
</html>