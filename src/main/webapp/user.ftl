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
    <a href="/Labr_war/chat"
       class="header__account">Чат</a>
    <a href="/Labr_war/reactions" class="header__account">Реакции</a>
</header>
<main class="user">
    <h1 class="user__title">Твой профиль</h1>
    <p class="user__text">Username - ${user.username}</p>
    <p class="user__text">Email - ${user.email}</p>
    <form action="user" class="user__form" method="post">
        <input type="submit" value="Выйти из аккаунта" class="reg__btn" id="btn">
    </form>
</main>

</body>
</html>