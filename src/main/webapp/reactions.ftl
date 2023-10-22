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
    <a href="/Labr_war/registration" class="header__account">Аккаунт</a>
    <a href="/Labr_war/posts" class="header__account">Посмотреть посты</a>
    <a href="/Labr_war/chat" class="header__account">Чат</a>
</header>
<main class="reactions">
    <div class="reactions__wrapper">
        <#if auth>
            <form method="post" class="reactions__form">
                <input type="text" placeholder="Вставьте сюда ссылку" class="reactions__input" id="url"
                       name="url">
                <input type="submit" value="Отправить" class="reactions__btn" id="btn">
            </form>
        </#if>
        <div class="reactions__list">
            <#list list as item>
                <div class="reactions__item">
                    <img src="${item.reaction_url}"  class="reactions__img">
                    <div class="reactions__author">${item.username}</div>
                </div>

            </#list>
        </div>

    </div>


</main>
</body>
</html>