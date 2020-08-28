<#include "security.ftlh">
<#import "login.ftlh" as l>

<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <div class="navbar-header">
        <a class="navbar-brand" href="/">АО "ЕКА"</a>
    </div>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
            <#if user??>
                <li class="nav-item active">
                    <a class="nav-link" href="/sale">Чеки</a>
                </li>
                <li class="nav-item active">
                    <a class="nav-link" href="/bus-stop">Остановки</a>
                </li>
                <li class="nav-item active">
                    <a class="nav-link" href="/route">Маршруты</a>
                </li>
                <li class="nav-item active">
                    <a class="nav-link" href="/trip">Рейсы</a>
                </li>
                <#if isAdmin>
                    <li class="nav-item active">
                        <a class="nav-link" href="/user">Пользователи</a>
                    </li>
                    <li class="nav-item active">
                        <a class="nav-link" href="/cashier">Кассиры</a>
                    </li>
                    <li class="nav-item active">
                        <a class="nav-link" href="/ofd-config">Кассы ККМ</a>
                    </li>
                </#if>
            </#if>
        </ul>
        <ul class="nav navbar-nav navbar-right">
            <#if user??>
                <li class="nav-item active">
                    <a class="nav-link" href="/user/profile">${name}</a>
                </li>
                <li><@l.logout/></li>
            </#if>
            <#if !user??>
                <li><@l.loginBtn/></li>
            </#if>
        </ul>
    </div>
</nav>