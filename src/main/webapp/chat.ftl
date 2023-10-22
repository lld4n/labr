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
</header>
<main class="chat">
    <div class="chat__wrapper">
        <div class="chat__list">
            <#list list as item>

                <div class="chat__author">${item.user.username}</div>

                <div class="chat__item">${item.message_text}</div>
            </#list>
        </div>

        <form method="post" class="chat__form">
            <input type="text" placeholder="Напишите что-нибудь в чат" class="chat__input" id="message" name="message">
            <input type="submit" value="Отправить" class="chat__btn" id="btn">
        </form>
    </div>


</main>
</body>
</html>